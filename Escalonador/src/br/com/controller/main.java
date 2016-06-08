/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Experimento;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class main {
    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        String in = arq.lerArquivo("src/br/com/files/sjf.exp");
        String split[]=in.split("\n");
        String arquivoProcessos=arq.lerArquivo("src/br/com/files/"+split[1]);        
        ProcessController pl = new ProcessController(arquivoProcessos.trim());
        
        Experimento exp = new Experimento(in,pl);
        String out = exp.getEscalonador().run();
        System.out.println(out);
        arq.escreverArquivo(exp.getCaminhoArqSaida(), out);        
    }
}
