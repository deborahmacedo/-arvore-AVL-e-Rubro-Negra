public class NoRN extends No<NoRN> {

    public static final String VERMELHO = "vermelho";
    public static final String PRETO = "preto";

    String color;
    public NoRN(int chave){
        super(chave);
        this.color = VERMELHO;
    }

}
