package br.com.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Processo {
    private int pid;
    private int prioridade;
    private int qtEventos;//indica quantos eventos este processo tera.  Este numero inclui a quantidade de linhas inclusive e entre T ENTRADA e TN TERMINO
    private Evento inicio;
    private Evento fim;
    private List<Evento> eventos;
    private int eventoSelecionado;
    public int tempoOcupouCpu;
    
    public Processo(String pString){
        carregarComTempoRelativoAoRelogio(pString);
    }
    
    void carregarComTempoRelativoAoRelogio(String pString){
        eventos = new ArrayList<>();
        String vp[]=pString.split("\n");
        pid=Integer.parseInt(vp[0]);
        prioridade=Integer.parseInt(vp[1].trim());
        qtEventos=Integer.parseInt(vp[2].trim());        
        inicio = new Evento("CRIACAO",Integer.parseInt(vp[3].trim()),pid);
        eventos.add(inicio);
        String split0[]=vp[vp.length-1].split(" ");        
        for(int i = 4;i<=vp.length-2;i+=2){
            String split[]=vp[i].split(" ");
            String split2[]=vp[i+1].split(" ");            
            Evento lock = new Evento("BLOQUEIO",Integer.parseInt(split[0]),pid);            
            Evento unlock = new Evento("DESBLOQUEIO",Integer.parseInt(split2[0]),pid);
            this.eventos.add(lock);
            this.eventos.add(unlock);            
        }        
        fim=new Evento("TERMINO",Integer.parseInt(split0[0]),pid);
        eventos.add(fim);
        eventoSelecionado=0;
        this.tempoOcupouCpu=0;
    }
    
    void carregarComTempoRelativoAoProcesso(String pString){
        eventos = new ArrayList<>();
        String vp[]=pString.split("\n");
        pid=Integer.parseInt(vp[0]);
        prioridade=Integer.parseInt(vp[1]);
        qtEventos=Integer.parseInt(vp[2]);
        inicio = new Evento("CRIACAO",Integer.parseInt(vp[3]),pid);
        eventos.add(inicio);
        eventos.add(new Evento("EXEC",inicio.getTempoOcorrencia(),pid));
        String split0[]=vp[vp.length-1].split(" ");
        int sumTime = Integer.parseInt(vp[3])+inicio.getTempoOcorrencia();
        for(int i = 4;i<=vp.length-2;i+=2){
            String split[]=vp[i].split(" ");
            String split2[]=vp[i+1].split(" ");
            sumTime+=Integer.parseInt(split[0]);
            Evento lock = new Evento("BLOQUEIO",sumTime,pid);
            sumTime+=Integer.parseInt(split2[0]);
            Evento unlock = new Evento("DESBLOQUEIO",sumTime,pid);
            this.eventos.add(lock);
            this.eventos.add(unlock);
            eventos.add(new Evento("EXEC",unlock.getTempoOcorrencia(),pid));
        }   
        sumTime+=Integer.parseInt(split0[0]);
        fim=new Evento("TERMINO",sumTime,pid);
        eventos.add(fim);
        eventoSelecionado=0;
    }
    
    public Evento getSelectedEvent(){
        if(eventoSelecionado>this.eventos.size()){
            return null;
        }
        return this.eventos.get(eventoSelecionado);
    }
    
    public void moveToNextEvent(){
        this.eventoSelecionado++;
    }

    public int getRemainingExecutionTime(){
        int blockTime=0;
        int totalBurstTime;
        for(int i = eventoSelecionado;i<=this.eventos.size()-1;i++){
            if(eventos.get(i).getNome().equals("DESBLOQUEIO")){
                blockTime+=eventos.get(i).getTempoOcorrencia();
            }            
        }        
        return this.fim.getTempoOcorrencia()-blockTime;
    }
    
    public boolean isShortest(Processo p){
        if(this.getRemainingExecutionTime()<p.getRemainingExecutionTime()){
            return true;
        }
        return false;
    }
    
    public void println(){
        System.out.print("PID:"+this.pid+" Prioridade:"+this.prioridade+" QtEventos:"+this.qtEventos+"\n");
        System.out.println("Eventos");
        for(Evento op:this.eventos){
            op.println();
        }        
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getQtEventos() {
        return qtEventos;
    }

    public void setQtEventos(int qtEventos) {
        this.qtEventos = qtEventos;
    }

    public Evento getInicio() {
        return inicio;
    }

    public void setInicio(Evento inicio) {
        this.inicio = inicio;
    }

    public Evento getFim() {
        return fim;
    }

    public void setFim(Evento fim) {
        this.fim = fim;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }


    public Evento getNextSelectedEvent(){
        return this.getEventos().get(eventoSelecionado+1);
    }

    public Evento getCorrectPreviousEvent(){
        if(this.getEventos().get(eventoSelecionado-1).getNome().equals("CRIACAO")){
            return this.getEventos().get(eventoSelecionado-1);
        }else{
            return this.getEventos().get(eventoSelecionado-2);
        }
    }
    public void setPosEventoSelecionado(int eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

    public int getPosEventoSelecionado() {
        return eventoSelecionado;
    }

    
    
}
