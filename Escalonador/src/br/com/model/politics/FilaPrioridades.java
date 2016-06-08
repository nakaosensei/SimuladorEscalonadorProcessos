/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politics;

import br.com.model.Nivel;
import br.com.model.Processo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class FilaPrioridades extends Politica {
    List<Nivel> niveis;
    
    
    public FilaPrioridades(String nome,int quantum,List<Nivel> niveis) {
        super(nome,quantum);
        this.niveis=niveis;        
    }

    @Override
    public Processo doSelectionPolitic(List<Processo> prontos) {
        if(prontos.isEmpty()){
            return null;
        }
        List<Processo> newProntos = getProcessesWithHigherPriority(prontos);
        for(Nivel n:niveis){
            if(n.getPrioridade()==newProntos.get(0).getPrioridade()){
                this.quantum=n.getPolitica().getQuantum();
                Processo exec= n.getPolitica().doSelectionPolitic(newProntos);
                prontos.remove(exec);
                return exec;
            }            
        }
        return null;
    }
    
    public List<Processo> getProcessesWithHigherPriority(List<Processo> prontos){
        List<Processo> highers= new ArrayList<>();
        int maior=prontos.get(0).getPrioridade();
        for(Processo p:prontos){
            if(p.getPrioridade()<maior){//Neste contexto, a menor prioridade é a maior!!!1 é o maximo e 40 o minimo
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
