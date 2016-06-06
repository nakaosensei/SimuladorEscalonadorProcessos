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
public class Fcfs extends Politica{

    public Fcfs(String nome,int quantum) {
        super(nome,quantum);
    }

    @Override
    public Processo doSelectionPolitic(List<Processo> prontos) {
        if(prontos.isEmpty()){
            return null;
        }
        Processo executando;
        executando=prontos.get(0);
        prontos.remove(0);
        return executando;
    }
    
}
