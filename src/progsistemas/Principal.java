package progsistemas;

import java.io.*;
import javax.swing.*;

public class Principal {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Mensagem inicial
        JOptionPane.showMessageDialog(null, "Simulador do Motorola 68k v. 1.0", "Bem-vindo!", 1, null);

        // Digita nome do arquivo de entrada
        String arq = JOptionPane.showInputDialog(null, "Digite o nome do arquivo de entrada", "Arquivo", JOptionPane.QUESTION_MESSAGE);
        while (arq == null || arq.equals("")) {
            arq = JOptionPane.showInputDialog(null, "Digite novamente o nome do arquivo de entrada", "Erro: nome inválido", JOptionPane.ERROR_MESSAGE);
        }

        // Leitura do arquivo de entrada
        BufferedReader bf = new BufferedReader(new FileReader(arq + ".txt"));

        // Le a primeira linha
        String linha = bf.readLine();

        // Enquanto nao terminar o arquivo
        while (linha != null) {
            /*
            Todas as verificacoes e manipulacoes das instrucoes contidas no arquivo,
            chamando a classe correspondente (Montador, Macro, Carregador, Ligador)
            */
            
            // Le proxima linha
            linha = bf.readLine();
        }

    }

}
