import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CafeteriaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/coffeebreak";
    private static final String USUARIO = "root";
    private static final String SENHA = "estalloneh_1";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
