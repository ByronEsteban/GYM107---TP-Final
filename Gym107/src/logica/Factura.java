package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Factura {
    
    public int codCli;
    public int codMaquina;
    public int valorAsesoramiento;
    public int montoFinal;

    public Factura() {
    }

    public Factura(int codCli, int codMaquina, int valorAsesoramiento, int montoFinal) {
        this.codCli = codCli;
        this.codMaquina = codMaquina;
        this.valorAsesoramiento = valorAsesoramiento;
        this.montoFinal = montoFinal;
    }

    public void insertarFactura() {
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            String sql = "INSERT INTO Facturas (codCli, codMaquina, valorAsesoramiento, montoFinal, fecha) VALUES (?, ?, ?, ?, CURDATE());";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, codCli);
            pstmt.setInt(2, codMaquina);
            pstmt.setInt(3, valorAsesoramiento);
            pstmt.setInt(4, montoFinal);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserción exitosa");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar la factura: " + e.getMessage());
        }
    }
    //factura.insertarFactura(); -> pegar en InsertarFactura.java
    
    
}
