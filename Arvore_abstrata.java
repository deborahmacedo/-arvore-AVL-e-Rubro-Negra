public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    // Métodos abstratos para serem implementados nas subclasses
    public abstract N criar_no(int chave);
    public abstract void balancearInsercao(N no);
    public abstract void balancearRemocao(N no);

    // Método de inserção genérico (CORRIGIDO)
    public void inserir(int chave) {
        N novo_no = criar_no(chave);

        if (raiz == null) {
            raiz = novo_no;
            // NÃO HÁ 'return' AQUI.
        } else {
            N atual = raiz;
            N pai = null;
            while (atual != null) {
                pai = atual;
                if (chave < atual.chave) {
                    atual = atual.esquerdo;
                } else if (chave > atual.chave) {
                    atual = atual.direito;
                } else {
                    System.out.println("Chave já existe na árvore.");
                    return; // Chave duplicada, sai.
                }
            }

            // Liga o nó ao pai
            novo_no.pai = pai;
            if (chave < pai.chave) {
                pai.esquerdo = novo_no;
            } else {
                pai.direito = novo_no;
            }
        }

        balancearInsercao(novo_no);
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
       // return no_balanceado; // retorna o nó rebalanceado para a chamada anterior
        return no; //MUDAR DE VOLTA DEPOIS
    }

    // chama o balanceamento depois da remoção (para o nó atual e seus ancestrais)
        //NoAVL no_balanceado = balancear(No);

    public N buscar(int chave) {
        System.out.println("\n Procurando pela chave: " + chave);
        N atual = raiz;
        while (atual != null) {
            if (chave > atual.chave) {
                atual = atual.direito;
            } else if (chave < atual.chave) {
                atual = atual.esquerdo;
            } else {
                System.out.println("Chave: " + chave + " ENCONTRADA!");
                return atual;
            }
        }
        System.out.println("Chave: " + chave + " NÃO ENCONTRADA.");
            return null;
        }

    // Rotação à esquerda
//    public N rotacao_esquerda(N x) {
//        N y = x.direito;
//        N T2 = y.esquerdo;
//
//        // Faz a rotação
//        y.esquerdo = x;
//        x.direito = T2;
//
//        if (T2 != null) {
//            T2.pai = x;
//        }
//
//         // Atualiza os pais
//        if (x.pai != null) {
//            if (x == x.pai.esquerdo)
//                x.pai.esquerdo = y;
//            else
//                x.pai.direito = y;
//        }
//
//        y.pai = x.pai;
//        x.pai = y;
//
//        // Retorna a nova raiz dessa subárvore
//        return y;
//    }
//
//    // Rotação à direita
//    public N rotacao_direita(N y) {
//        N x = y.esquerdo;
//        N T2 = x.direito;
//
//        // Faz a rotação
//        x.direito = y;
//        y.esquerdo = T2;
//
//        if (T2 != null) {
//            T2.pai = y;
//        }
//
//         // Atualiza os pais
//        if (y.pai != null) {
//            if (y == y.pai.esquerdo)
//                y.pai.esquerdo = x;
//            else
//                y.pai.direito = x;
//        }
//
//        x.pai = y.pai;
//        y.pai = x;
//
//        // Retorna a nova raiz dessa subárvore
//        return x;
//}

    // Em Arvore_abstrata

    // Rotação à esquerda (CORRIGIDA e como void)
    public void rotacao_esquerda(N x) {
        N y = x.direito;
        N T2 = y.esquerdo;

        // 1. Faz a rotação
        y.esquerdo = x;
        x.direito = T2;

        // 2. Atualiza o pai de T2 (se ele existir)
        if (T2 != null) {
            T2.pai = x;
        }

        // 3. Atualiza os pais de x e y (A PARTE CRÍTICA)
        y.pai = x.pai; // O pai de y se torna o antigo pai de x

        if (x.pai == null) {
            // Se x ERA a raiz, y AGORA é a raiz
            this.raiz = y;
        } else {
            // Se x NÃO era a raiz, liga o pai de x a y
            if (x == x.pai.esquerdo)
                x.pai.esquerdo = y;
            else
                x.pai.direito = y;
        }

        x.pai = y; // O pai de x agora é y
    }

    // Rotação à direita (CORRIGIDA e como void)
    public void rotacao_direita(N y) {
        N x = y.esquerdo;
        N T2 = x.direito;

        // 1. Faz a rotação
        x.direito = y;
        y.esquerdo = T2;

        // 2. Atualiza o pai de T2 (se ele existir)
        if (T2 != null) {
            T2.pai = y;
        }

        // 3. Atualiza os pais de x e y (A PARTE CRÍTICA)
        x.pai = y.pai; // O pai de x se torna o antigo pai de y

        if (y.pai == null) {
            // Se y ERA a raiz, x AGORA é a raiz
            this.raiz = x;
        } else {
            // Se y NÃO era a raiz, liga o pai de y a x
            if (y == y.pai.esquerdo)
                y.pai.esquerdo = x;
            else
                y.pai.direito = x;
        }

        y.pai = x; // O pai de y agora é x
    }
    // Dentro de Arvore_abstrata

    /**
     * Método público que inicia a impressão da árvore.
     */
    public void printar() {
        // Verifica se a raiz é 'null', pois sua implementação usa 'null'
        if (this.raiz == null) {
            System.out.println("Árvore está vazia.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        // Chama o método recursivo auxiliar para construir a string
        // A raiz não tem "padding" (preenchimento) nem "pointer" (ponteiro)
        printarHelper(sb, "", "", this.raiz);

        // Imprime o resultado final
        System.out.println(sb.toString());
    }

    /**
     * Método recursivo auxiliar (Pré-Ordem) que constrói a string da árvore.
     *
     * @param sb O StringBuilder para construir a string
     * @param padding O preenchimento ("|   " ou "    ") vindo dos pais
     * @param pointer O ponteiro ("|--" ou "`-- ") para o nó atual
     * @param no O nó atual
     */
    private void printarHelper(StringBuilder sb, String padding, String pointer, N no) {
        // Condição de parada: sua implementação usa 'null'
        if (no == null) {
            return;
        }

        // 1. Adiciona a linha do nó ATUAL
        sb.append(padding);
        sb.append(pointer);
        sb.append(getNodeDetails(no)); // <-- Pega os detalhes (chave, cor, etc.)
        sb.append("\n");

        // 2. Prepara o 'padding' (preenchimento) para os FILHOS
        // Se o nó atual é um galho (|--), seus filhos continuam o galho na vertical (|   ).
        // Se o nó atual é o último (`--), seus filhos recebem apenas espaço (    ).
        String novoPadding = padding + (pointer.equals("|-- ") ? "|   " : "    ");

        // 3. Define os ponteiros para os FILHOS

        // O filho da ESQUERDA:
        // - Se houver um filho direito, o esquerdo usa "|-- " (não é o último)
        // - Se NÃO houver um filho direito, o esquerdo usa "`-- " (é o último)
        String pointerEsq = (no.direito != null) ? "|-- " : "`-- ";

        // O filho da DIREITA:
        // - É sempre o último, então usa "`-- "
        String pointerDir = "`-- ";

        // 4. Chama a recursão (Nó, Esquerda, Direita)
        printarHelper(sb, novoPadding, pointerEsq, no.esquerdo);
        printarHelper(sb, novoPadding, pointerDir, no.direito);
    }

    /**
     * Método protegido que fornece os detalhes do nó.
     * As subclasses (como Arvore_RN) podem sobrescrever isso.
     * A implementação padrão mostra apenas a chave.
     */
    protected String getNodeDetails(N no) {
        // Converte a chave (int) para String
        return String.valueOf(no.chave);
    }

    }
