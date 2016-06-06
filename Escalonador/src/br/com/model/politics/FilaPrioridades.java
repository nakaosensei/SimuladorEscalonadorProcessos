/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politics;

import br.com.model.NewNivel;
import br.com.model.Processo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class FilaPrioridades extends Politica {
    List<NewNivel> niveis;
    
    
    public FilaPrioridades(String nome,int quantum,List<NewNivel> niveis) {
        super(nome,quantum);
        this.niveis=niveis;        
    }

    @Override
    public Processo doSelectionPolitic(List<Processo> prontos) {
        List<Processo> newProntos = getProcessesWithHigherPriority(prontos);
        for(NewNivel n:niveis){
            if(n.getPrioridade()==newProntos.get(0).getPrioridade()){
                this.quantum=n.getPolitica().getQuantum();
                return n.getPolitica().doSelectionPolitic(newProntos);                
            }            
        }
        return null;
    }
    
    public List<Processo> getProcessesWithHigherPriority(List<Processo> prontos){
        List<Processo> highers= new ArrayList<>();
        int maior=prontos.get(0).getPrioridade();
        for(Processo p:prontos){
            if(p.getPrioridade()>maior){
                maior=p.getPrioridade();
            }
        }
        for(Processo p:prontos){
            if(p.getPrioridade()==maior){
                highers.add(p);
            }
        }
        return highers;
    }    
    
}
