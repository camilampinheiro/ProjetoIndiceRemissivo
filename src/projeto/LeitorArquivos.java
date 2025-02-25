package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivos {

    public static ListaEncadeada lerArquivoTexto(String caminho) throws IOException {
        ListaEncadeada linhasTexto = new ListaEncadeada();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhasTexto.insereFinal(linha.toLowerCase().trim());
            }
        }
        return linhasTexto;
    }

    public static TabelaDispersao lerArquivoPalavrasChave(String caminho) throws IOException {
        TabelaDispersao palavrasChave = new TabelaDispersao(100);
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                palavrasChave.inserir(linha.toLowerCase().trim());
            }
        }
        return palavrasChave;
    }
}
