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
public class Evento{
    protected int tempoOcorrencia;
    protected int ownerId;
    protected String nome;

    
    public Evento(String nome,int tempoOcorrencia,int ownerId){
        this.tempoOcorrencia=tempoOcorrencia;
        this.ownerId=ownerId;
        this.nome=nome;
        
    }
    
    public void println(){
        System.out.println(nome+" Tempo:"+this.tempoOcorrencia+" PID:"+this.ownerId);
    }
    
    
    public String getStringEvt(){
        return ("Tempo:"+this.tempoOcorrencia+" Evento:"+this.nome+" PID:"+this.ownerId);
    }
    
    public int getTempoOcorrencia() {
        return tempoOcorrencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setTempoOcorrencia(int tempoOcorrencia) {
        this.tempoOcorrencia = tempoOcorrencia;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }   
   
}
