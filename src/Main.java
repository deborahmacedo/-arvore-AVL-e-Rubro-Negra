import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pedido> pedidos = new ArrayList<>();
        int proximoNumero = 1;
        int opcao;

        do {
            System.out.println("\n1 - Registrar Pedido");
            System.out.println("2 - Listar Pedidos");
            System.out.println("3 - Remover Pedido");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Nome do cliente: ");
                String cliente = sc.nextLine();
                Pedido pedido = new Pedido(proximoNumero++, cliente);

                String continuar;
                do {
                    System.out.print("Nome do item: ");
                    String nomeItem = sc.nextLine();
                    System.out.print("Preço do item: ");
                    double precoItem = sc.nextDouble();
                    sc.nextLine();
                    pedido.adicionarItem(new Item(nomeItem, precoItem));

                    System.out.print("Adicionar mais um item? (s/n): ");
                    continuar = sc.nextLine();
                } while (continuar.equalsIgnoreCase("s"));

                pedidos.add(pedido);
                System.out.println("Pedido registrado com sucesso!");
            } 
            else if (opcao == 2) {
                if (pedidos.isEmpty()) {
                    System.out.println("Nenhum pedido registrado.");
                } else {
                    for (Pedido p : pedidos) {
                        System.out.println("\nPedido Nº " + p.getNumero());
                        System.out.println("Cliente: " + p.getCliente());
                        for (Item i : p.getItens()) {
                            System.out.println("- " + i.getNome() + " R$ " + i.getPreco());
                        }
                        System.out.println("Total: R$ " + p.calcularTotal());
                    }
                }
            } 
            else if (opcao == 3) {
                System.out.print("Número do pedido a remover: ");
                int num = sc.nextInt();
                sc.nextLine();
                Pedido remover = null;
                for (Pedido p : pedidos) {
                    if (p.getNumero() == num) {
                        remover = p;
                        break;
                    }
                }
                if (remover != null) {
                    pedidos.remove(remover);
                    System.out.println("Pedido removido!");
                } else {
                    System.out.println("Pedido não encontrado.");
                }
            }
        } while (opcao != 4);

        sc.close();
    }
}