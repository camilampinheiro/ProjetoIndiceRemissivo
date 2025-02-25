package projeto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeradorIndiceRemissivo {
    public static void gerarArquivo(String caminho, ABB arvore) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));
        arvore.imprimir(writer);
        writer.close();
    }
}
