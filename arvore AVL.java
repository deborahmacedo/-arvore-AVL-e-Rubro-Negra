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
    // recursividade para remover 
    private NoAVL remover(NoAVL no, int chave){
        if (no == null) {
            return null; // nó não encontrado
        }

        if (chave < no.chave) {
            no.esquerdo = remover(no.esquerdo, chave);
            if (no.esquerdo != null) {
                no.esquerdo.pai = no; // atualiza o pai
            }

        } else if (chave > no.chave) {
            no.direito = remover(no.direito, chave);
            if (no.direito != null) {
                no.direito.pai = no; // atualiza o pai
            }

        } else {
            // se encontrou o nó
            if (no.esquerdo == null || no.direito == null) { // caso 0 ou 1 filho

                NoAVL filho = null;

                // decide qual filho, caso diferente de vazio, deve substituir o nó removido
                if (no.esquerdo != null) {
                    filho = no.esquerdo;
                } else if (no.direito != null) {
                    filho = no.direito;
                }

                // atualiza o ponteiro do pai do filho, caso diferente de vazio
                if (filho != null) {
                    filho.pai = no.pai;
                }

                return filho; // retorna o filho como novo nó no lugar do removido

            } else {
                // caso com DOIS FILHOS
                NoAVL sucessor = menorNo(no.direito);
                no.chave = sucessor.chave; // substitui pelo sucessor

                // remove o sucessor e atualiza a subárvore direita do nó atual (que mudou de chave)
                no.direito = remover(no.direito, sucessor.chave);

                if (no.direito != null) {
                    no.direito.pai = no;
                }
            }
        }


        // chama o balanceamento depois da remoção (para o nó atual e seus ancestrais)
        NoAVL no_balanceado = balancearNo(no);

        // se tiver rotação, o pai do novo nó precisa ser atualizado na recursão
        return no_balanceado; // retorna o nó rebalanceado para a chamada anterior
    }

     private int altura(NoAVL no) {
        if (no == null) {
            return 0;
        } else {
            return no.altura;
        }
     }

    private void novaAltura (NoAVL no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }
