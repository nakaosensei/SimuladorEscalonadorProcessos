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
        ProcessController pl = new ProcessController(arq.lerArquivo("src/br/com/files/processos_1.proc"));
        //pl.print();
        Experimento exp = new Experimento(arq.lerArquivo("src/br/com/files/rr.exp"),pl);
        exp.print();
    }
}
