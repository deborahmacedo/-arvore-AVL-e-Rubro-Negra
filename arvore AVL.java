//public class ArvoreAVL extends Arvore_abstrata<NoAVL> {
//
//    @Override
//    // criar nó
//    public NoAVL criar_no(int chave) {
//        return new NoAVL(chave);
//    }
//
//    // inserir novo nó
//    public NoAVL inserir(int chave) {
//        NoAVL novono = super.inserir(chave);
//        balancear(novono);
//        return novono;
//    }
//    // recursividade para remover
//    private NoAVL remover(NoAVL no, int chave){
//        if (no == null) {
//            return null; // nó não encontrado
//        }
//
//        if (chave < no.chave) {
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
//                NoAVL filho = null;
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
//                return filho; // retorna o filho como novo nó no lugar do removido
//
//            } else {
//                // caso com DOIS FILHOS
//                NoAVL sucessor = menorNo(no.direito);
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
//
//        // chama o balanceamento depois da remoção (para o nó atual e seus ancestrais)
//        NoAVL no_balanceado = balancearNo(no);
//
//        // se tiver rotação, o pai do novo nó precisa ser atualizado na recursão
//        return no_balanceado; // retorna o nó rebalanceado para a chamada anterior
//    }
//
//     private int altura(NoAVL no) {
//        if (no == null) { // se o nó não existe
//            return 0;
//        } else {
//            return no.altura;
//        }
//     }
//
//    // calcula altura do nó
//    private void novaAltura (NoAVL no) {
//        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito)); // retorna o maior valor entre a altura do filho esquerdo e a altura do filho direito.
//        //maior valor = caminho mais longo  a partir do nó, e adiciona mais 1, pois o nó pai tá um nível acima
//    }
//
// private int fatordeBalanceamento(NoAVL no) { // cálculo do balanceamento da AVL
//        if (no == null) {
//            return 0;
//        } else {
//            return altura(no.esquerdo) - altura(no.direito);
//        }
//    }
//
//    // balancear arvore
//    private NoAVL balancearNo(NoAVL no) {
//        if (no == null) return null; // se o nó for vazio, retorna vazio
//
//        novaAltura(no); // atualiza a altura antes de calcular o FB
//        // garatindo que a altura esta certa para ver se está desbalanceada e qual caso usar
//        int fb = fatordeBalanceamento(no);
//
//        // CASO LL
//        // fb>1 : o nó  está desbalanceado para a esquerda
//        if (fb > 1 && fatordeBalanceamento(no.esquerdo) >= 0) {
//            return rotacao_direita(no); //o filho esquerdo sobe e se torna o novo pai da subárvore
//        }
//
//        // CASO LR
//        // desbalanceamento na subárvore direita do filho esquerdo do nó desbalanceado
//        if (fb > 1 && fatordeBalanceamento(no.esquerdo) < 0) {
//            no.esquerdo = rotacao_esquerda(no.esquerdo);
//            // atualiza pai após rotação
//            if (no.esquerdo != null) no.esquerdo.pai = no;
//            return rotacao_direita(no);
//        }
//
//        // CASO RR
//        // desbalanceamento na subárvore direita do filho direito do nó desbalanceado
//        if (fb < -1 && fatordeBalanceamento(no.direito) <= 0) {
//            return rotacao_esquerda(no);
//        }
//
//        // CASO RL
//        // desbalanceamento na subárvore esquerda do filho direito do nó desbalanceado
//        if (fb < -1 && fatordeBalanceamento(no.direito) > 0) {
//            no.direito = rotacao_direita(no.direito);
//            // atualiza pai depois da rotação
//            if (no.direito != null) no.direito.pai = no;
//            return rotacao_esquerda(no);
//        }
//
//        return no; // retorna o nó agora balanceado
//    }
//
//    // chama a função balancearNo para balancear para inserir
//    @Override
//    public void balancear(NoAVL no) {
//        while (no != null) { // percorre enquanto o nó nao for vazio
//            NoAVL novo = balancearNo(no); // balanceia o nó atual
//
//            // atualiza a raiz se a rotação a alterou
//            if (novo.pai == null)
//                raiz = novo;
//            no = novo.pai; // sobe para o pai
//
//        }
//    }
//}
