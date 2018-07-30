/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progsistemas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author FelipeGruend
 */
public class ProcessadorMacros {
    private ArrayList<Macro> macros;
    private ArrayList<Macro> chamadas;
    private ArrayList<Label> labelsMacros;
    
    public ProcessadorMacros(String arquivoEntrada, String arquivoSaida) throws FileNotFoundException, IOException{
        
        try {
            // Leitura do arquivo de entrada
            BufferedReader bf = new BufferedReader(new FileReader(arquivoEntrada + ".txt"));
            // Cria o arquivo temporario
            FileWriter arqSaida = new FileWriter(arquivoSaida + ".txt");
        
            // Cria uma string para ler cada linha
            String linha;
            // Cria um vetor pra armazenar as instrucoes, registradores, labels e chamadas de macro
            String[] code;
                       
            // Le a primeira linha
            linha = bf.readLine();

            // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
               // Remove os comentários da linha se houver
               linha = linha.replaceAll(";.*", "");
               // Quebra a linha em cada tab
               code = linha.split("\t");
               
               // Se encontrar a definicao de uma macro
               if(code[1].equalsIgnoreCase("MACRO")){
                    bf.mark(9999);
                    // Pega o nome da macro
                    String nomeMacro = code[0];
                    
                    // Cria uma lista para os parametros
                    ArrayList<String> parametros = new ArrayList<>();
                    
                    Pattern p;
                    Matcher m;
                    
                    while(!(linha.contains("ENDM"))){
                        // Remove os comentários da linha se houver
                        linha = linha.replaceAll(";.*", "");
                        p = Pattern.compile(" (\\\\d) ");
                        m = p.matcher(linha);
                        while (m.find()) {
                           parametros.add(m.group());
                        }  
                        linha = bf.readLine();
                   }
                    // Para cada parametro
                    for(int i=0; i<parametros.size()-1; i++){
                        //Percorre os outros parametros
                        for(int j=i+1; j<parametros.size(); j++){
                            // Se encontrar um parametro igual ao atual, remove a repeticao
                            if(parametros.get(i).equals(parametros.get(j))){
                                parametros.remove(j);
                            }
                        }
                    }
                    // Depois de ter a quantidade certa de parametros, pode iniciar a macro com o nome
                    // e numero de parametros corretos
                    Macro novo = new Macro(nomeMacro, parametros.size());
                    // Volta para o inicio da definicao da macro
                    bf.reset();
                    // Le as linhas da definicao da macro e adiciona nas instrucoes
                    while(!(linha.contains("ENDM"))){
                        novo.addInstrucao(bf.readLine());
                    }
                    
               }
                        
               linha = bf.readLine(); // lê da segunda até a última linha
            }

            arqSaida.close();
            bf.close();
          }catch (FileNotFoundException e){
              System.err.printf("Arquivo não encontrado: %s.\n", e.getMessage());
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
          }            
    }
}
