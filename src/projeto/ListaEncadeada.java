package projeto;

public class ListaEncadeada {
    private Nodo primeiro;
    private Nodo ultimo;
    private int tamanho;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public void insereFinal(String valor) {
        if (!contem(valor)) {
            String valorNormalizado = ABB.removerAcentuacao(valor);
            Nodo novoNodo = new Nodo(valorNormalizado);
            if (ultimo == null) {
                primeiro = novoNodo;
                ultimo = novoNodo;
            } else {
                ultimo.proximo = novoNodo;
                ultimo = novoNodo;
            }
            tamanho++;
        }
    }

    public boolean contem(String valor) {
        Nodo atual = primeiro;
        while (atual != null) {
            if (atual.valor.equals(valor)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int tamanho() {
        return tamanho;
    }

    public Nodo obterPrimeiro() {
        return primeiro;
    }

    public String obter(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        Nodo atual = primeiro;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }
        return atual.valor;
    }
}
