/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politics;

import br.com.model.NewNivel;
import br.com.model.Processo;
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
        return null;
    }
    
}
