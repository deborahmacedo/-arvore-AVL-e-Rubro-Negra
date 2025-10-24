public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    public abstract N criar_no(int chave);
    public abstract N balancearInsercao(N no);
    public abstract N balancearRemocao(N no);


    // inserção
    public N inserir(int chave) { // mudei o retorno para N, o nó balanceado
        N novo_no = criar_no(chave);

        if (raiz ==null) {
            raiz = novo_no;
            return raiz; // retorna a nova raiz
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

        // BALANCEAMENTO DA INSERÇÃO
        // chama o método de balanceamento após a inserção e atualizar a raiz
        N no_balanceado = balancearInsercao(novo_no);

        // Se a rotação afetou a raiz, atualiza
        if (raiz.pai != null) {
            raiz = raiz.pai;
        }
        // retorna a nova raiz balanceada
        return no_balanceado;
    }


    // REMOÇÃO GENÉRICA

    // encontrar o nó de menor valor (corrigido para tipo genérico N)
    private N menorNo(N no) {
        while (no.esquerdo != null){
            no = no.esquerdo;
        }
        return no;
    }

    //  iniciar a remoção
    public void remover(int chave) {
        // A raiz é atualizada com o resultado da remoção recursiva
        this.raiz = remover(this.raiz, chave);
        // Garante que se a árvore ficar vazia (raiz == null), o pai da raiz continua null (não é estritamente necessário, mas boa prática)
        if (this.raiz != null) {
            this.raiz.pai = null;
        }
    }


    // recursivo de remoção
    private N remover(N no, int chave) {
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

                N filho = null; //

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


                // O balanceamento ocorrerá na volta da recursão, para o pai do nó removido.
                return filho; // retorna o filho como novo nó no lugar do removido

            } else {
                // caso com DOIS FILHOS
                N sucessor = menorNo(no.direito);
                no.chave = sucessor.chave; // substitui pelo sucessor

                // remove o sucessor e atualiza a subárvore direita do nó atual (que mudou de chave)
                no.direito = remover(no.direito, sucessor.chave);

                if (no.direito != null) {
                    no.direito.pai = no;
                }
            }
        }

        // CHAMA O BALANCEAMENTO APÓS A REMOÇÃO (para o nó atual e seus ancestrais)
        // o método balancear retorna o nó que se torna a nova raiz dessa sub-árvore
        return balancearRemocao(no);
    }



    public N buscar(int chave) {
        // ... (código inalterado)
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

    // chama a função balancearNo para balancear para inserir
    public abstract void balancear(NoAVL no);
}
