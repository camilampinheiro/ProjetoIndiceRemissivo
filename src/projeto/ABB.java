package projeto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class ABB {
    private class NodoABB {
        String palavra;
        ListaEncadeada linhas;
        NodoABB esquerda, direita;

        public NodoABB(String palavra, int linha) {
            this.palavra = palavra;
            this.linhas = new ListaEncadeada();
            this.linhas.insereFinal(String.valueOf(linha));
            esquerda = direita = null;
        }
    }

    private NodoABB raiz;

    public ABB() {
        raiz = null;
    }

    public void inserir(String palavra, int linha) {
        palavra = normalizarPalavra(palavra);
        raiz = inserirRecursivo(raiz, palavra, linha);
    }

    private NodoABB inserirRecursivo(NodoABB raiz, String palavra, int linha) {
        if (raiz == null) {
            raiz = new NodoABB(palavra, linha);
            return raiz;
        }
        if (palavra.compareTo(raiz.palavra) < 0) {
            raiz.esquerda = inserirRecursivo(raiz.esquerda, palavra, linha);
        } else if (palavra.compareTo(raiz.palavra) > 0) {
            raiz.direita = inserirRecursivo(raiz.direita, palavra, linha);
        } else {
            raiz.linhas.insereFinal(String.valueOf(linha));
        }
        return raiz;
    }

    public static String normalizarPalavra(String palavra) {
        palavra = palavra.toLowerCase().trim();
        palavra = removerAcentuacao(palavra);
        if (palavra.endsWith("s")) {
            return palavra.substring(0, palavra.length() - 1);
        }
        return palavra;
    }

    public void imprimir(BufferedWriter writer) throws IOException {
        imprimirRecursivo(raiz, writer);
    }

    private void imprimirRecursivo(NodoABB raiz, BufferedWriter writer) throws IOException {
        if (raiz != null) {
            imprimirRecursivo(raiz.esquerda, writer);
            writer.write(raiz.palavra + ": ");
            Nodo atual = raiz.linhas.obterPrimeiro();
            boolean primeiro = true;
            while (atual != null) {
                if (!primeiro) {
                    writer.write(", ");
                }
                writer.write(atual.valor);
                primeiro = false;
                atual = atual.proximo;
            }
            writer.newLine();
            imprimirRecursivo(raiz.direita, writer);
        }
    }

    public static String removerAcentuacao(String palavra) {
        String normalized = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}
