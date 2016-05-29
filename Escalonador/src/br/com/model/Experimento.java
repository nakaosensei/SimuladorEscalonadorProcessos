package br.com.model;

import br.com.controller.ProcessController;
import br.com.model.politicas.Fifo;
import br.com.model.politicas.FilaPrioridades;
import br.com.model.politicas.Sjf;
import br.com.model.politicas.Politica;
import br.com.model.politicas.Random;
import br.com.model.politicas.RoundRobin;
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
    protected Politica politica;
    protected String[] param;
    
            
    public Experimento(String file,ProcessController pc){
        carregar(file,pc);
    }
    
    public void carregar(String experimentFile,ProcessController pc){
        param=experimentFile.split("\n");
        this.nomeExp=param[0];
        this.caminhoArqProcessos=param[1];
        this.caminhoArqSaida=param[2];
        this.politica=generatePolitic(param[3],pc);        
    }
    
    public void print(){
        System.out.print("Nome Experimento: "+this.nomeExp+"\n"+"Arquivo Processos: "+this.caminhoArqProcessos+"\n"+"Arquivo saida: "+this.caminhoArqSaida+"\n");
        this.politica.print();
    }   
    private Politica generatePoliticRoundRobin(int quantum,ProcessController pc){
        return new RoundRobin(pc,quantum);
    }
    
    public Politica generatePolitic(String algoritmo,ProcessController pc){
        algoritmo = algoritmo.trim();
        if(algoritmo.equals("fcfs")){
            return new Fifo(pc);
        }else if(algoritmo.equals("rr")){
            return new RoundRobin(pc,Integer.parseInt(param[4]));
        }else if(algoritmo.equals("sjf")){
            return new Sjf(pc);
        }else if(algoritmo.equals("rand")){
            return new Random(pc);
        }else if(algoritmo.equals("fp")){
            List<Politica> politicas = new ArrayList<>();
            for(int i = 5;i<=param.length-1;i++){
                if(param[i].contains("rr")){
                    String quantum="";
                    for(int j = 3;param[i].charAt(j)!=')';j++){
                        quantum+=param[i].charAt(j);
                    }
                    politicas.add(generatePoliticRoundRobin(Integer.parseInt(quantum),pc));
                }else{
                    politicas.add(generatePolitic(param[i],pc));
                }                
            }
            return new FilaPrioridades(pc,Integer.parseInt(param[4]),politicas);
        }
        return null;
    }
    
    
}
