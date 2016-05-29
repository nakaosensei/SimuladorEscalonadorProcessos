/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politicas;

import br.com.controller.ProcessController;
import br.com.model.Processo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public abstract class Politica {
    protected String nome;
    protected ProcessController pc;
    protected List<Processo> prontos;
    protected List<Processo> executando;
    protected List<Processo> bloqueados;
    
    public abstract String run();

    public Politica(ProcessController processos){
        pc=processos;
        prontos=pc.getProcessos();
        executando=new ArrayList<>();
        bloqueados=new ArrayList<>();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void print(){
        System.out.print("Politica:"+nome);
    }
    
    public void printProcesseces(){
        this.pc.print();
    }

    public ProcessController getPc() {
        return pc;
    }

    public void setPc(ProcessController pc) {
        this.pc = pc;
    }
                            
}
