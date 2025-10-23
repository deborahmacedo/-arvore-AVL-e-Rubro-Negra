public class ArvoreAVL extends Arvore_abstrata<NoAVL> {

    @Override
    // criar nó
    public NoAVL criar_no(int chave) {
        return new NoAVL(chave);
    }

    // inserir novo nó
    public NoAVL inserir(int chave) {
        NoAVL novono = super.inserir(chave);
        balancear(novono);
        return novono;
    }

    private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        } else {
            return no.altura;
        }
    }

    private void novaAltura(NoAVL no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    private int fatordeBalanceamento(NoAVL no) { // cálculo do balanceamento da AVL
        if (no == null) {
            return 0;
        } else {
            return altura(no.esquerdo) - altura(no.direito);
        }
    }

    private NoAVL balancearNo(NoAVL no) {
        if (no == null) return null; // se o nó for vazio, retorna vazio

        novaAltura(no); // atualiza a altura antes de calcular o FB
        // garatindo que a altura esta certa para ver se está desbalanceada e qual caso usar

        int fb = fatordeBalanceamento(no);

        // CASO LL
        // fb>1 : o nó  está desbalanceado para a esquerda
        if (fb > 1 && fatordeBalanceamento(no.esquerdo) >= 0) {
            return rotacao_direita(no); //o filho esquerdo sobe e se torna o novo pai da subárvore
        }

        // CASO LR
        // desbalanceamento na subárvore direita do filho esquerdo do nó desbalanceado
        if (fb > 1 && fatordeBalanceamento(no.esquerdo) < 0) {
            no.esquerdo = rotacao_esquerda(no.esquerdo);
            // atualiza pai após rotação
            if (no.esquerdo != null) no.esquerdo.pai = no;
            return rotacao_direita(no);
        }

        // CASO RR
        // desbalanceamento na subárvore direita do filho direito do nó desbalanceado
        if (fb < -1 && fatordeBalanceamento(no.direito) <= 0) {
            return rotacao_esquerda(no);
        }

        // CASO RL
        // desbalanceamento na subárvore esquerda do filho direito do nó desbalanceado
        if (fb < -1 && fatordeBalanceamento(no.direito) > 0) {
            no.direito = rotacao_direita(no.direito);
            // Atualiza pai após rotação
            if (no.direito != null) no.direito.pai = no;
            return rotacao_esquerda(no);
        }

        return no; // agora balanceado
    }

    // chama a função balancearNo para balancear para inserir
    @Override
    public void balancear(NoAVL no) {
        while (no != null) { // percorre enquanto o nó nao for vazio
            NoAVL novo = balancearNo(no); // balanceia o nó atual

            // atualiza a raiz se a rotação a alterou
            if (novo.pai == null)
                raiz = novo;
            no = novo.pai; // sobe para o pai

        }
    }
}
