package progsistemas;

import java.io.*;
import java.util.*;

public class Montador {

    private int locationCounter; // contador de localizacao
    private Map<String, Integer> tabSimbolo; // tabela de simbolos

    public Montador() {
        locationCounter = 0;
        tabSimbolo = new HashMap<>();
    }

    public void etapa1(String arq) throws FileNotFoundException, IOException {
        // Leitura do arquivo de entrada
        BufferedReader bf = new BufferedReader(new FileReader(arq + ".txt"));

        // Cria o arquivo temporario
        FileWriter arqTemp = new FileWriter("temp.txt");

        // Le a primeira linha
        String linha = bf.readLine();

        // Enquanto nao terminar o arquivo
        while (linha != null) {
            // Separa a linha nos espacos
            String[] textoSeparado = linha.split(" ");

            int ini = 1;
            // Se for label, retorna false
            if (verificaOperacao(textoSeparado[0], arqTemp) == false) {
                // Coloca na tabela de simbolos
                tabSimbolo.put(textoSeparado[0], locationCounter);
                // Verifica a operacao do proximo
                verificaOperacao(textoSeparado[1], arqTemp);
                ini = 2;
            }

            locationCounter++;

            // Escreve no arquivo temporario os operandos
            for (int i = ini; i < textoSeparado.length; ++i) {
                arqTemp.write(textoSeparado[i] + " ");
                locationCounter++;
            }

            // Imprime nova linha no arquivo temporario
            arqTemp.write("\n");

            // Le a proxima linha
            linha = bf.readLine();
        }

        // Fecha o arquivo
        arqTemp.close();
    }

    public void etapa2() throws FileNotFoundException, IOException {
        // Leitura do arquivo temporario
        BufferedReader bf = new BufferedReader(new FileReader("temp.txt"));

        // Cria o arquivo objeto
        FileWriter arqObj = new FileWriter("objeto.txt");

        // Le a primeira linha
        String linha = bf.readLine();

        // Enquanto nao terminar o arquivo
        while (linha != null) {
            // Separa a linha nos espacos
            String[] textoSeparado = linha.split(" ");

            // Escreve no arquivo objeto os enderecos dos operandos e copia os das operacoes
            for (int i = 0; i < textoSeparado.length; ++i) {
                if (!textoSeparado[i].equals("XX")) {
                    if (tabSimbolo.get(textoSeparado[i]) != null) {
                        int end = tabSimbolo.get(textoSeparado[i]);
                        arqObj.write(end + " ");
                    } else {
                        arqObj.write(textoSeparado[i] + " ");
                    }
                }
            }

            // Imprime nova linha no arquivo temporario
            arqObj.write("\n");

            // Le a proxima linha
            linha = bf.readLine();
        }

        // Fecha o arquivo
        arqObj.close();
    }

    public boolean verificaOperacao(String palavra, FileWriter arq) throws IOException {
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
            case "CONST":
                arq.write("XX ");
                return true;
            default:
                return false;
        }
    }

}
