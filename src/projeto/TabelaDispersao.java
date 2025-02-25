package projeto; 

import java.util.Objects; 

public class TabelaDispersao { 
    private Nodo[] tabela; 
    private int tamanho; 

    public TabelaDispersao(int capacidade) { 
        tabela = new Nodo[capacidade]; 
        tamanho = 0; 
    }

    private int funcaoHashDiv(String chave) { 
        int hashCode = chave.hashCode(); 
        return Math.abs(hashCode) % tabela.length; 
    }

    public void inserir(String palavra) { 
        palavra = ABB.normalizarPalavra(palavra); 
        int pos = funcaoHashDiv(palavra); 
        Nodo entrada = tabela[pos];
        
        while (entrada != null && !entrada.valor.equals(palavra)) { 
            entrada = entrada.proximo; 
        }

        if (entrada == null) { 
            entrada = new Nodo(palavra); 
            entrada.proximo = tabela[pos]; 
            tabela[pos] = entrada; 
            tamanho++; 
        }
    }

    public boolean contem(String palavra) { 
        palavra = ABB.normalizarPalavra(palavra); 
        int pos = funcaoHashDiv(palavra); 
        if(tabela[pos] == null) { 
            return false; 
        } else { 
            if(Objects.equals(tabela[pos].valor, palavra)) { 
                return true; 
            } else { 
                Nodo atual = tabela[pos]; 
                while (atual != null){ 
                    if(Objects.equals(atual.valor, palavra)){ 
                        return true; 
                    }
                    atual = atual.proximo; 
                } 
            }
        }
        return Objects.equals(tabela[pos].valor, palavra); 
    }
}
