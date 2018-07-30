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

    private final String arquivoEntrada;

    public ProcessadorMacros(String arquivoEntrada) {
        this.arquivoEntrada = arquivoEntrada;
        this.macros = new ArrayList<>();
        this.chamadas = new ArrayList<>();
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void processa() throws FileNotFoundException, IOException {

        try {
            // Leitura do arquivo de entrada
            BufferedReader bf = new BufferedReader(new FileReader(arquivoEntrada + ".txt"));

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
                if (!(linha.contains("MACRO"))) {
                    linha = bf.readLine();
                } else {
                    bf.mark(1000000);
                    // Pega o nome da macro
                    String nomeMacro = code[0];

                    // Cria uma lista para os parametros
                    ArrayList<String> parametros = new ArrayList<>();

                    Pattern p;
                    Matcher m;

                    while (!(linha.contains("ENDM"))) {
                        // Remove os comentários da linha se houver
                        linha = linha.replaceAll(";.*", "");
                        // Procura por \ seguido de digito (sintaxe dos parametros)
                        p = Pattern.compile("\\\\d");
                        m = p.matcher(linha);
                        while (m.find()) {
                            String[] a;
                            a = m.group().split("\n");
                            for (int i = 0; i <= m.groupCount(); i++) {
                                // Adiciona todos parametros na lista
                                parametros.add(a[i]);
                            }
                        }
                        linha = bf.readLine();
                    }
                    // Para cada parametro
                    for (int i = 0; i < parametros.size() - 1; i++) {
                        //Percorre os outros parametros
                        for (int j = i + 1; j < parametros.size(); j++) {
                            // Se encontrar um parametro igual ao atual, remove a repeticao
                            if (parametros.get(i).equals(parametros.get(j))) {
                                parametros.remove(j);
                            }
                        }
                    }
                    // Depois de ter a quantidade certa de parametros, pode iniciar a macro com o nome
                    // e numero de parametros corretos
                    Macro novo = new Macro(nomeMacro, parametros.size());
                    // Volta para o inicio da definicao da macro
                    bf.reset();
                    linha = bf.readLine();
                    // Le as linhas da definicao da macro e adiciona nas instrucoes
                    while (!(linha.contains("ENDM"))) {
                        // Remove os comentários da linha se houver
                        linha = linha.replaceAll(";.*", "");
                        novo.addInstrucao(linha);
                        linha = bf.readLine();
                    }
                    // Adiciona na lista de definicoes
                    this.macros.add(novo);
                }
            }
            bf.close();
        } catch (FileNotFoundException e) {
            System.err.printf("Arquivo não encontrado: %s.\n", e.getMessage());
        } // FIM DA PRIMEIRA PASSADA

        // INICIO DA SEGUNDA PASSADA
        try {
            // Leitura do arquivo de entrada
            BufferedReader bf2 = new BufferedReader(new FileReader(arquivoEntrada + ".txt"));
            // Cria o arquivo temporario
            FileWriter arqSaida = new FileWriter("saida_" + arquivoEntrada + ".txt");

            // Cria uma string para ler cada linha
            String linha2;
            // Cria um vetor pra armazenar as instrucoes, registradores, labels e chamadas de macro
            String[] code2;
            int f;
            linha2 = bf2.readLine();

            while (linha2 != null) {
                // Remove os comentários da linha se houver
                linha2 = linha2.replaceAll(";.*", "");
                // Quebra a linha em cada tab
                code2 = linha2.split("\t");
                f = 0;
                // Se a linha contem a definicao de uma macro
                if (linha2.contains("MACRO")) {
                    // Pula todas as linhas até o final da macro
                    while (!(linha2.contains("ENDM"))) {
                        linha2 = bf2.readLine();
                    }
                    // E le a proxima linha
                    linha2 = bf2.readLine();
                } else {
                    // Testa se tem uma chamada de macro
                    for (Macro macro : macros) {
                        // Se for uma chama aos macros ja definidos
                        if (macro.getNome().equals(code2[0])) {
                            f = 1;
                            // Cria um novo macro
                            Macro nova;
                            nova = new Macro(macro.getNome(), macro.getNumParametros());
                            nova.setInstrucoes(macro.getInstrucoes());
                            // Adiciona a lista de chamadas
                            this.chamadas.add(nova);

                            // Adiciona os parametros da chamada
                            nova.addParametros(linha2);
                            // Substitui os parametros nas instrucoes
                            nova.substituiParametros();

                            // Pega as instrucoes atualizadas da macro expandida
                            ArrayList<String> expansao;
                            expansao = nova.getInstrucoes();

                            // Escreve no arquivo de saida
                            for (int i = 0; i < expansao.size(); i++) {
                                arqSaida.write(expansao.get(i) + "\n");
                            }
                        }
                    }
                    // Se a flag estiver em zero significa que nao teve chamada de macro
                    if (f == 0) {
                        arqSaida.write(linha2 + "\n");
                    }
                    // Le a proxima linha
                    linha2 = bf2.readLine();
                }
            }
            bf2.close();
            arqSaida.close();
        } catch (FileNotFoundException e) {
            System.err.printf("Arquivo não encontrado: %s.\n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }// FIM DA SEGUNDA PASSADA     
    }
}
