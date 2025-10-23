public class ArvoreAVL extends Arvore_abstrata<NoAVL> {
    @Override
    public NoAVL criar_no (int chave){
        return new NoAVL(chave);
    }
    @Override
    public void balancear(NoAVL No){
        //calcular fator balanceamento
        //fazer rotações
    }

    public void inserir(int chave){
        super.inserir(chave);
    }
}
