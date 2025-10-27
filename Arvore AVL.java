/**
 * Classe ArvoreAVL.
 * * Herda de Arvore_abstrata e implementa a lógica de auto-balanceamento
 * específica da AVL, baseada no Fator de Balanceamento (FB) e alturas.
 * * NÃO implementa 'inserir', 'rotacao_esquerda', 'rotacao_direita', 'buscar', 'printar',
 * pois estes são herdados da classe-pai.
 */
public class ArvoreAVL extends Arvore_abstrata<NoAVL> {

    // ========================================================================
    // MÉTODOS ABSTRATOS IMPLEMENTADOS
    // ========================================================================

    /**
     * Cria um novo nó do tipo NoAVL.
     * (Implementação do método abstrato)
     */
    @Override
    public NoAVL criar_no(int chave) {
        return new NoAVL(chave);
    }

    /**
     * Inicia o processo de balanceamento após uma INSERÇÃO.
     * (Implementação do método abstrato)
     * * @param no O nó recém-inserido.
     */
    @Override
    public void balancearInsercao(NoAVL no) {
        // O balanceamento de inserção começa no nó inserido e sobe
        rebalancearParaCima(no);
    }

    /**
     * Inicia o processo de balanceamento após uma REMOÇÃO.
     * (Implementação do método abstrato)
     * * @param noSubstituto O nó que substituiu o nó fisicamente removido (pode ser null).
     * @param pai O pai do nó fisicamente removido, de onde o rebalanceamento começa.
     */
    @Override
    public void balancearRemocao(NoAVL noSubstituto, NoAVL pai) {
        // O balanceamento de remoção começa no PAI do nó que foi
        // fisicamente removido e sobe.
        rebalancearParaCima(pai);
    }

    /**
     * Remove um nó com a chave especificada e inicia o rebalanceamento.
     * (Implementação do método abstrato)
     */
    @Override
    public void remover(int chave) {
        NoAVL noRemover = buscar(chave); // Usa o 'buscar' da classe-pai
        if (noRemover == null) {
            System.out.println("Chave: " + chave + " não encontrada para remoção.");
            return;
        }

        NoAVL pai;
        NoAVL noSubstituto;

        // --- Lógica de Remoção Padrão (BST) ---

        // CASO 1 ou 2: Nó tem 0 ou 1 filho
        if (noRemover.esquerdo == null || noRemover.direito == null) {
            pai = noRemover.pai;
            noSubstituto = (noRemover.esquerdo != null) ? noRemover.esquerdo : noRemover.direito;

            // Se 'noRemover' é a raiz
            if (pai == null) {
                this.raiz = noSubstituto;
            } else {
                // Liga o pai ao substituto
                if (noRemover == pai.esquerdo) {
                    pai.esquerdo = noSubstituto;
                } else {
                    pai.direito = noSubstituto;
                }
            }

            // Liga o substituto ao pai
            if (noSubstituto != null) {
                noSubstituto.pai = pai;
            }

            // Inicia o balanceamento a partir do PAI do nó removido
            balancearRemocao(noSubstituto, pai);

        }
        // CASO 3: Nó tem 2 filhos
        else {
            // Encontra o sucessor (menor nó da subárvore direita)
            NoAVL sucessor = menorNo(noRemover.direito);

            // O 'pai' de onde começará o balanceamento é o pai do sucessor
            pai = sucessor.pai;
            // O 'substituto' do sucessor é o filho direito do sucessor
            noSubstituto = sucessor.direito;

            // Copia a chave do sucessor para o nó que queríamos remover
            noRemover.chave = sucessor.chave;

            // Remove o sucessor (que tem 0 ou 1 filho à direita)
            if (sucessor == pai.esquerdo) {
                pai.esquerdo = noSubstituto;
            } else {
                pai.direito = noSubstituto;
            }

            if (noSubstituto != null) {
                noSubstituto.pai = pai;
            }

            // Inicia o balanceamento a partir do PAI do sucessor
            balancearRemocao(noSubstituto, pai);
        }
    }


    // ========================================================================
    // MÉTODOS AUXILIARES (LÓGICA AVL)
    // ========================================================================

    // Retorna a altura de um nó. Um nó nulo tem altura 0.
    private int altura(NoAVL no) {
        if (no == null) {
            return 0; // Consistente com altura = 1 para novas folhas
        }
        return no.altura;
    }

    // Recalcula e atualiza a altura de um nó baseado na altura dos seus filhos.
    private void novaAltura(NoAVL no) {
        if (no != null) {
            no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
        }
    }

    // Calcula o Fator de Balanceamento (FB) de um nó.
    private int fatordeBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerdo) - altura(no.direito);
    }

    /**
     * Loop principal de rebalanceamento. Sobe do nó 'no' até a raiz,
     * verificando e corrigindo o balanceamento de cada ancestral.
     * * @param no Nó onde o rebalanceamento começa.
     */
    private void rebalancearParaCima(NoAVL no) {
        NoAVL noAtual = no;

        while (noAtual != null) {
            // Armazena o pai ANTES de qualquer rotação,
            // pois a rotação pode mudar 'noAtual.pai'
            NoAVL pai = noAtual.pai;

            // 1. Atualiza a altura do nó atual
            novaAltura(noAtual);

            // 2. Verifica o balanceamento e rotaciona se necessário
            balancearNo(noAtual);

            // 3. Sobe para o pai original
            noAtual = pai;
        }
    }

    /**
     * Verifica o Fator de Balanceamento de um ÚNICO nó e aplica as
     * rotações necessárias (LL, RR, LR, RL).
     * * Este método chama as rotações (void) da classe-pai e, em seguida,
     * ATUALIZA AS ALTURAS dos nós envolvidos.
     * * @param no O nó a ser verificado e balanceado.
     */
    private void balancearNo(NoAVL no) {
        int fb = fatordeBalanceamento(no);

        // CASO LL (Esquerda-Esquerda)
        if (fb > 1 && fatordeBalanceamento(no.esquerdo) >= 0) {
            NoAVL x = no.esquerdo; // 'x' será o novo pai
            rotacao_direita(no); // Chama rotação (void) da classe-pai

            // Atualiza alturas (filho 'no' primeiro, depois novo pai 'x')
            novaAltura(no);
            novaAltura(x);
        }

        // CASO RR (Direita-Direita)
        else if (fb < -1 && fatordeBalanceamento(no.direito) <= 0) {
            NoAVL y = no.direito; // 'y' será o novo pai
            rotacao_esquerda(no); // Chama rotação (void) da classe-pai

            // Atualiza alturas (filho 'no' primeiro, depois novo pai 'y')
            novaAltura(no);
            novaAltura(y);
        }

        // CASO LR (Esquerda-Direita)
        else if (fb > 1 && fatordeBalanceamento(no.esquerdo) < 0) {
            NoAVL y = no.esquerdo;
            NoAVL z = y.direito;

            rotacao_esquerda(y); // Rotação interna
            novaAltura(y); // Atualiza y
            novaAltura(z); // Atualiza z (novo pai de y)

            rotacao_direita(no); // Rotação externa
            novaAltura(no); // Atualiza no
            novaAltura(z);  // Atualiza z (novo pai de no)
        }

        // CASO RL (Direita-Esquerda)
        else if (fb < -1 && fatordeBalanceamento(no.direito) > 0) {
            NoAVL y = no.direito;
            NoAVL z = y.esquerdo;

            rotacao_direita(y); // Rotação interna
            novaAltura(y); // Atualiza y
            novaAltura(z); // Atualiza z (novo pai de y)

            rotacao_esquerda(no); // Rotação externa
            novaAltura(no); // Atualiza no
            novaAltura(z);  // Atualiza z (novo pai de no)
        }
    }

    /**
     * Encontra o nó com o menor valor na subárvore.
     * (Método auxiliar para a remoção)
     */
    private NoAVL menorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual != null && atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }
}

