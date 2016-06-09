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
        try {
            if(args[0].trim().equals("")){
                System.out.println("Digitou errado o comando!\ndigite o comando assim no terminal: sudo java -jar Escalonador.jar fifo.exp\nPS:Execute o jar dentro do diretorio src/br/com/files/Escalonador.jar, se quiser executar diretamente \nno projeto do NetBeans, Basta trocar o args[0] por src/br/com/files/fifo.exp por exemplo");
            }else{
                String in = arq.lerArquivo(args[0]);
                String split[]=in.split("\n");
                String arquivoProcessos=arq.lerArquivo(split[1]);        
                ProcessController pl = new ProcessController(arquivoProcessos.trim());

                Experimento exp = new Experimento(in,pl);
                String out = exp.getEscalonador().run();
                arq.escreverArquivo(exp.getCaminhoArqSaida(), out);
                System.out.println("Arquivo gerado neste diretorio, seu nome e "+exp.getCaminhoArqSaida());
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao abrir arquivo,  digite o comando assim no terminal: sudo java -jar Escalonador.jar fifo.exp\nPS:Execute o jar dentro do diretorio src/br/com/files/Escalonador.jar, se quiser executar diretamente no projeto do NetBeans, Basta trocar o args[0] por src/br/com/files/fifo.exp por exemplo");
        }
        
        
                
    }
}
