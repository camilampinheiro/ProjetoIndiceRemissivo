import java.io.IOException; 
import projeto.*; 

public class App { 
    public static void main(String[] args) { 
        String arquivoPalavrasChave = "C:\\Users\\camil\\OneDrive\\Área de Trabalho\\TrabalhoTulio\\TrabalhoTulio\\TrabalhoTulio\\arquivos\\palavras-chave.txt"; 
        String arquivoTexto = "C:\\Users\\camil\\OneDrive\\Área de Trabalho\\TrabalhoTulio\\TrabalhoTulio\\TrabalhoTulio\\arquivos\\texto.txt"; 
        String arquivoSaida = "C:\\Users\\camil\\OneDrive\\Área de Trabalho\\TrabalhoTulio\\TrabalhoTulio\\TrabalhoTulio\\arquivos\\saida.txt"; 

        try { 
            TabelaDispersao palavrasChave = LeitorArquivos.lerArquivoPalavrasChave(arquivoPalavrasChave); 
            ListaEncadeada texto = LeitorArquivos.lerArquivoTexto(arquivoTexto); 

            ABB arvore = new ABB(); 

            int linhaNumero = 1; 
            for (int i = 0; i < texto.tamanho(); i++) { 
                String linha = texto.obter(i).toLowerCase(); 
                String[] palavras = linha.split("[^\\p{L}'\"]+"); 
                for (String palavra : palavras) { 
                    palavra = ABB.normalizarPalavra(palavra); 
                    if (palavrasChave.contem(palavra)) { 
                        arvore.inserir(palavra, linhaNumero); 
                    }
                }
                linhaNumero++; 
            }
            
            GeradorIndiceRemissivo.gerarArquivo(arquivoSaida, arvore); 

        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }
}
