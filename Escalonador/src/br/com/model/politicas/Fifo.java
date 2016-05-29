/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politicas;

import br.com.controller.ProcessController;
import br.com.model.Processo;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Fifo extends Politica {
    
    
    public Fifo(ProcessController pc){
        super(pc);
        this.nome="FIFO";
        
    }

    @Override
    public String run() {
        String out="";
        for(Processo p:this.prontos){
            
        }
        return out;
    }
    
    private void execute(Processo p){
        if(executando.isEmpty()){
            executando.add(p);
            
        }else{            
            prontos.add(executando.get(0));
            executando.clear();
        }
        
        
    }
}



