package logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Maquina {
    public String modelo;
    public String marca;
    public int stock;
    public int precioCompra;
    public int precioVenta;

    public Maquina() {
    }

    public Maquina(String modelo, String marca, int stock, int precioCompra, int precioVenta) {
        this.modelo = modelo;
        this.marca = marca;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
    
    public void insertarMaquina() {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            
            if(maquinaExiste(connection, modelo, marca)){
                String sql = "INSERT INTO Maquinas (modelo, marca, Stock, precioCompra, precioVenta) VALUES (?, ?, ?, ?, ?);";
                PreparedStatement pstmt = connection.prepareStatement(sql);

                pstmt.setString(1, modelo);
                pstmt.setString(2, marca);
                pstmt.setInt(3, stock);
                pstmt.setInt(4, precioCompra);
                pstmt.setInt(5, precioVenta);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserción exitosa");
                    connection.close();
                }
            }
            else{
                String sql = "UPDATE Maquinas SET stock = stock + ? WHERE modelo = ? AND marca = ?;";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                
                pstmt.setInt(1, stock);
                pstmt.setString(2, modelo);
                pstmt.setString(3, marca);
                

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Actualizacion exitosa");
                    connection.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar la maquina: " + e.getMessage());
        }
    }
    
    private boolean maquinaExiste(Connection connection, String modelo, String marca) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Maquinas WHERE Modelo = ? AND Marca = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, modelo);
        pstmt.setString(2, marca);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count == 0; // Si count es igual a 0, la máquina no existe en la base de datos.
    }

}
