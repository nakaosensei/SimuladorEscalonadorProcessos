/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import br.com.model.politics.Politica;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Nivel {
    private int prioridade;
    private Politica politica;
    
    
    public Nivel(int prioridade,Politica politica){
        this.politica=politica;
        this.prioridade=prioridade;
    }
    
    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Politica getPolitica() {
        return politica;
    }

    public void setPolitica(Politica politica) {
        this.politica = politica;
    }
    
    
}
