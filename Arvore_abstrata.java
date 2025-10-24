public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    // Métodos abstratos para serem implementados nas subclasses
    public abstract N criar_no(int chave);
    public abstract void balancearInsercao(N no);
    public abstract void balancearRemocao(N x, N xPai);
    public abstract void remover(int chave);

    // Método de inserção genérico
    public void inserir(int chave) {
        N novo_no = criar_no(chave);

        if (raiz == null) {
            raiz = novo_no;
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
                    return;
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

//    //REMOÇÃO SEGUNDA TENTATIVA
//
//    // ===============================================
//    // REMOÇÃO CORRIGIDA
//    // ===============================================
//
//    /**
//     * Método PÚBLICO para iniciar a remoção.
//     * @param valor A chave a ser removida.
//     */
//    public void remover(int valor) {
//        // A raiz é atualizada com o resultado da remoção recursiva
//        this.raiz = removerRec(this.raiz, valor);
//
//        // Garante que o pai da nova raiz é nulo
//        if (this.raiz != null) {
//            this.raiz.pai = null;
//        }
//    }
//
//
//    /**
//     * Método auxiliar recursivo para encontrar e remover o nó.
//     * CORRIGIDO para usar o tipo 'N' e chamar o balanceamento.
//     *
//     * @param no O nó atual (raiz da subárvore).
//     * @param valor O valor (chave) a ser removido.
//     * @return O nó que deve substituir 'no' como raiz da subárvore.
//     */
//    // CORREÇÃO 1: Assinatura deve usar 'N', não 'No'
//    private N removerRec(N no, int valor) {
//        if (no == null) {
//            return null; // nó não encontrado
//        }
//
//        int comparacao = Integer.compare(valor, no.chave);
//
//        if (comparacao < 0) {
//            no.esquerdo = removerRec(no.esquerdo, valor);
//            if (no.esquerdo != null) {
//                no.esquerdo.pai = no;
//            }
//        } else if (comparacao > 0) {
//            no.direito = removerRec(no.direito, valor);
//            if (no.direito != null) {
//                no.direito.pai = no;
//            }
//        } else {
//            // --- Nó encontrado (comparacao == 0) ---
//
//            // --- CASO 1: Nó sem filhos (folha) ---
//            if (no.esquerdo == null && no.direito == null) {
//                return null; // O nó é removido
//            }
//
//            // --- CASO 2: Nó com apenas um filho ---
//            if (no.esquerdo == null) {
//                // CORREÇÃO 2: Verifique se o filho não é null
//                // antes de acessar seu campo 'pai'
//                if(no.direito != null) {
//                    no.direito.pai = no.pai;
//                }
//                return no.direito;
//            }
//            if (no.direito == null) {
//                // CORREÇÃO 2: Verifique se o filho não é null
//                if(no.esquerdo != null) {
//                    no.esquerdo.pai = no.pai;
//                }
//                return no.esquerdo;
//            }
//
//            // --- CASO 3: Nó com dois filhos ---
//            // CORREÇÃO 3: Assinatura de encontrarMinimo também deve usar N
//            N sucessor = encontrarMinimo(no.direito);
//            no.chave = sucessor.chave; // Copia a chave
//
//            // Remove o sucessor da subárvore direita
//            no.direito = removerRec(no.direito, sucessor.chave);
//            if (no.direito != null) {
//                no.direito.pai = no;
//            }
//        }
//
//        // CORREÇÃO 4: CHAMA O BALANCEAMENTO
//        // Após a remoção (ou na volta da recursão), o 'no' atual
//        // (ou seu substituto) pode precisar de balanceamento.
//        // O método 'balancearRemocao' deve retornar a nova
//        // raiz da subárvore (pós-rotações).
//        // NOTA: O seu balancearRemocao é 'void', então ele deve
//        // modificar a árvore por referência.
//
//        balancearRemocao(no); // <--- PARTE CRÍTICA
//        return no; // Retorna o nó (potencialmente rotacionado)
//    }
//
//    /**
//     * Método auxiliar para encontrar o nó com o menor valor.
//     * CORRIGIDO para usar o tipo 'N'.
//     */
//    // CORREÇÃO 5: Assinatura deve usar 'N', não 'No'
//    private N encontrarMinimo(N no) {
//        N atual = no;
//        // Sua classe usa 'null' como terminador
//        while (atual != null && atual.esquerdo != null) {
//            atual = atual.esquerdo;
//        }
//        return atual;
//    }



//
//    // REMOÇÃO GENÉRICA
//
//    // encontrar o nó de menor valor (corrigido para tipo genérico N)
//    private N menorNo(N no) {
//        while (no.esquerdo != null){
//            no = no.esquerdo;
//        }
//        return no;
//    }
//
//    //  iniciar a remoção
//    public void remover(int chave) {
//        // A raiz é atualizada com o resultado da remoção recursiva
//        this.raiz = remover(this.raiz, chave);
//        // Garante que se a árvore ficar vazia (raiz == null), o pai da raiz continua null (não é estritamente necessário, mas boa prática)
//        if (this.raiz != null) {
//            this.raiz.pai = null;
//        }
//
//    }
//
//
//    // recursivo de remoção
//    private N remover(N no, int chave) {
//        if (no == null) {
//            return null; // nó não encontrado
//        }
//
//        if (chave < no.chave) {
//
//            no.esquerdo = remover(no.esquerdo, chave);
//            if (no.esquerdo != null) {
//                no.esquerdo.pai = no; // atualiza o pai
//            }
//
//        } else if (chave > no.chave) {
//            no.direito = remover(no.direito, chave);
//            if (no.direito != null) {
//                no.direito.pai = no; // atualiza o pai
//            }
//
//        } else {
//            // se encontrou o nó
//            if (no.esquerdo == null || no.direito == null) { // caso 0 ou 1 filho
//
//                N filho = null; //
//
//                // decide qual filho, caso diferente de vazio, deve substituir o nó removido
//                if (no.esquerdo != null) {
//                    filho = no.esquerdo;
//                } else if (no.direito != null) {
//                    filho = no.direito;
//                }
//
//                // atualiza o ponteiro do pai do filho, caso diferente de vazio
//                if (filho != null) {
//                    filho.pai = no.pai;
//                }
//
//
//                // O balanceamento ocorrerá na volta da recursão, para o pai do nó removido.
//                return filho; // retorna o filho como novo nó no lugar do removido
//
//            } else {
//                // caso com DOIS FILHOS
//                N sucessor = menorNo(no.direito);
//                no.chave = sucessor.chave; // substitui pelo sucessor
//
//                // remove o sucessor e atualiza a subárvore direita do nó atual (que mudou de chave)
//                no.direito = remover(no.direito, sucessor.chave);
//
//                if (no.direito != null) {
//                    no.direito.pai = no;
//                }
//            }
//        }
//
//        // CHAMA O BALANCEAMENTO APÓS A REMOÇÃO (para o nó atual e seus ancestrais)
//        // o método balancear retorna o nó que se torna a nova raiz dessa sub-árvore
//        //return balancearRemocao(no);
//        return no;
//    }
//


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


    public void printar() {
        // Verifica se a raiz é 'null', pois sua implementação usa 'null'
        if (this.raiz == null) {
            System.out.println("Árvore está vazia.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        // A raiz não tem "padding" (preenchimento) nem "pointer" (ponteiro)
        printarHelper(sb, "", "", this.raiz);

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
