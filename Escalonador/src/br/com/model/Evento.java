/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author nakao<nakaosensei@gmail.com>
 */
public class Evento {
    private int tempo;
    private String evento;//Lock,Unlock,Termino
    //Em caso de lock, o tempo indica quanto tempo
    //o processo deve executar para ser bloqueado
    //Em caso de unlock, se trata de quantos simbolos
    //de execução geral precisam ser executados pra desbloquear
    //Termino : valor do tempo executando pra sair o cara
    public Evento(int tempo,String evento){
        this.tempo=tempo;
        this.evento=evento;
    }
    
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int id) {
        this.tempo = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
    
    public void println(){        
        System.out.println(this.tempo+" "+this.evento);
    }
}
