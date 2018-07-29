/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;

/**
 *
 * @author FelipeGruend
 */
public class Label {
    private String nome;
    private int ocorrencias;
    
    /**
     * Construtor
     * @param nome
     */
    public Label(String nome){
        this.nome = nome;
        this.ocorrencias = 0;
    }
    /**
     * @return nome do label
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     * @return ocorrencias do label
     */
    public int getOcorrencias(){
        return this.ocorrencias;
    }
    
    /**
     *  Incrementas as ocorrencias da label
     */
    public void addOcorrencia(){
        this.ocorrencias++;
    }
}
