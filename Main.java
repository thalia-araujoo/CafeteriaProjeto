import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexao = CafeteriaDAO.conectar();
            System.out.println("Conexão com o banco de dados estabelecida.\n");
            System.out.println("\nBem - Vinda Jamires, a nossa Caféteria Coffee Break!!!.\n");


            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("Menu:");
                System.out.println("1. Inserir um novo produto");
                System.out.println("2. Consultar todos os produtos");
                System.out.println("3. Cadastrar um novo pedido");
                System.out.println("4. Consultar todos os pedidos");
                System.out.println("5. Excluir um pedido");
                System.out.println("6. Excluir um produto");
                System.out.println("7. Inserir um novo cliente");
                System.out.println("8. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
    
                switch (opcao) {
                    case 1:
                        inserirNovoProduto();
                        break;
                    case 2:
                        consultarTodosProdutos();
                        break;
                    case 3:
                        cadastrarNovoPedido();
                        break;
                    case 4:
                        consultarTodosPedidos();
                        break;
                    case 5:
                        excluirPedido();
                        break;
                    case 6:
                        excluirProduto();
                        break;
                    case 7:
                        inserirNovoCliente();
                        break;
                    case 8:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } while (opcao != 8);
    
            conexao.close();
            System.out.println("\nConexão com o banco de dados fechada.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void excluirProduto() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("ID do produto que deseja excluir: ");
        int idProduto = scanner.nextInt();
    
        Produto.excluirProduto(idProduto);
    }

    private static void excluirPedido() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("ID do pedido que deseja excluir: ");
        int idPedido = scanner.nextInt();
    
        Pedidos.excluirPedido(idPedido);
    }

    private static void inserirNovoProduto() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        Produto novoProduto = new Produto();

        System.out.print("Nome do produto: ");
        novoProduto.setNomeProduto(scanner.nextLine());

        System.out.print("Descrição do produto: ");
        novoProduto.setDescricaoProduto(scanner.nextLine());

        System.out.print("Preço do produto: ");
        novoProduto.setPrecoProduto(scanner.nextDouble());

        Produto.inserirProduto(novoProduto);
    }

    private static void consultarTodosProdutos() {
        System.out.println("Lista de Produtos:");
        Produto.consultarProdutos();
    }

    private static void cadastrarNovoPedido() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        Pedidos novoPedido = new Pedidos();

        System.out.print("ID do cliente associado ao pedido: ");
        novoPedido.setIdCliente(scanner.nextInt());
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.print("Nome do pedido: ");
        novoPedido.setNomePedido(scanner.nextLine());

        novoPedido.setDataPedido(new Date(System.currentTimeMillis()));

        System.out.print("Status do pedido: ");
        novoPedido.setStatus(scanner.nextLine());

        System.out.print("Valor total do pedido: ");
        novoPedido.setValorTotal(scanner.nextDouble());

        Pedidos.cadastrarPedido(novoPedido);
    }

    private static void consultarTodosPedidos() {
        System.out.println("Lista de Pedidos com Itens:");
        Pedidos.consultarPedidos();
    }

    private static void inserirNovoCliente() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        Cliente novoCliente = new Cliente();

        System.out.print("ID do cliente: ");
        novoCliente.setIdCliente(scanner.nextInt());
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.print("Nome do cliente: ");
        novoCliente.setNomeCliente(scanner.nextLine());

        System.out.print("Email do cliente: ");
        novoCliente.setEmailCliente(scanner.nextLine());

        System.out.print("Telefone do cliente: ");
        novoCliente.setTelefoneCliente(scanner.nextLine());

        Cliente.inserirCliente(novoCliente);
    }
}
