/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politicas;

import br.com.controller.ProcessController;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class RoundRobin extends Politica {
    private int quantum;
    
    public RoundRobin(ProcessController pc,int quantum){
        super(pc);
        this.nome="RR";
        this.quantum=quantum;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    public void print(){
        super.print();
        System.out.println("\nQuantum:"+this.quantum);
    }

    @Override
    public String run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
