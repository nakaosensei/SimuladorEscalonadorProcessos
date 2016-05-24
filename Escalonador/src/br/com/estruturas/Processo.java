/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estruturas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Processo {
    private int pid;
    private int prioridade;
    private int qtEventos;//indica quantos eventos este processo tera.  Este numero inclui a quantidade de linhas inclusive e entre T ENTRADA e TN TERMINO
    private Operacao entrada;
    private List<Operacao> operacoes;
    
    public Processo(String pString){
        operacoes = new ArrayList<>();
        String vp[]=pString.split("\n");
        pid=Integer.parseInt(vp[0]);
        prioridade=Integer.parseInt(vp[1]);
        qtEventos=Integer.parseInt(vp[2]);
        entrada = new Operacao(Integer.parseInt(vp[3]), null);
        for(int i = 4;i<=vp.length-1;i++){
            String split[]=vp[i].split(" ");
            Operacao op = new Operacao(Integer.parseInt(split[0]), split[1]);
            this.operacoes.add(op);
        }
    }
    
    void println(){
        System.out.print("PID:"+this.pid+" Prioridade:"+this.prioridade+" QtEventos:"+this.qtEventos+"\n"+"Operacao Entrada: "+this.entrada.getTempo()+"\n");
        System.out.println("Demais Operacoes");
        for(Operacao op:this.operacoes){
            op.println();
        }        
    }
}
