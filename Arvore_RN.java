public class Arvore_RN extends Arvore_abstrata<NoRN>{

    @Override
    public NoRN criar_no(int chave) {
        return new NoRN(chave);
    }


    @Override
    public void balancearInsercao(NoRN no) {
        if (no.pai == null) {
            no.color = NoRN.PRETO;
            raiz = no;
            return;
        }

        while (no.pai != null && no.pai.color.equals(NoRN.VERMELHO)) {
            NoRN pai = no.pai;
            NoRN avo = pai.pai;

            // Se o avô for nulo, o pai é a raiz. A raiz deve ser PRETA.
            if (avo == null) {
                pai.color = NoRN.PRETO;
                break;
            }

            if (pai == avo.esquerdo) {
                NoRN tio = avo.direito;

                if (getCor(tio).equals(NoRN.VERMELHO)) {
                    // Caso 1: Tio é VERMELHO
                    pai.color = NoRN.PRETO;
                    setCor(tio, NoRN.PRETO); // Use setCor para segurança
                    avo.color = NoRN.VERMELHO;
                    no = avo;
                } else {
                    // Caso 2: Tio é PRETO (ou nulo) e 'no' é filho DIREITO
                    if (no == pai.direito) {
                        no = pai;
                        rotacao_esquerda(no);
                    }
                    // Caso 3: Tio é PRETO (ou nulo) e 'no' é filho ESQUERDO
                    no.pai.color = NoRN.PRETO;
                    no.pai.pai.color = NoRN.VERMELHO;
                    rotacao_direita(no.pai.pai);
                }
            } else {
                // lado direito
                NoRN tio = avo.esquerdo;

                if (getCor(tio).equals(NoRN.VERMELHO)) {
                    // Caso 1: Tio é VERMELHO
                    pai.color = NoRN.PRETO;
                    setCor(tio, NoRN.PRETO);
                    avo.color = NoRN.VERMELHO;
                    no = avo;
                } else {
                    // Caso 2: Tio é PRETO (ou nulo) e 'no' é filho ESQUERDO
                    if (no == pai.esquerdo) {
                        no = pai;
                        rotacao_direita(no);
                    }
                    // Caso 3: Tio é PRETO (ou nulo) e 'no' é filho DIREITO
                    no.pai.color = NoRN.PRETO;
                    no.pai.pai.color = NoRN.VERMELHO;
                    rotacao_esquerda(no.pai.pai);
                }
            }
        }

        if (this.raiz != null) {
            this.raiz.color = NoRN.PRETO;
        }
    }

    @Override
    public void remover(int chave) {
        NoRN z = buscar(chave);
        if (z == null) {
            return;
        }

        NoRN y = z;
        NoRN x;
        NoRN xPai;

        String yCorOriginal = getCor(y);

        if (z.esquerdo == null) {
            // Caso 1: z não tem filho esquerdo
            x = z.direito;
            xPai = z.pai;
            transplantar(z, z.direito);
        } else if (z.direito == null) {
            // Caso 2: z não tem filho direito
            x = z.esquerdo;
            xPai = z.pai; // Salva o pai
            transplantar(z, z.esquerdo);
        } else {
            // Caso 3:  tem dois filhos
            y = menorNo(z.direito);
            yCorOriginal = getCor(y);

            x = y.direito; // 'x' é o filho direito do sucessor

            if (x != null) {
                xPai = y; // Pai de x é y
            } else {
                xPai = y.pai; // Pai de x (null) é o pai de y
            }

            if (y.pai == z) {
                // 'y' é filho direto de 'z'
                if (x != null) {
                    x.pai = y; // (Se x não for null, seu pai é y)
                }
                xPai = y; // Pai de x é y (mesmo se x for null)
            } else {
                xPai = y.pai; // Pai de x é o pai de y
                transplantar(y, y.direito);
                y.direito = z.direito;
                y.direito.pai = y;
            }

            // Coloca 'y' no lugar de 'z'
            transplantar(z, y);
            y.esquerdo = z.esquerdo;
            y.esquerdo.pai = y;

            // y herda a cor de z
            y.color = z.color;
        }

        // a árvore pode estar desbalanceada.
        if (yCorOriginal.equals(NoRN.PRETO)) {
            balancearRemocao(x, xPai);
        }
    }


    @Override
    public void balancearRemocao(NoRN x, NoRN xPai) {

        NoRN w; // w é o "irmão" x

        while (x != raiz && getCor(x).equals(NoRN.PRETO)) {

            // CASO A: 'x' é um FILHO ESQUERDO
            // (Verificamos 'xPai' pois 'x' pode ser null)
            if (x == xPai.esquerdo) {
                w = xPai.direito; // 'w' é o irmão direito

                // Caso 1: Irmão 'w' é VERMELHO
                // (Transforma no Caso 2, 3 ou 4)
                if (getCor(w).equals(NoRN.VERMELHO)) {
                    setCor(w, NoRN.PRETO);
                    setCor(xPai, NoRN.VERMELHO);
                    rotacao_esquerda(xPai);
                    w = xPai.direito; // Atualiza 'w' para o novo irmão
                }

                // Caso 2: Irmão 'w' é PRETO e seus dois filhos são PRETOS
                if (getCor(w.esquerdo).equals(NoRN.PRETO) && getCor(w.direito).equals(NoRN.PRETO)) {
                    setCor(w, NoRN.VERMELHO);
                    x = xPai; // O "duplo-preto" sobe para o pai
                    xPai = x.pai; // Atualiza o xPai
                } else {
                    // Caso 3: Irmão 'w' é PRETO, filho esquerdo VERMELHO, filho direito PRETO
                    // (Transforma no Caso 4)
                    if (getCor(w.direito).equals(NoRN.PRETO)) {
                        setCor(w.esquerdo, NoRN.PRETO);
                        setCor(w, NoRN.VERMELHO);
                        rotacao_direita(w);
                        w = xPai.direito; // Atualiza 'w'
                    }

                    // Caso 4: Irmão 'w' é PRETO, filho direito VERMELHO
                    setCor(w, getCor(xPai));
                    setCor(xPai, NoRN.PRETO);
                    setCor(w.direito, NoRN.PRETO);
                    rotacao_esquerda(xPai);
                    x = raiz; // Termina o loop
                }
            }
            // CASO B: 'x' é um FILHO DIREITO (simétrico ao Caso A)
            else {
                w = xPai.esquerdo; // 'w' é o irmão esquerdo

                // Caso 1: Irmão 'w' é VERMELHO
                if (getCor(w).equals(NoRN.VERMELHO)) {
                    setCor(w, NoRN.PRETO);
                    setCor(xPai, NoRN.VERMELHO);
                    rotacao_direita(xPai);
                    w = xPai.esquerdo; // Atualiza 'w'
                }

                // Caso 2: Irmão 'w' é PRETO e seus dois filhos são PRETOS
                if (getCor(w.esquerdo).equals(NoRN.PRETO) && getCor(w.direito).equals(NoRN.PRETO)) {
                    setCor(w, NoRN.VERMELHO);
                    x = xPai; // O "duplo-preto" sobe
                    xPai = x.pai; // Atualiza o xPai
                } else {
                    // Caso 3: Irmão 'w' é PRETO, filho direito VERMELHO, filho esquerdo PRETO
                    if (getCor(w.esquerdo).equals(NoRN.PRETO)) {
                        setCor(w.direito, NoRN.PRETO);
                        setCor(w, NoRN.VERMELHO);
                        rotacao_esquerda(w);
                        w = xPai.esquerdo; // Atualiza 'w'
                    }

                    // Caso 4: Irmão 'w' é PRETO, filho esquerdo VERMELHO
                    setCor(w, getCor(xPai));
                    setCor(xPai, NoRN.PRETO);
                    setCor(w.esquerdo, NoRN.PRETO);
                    rotacao_direita(xPai);
                    x = raiz; // Termina o loop
                }
            }
        }

        // Garante que 'x' (que pode ser a raiz) seja PRETO
        setCor(x, NoRN.PRETO);
    }

    @Override
    protected String getNodeDetails(NoRN no) {
        String corLabel = (no.color.equals(NoRN.VERMELHO)) ? "(V)" : "(P)";

        return corLabel + " " + no.chave;
    }

    private NoRN menorNo(NoRN no) {
        while (no != null && no.esquerdo != null) {
            no = no.esquerdo;
        }
        return no;
    }

    private void transplantar(NoRN u, NoRN v) {
        if (u.pai == null) {
            this.raiz = v;
        } else if (u == u.pai.esquerdo) {
            u.pai.esquerdo = v;
        } else {
            u.pai.direito = v;
        }

        if (v != null) {
            v.pai = u.pai;
        }
    }

    private String getCor(NoRN no) {
        if (no == null) {
            return NoRN.PRETO;
        }
        return no.color;
    }

    private void setCor(NoRN no, String cor) {
        if (no != null) {
            no.color = cor;
        }
    }
}
