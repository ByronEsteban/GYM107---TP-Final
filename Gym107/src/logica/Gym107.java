package logica;

import logica.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import gui.PantallaPrincipal;

public class Gym107 {

    public static void main(String[] args) {
        Connection connection = DbConnection.getConnection();

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
        }
        
        PantallaPrincipal panta = new PantallaPrincipal();
        panta.setVisible(true);
    }
    
}
