package br.com.model;

import br.com.controller.ProcessController;
import br.com.model.politics.Politica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Escalonador {
    private Politica politica;
    private ProcessController pc;
    private List<Processo> prontos;
    private Processo executando;
    private List<Processo> bloqueados;
    private int tempo;
    private int qtChaveamentos;
    private int tme;
    private int tmr;
    private int vazao;
    private List<Processo> sequenciaTermino;
    private List<Evento> diagramaEventos;
    private int quantum;
    
    public Escalonador(ProcessController processos,Politica politica){
        pc=processos;
        prontos=new ArrayList<>();
        bloqueados=new ArrayList<>();
        sequenciaTermino = new ArrayList<>();
        diagramaEventos=new ArrayList<>();
        tempo=0;        
        this.politica=politica;
        this.quantum=this.politica.getQuantum();
    }    

    public String run(){
        while(pc.getProcessos().size()>0||prontos.size()>0||bloqueados.size()>0||executando!=null){
            List<Processo> plusToProntos=pc.getWhoArrivedAtThatTime(this.tempo);
            for(Processo p:plusToProntos){
                pc.remove(p);
                diagramaEventos.add(new Evento("CRIACAO", tempo, p.getPid()));
                p.moveToNextEvent();
                prontos.add(p);                
            }            
            if(!bloqueados.isEmpty()){
                List<Processo> toRm = new ArrayList<>();
                for(Processo p:this.bloqueados){
                    Evento e = p.getSelectedEvent();
                    while(!e.getNome().equals("DESBLOQUEIO")){
                        p.moveToNextEvent();
                        e = p.getSelectedEvent();
                    }                    
                    int lockTime=this.getLockTime(p);
                    if(tempo==lockTime+e.getTempoOcorrencia()){
                        this.diagramaEventos.add(new Evento("DESBLOQUEIO",tempo,p.getPid()));
                        toRm.add(p);
                    }
                }
                for(Processo r:toRm){                        
                    bloqueados.remove(r);
                    prontos.add(r);
                }
            }   
            if(executando==null){//Se não tem ninguem executando
                executando = politica.doSelectionPolitic(prontos);                    
                if(executando!=null){
                    this.qtChaveamentos++;//Sempre que um processo sai da lista de prontos e vai para lista de execução, ocorre uma troca de contexto
                    int tempoDaUltimaOp=this.getLockOrCreationTime();                
                    if(tempo==tempoDaUltimaOp+1){//Se esta execucao ocorre exatamente depois de um bloqueio ou criacao
                        tempo--;                        
                    }
                    diagramaEventos.add(new Evento("EXEC",tempo,executando.getPid()));
                    //executando.tempoOcupouCpu+=1;
                }
            }else{
                //Se alguem esta executando
                int tempoExecucao=getExecutionTime(executando);
                executando.tempoOcupouCpu+=1;
                if(tempo==tempoExecucao+quantum&&quantum!=0){                    
                    if(executando.tempoOcupouCpu<executando.getFim().getTempoOcorrencia()){
                        prontos.add(executando);
                        this.diagramaEventos.add(new Evento("QUANTUM_EX", tempo, executando.getPid()));
                        executando=null;                    
                    }                  
                }else{                 
                    Evento e = executando.getSelectedEvent();                    
                    //Se eu nao estiver sobre um bloqueio, encontre o proximo bloqueio
                    while(!e.getNome().equals("BLOQUEIO")&&!e.getNome().equals("TERMINO")){
                        if(!e.getNome().equals("TERMINO")){
                            executando.moveToNextEvent();
                            e = executando.getSelectedEvent();
                        }   
                    }
                    //Agora, com certeza estou sobre um bloqueio ou termino
                    int remainingTime=executando.getFim().getTempoOcorrencia()-executando.tempoOcupouCpu;
                    if(e.getNome().equals("BLOQUEIO")){
                        if(tempo==tempoExecucao+e.getTempoOcorrencia()&&remainingTime>=e.getTempoOcorrencia()){
                            diagramaEventos.add(new Evento(e.getNome(),tempo, executando.getPid()));
                            bloqueados.add(executando);
                            executando=null;
                        }
                    }
                    if(remainingTime==0){
                        System.out.println(executando.tempoOcupouCpu);
                        diagramaEventos.add(new Evento("TERMINO",tempo, executando.getPid()));
                        executando=null;
                        tempo--;
                    }                  
                }                        
            }              
            tempo++;          
        }
        return getDiagramaEventosString();
    }

    public String getDiagramaEventosString(){
        String out="";
        Collections.sort (this.diagramaEventos, new Comparator() {
            public int compare(Object o1, Object o2) {
                Evento p1 = (Evento) o1;
                Evento p2 = (Evento) o2;
                return p1.getTempoOcorrencia() < p2.getTempoOcorrencia() ? -1 : (p1.getTempoOcorrencia() > p2.getTempoOcorrencia() ? +1 : 0);
            }
        });
        for(Evento e:this.diagramaEventos){
            out+=e.getStringEvt()+"\n";
        }
        return out;
    }
    
    private int getExecutionTime(Processo p){
        Evento tmp;
        for(int i = diagramaEventos.size()-1;i>=0;i--){
            tmp=diagramaEventos.get(i);
            if(tmp.getOwnerId()==p.getPid()&tmp.getNome().equals("EXEC")){
                return tmp.getTempoOcorrencia();
            }
        }
        return -1;
    }
    
    private int getLockTime(Processo p){
        Evento tmp;
        for(int i = diagramaEventos.size()-1;i>=0;i--){
            tmp=diagramaEventos.get(i);
            if(tmp.getOwnerId()==p.getPid()&tmp.getNome().equals("BLOQUEIO")){
                return tmp.getTempoOcorrencia();
            }
        }
        return -1;
    }

    private int getLockOrCreationTime(){
        Evento tmp;
        for(int i = diagramaEventos.size()-1;i>=0;i--){
            tmp=diagramaEventos.get(i);
            if(tmp.getNome().equals("BLOQUEIO")||tmp.getNome().equals("CRIACAO")||tmp.getNome().equals("QUANTUM_EX")){
                return tmp.getTempoOcorrencia();
            }
        }
        return -1;
    }
}



