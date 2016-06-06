/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politics;

import br.com.model.Processo;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public abstract class Politica {
    protected String nome;
    protected int quantum;
    
    public Politica(String nome,int quantum){
        this.nome=nome;
        this.quantum=quantum;
    }
    
    public abstract Processo doSelectionPolitic(List<Processo> prontos);
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    
    
}
