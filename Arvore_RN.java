public class Arvore_RN extends Arvore_abstrata<NoRN>{

    @Override
    public NoRN criar_no(int chave) {
        return new NoRN(chave);
    }

    @Override
    public void balancear(NoRN no) {
        if (no.pai == null) {
            no.color = NoRN.PRETO;
            raiz = no;
            return;
        }

        while (no.pai != null && no.pai.color.equals(NoRN.VERMELHO)) {
            NoRN pai = no.pai;
            NoRN avo = pai.pai;

            if (pai == avo.esquerdo) {
                NoRN tio = avo.direito;

                if (tio != null && tio.color.equals(NoRN.VERMELHO)) {
                    pai.color = NoRN.PRETO;
                    tio.color = NoRN.PRETO;
                    avo.color = NoRN.VERMELHO;
                    no = avo;
                } else {
                    if (no == pai.direito) {
                        no = pai;
                        rotacao_esquerda(no);
                    }
                    pai.color = NoRN.PRETO;
                    avo.color = NoRN.VERMELHO;
                    rotacao_direita(avo);
                }
            } else {
                NoRN tio = avo.esquerdo;

                if (tio != null && tio.color.equals(NoRN.VERMELHO)) {
                    pai.color = NoRN.PRETO;
                    tio.color = NoRN.PRETO;
                    avo.color = NoRN.VERMELHO;
                    no = avo;
                } else {
                    if (no == pai.esquerdo) {
                        no = pai;
                        rotacao_direita(no);
                    }
                    pai.color = NoRN.PRETO;
                    avo.color = NoRN.VERMELHO;
                    rotacao_esquerda(avo);
                }
            }
        }
        this.raiz.color = NoRN.PRETO;
    }



}
