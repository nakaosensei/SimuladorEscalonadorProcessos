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
public class Sjf extends Politica{

    public Sjf(String nome,int quantum) {
        super(nome,quantum);
    }

    @Override
    public Processo doSelectionPolitic(List<Processo> prontos) {
        if(prontos.isEmpty()){
            return null;
        }
        Processo menor=prontos.get(0);
        Processo executando;
        for(Processo p:prontos){
            if(!menor.isShortest(p)){
                menor = p;
            }
        }
        executando=menor;
        prontos.remove(menor);
        return executando;
    }
    
}
