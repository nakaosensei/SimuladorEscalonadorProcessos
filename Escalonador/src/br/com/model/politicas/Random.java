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
public class Random extends Politica{
    public Random(ProcessController pc){
        super(pc);
        this.nome="RANDOM";
    }

    @Override
    public String run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
