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
    private String regA = "";
    private String regD = "";
    
    public Principal(String Arq) throws IOException{
        arq = Arq;
        arq1 = arq.concat("1");
        if(arq != null){
            Montador mont = new Montador();
            Montador mont1 = new Montador();
            Memoria mem = new Memoria();
            Ligador linker = new Ligador();
            Carregador c = new Carregador();
            
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
                mem = c.carrega("codigo_ligado.txt"); 
                defRegAD(mem);
                
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
    
    public String getInArq(){
        return this.inArq;
    }
    
    public String getOutArq(){
        return this.outArq;
    }
    
    public String getLigado(){
        return this.ligado;
    }
    
    public void defRegAD(Memoria mem){
        int i;
        regA = "| ";
        regD = "| ";
        for(i=0; i<8; i++){
            regA = regA.concat(mem.getA(i));
            regA = regA.concat(" | ");
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

}
