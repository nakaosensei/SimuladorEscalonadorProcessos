/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

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
    
    private List<Evento> eventos;
    
    public Processo(String pString){
        eventos = new ArrayList<>();
        String vp[]=pString.split("\n");
        pid=Integer.parseInt(vp[0]);
        prioridade=Integer.parseInt(vp[1]);
        qtEventos=Integer.parseInt(vp[2]);
        this.eventos.add(new Evento(Integer.parseInt(vp[3]), null));
        for(int i = 4;i<=vp.length-1;i++){
            String split[]=vp[i].split(" ");
            Evento op = new Evento(Integer.parseInt(split[0]), split[1]);
            this.eventos.add(op);
        }
    }
    
    public void processar(){
        for(Evento e:this.eventos){
            
        }
    }
    
    public void println(){
        System.out.print("PID:"+this.pid+" Prioridade:"+this.prioridade+" QtEventos:"+this.qtEventos+"\n");
        System.out.println("Eventos");
        for(Evento op:this.eventos){
            op.println();
        }        
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getQtEventos() {
        return qtEventos;
    }

    public void setQtEventos(int qtEventos) {
        this.qtEventos = qtEventos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    
}
