import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int opcao;
        int opcaoarv;
        printMenu();
        opcao = leitor.nextInt();
        switch (opcao) {
            case 0 :
                return;
            case 1 : //ARVORE AVL
                printMenuArvores();
                opcaoarv = leitor.nextInt();
                int chave = leitor.nextInt();
                switch (opcaoarv) {
                    case 0 :
                        return;
                    case 1 : //inserir
                    case 2:  //remover
                    case 3:  //buscar

                }
            case 2: //ARVORE RUBRO NEGRO
                printMenuArvores();
                opcaoarv = leitor.nextInt();
                int chave2 = leitor.nextInt();
                switch (opcaoarv) {
                    case 0 :
                        return;
                    case 1 : //inserir
                    case 2:  //remover
                    case 3:  //buscar
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
        System.out.println("----Árvore AVL----\n");
        System.out.println("Selecione uma opção: \n");
        System.out.println("0.Sair \n 1.Inserir \n 2.Remover\n 3.Buscar");

    }

}

