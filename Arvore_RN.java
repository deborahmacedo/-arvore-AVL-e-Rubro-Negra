public class Arvore_RN extends Arvore_abstrata<NoRN>{

    @Override
    public NoRN criar_no(int chave){
        return  new NoRN(chave);
    }

    @Override
    public void balancear(NoRN no) {

    }

    @Override
    public void inserir(int chave) {
        super.inserir(chave);
    }
}
