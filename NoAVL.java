public class NoAVL extends No <NoAVL> {
    int altura;

    public NoAVL (int chave) {
        super (chave);
        this.altura = 1; //nesse caso é uma folha: subárvore com um único nível

    }
}
