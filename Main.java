import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao;
        int opcaoarv;
        printMenu();
        opcao = leitor.nextInt();
        Arvore_abstrata arvore = null; // pode ser AVL ou Rubro-Negra

        switch (opcao) {
            case 0:
                return;
            case 1:
                //arvore = new ArvoreAVL();
                break;
            case 2:
                arvore = new Arvore_RN();
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        boolean rodando = true;
        while(rodando) {
            printMenuArvores();
            opcaoarv = leitor.nextInt();
            int chave;
            switch (opcaoarv) {
                case 0:
                    rodando = false;
                    break;
                case 1: //inserir
                    chave = leitor.nextInt();
                    arvore.inserir(chave);
                    break;
                case 2:  //remover
                    chave = leitor.nextInt();
                    arvore.inserir(chave);
                    break;
                case 3:  //buscar
                    chave = leitor.nextInt();
                    arvore.buscar(chave);
                    break;
                case 4: //printar
                    arvore.printar();
                    break;
                default:
                    System.out.println("Opção de árvore inválida.");
                    break;

                    }

            }
        }



    public static void printMenu(){
        System.out.println("Bem vindo ao Sistema de Árvores\n");
        //System.out.println("Selecione uma opção: \n");
        System.out.println("Selecione o tipo de árvore desejada\n");
        System.out.println("0. Sair \n 1. Árvore AVL \n 2.Árvore Rubro Negro\n ");

    }
    public static void printMenuArvores(){
        System.out.println("----Árvore----\n");
        System.out.println("Selecione uma opção: \n");
        System.out.println("\n0.Sair \n 1.Inserir \n 2.Remover\n 3.Buscar\n 4.Printar árvore\n");

    }

}

