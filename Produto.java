import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double precoProduto;

    public int getIdProduto() {
        return idProduto;
    }
    
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public String getNomeProduto() {
        return nomeProduto;
    }
    
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    
    public double getPrecoProduto() {
        return precoProduto;
    }
    
    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public static void inserirProduto(Produto produto) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "INSERT INTO Produto (Nome_Produto, Descricao_Produto, Preco_Produto) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, produto.getNomeProduto());
                statement.setString(2, produto.getDescricaoProduto());
                statement.setDouble(3, produto.getPrecoProduto());
                statement.executeUpdate();
                System.out.println("Produto inserido com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void consultarProdutos() {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "SELECT * FROM Produto";
            try (PreparedStatement statement = conexao.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idProduto = resultSet.getInt("Id_Produto");
                    String nomeProduto = resultSet.getString("Nome_Produto");
                    String descricaoProduto = resultSet.getString("Descricao_Produto");
                    double precoProduto = resultSet.getDouble("Preco_Produto");

                    System.out.println("ID: " + idProduto + ", Nome: " + nomeProduto +
                                       ", Descrição: " + descricaoProduto + ", Preço: " + precoProduto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarProduto(Produto produto) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "UPDATE Produto SET Nome_Produto = ?, Descricao_Produto = ?, Preco_Produto = ? WHERE Id_Produto = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, produto.getNomeProduto());
                statement.setString(2, produto.getDescricaoProduto());
                statement.setDouble(3, produto.getPrecoProduto());
                statement.setInt(4, produto.getIdProduto());
                statement.executeUpdate();
                System.out.println("Produto atualizado com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluirProduto(int idProduto) {
        try (Connection conexao = CafeteriaDAO.conectar()) {
            String sql = "DELETE FROM Produto WHERE Id_Produto = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setInt(1, idProduto);
                statement.executeUpdate();
                System.out.println("Produto excluído com sucesso.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
