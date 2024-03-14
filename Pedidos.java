import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pedidos {
    private int idPedido;
    private int idCliente;
    private String nomePedido;  
    private Date dataPedido; // Alterado para java.sql.Date
    private String status;
    private double valorTotal;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) { // Alterado para java.sql.Date
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public static void cadastrarPedido(Pedidos pedido) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "INSERT INTO Pedidos (Id_Cliente, Nome_Pedido, Data_Pedido, Status, Valor_Total) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, pedido.getIdCliente());
                statement.setString(2, pedido.getNomePedido());
                statement.setDate(3, pedido.getDataPedido());
                statement.setString(4, pedido.getStatus());
                statement.setDouble(5, pedido.getValorTotal());
                statement.executeUpdate();
                System.out.println("Pedido cadastrado com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void excluirPedido(int idPedido) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "DELETE FROM Pedidos WHERE Id_Pedido = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, idPedido);
                int linhasAfetadas = statement.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Pedido excluído com sucesso.");
                } else {
                    System.out.println("Nenhum pedido encontrado com o ID fornecido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarPedido(Pedidos pedido) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "UPDATE Pedidos SET Id_Cliente = ?, Data_Pedido = ?, Status = ? WHERE Id_Pedido = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, pedido.getIdCliente());
                statement.setDate(2, pedido.getDataPedido());
                statement.setString(3, pedido.getStatus());
                statement.setInt(4, pedido.getIdPedido());
                int linhasAfetadas = statement.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Pedido atualizado com sucesso.");
                } else {
                    System.out.println("Nenhum pedido encontrado com o ID fornecido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarPedidos() {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "SELECT * FROM Pedidos";
            try (PreparedStatement statement = conexao.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idPedido = resultSet.getInt("Id_Pedido");
                    int idCliente = resultSet.getInt("Id_Cliente");
                    Date dataPedido = resultSet.getDate("Data_Pedido");
                    String status = resultSet.getString("Status");

                    System.out.println("ID: " + idPedido + ", Cliente: " + idCliente +
                                       ", Data: " + dataPedido + ", Status: " + status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIdPedido(java.util.Date date) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setIdPedido'");
    }

    public void setDataPedido(java.util.Date date) {
        this.dataPedido = new Date(date.getTime());
    }
    
}
