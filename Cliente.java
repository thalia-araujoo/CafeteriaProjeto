import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String telefoneCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public static void inserirCliente(Cliente cliente) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "INSERT INTO Cliente (Id_Cliente, Nome_Cliente, Email_Cliente, Telefone_Cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, cliente.getIdCliente());
                statement.setString(2, cliente.getNomeCliente());
                statement.setString(3, cliente.getEmailCliente());
                statement.setString(4, cliente.getTelefoneCliente());
                statement.executeUpdate();
                System.out.println("Cliente inserido com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
