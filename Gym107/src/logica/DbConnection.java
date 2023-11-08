package logica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
    private static String url = "jdbc:mysql://localhost:3306/gym_107?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver); // Cargar el controlador JDBC
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Se conecto OK");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC");
        }
        
        
        return connection;
    }
}
