package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Cliente {
    public int dni;
    public int tel;
    public String direccion;
    public String email;
    public String nomApell;

    public Cliente() {
    }
    
    public Cliente(int dni, int tel, String direccion, String email, String nomApell) {
        this.dni = dni;
        this.tel = tel;
        this.direccion = direccion;
        this.email = email;
        this.nomApell = nomApell;
    }

    public void insertarCliente() {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            
            if(!clienteExiste(connection, dni)){
                JOptionPane.showMessageDialog(null, "El cliente ya existe");
                return;
            }
            
            String sql = "INSERT INTO Clientes (DNI, tel, direccion, mail, nomApell) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, dni);
            pstmt.setInt(2, tel);
            pstmt.setString(3, direccion);
            pstmt.setString(4, email);
            pstmt.setString(5, nomApell);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserción exitosa");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }
    
    private boolean clienteExiste(Connection connection, int dni) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Clientes WHERE dni = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, dni);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count == 0; // Si count es igual a 0, la máquina no existe en la base de datos.
    }
}
