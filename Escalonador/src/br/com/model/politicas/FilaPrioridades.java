/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.politicas;

import br.com.controller.ProcessController;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class FilaPrioridades extends Politica{
    private int numFilas;
    private List<Politica> politicas;
    
    
    public FilaPrioridades(ProcessController pc,int numFilas,List<Politica> politicas){
        super(pc);
        this.nome="FP";
        this.numFilas=numFilas;
        this.politicas=politicas;
        
    }
    public void print(){
        super.print();
        System.out.println("\nNum filas:"+this.numFilas);
        System.out.println("Politicas de cada fila");
        for(Politica p:politicas){
            p.print();
        }
    }

    @Override
    public String run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
