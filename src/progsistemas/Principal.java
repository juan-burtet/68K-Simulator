package progsistemas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Principal {

    private String arq;
    private String inArq;
    private String outArq;
    
    public Principal() throws IOException{
        arq = JOptionPane.showInputDialog(null, "Digite o nome do arquivo de entrada", "Arquivo", JOptionPane.QUESTION_MESSAGE);
        while (/*arq != null || */ arq.equals("")) {
            arq = JOptionPane.showInputDialog(null, "Digite novamente o nome do arquivo de entrada", "Erro: nome inválido", JOptionPane.ERROR_MESSAGE);
        }
        
        if(arq != null){
            Montador mont = new Montador();
            Memoria mem = new Memoria();
            try {
                mont.monta(arq);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            inArq = arq;
            mont.monta(arq);
            outArq = arq;
        }
        else{
            arq = JOptionPane.showInputDialog(null, "Sem resultados obtidos", "Resultados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getInArq(){
        return this.inArq;
    }
    public String getOutArq(){
        return this.outArq;
    }

}
