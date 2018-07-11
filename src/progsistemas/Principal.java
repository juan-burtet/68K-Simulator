package progsistemas;

import java.io.*;
import java.util.*;
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

        /*MONTADOR ETAPA 1*/
        // Le a primeira linha
        int locationCounter = 0;

        //Criar o objeto.txt
        FileWriter arqTemp = new FileWriter("Temp.txt");

        //Criar tabela de símbolos
        Map<String, Integer> tabSimbolo = new HashMap<>();

        // Le a primeira linha
        String linha = bf.readLine();

        // Enquanto nao terminar o arquivo
        while (linha != null) {
            //Separa a linha no espaço
            String[] textoSeparado = linha.split(" ");

            int ini = 1;
            if (verificaOperacao(textoSeparado[0], arqTemp) == false) {
                tabSimbolo.put(textoSeparado[0], locationCounter);
                verificaOperacao(textoSeparado[1], arqTemp);
                ini = 2;
            }

            locationCounter++;

            for (int i = ini; i < textoSeparado.length; ++i) {
                arqTemp.write(textoSeparado[i] + " ");
                locationCounter++;
            }

            //Imprime nova linha no Temp.txt
            arqTemp.write("\n");

            // Le proxima linha
            linha = bf.readLine();
        }

        arqTemp.close();

        /*MONTADOR ETAPA 2*/
        bf = new BufferedReader(new FileReader("Temp.txt"));

        FileWriter arqObj = new FileWriter("objeto.txt");

        linha = bf.readLine();

        while (linha != null) {

            String[] textoSeparado = linha.split(" ");

            for (int i = 0; i < textoSeparado.length; ++i) {
                if (tabSimbolo.get(textoSeparado[i]) != null)  {
                    int end = tabSimbolo.get(textoSeparado[i]);
                    arqObj.write(end + " ");
                } else {
                    arqObj.write(textoSeparado[i] + " ");
                }
            }
            
            arqObj.write("\n");

            linha = bf.readLine();
        }
        
        arqObj.close();

    }

    public static boolean verificaOperacao(String palavra, FileWriter arq) throws IOException {
        switch (palavra) {
            case "ADD":
                arq.write("01 ");
                return true;
            case "ADDI":
                arq.write("02 ");
                return true;
            case "AND":
                arq.write("03 ");
                return true;
            case "ANDI":
                arq.write("04 ");
                return true;
            case "BRA":
                arq.write("05 ");
                return true;
            case "CMP":
                arq.write("06 ");
                return true;
            case "CMPI":
                arq.write("07 ");
                return true;
            case "DIVS":
                arq.write("08 ");
                return true;
            case "DIVU":
                arq.write("09 ");
                return true;
            case "JMP":
                arq.write("10 ");
                return true;
            case "LSL":
                arq.write("11 ");
                return true;
            case "LSR":
                arq.write("12 ");
                return true;
            case "MOVE":
                arq.write("13 ");
                return true;
            case "MULS":
                arq.write("14 ");
                return true;
            case "MULU":
                arq.write("15 ");
                return true;
            case "NEG":
                arq.write("16 ");
                return true;
            case "NOT":
                arq.write("17 ");
                return true;
            case "OR":
                arq.write("18 ");
                return true;
            case "ORI":
                arq.write("19 ");
                return true;
            case "SUB":
                arq.write("20 ");
                return true;
            case "SUBI":
                arq.write("21 ");
                return true;
            case "NOP":
                arq.write("XX ");
                return true;
            default:
                return false;

        }
    }

}
