package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pago {
    
    public String tipo;
    public int monto;
    public int numTarjeta;
    public String alias;
    public int cvu;
    public int cuentaBancaria;

    public Pago() {
    }

    public Pago(String tipo, int monto, int numTarjeta, String alias, int cvu, int cuentaBancaria) {
        this.tipo = tipo;
        this.monto = monto;
        this.numTarjeta = numTarjeta;
        this.alias = alias;
        this.cvu = cvu;
        this.cuentaBancaria = cuentaBancaria;
    }
    
    public void insertarPago() {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();

            String sql = "INSERT INTO Pagos (codMetodoPago, codFactura, monto, numTarjeta, alias, cvu, cuentaBancaria, fechaPago) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE());";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, selectCodPago(connection, tipo));
            pstmt.setInt(2, selectFactura(connection));
            pstmt.setInt(3, monto);
            pstmt.setInt(4, numTarjeta);
            pstmt.setString(5, alias);
            pstmt.setInt(6, cvu);
            pstmt.setInt(7, cuentaBancaria);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserción exitosa");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }
    
    private int selectCodPago(Connection connection, String tipo) throws SQLException {
        String sql = "SELECT codMetodoPago FROM MetododePago WHERE tipo = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, tipo);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count; // Si count es igual a 0, la máquina no existe en la base de datos.
    }
    
    private int selectFactura(Connection connection) throws SQLException {
        String sql = "SELECT MAX(codFactura) FROM Facturas";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count; // Si count es igual a 0, la máquina no existe en la base de datos.
    }
    
    //pago.insertarPago(); -> para el java
    
}
