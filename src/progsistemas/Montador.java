package progsistemas;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Montador {

    private int locationCounter; // contador de localizacao
    private Map<String, Integer> tabSimbolo; // tabela de simbolos
    private Map<String, Integer> labels; // rotulos

    public Montador() {
        locationCounter = 0;
        tabSimbolo = new HashMap<>();
        labels = new HashMap<>();
    }

    public void monta(String arq) throws FileNotFoundException, IOException {
        // Leitura do arquivo de entrada
        BufferedReader bf = new BufferedReader(new FileReader(arq + ".txt"));

        // Cria o arquivo temporario
        FileWriter arqObj = new FileWriter("objeto.txt");

        // Le a primeira linha
        String linha = bf.readLine();

        // Separa a linha nos tabs
        String[] textoSeparado = linha.split("\t");

        // Enquanto nao encontra o inicio das instrucoes
        while (!textoSeparado[0].equals("ORG")) {
            // Guarda no mapa de rotulos
            labels.put(textoSeparado[0], Integer.parseInt(textoSeparado[2].substring(1)));
            // Le nova linha 
            linha = bf.readLine();
            // Separa a linha nos tabs
            textoSeparado = linha.split("\t");
        }

        // Atualiza locationCounter de acordo com o endereco inicial das instrucoes
        textoSeparado[1] = textoSeparado[1].substring(1);
        locationCounter = Integer.parseInt(textoSeparado[1]);

        // Le primeira instrucao
        linha = bf.readLine();

        // Separa a linha nos tabs
        textoSeparado = linha.split("\t");

        // Enquanto nao terminam as instrucoes
        while (!textoSeparado[0].equals("END")) {

            // se encontra w na instrucao, ignora-o
            if (textoSeparado[0].charAt(textoSeparado[0].length() - 1) == 'W') {
                verificaOperacao(textoSeparado[0].substring(0, textoSeparado[0].length() - 2), arqObj);
            } else {
                verificaOperacao(textoSeparado[0], arqObj);
            }
            locationCounter += 4;

            switch (textoSeparado[1].charAt(0)) {
                case '#':
                    switch (textoSeparado[1].charAt(1)) {
                        case '$':
                            // hexadecimal
                            textoSeparado[1] = textoSeparado[1].substring(2);
                            arqObj.write(new BigInteger(textoSeparado[1], 16).toString(2) + " ");
                            break;
                        case '@':
                            // octal
                            textoSeparado[1] = textoSeparado[1].substring(2);
                            arqObj.write(new BigInteger(textoSeparado[1], 8).toString(2) + " ");
                        //break;
                        case '%':
                            // binario
                            textoSeparado[1] = textoSeparado[1].substring(2);
                            arqObj.write(textoSeparado[1] + " ");
                            break;
                        default:
                            // decimal
                            textoSeparado[1] = textoSeparado[1].substring(1);
                            arqObj.write(Integer.toBinaryString(Integer.parseInt(textoSeparado[1])) + " ");
                            break;
                    }
                    break;
                case 'D':
                    arqObj.write(Integer.toBinaryString(Integer.parseInt(textoSeparado[1].substring(1))) + " ");
                    break;
                default:
                    arqObj.write(Integer.toBinaryString(labels.get(textoSeparado[1])) + " ");
                    break;
            }
            locationCounter += 4;

            if (textoSeparado.length == 3) {
                switch (textoSeparado[2].charAt(0)) {
                    case '#':
                        switch (textoSeparado[2].charAt(1)) {
                            case '$':
                                // hexadecimal
                                textoSeparado[2] = textoSeparado[2].substring(2);
                                arqObj.write(new BigInteger(textoSeparado[2], 16).toString(2) + " ");
                                break;
                            case '@':
                                // octal
                                textoSeparado[2] = textoSeparado[2].substring(2);
                                arqObj.write(new BigInteger(textoSeparado[2], 8).toString(2) + " ");
                            //break;
                            case '%':
                                // binario
                                textoSeparado[2] = textoSeparado[2].substring(2);
                                arqObj.write(textoSeparado[2] + " ");
                                break;
                            default:
                                // decimal
                                textoSeparado[2] = textoSeparado[2].substring(1);
                                arqObj.write(Integer.toBinaryString(Integer.parseInt(textoSeparado[2])) + " ");
                                break;
                        }
                        break;
                    case 'D':
                        arqObj.write(Integer.toBinaryString(Integer.parseInt(textoSeparado[2].substring(1))) + " ");
                        break;
                    default:
                        arqObj.write(Integer.toBinaryString(labels.get(textoSeparado[2])) + " ");
                        break;
                }
                locationCounter += 4;
            }

            arqObj.write("\n");

            // Le nova linha 
            linha = bf.readLine();
            // Separa a linha nos tabs
            textoSeparado = linha.split("\t");

        }

        // Fecha o arquivo
        arqObj.close();
    }

    public void verificaOperacao(String palavra, FileWriter arq) throws IOException {
        switch (palavra) {
            case "ADD":
                arq.write("1101 ");
                break;
            case "ADDI":
                arq.write("00000110 ");
                break;
            case "AND":
                arq.write("1100 ");
                break;
            case "ANDI":
                arq.write("00000010 ");
                break;
            case "BRA":
                arq.write("01100000 ");
                break;
            case "CMP":
                arq.write("1001 ");
                break;
            case "CMPI":
                arq.write("00001100 ");
                break;
            case "DIVS":
                arq.write("1000 ");
                break;
            case "DIVU":
                arq.write("1000 ");
                break;
            case "JMP":
                arq.write("0100111011 ");
                break;
            case "LSL":
                arq.write("1110 ");
                break;
            case "LSR":
                arq.write("1110 ");
                break;
            case "MOVE":
                arq.write("00 ");
                break;
            case "MULS":
                arq.write("1100 ");
                break;
            case "MULU":
                arq.write("0100 ");
                break;
            case "NOP":
                arq.write("0100111001110001");
                break;
            case "NEG":
                arq.write("01000100 ");
                break;
            case "NOT":
                arq.write("01000110 ");
                break;
            case "OR":
                arq.write("1000 ");
                break;
            case "ORI":
                arq.write("00000000 ");
                break;
            case "SUB":
                arq.write("1001 ");
                break;
            case "SUBI":
                arq.write("00000100 ");
                break;
            case "STOP":
                arq.write("0100111001110010 ");
        }
    }

}
