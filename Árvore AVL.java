public class ArvoreAVL extends Arvore_abstrata<NoAVL> {


    @Override
    public NoAVL criar_no(int chave) {
        return new NoAVL(chave);
    }

    public void inserir(int chave) {
        super.inserir(chave);
    }





    @Override
    public void remover(int chave) {
       raiz = remover(raiz, chave);


    }






    @Override
    private int altura(NoAVL No) {
        if (No.altura == null) {
            return 0;
        } else {
            return No.altura;
        }
    }

    @Override
    private int fatordeBalanceamento(NoAVL No) { // Cálculo do balanceamento da AVL
        if (No == null) {
            return 0;
        } else {
            return altura(No.esquerdo) - altura(No.direito);
        }
    }

    private void atualizarAltura(NoAVL No) {
        No.altura = 1 + Math.max(altura(No.esquerdo), altura(No.direito));
    }

    @Override
    public void balancear(NoAVL No) {
        // Vai até a raiz da árvore, ajustando alturas e aplicando rotações
        while (No != null) {
            atualizarAltura(No);
            int balanceado = fatordeBalanceamento(No);

        // CASO1
        if (balanceado > 1) { // desbalanceamento para a esquerda, logo gira para direita

            // Caso LL: subárvore esquerda mais pesada e chave à esquerda
            if (fatordeBalanceamento(No.esquerdo) >= 0) {
                No = rotacao_direita(No);
            }
            // Caso LR: filho esquerdo é pesado à direita
            else {
                No.esquerdo = rotacao_esquerda(No.esquerdo);
                No = rotacao_direita(No);
            }
        }

        // Caso2
        else if (balanceado < -1) {   // desbalanceamento para a direita, logo gira para esquerda

            // Caso RR: subárvore direita mais pesada e chave à direita
            if (fatordeBalanceamento(No.direito) <= 0) {
                No = rotacao_esquerda(No);
            }
            // Caso RL: filho direito é pesado à esquerda
            else {
                No.direito = rotacao_direita(No.direito);
                No = rotacao_esquerda(No);
            }
        }

        // Se for o topo da árvore, atualiza a raiz
        if (No.pai == null) {
            raiz = No;
        }

        // Sobe na árvore
        No = No.pai;
    }
}


    }
}
