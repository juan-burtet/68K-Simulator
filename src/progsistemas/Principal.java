
package progsistemas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static sun.text.normalizer.UTF16.append;


public class Principal {

    private String arq;
    private String arq1;
    private String inArq = "";
    private String outArq = "";
    private String ligado = "";
    private String carregado = "";
    private String regA = "";
    private String regD = "";
    private String arqM = "";
    private String MacroDef = "";
    private String MacroExp = "";
    
    public Principal(String Arq) throws IOException{
        arq = Arq;
        arq1 = arq.concat("1");
        if(arq != null){
            
            arqM = JOptionPane.showInputDialog(null, "Digite o nome do arquivo de macro sem '.txt'", "Arquivo", JOptionPane.QUESTION_MESSAGE);
            if(arqM.equals("")) {
                JOptionPane.showMessageDialog(null, "Sem macros para analizar", "Alerta!", 1, null);
            }
            else{
                ProcessadorMacros macro = new ProcessadorMacros(arqM);
                macro.processa();
                DefMDef();
                DefMExp();
            }
            Montador mont = new Montador();
            Montador mont1 = new Montador();
            Memoria mem = new Memoria();
            Ligador linker = new Ligador();
            Carregador loader = new Carregador();
            
            try {
                
                InArq(arq);
                mont.monta(arq);
                OutArq(arq);
                InArq(arq1);
                mont1.monta(arq1);
                OutArq(arq1);
                linker.liga(mont.GetTabelaDeDefinicoes(),mont1.GetTabelaDeDefinicoes(),
                            mont.GetTabelaDeUso(),mont1.GetTabelaDeUso(),arq,arq1);
                Ligado();
                mem = loader.carrega(ligado);
                Carregado();
                defRegA(mem);
                defRegD(mem);
                JOptionPane.showMessageDialog(null, "Codigo executado com exito", "Executado", 1, null);
            } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, "Arquivo invalido", "Erro!", JOptionPane.ERROR_MESSAGE, null);
            }
        }
        else{
            arq = JOptionPane.showInputDialog(null, "Sem resultados obtidos", "Resultados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void InArq(String arq) throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader(arq + ".txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.inArq += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
    public void OutArq(String arq) throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader("objeto" + "_" + arq + ".txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.outArq += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
    public void Ligado() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader("codigo_ligado.txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.ligado += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
     public void Carregado() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader("arquivo_carregado.txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.carregado += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
     
     public void DefMDef() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader(arqM + ".txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.MacroDef += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
     
    public void DefMExp() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader("MacroProcessada.txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.MacroDef += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
    
    public String getInArq(){
        return this.inArq;
    }
    
    public String getOutArq(){
        return this.outArq;
    }
    
    public String getLigado(){
        return this.ligado;
    }
    
    public String getCarregado(){
         return this.carregado;
    }
    
    public void defRegA(Memoria mem){
        int i;
        regA = "| ";
        for(i=0; i<8; i++){
            regA = regA.concat(mem.getA(i));
            regA = regA.concat(" | ");
        }
    }
    
    public void defRegD(Memoria mem){
        int i;
        regD = "| ";
        for(i=0; i<8; ++i){
            regD = regD.concat(mem.getD(i));
            regD = regD.concat(" | ");
        }
    }
    
    public String getRegA(){
        return regA;
    }
    
    public String getRegD(){
        return regD;
    }
    
    public String getMacroDef(){
        return MacroDef;
    }
    
    public String getMacroExp(){
        return MacroExp;
    }

}