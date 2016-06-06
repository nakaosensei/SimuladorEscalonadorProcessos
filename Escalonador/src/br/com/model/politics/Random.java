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
public class Random extends Politica{

    public Random(String nome,int quantum) {
        super(nome,quantum);
    }

    @Override
    public Processo doSelectionPolitic(List<Processo> prontos) {
        if(prontos.isEmpty()){
            return null;
        }
        Processo executando;
        java.util.Random r = new java.util.Random();        
        int next=r.nextInt(prontos.size());
        executando=prontos.remove(next);
        return executando;
    }
    
    
}
