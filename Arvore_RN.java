public class Arvore_RN extends Arvore_abstrata<NoRN>{

    @Override
    public NoRN criar_no(int chave) {
        return new NoRN(chave);
    }

    @Override
    public void balancearInsercao(NoRN no){
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
                    no.pai.color = NoRN.PRETO;
                    no.pai.pai.color = NoRN.VERMELHO;
                    rotacao_direita(no.pai.pai);
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
                    no.pai.color = NoRN.PRETO;
                    no.pai.pai.color = NoRN.VERMELHO;
                    rotacao_esquerda(no.pai.pai);
                }
            }
        }
        this.raiz.color = NoRN.PRETO;
    }

    @Override
    public NoRN balancearRemocao(NoRN no) {

    }

    @Override
    protected String getNodeDetails(NoRN no) {
        // Pega a primeira letra da cor (V para Vermelho, P para Preto)
        // Usa as constantes que você definiu no NoRN
        String corLabel = (no.color.equals(NoRN.VERMELHO)) ? "(V)" : "(P)";

        return corLabel + " " + no.chave;
    }


    private String getColor(NoRN no) {
        if(no == null){
            return NoRN.PRETO;
        }
        return no.color;
    }

    private String getCor(NoRN no) {
        if (no == null) {
            return NoRN.PRETO;
        }
        return no.color;
    }
}
