public class ArvoreAVL extends Arvore_abstrata<NoAVL> {

    @Override
    // criar nó
    public NoAVL criar_no(int chave) {
        return new NoAVL(chave);
    }

    @Override
    public NoAVL balancearInsercao(NoAVL no) {
        return null;
    }

    @Override
    public NoAVL balancearRemocao(NoAVL no) {
        return null;
    }


    private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        } else {
            // NoAVL tem o campo altura
            return no.altura;
        }
    }

    private void novaAltura(NoAVL no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    private int fatordeBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        } else {
            return altura(no.esquerdo) - altura(no.direito);
        }
    }

    private NoAVL balancearNo(NoAVL no) {
        if (no == null) return null;

        novaAltura(no); // atualiza a altura do nó
        int fb = fatordeBalanceamento(no); // valcula o Fator de Balanceamento

        // casos de rotação

        // CASO LL
        if (fb > 1 && fatordeBalanceamento(no.esquerdo) >= 0) {
            return rotacao_direita(no);
        }

        // CASO LR
        if (fb > 1 && fatordeBalanceamento(no.esquerdo) < 0) {
            rotacao_esquerda(no.esquerdo);
            // o balanceamento do pai é tratado pelan rotacao_direita(no)
            // não precisa atualizar o pai aqui, pois será refeito na próxima rotação
            return rotacao_direita(no);
        }

        // CASO RR
        if (fb < -1 && fatordeBalanceamento(no.direito) <= 0) {
            return rotacao_esquerda(no);
        }

        // CASO RL
        if (fb < -1 && fatordeBalanceamento(no.direito) > 0) {
            no.direito = rotacao_direita(no.direito);
            // o balanceamento do pai é tratado pelo rotacao_esquerda(no)
            return rotacao_esquerda(no);
        }

        return no; // Já balanceado
    }

    //Percorre do nó desbalanceado/modificado até a raiz, balanceando cada ancestral
    //Retorna a nova raiz da sub-árvore que foi balanceada
     
    @Override
    public NoAVL balancear(NoAVL no) {
        NoAVL resultado = no; // começa com o próprio nó
        NoAVL pai = no.pai;

        // balanceia o nó atual (ponto de inserção/remoção) e sobe
        resultado = balancearNo(no);

        // se a rotação mudou o pai, atualiza 'no' para a nova sub-raiz
        no = resultado;
        pai = no.pai; // Atualiza o pai

        while (pai != null) {
            // no é a sub-raiz que acabou de ser balanceada

            // corrige o ponteiro do pai para apontar para o nó rebalanceado 
            if (pai.esquerdo == no) {
                pai.esquerdo = balancearNo(pai);
                no = pai.esquerdo;
            } else { // if (pai.direito == no)
                pai.direito = balancearNo(pai);
                no = pai.direito;
            }

            // atualiza a raiz da árvore se uma rotação no nível da raiz ocorreu
            if (no.pai == null && no != raiz) {
                raiz = no;
            }

            pai = no.pai; // Sobe para o próximo pai
        }

        // retorna a nova raiz da sub-árvore 
        return resultado;
    }
}
