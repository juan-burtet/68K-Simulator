/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Meu computador
 */
public class Save {
    private String namePath;
    
    public Save(String obj, String objPath) throws IOException{
        namePath = objPath;
        String name = objPath + ".txt";
        File arquivo = new File(name);
        
        FileWriter grava = new FileWriter(arquivo);
        PrintWriter escreve = new PrintWriter(grava);
        escreve.println(obj);
        
        escreve.close();
        grava.close();
    }
    
    public String fileName(){
        return namePath;
    }
}
