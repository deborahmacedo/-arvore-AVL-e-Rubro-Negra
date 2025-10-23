public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    // Métodos abstratos para serem implementados nas subclasses
    public abstract N criar_no(int chave);
    public abstract void balancear(N no);

    // Método de inserção genérico
    public NoAVL inserir(int chave) {
      N novo_no = criar_no(chave);
      
    if (raiz ==null) {
        raiz = novo_no;
        return null;
    }
    N atual = raiz;
    N pai = null;
    while (atual != null) {
        pai = atual;
         if(chave < atual.chave) {
            atual = atual.esquerdo;
         } else if (chave > atual.chave) {
            atual = atual.direito;
         } else {
            System.out.println("Chave já existe na árvore.");
             return null;
         }
    }

    novo_no.pai = pai;
    if (chave < pai.chave) {
        pai.esquerdo = novo_no;
    } else {
        pai.direito = novo_no;
    }

    // Chamar o método de balanceamento após a inserção
    balancear(novo_no);
        return null;
    }

    // encontrar o nó de menor valor
    private NoAVL menorNo(NoAVL no) {
        while (no.esquerdo != null){
            no = no.esquerdo;
        }
        return no;
    }
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

        // se tiver rotação, o pai do novo nó precisa ser atualizado na recursão
        return no_balanceado; // retorna o nó rebalanceado para a chamada anterior
    }

    // chama o balanceamento depois da remoção (para o nó atual e seus ancestrais)
        NoAVL no_balanceado = balancear(No);

    public N buscar(int chave) {
        N atual = raiz;
        while (atual != null) {
            if (chave > atual.chave) {
                atual = atual.direito;
            } else if (chave < atual.chave) {
                atual = atual.esquerdo;
            } else {
                return atual;
            }
        }
            return null;
        }

    // Rotação à esquerda
    public N rotacao_esquerda(N x) {
        N y = x.direito;   
        N T2 = y.esquerdo; 

        // Faz a rotação
        y.esquerdo = x;
        x.direito = T2;

        if (T2 != null) {
            T2.pai = x;
        }

         // Atualiza os pais
        if (x.pai != null) {
            if (x == x.pai.esquerdo)
                x.pai.esquerdo = y;
            else
                x.pai.direito = y;
        }

        y.pai = x.pai;
        x.pai = y;

        // Retorna a nova raiz dessa subárvore
        return y;
    }

    // Rotação à direita
    public N rotacao_direita(N y) {
        N x = y.esquerdo;
        N T2 = x.direito;  

        // Faz a rotação
        x.direito = y;
        y.esquerdo = T2;

        if (T2 != null) {
            T2.pai = y;
        }

         // Atualiza os pais
        if (y.pai != null) {
            if (y == y.pai.esquerdo)
                y.pai.esquerdo = x;
            else
                y.pai.direito = x;
        }

        x.pai = y.pai;
        y.pai = x;

        // Retorna a nova raiz dessa subárvore
        return x;
}

    }
