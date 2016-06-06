/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Arquivo;
import br.com.model.Experimento;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class main {
    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        String in = arq.lerArquivo("src/br/com/files/fifo.exp");
        String split[]=in.split("\n");
        String arquivoArchs=arq.lerArquivo("src/br/com/files/"+split[1]);        
        ProcessController pl = new ProcessController(arquivoArchs.trim());        
        Experimento exp = new Experimento(in,pl);
        String out = exp.getEscalonador().run();
        arq.escreverArquivo(exp.getCaminhoArqSaida(), out);
        //exp.print();
    }
}
