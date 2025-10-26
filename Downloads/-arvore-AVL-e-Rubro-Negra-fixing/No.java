public class No< N extends No<N>> {
    public int chave;
    public N esquerdo;
    public N direito;
    public N pai;

    public No(int chave) {
        this.chave = chave;
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
    }
}
