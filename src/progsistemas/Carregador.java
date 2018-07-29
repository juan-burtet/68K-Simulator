/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Juan Burtet
 */
public class Carregador extends Instrucoes{
    
    
    public Carregador(){
        
    }//Carregador
    
    
    public Memoria carrega(String arq) throws FileNotFoundException, IOException {
        int cont; // contador
        int aux; // auxiliar
        String[] linhas = arq.split("\n"); // divide a string em linhas
        String[] linha_unica; // usado para dividir a linha em palavras
        int[] location_counter = new int[linhas.length]; // localização do inicio de cada linha
        Memoria memoria = new Memoria(); // Memoria utilizada
        
        // atualiza as localizações
        aux = 0;
        for(cont = 0; cont < location_counter.length; cont++){
            location_counter[cont] = aux;
            linha_unica = linhas[cont].split("\t");
            aux += linha_unica.length;
        }// for
        
        // atravessa o código o executando
        for(cont = 0; cont < linhas.length; cont++){
            linha_unica = linhas[cont].split("\t");
            switch (linha_unica[0]){
                // ADD
                case "1101":
                            break;
                // ADDI
                case "00000110":
                            break;
                // AND + MULS + MULU
                case "1100":
                            // AND
                            // MULS -> PRECISA TER "111" NO MEIO
                            // MULU -> PRECISA TER "011" NO MEIO
                            break;
                // ANDI
                case "00000010":
                            break;
                // BRA
                case "01100000":
                            break;
                // CMP
                case "1011":
                            break;
                // CMPI
                case "00001100":
                            break;
                // DIVS + DIVU + OR
                case "1000":
                            // DIVS -> "111" NO MEIO
                            // DIVU -> "011" NO MEIO
                            // OR
                            break;
                // JMP
                case "0100111011":
                            break;
                // LSL + LSR
                case "1110":
                            // "01" PERTO DO FIM
                            break;
                // MOVE
                case "00":
                            break;
                // NOP
                case "0100111001110001":
                            break;
                // NEG
                case "01000100":
                            break;
                // NOT
                case "01000110":
                            break;
                // ORI
                case "00000000":
                            break;
                // SUB
                case "1001":
                            break;
                // SUBI
                case "00000100":
                            break;
                // STOP
                case "0100111001110010":
                            break;
            }// switch
        }//for
        
        // retorna a memória depois da execução
        return memoria;
    }//carrega
    
}//Carregador

/*
"ADD" = "1101" - CERTO -
"ADDI" = "00000110" - CERTO -
"AND" = "1100" - CERTO -
"ANDI" = "00000010" - CERTO - 
"BRA" = "01100000" - CERTO -
"CMP" = "1001" -> MUDAR PARA -> "1011" - 
"CMPI" = "00001100" - CERTO
"DIVS" = "1000" - CERTO, PRECISA TER "111" NO MEIO
"DIVU" = "1000" -  CERTO, PRECISA TER "011" NO MEIO
"JMP" = "0100111011" - CERTO
"LSL" = "1110" - CERTO, TEM "01" PERTO DO FIM
"LSR" = "1110" - CERTO, TEM "01" PERTO DO FIM
"MOVE" = "00" - CERTO
"MULS" = "1100" - CORRETO, PRECISA TER "111" NO MEIO
"MULU" = "0100" -> MUDAR PARA -> "1100" E PRECISA TER "011"
"NOP" = "0100111001110001" -> CERTO 
"NEG" = "01000100" -> CERTO
"NOT" = "01000110" -> CERTO
"OR" = "1000" -> CERTO
"ORI" = "00000000" -> CERTO
"SUB" = "1001" -> CERTO
"SUBI" = "00000100" -> CERTO
"STOP" = "0100111001110010" -: CERTO 
*/