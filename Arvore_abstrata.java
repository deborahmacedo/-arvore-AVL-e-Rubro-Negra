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
    }
