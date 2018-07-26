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

    private String arq = "";
    private String inArq = "";
    private String outArq = "";
    
    public Principal(String Arq) throws IOException{
        arq = Arq;
        
        if(arq != null){
            Montador mont = new Montador();
            Memoria mem = new Memoria();
            try {
                InArq();
                mont.monta(arq);
                OutArq();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            arq = JOptionPane.showInputDialog(null, "Sem resultados obtidos", "Resultados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void InArq() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader(arq + ".txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.inArq += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
    public void OutArq() throws FileNotFoundException, IOException{
        FileReader arqPath = new FileReader("objeto.txt");
        BufferedReader arqObj = new BufferedReader(arqPath);

        while(arqObj.ready()){
          this.outArq += arqObj.readLine() + "\n";
        }
        
        arqObj.close();
    }
    
    public String getInArq(){
        return this.inArq;
    }
    
    public String getOutArq(){
        return this.outArq;
    }

}
