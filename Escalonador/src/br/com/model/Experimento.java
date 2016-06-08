package br.com.model;
import br.com.controller.Escalonador;
import br.com.controller.ProcessController;

import br.com.model.politics.FilaPrioridades;
import br.com.model.politics.Sjf;

import br.com.model.politics.Random;
import br.com.model.politics.RoundRobin;
import br.com.model.politics.Fcfs;
import br.com.model.politics.Politica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Experimento {
    protected String nomeExp;
    protected String caminhoArqProcessos;//Ja inclui o nome do arquivo
    protected String caminhoArqSaida;
    protected Escalonador escalonador;
    protected String[] param;
                    
    public Experimento(String file,ProcessController pc){
        carregar(file,pc);        
    }        
    public void carregar(String experimentFile,ProcessController pc){
        param=experimentFile.split("\n");
        this.nomeExp=param[0];
        this.caminhoArqProcessos=param[1];
        this.caminhoArqSaida=param[2];
        Politica p = generatePolitic(param[3]);
        this.escalonador=new Escalonador(pc, p);        
    }  
    
    
    public void print(){
        System.out.print("Nome Experimento: "+this.nomeExp+"\n"+"Arquivo Processos: "+this.caminhoArqProcessos+"\n"+"Arquivo saida: "+this.caminhoArqSaida+"\n");
        
    }   
    
    private Politica generatePoliticRoundRobin(int quantum){
        return new RoundRobin("rr",quantum);
    }
    
    public Politica generatePolitic(String algoritmo){
        algoritmo = algoritmo.trim();
        if(algoritmo.equals("fcfs")){
            return new Fcfs("fcfs", 0);
        }else if(algoritmo.equals("rr")){
            return new RoundRobin("rr",Integer.parseInt(param[4]));
        }else if(algoritmo.equals("sjf")){
            return new Sjf("sjf",0);
        }else if(algoritmo.equals("random")){
            return new Random("random",0);
        }else if(algoritmo.equals("fp")){
            List<Nivel> niveis = new ArrayList<>();
            for(int i = 5;i<=param.length-1;i++){
                if(param[i].contains("rr")){
                    String quantum="";
                    for(int j = 3;param[i].charAt(j)!=')';j++){
                        quantum+=param[i].charAt(j);
                    }
                    niveis.add(new Nivel(i-4,generatePoliticRoundRobin(Integer.parseInt(quantum))));
                }else{
                    niveis.add(new Nivel(i-4,generatePolitic(param[i])));
                }                
            }
            return new FilaPrioridades("fp",0,niveis);
        }
        return null;
    }

    public String getNomeExp() {
        return nomeExp;
    }

    public void setNomeExp(String nomeExp) {
        this.nomeExp = nomeExp;
    }

    public String getCaminhoArqProcessos() {
        return caminhoArqProcessos;
    }

    public void setCaminhoArqProcessos(String caminhoArqProcessos) {
        this.caminhoArqProcessos = caminhoArqProcessos;
    }

    public String getCaminhoArqSaida() {
        return caminhoArqSaida;
    }

    public void setCaminhoArqSaida(String caminhoArqSaida) {
        this.caminhoArqSaida = caminhoArqSaida;
    }

    public Escalonador getEscalonador() {
        return escalonador;
    }

    public void setEscalonador(Escalonador escalonador) {
        this.escalonador = escalonador;
    }

    public String[] getParam() {
        return param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }
    
    
}
