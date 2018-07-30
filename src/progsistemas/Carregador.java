/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Juan Burtet
 */
public class Carregador extends Instrucoes{
    private Map<String, Integer> enderecos;
    private Memoria memoria;
    
    public Carregador(){
        enderecos = new HashMap<>();
    }//Carregador
    
    
    public Memoria carrega(String arq) throws FileNotFoundException, IOException {
        int cont; // contador
        int cont2; // contador
        int aux; // auxiliar
        boolean fim = false; // indica o stop
        FileWriter arqObj = new FileWriter("arquivo_carregado.txt"); // Arquivo criado pelo carregado
        String[] linhas = arq.split("\n"); // divide a string em linhas
        String[] linha_unica; // usado para dividir a linha em palavras
        String palavra; // Palavra sozinha
        int palavra_inteiro; // Valor inteiro da palavra
        int valor1; // Valores dos labels
        int valor2 = 0; // Valores dos labels
        int[] location_counter = new int[linhas.length]; // localização do inicio de cada linha
        memoria = new Memoria(); // Memoria utilizada
        
        // atualiza as localizações
        aux = 0;
        for(cont = 0; cont < location_counter.length; cont++){
            location_counter[cont] = aux;
            linha_unica = linhas[cont].split("\t");
            aux += linha_unica.length;
        }// for
        
        // atravessa o código o executando
        for(cont = 0; cont < linhas.length; cont++){
            // Divide as opções das linhass
            linha_unica = linhas[cont].split("\t");

            // Resultado das operações
            int resultado;
            
            // Chegou no fim
            if(linha_unica.length == 1)
                break;
            
            
            // pegar a primeira parte da instrução
            palavra = linha_unica[1];
            palavra_inteiro = Integer.parseInt(palavra, 10);
            
            // Maior que 2000, é endereço
            if(palavra_inteiro >= 2000){
              // Se o endereço não foi utilizado, inicializa com valor 0
              if(enderecos.get(palavra) == null){
                  enderecos.put(palavra, 0);
              }
              // valor 1 recebe valor do endereço
              valor1 = enderecos.get(palavra);
            }//if
            // Entre 2000 e 7, é valor
            else if (palavra_inteiro >= 8){
                valor1 = palavra_inteiro;
            }// else if
            // Registradores
            else{
                palavra = memoria.getD(palavra_inteiro);
                valor1 = Integer.parseInt(palavra, 2);
            }//else
            
            // Se a operação usar 2 operadores, pega esses valores
            if(linha_unica.length > 2){
                // pegar a primeira parte da instrução
                palavra = linha_unica[2];
                palavra_inteiro = Integer.parseInt(palavra, 10);

                // Maior que 2000, é endereço
                if(palavra_inteiro >= 2000){
                  // Se o endereço não foi utilizado, inicializa com valor 0
                  if(enderecos.get(palavra) == null){
                      enderecos.put(palavra, 0);
                  }
                  // valor 1 recebe valor do endereço
                  valor2 = enderecos.get(palavra);
                }//if
                // Entre 2000 e 7, é valor
                else if (palavra_inteiro >= 8){
                    valor2 = palavra_inteiro;
                }// else if
                // Registradores
                else{
                    palavra = memoria.getD(palavra_inteiro);
                    valor2 = Integer.parseInt(palavra, 2);
                }//else
            }//if
            
            
            // Decide qual operação vai ser utilizada
            switch (linha_unica[0]){
                // ADD
                case "1101":
                            // Recebe o resultado de ADD
                            resultado = add(valor1, valor2);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // ADDI
                case "00000110":
                            // Recebe o resultado de ADDI
                            resultado = addi(valor1, valor2);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // AND 
                case "1100":
                            // Recebe o resultado de AND
                            resultado = and(valor1, valor2);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // ANDI
                case "00000010":
                            // Recebe o resultado de ANDI
                            resultado = add(valor1, valor2);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // CMP
                case "1011":
                            resultado = cmp(valor1, valor2);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // CMPI
                case "00001100":
                            resultado = cmp(valor2,valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // OR
                case "1000":
                            resultado = or(valor2, valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // JMP
                case "0100111011":
                            resultado = jmp(valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // LSR
                case "1110":
                            resultado = lsr(valor2, valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // MOVE
                case "00":
                            resultado = move(valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // NOP
                case "0100111001110001":
                            // faz nada
                            break;
                // NEG
                case "01000100":
                            resultado = neg(valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // NOT
                case "01000110":
                            resultado = not(valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // ORI
                case "00000000":
                            resultado = ori(valor2, valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // SUB
                case "1001":
                            resultado = sub(valor2, valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // SUBI
                case "00000100":
                            resultado = subi(valor2, valor1);
                            entrega_resultado(resultado, palavra_inteiro);
                            break;
                // STOP
                case "0100111001110010":
                            fim = true;
                            break;
            }// switch
            
            
            if(fim){
                break;
            }//if
        }//for
        
        for(cont = 0; cont < linhas.length; cont++){
            linha_unica = linhas[cont].split("\t");
            for(cont2 = 0; cont2 < linha_unica.length; cont2++){
                if(cont2 == 0){
                    arqObj.write(linha_unica[cont2] + " ");
                }
                else{
                    int x = Integer.parseInt(linha_unica[cont2], 10);
                    arqObj.write(Integer.toBinaryString(x) + " ");
                }
            }
            arqObj.write("\n");
        }//for
        
        arqObj.close();
        // retorna a memória depois da execução
        conserta_memoria();
        return memoria;
    }//carrega
    
    public void entrega_resultado(int resultado, int palavra){
        String x = Integer.toBinaryString(resultado);
        // Endereços
        if (palavra >= 8){
            enderecos.put(Integer.toBinaryString(palavra), resultado);
        }//if
        // Registradores
        else{
            memoria.setD(palavra, x);
        }
        
    }//entrega_resultado
    
    public void imprime_memoria(Memoria m){
        int i;
        String x;
        for(i = 0; i < 8; i++){
            x = m.getD(i);
            System.out.println("D" + i + ": " + Integer.parseInt(x, 2));
        }
    }//imprime_memoria
    
    public void conserta_memoria(){
        int i;
        String x;
        for(i = 0; i < 8; i++){
            x = memoria.getD(i);
            while(x.length() <= 16){
                x = "0" + x;
            }//while
            
            memoria.setD(i, x);
        }//for
    }//conserta_memoria
}//Carregador

/*
"ADD" = "1101" - CERTO
"ADDI" = "00000110" - CERTO
"AND" = "1100" - CERTO
"ANDI" = "00000010" - CERTO
"BRA" = "01100000" - CERTO
"CMP" = "1011" - CERTO
"CMPI" = "00001100" - CERTO
"DIVS" = "1000" - CERTO, PRECISA TER "111" NO MEIO
"DIVU" = "1000" -  CERTO, PRECISA TER "011" NO MEIO
"JMP" = "0100111011" - CERTO
"LSL" = "1110" - CERTO, TEM "01" PERTO DO FIM
"LSR" = "1110" - CERTO, TEM "01" PERTO DO FIM
"MOVE" = "00" - CERTO
"MULS" = "1100" - CERTO, PRECISA TER "111" NO MEIO
"MULU" = "1100" - CERTO, PRECISA TER "011" NO MEIO
"NOP" = "0100111001110001" - CERTO
"NEG" = "01000100" - CERTO
"NOT" = "01000110" - CERTO
"OR" = "1000" - CERTO
"ORI" = "00000000" - CERTO
"SUB" = "1001" - CERTO
"SUBI" = "00000100" - CERTO
"STOP" = "0100111001110010" - CERTO
*/