package br.com.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Arquivo {
    private FileReader arq;
    private BufferedReader buff;
    

    public String lerArquivo(String path){
        String file="";        
        try {
            arq = new FileReader (path);
            buff = new BufferedReader(arq);
            while (buff.ready()){
                file+=(buff.readLine())+"\n";                
            }
            buff.close();
            return file;
        }catch (Exception ex) {
            
            //System.out.println("");(null, "Caminho de arquivo incorreto");
            return file;
        }        
    }
    public void escreverArquivo(String path,String texto){
        try{
            FileWriter f = new FileWriter(path);
            PrintWriter gravarArq = new PrintWriter(f);
            gravarArq.print(texto);
            f.close();
        }catch(Exception e){
            System.out.println("O seu caminho não existe!");
            
        }
    }
    
    
}
