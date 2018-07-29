/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
*
 * @author FelipeGruend
 */
public class Macro {
    private final String nome;
    
    private int numParametros;
    private ArrayList<String> parametros;
    private ArrayList<String> instrucoes;
    
    /**
     * Construtor
     * @param nome strings nome da macro
     * @param numParametros numero de parametros na definicao da macro
     */
    public Macro(String nome, int numParametros){
        this.nome = nome;
        this.numParametros = numParametros;
        this.parametros = new ArrayList<>();
        this.instrucoes = new ArrayList<>();
    }
    
    /**
    * addInstrucao
    * Recebe uma linha da macro e
    * adiciona nas linhas de instrucao
    * @param linha 
    */
    public void addInstrucao(String linha){
        // Remove os comentários da linha se houver
        // linha = linha.replaceAll(";.*", "");
        // Adiciona na linha de instrucoes
        this.instrucoes.add(linha);
        System.out.println(linha);
    }
    
    /**
     * addParametros
     * Recebe a linha da chamada da macro
     * e adiciona os respectivos parâmetros da chamada
     * @param linha
     */
    public void addParametros(String linha){        
        // Pega todas as entradas de parametros da chamada da macro
        Matcher m;
        m = Pattern.compile("\\w*").matcher(linha);
        // Pega a primeira palavra e desconsidera, porque é o nome da macro
        m.find();
        // Para as seguintes adiciona como parametros da macro
        while (m.find()) {
            this.parametros.add(m.group());
        }
        System.out.println(this.parametros);
    }
    
     /**
     * Retorna o nome da macro
     * @return nome
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     * Retorna as instruções da macro em uma lista de strings
     * @return instrucoes
     */
    public ArrayList<String> getInstrucoes(){
        return this.instrucoes;
    }

     /**
     * Retorna os parametros da chamada da macro em uma lista de strings
     * @return parametros
     */
    public ArrayList<String> getParametros(){
        return this.parametros;
    }
    
    /**
     * Depois de definidos os parametros da macro
     * pela chamada, sao substituidas as ocorrencias
     * respectivas.
     */
    public void substituiParametros(){
        String aux;
        // Para cada linha de instrucoes
        for(int i=0; i<this.instrucoes.size();i++){
            // Atribui a linha a uma string auxiliar
            aux = this.instrucoes.get(i);
            // Depois para cada parametro, se existir na linha atual
            // substitui pelo parametro correto. Ex.: \2 -> D6 (substitui o parametro dois pelo reg D6)
            for(int j=1; j<=this.numParametros; j++){
                aux = aux.replaceAll("\\"+j, this.parametros.get(j));
            }
        }
    }
}
