public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    // Métodos abstratos para serem implementados nas subclasses
    public abstract N criar_no(int chave);
    public abstract void balancearInsercao(N no);
    public abstract void balancearRemocao(N no);

    // Método de inserção genérico
    public void  inserir(int chave) {
        N novo_no = criar_no(chave);

        if (raiz == null) {
            raiz = novo_no;
        }
        else{
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

        novo_no.pai = pai;
        if (chave < pai.chave) {
            pai.esquerdo = novo_no;
        } else {
            pai.direito = novo_no;
        }
    }
    // Chamar o método de balanceamento após a inserção
    balancearInsercao(novo_no);
    }

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
    public N rotacao_esquerda(N x) {
        N y = x.direito;   
        N T2 = y.esquerdo; 

        // Faz a rotação
        y.esquerdo = x;
        x.direito = T2;

        if (T2 != null) {
            T2.pai = x;
        }

        y.pai = x.pai;

         // Atualiza os pais
        if (x.pai == null) {
            this.raiz = y; //atualiza a raiz
        }
            else{
            if (x == x.pai.esquerdo)
                x.pai.esquerdo = y;
            else
                x.pai.direito = y;
        }

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
        x.pai = y.pai;
        if (y.pai == null) {
            this.raiz = x;
        }
        else{
            if (y == y.pai.esquerdo)
                y.pai.esquerdo = x;
            else
                y.pai.direito = x;
        }


        y.pai = x;

        // Retorna a nova raiz dessa subárvore
        return x;
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
