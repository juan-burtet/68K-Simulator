
package progsistemas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ligador {
    public  ArrayList<TabelaSimbolosGlobais> SimbolosGlobais = new ArrayList<> ();
    
    public void liga(ArrayList<TabelaDefinicoes> Def0, ArrayList<TabelaDefinicoes> Def1,
                     ArrayList<TabelaDeUso> Uso0, ArrayList<TabelaDeUso> Uso1,String obj1,String obj2) throws IOException {
        
        int Endereco=0;
        String linhaObjeto;
        Boolean achou=false;
        
        //Cria o arquivo com o codigo ligado
        FileWriter arqObj = new FileWriter("codigo_ligado"+ ".txt");
        
        // Leitura dos codigos fonte e objetos
        BufferedReader bf = new BufferedReader(new FileReader("objeto_" + obj1 + ".txt"));
        BufferedReader bf2 = new BufferedReader(new FileReader("objeto_" + obj2 + ".txt"));
        BufferedReader bf3 = new BufferedReader(new FileReader(obj1 + ".txt"));
        BufferedReader bf4 = new BufferedReader(new FileReader(obj2 + ".txt"));    
        // Cria uma variavel temporária
        TabelaSimbolosGlobais temp;
        // Unifica as tabelas de definição dos dois módulos na TabelaDeSimbolosGlobais
        for(int i=0;i<Def0.size();i++){
            temp = new TabelaSimbolosGlobais();
            temp.setSimbolo(Def0.get(i).getSimbolo());
            temp.setEndereco(Def0.get(i).getEndereco());
            temp.setRelocabilidade(Def0.get(i).getRelocabilidade());
            SimbolosGlobais.add(temp);
        }
        Endereco=Def0.get(Def0.size()-1).getEndereco();
        for(int i=0;i<Def1.size();i++){
            temp = new TabelaSimbolosGlobais();
            temp.setSimbolo(Def1.get(i).getSimbolo());
            if(Def1.get(i).getEndereco()!=0)
                temp.setEndereco(Def1.get(i).getEndereco() + Endereco);
            temp.setRelocabilidade(Def1.get(i).getRelocabilidade());
            SimbolosGlobais.add(temp);            
        }
        
       // Lê primeiro codigo fonte e objeto
        String linha = bf3.readLine();
        String[] textoSeparado = linha.split("\t");
        while (!textoSeparado[0].equals("ORG")) { // Avança até encontrar inicio das instruções
             linha = bf3.readLine();
             textoSeparado = linha.split("\t");
        }
        linha = bf3.readLine();
        textoSeparado = linha.split("\t");
        while (true) { // Enquanto não termina de ler instruções
             linhaObjeto=bf.readLine(); // Lê uma linha codigo objeto
            for(int i=0;i<SimbolosGlobais.size();i++){ 
                if(SimbolosGlobais.get(i).getSimbolo().equals(textoSeparado[1])){ // Se existe um simbolo global
                    textoSeparado = linhaObjeto.split("\t");
                    arqObj.write(textoSeparado[0] + "\t");
                    arqObj.write(SimbolosGlobais.get(i).getEndereco() + "\t"); // Saida do ligador recebe endereco atualizado
                    if (textoSeparado.length == 3)
                        arqObj.write(textoSeparado[2] + "\t");
                    achou=true;
                    break;
              }           
                else if(textoSeparado.length == 3){
                    if(SimbolosGlobais.get(i).getSimbolo().equals(textoSeparado[2])){
                    textoSeparado = linhaObjeto.split("\t");
                    arqObj.write(textoSeparado[0] + "\t");
                    arqObj.write(textoSeparado[1] + "\t");
                    arqObj.write(SimbolosGlobais.get(i).getEndereco() + "\t");
                    achou=true;
                    break;
                    }
                }
            }             
            if(achou==false && !(linhaObjeto.contains("0100111001110010"))){ // Se linha não possui codigo global copia igual ao do montador
                arqObj.write(linhaObjeto + "\t");
            }                     
            linha = bf3.readLine();           
            textoSeparado = linha.split("\t");
            achou=false;
            if(textoSeparado[0].equals("END"))
                break;
            else
                arqObj.write("\n");  
        }
        // Lê segundo codigo fonte e objeto
        linha = bf4.readLine();
        textoSeparado = linha.split("\t");
        while (!textoSeparado[0].equals("ORG")) {
             linha = bf4.readLine();
             textoSeparado = linha.split("\t");
        }
        linha = bf4.readLine();
        textoSeparado = linha.split("\t");
        while (!textoSeparado[0].equals("END")) {
             linhaObjeto=bf2.readLine();
            for(int i=0;i<SimbolosGlobais.size();i++){
                if(SimbolosGlobais.get(i).getSimbolo().equals(textoSeparado[1])){
                    textoSeparado = linhaObjeto.split("\t");
                    arqObj.write(textoSeparado[0] + "\t");
                    arqObj.write(SimbolosGlobais.get(i).getEndereco() + "\t");
                    if (textoSeparado.length == 3)
                        arqObj.write(textoSeparado[2] + "\t");
                    achou=true;
                    break;
              }
                else if(textoSeparado.length == 3){
                    if(SimbolosGlobais.get(i).getSimbolo().equals(textoSeparado[2])){
                    textoSeparado = linhaObjeto.split("\t");
                    arqObj.write(textoSeparado[0] + "\t");
                    arqObj.write(textoSeparado[1] + "\t");
                    arqObj.write(SimbolosGlobais.get(i).getEndereco() + "\t");
                    achou=true;
                    break;
                    }
                }
            }                
            if(achou==false){
                if(linhaObjeto.contains("0100111001110010"))
                    arqObj.write("0100111001110010" + "\t");
                else
                    arqObj.write(linhaObjeto + "\t");
            }
            arqObj.write("\n");
           
            linha = bf4.readLine();           
            textoSeparado = linha.split("\t");
            achou=false;            
        }        
     
        arqObj.close();
        bf.close();
        bf2.close();
        bf3.close();
        bf4.close();
    }
}





