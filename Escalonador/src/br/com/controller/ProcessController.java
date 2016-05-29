/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Processo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class ProcessController {
    private int numProcessos;
    private int prioridadeMinima;
    private int prioridadeMaxima;
    private List<Processo> processos;

    public ProcessController(String arq){
        carregarEstruturas(arq);
        
    }

    private void carregarEstruturas(String arq){
        String v[]=arq.split("\n");
        processos=new ArrayList<>();
        numProcessos=Integer.parseInt(v[0]);
        String prioridadeSplit[]=v[1].split("-");
        prioridadeMinima=Integer.parseInt(prioridadeSplit[0]);
        prioridadeMaxima=Integer.parseInt(prioridadeSplit[1]);
        String s="";
        for(int i = 2;i<=v.length-1;i++){
            s+=v[i]+"\n";
            if(v[i].contains("TERMINO")){
                Processo p = new Processo(s);
                s="";
                this.processos.add(p);
            }
        }
    }
    
    public void print(){
        System.out.println("Qtde Processos: "+this.numProcessos+" Prioridade Minima: "+prioridadeMinima +" Prioridade Maxima: "+this.prioridadeMaxima);
        System.out.println("Processos:");
        for(Processo p:this.processos){
            p.println();
        }
    }
    
    public int getNumProcessos() {
        return numProcessos;
    }

    public void setNumProcessos(int numProcessos) {
        this.numProcessos = numProcessos;
    }

    public int getPrioridadeMinima() {
        return prioridadeMinima;
    }

    public void setPrioridadeMinima(int prioridadeMinima) {
        this.prioridadeMinima = prioridadeMinima;
    }

    public int getPrioridadeMaxima() {
        return prioridadeMaxima;
    }

    public void setPrioridadeMaxima(int prioridadeMaxima) {
        this.prioridadeMaxima = prioridadeMaxima;
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }
    
}
