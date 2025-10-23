public abstract class Arvore_abstrata <N extends No<N>> {
    public N raiz;

    public Arvore_abstrata() {
        this.raiz = null;
    }

    // Métodos abstratos para serem implementados nas subclasses
    public abstract N criar_no(int chave);
    public abstract void balancear(N no);

    // Método de inserção genérico
    public void  inserir(int chave) {
      N novo_no = criar_no(chave);
      
    if (raiz ==null) {
        raiz = novo_no;
        return;
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
            return;
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
    }

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
