public class Arvore_RN extends Arvore_abstrata<NoRN>{
    public NoRN raiz;

    @Override
    public NoRN criar_no(int chave) {
        return new NoRN(chave);
    }

    @Override
    public void balancear(NoRN no) {
        //caso base, arvore vazia
        if(no.pai == null){
            no.color = "preto";
            raiz = no;
            return;
        }

        while(no.pai != null && no.pai.color.equals("vermelho")){
            NoRN pai = no.pai;
            NoRN avo = no.pai.pai;

            //Pai é filho esquerda do avô
            if(pai == avo.esquerdo){
                NoRN tio = avo.direito;

                // Caso 1: tio vermelho → recoloração
                if(tio != null && "vermelho".equals(tio.color)){
                    pai.color = "preto";
                    tio.color = "preto";
                    avo.color = "vermelho";
                    no = avo;}
                else{
                // Caso 2: nó é filho direito → rotação esquerda no pai
                    if(no == pai.direito){
                        no = pai;
                        rotacao_esquerda(no);}
                }
                // Caso 3: nó é filho esquerdo → rotação direita no avô
                pai.color = "preto";
                avo.color = "vermelho";
                rotacao_direita(avo);
            }
            //Pai é filho direito do avô
            else if(pai == avo.direito){
                NoRN tio = avo.esquerdo;
                if(tio != null && "vermelho".equals(tio.color)){
                    pai.color = "preto";
                    tio.color = "preto";
                    avo.color = "vermelho";
                    no = avo;}
                else{
                    if(no == pai.esquerdo){
                        no = pai;
                        rotacao_direita(pai);}

                    pai.color = "preto";
                    avo.color = "vermelho";
                    rotacao_esquerda(avo);
                }
            }
        }
        this.raiz.color = "preto";

    }


}
