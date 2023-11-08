mysql -u root


DROP DATABASE IF EXISTS gym_107;
CREATE DATABASE gym_107;
USE gym_107


CREATE TABLE Clientes(
    CodCli INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    DNI INT NOT NULL,
    Tel INT,
    Direccion VARCHAR(25),
    Mail VARCHAR(50),
    NomApell VARCHAR(50)
);


CREATE TABLE Maquinas(
    CodMaquina INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Marca VARCHAR(15),
    Modelo VARCHAR(25),
    PrecioVenta INT,
    PrecioCompra INT,
    Stock INT
);


CREATE TABLE Facturas(
    CodFactura INT NOT NULL AUTO_INCREMENT,
    CodCli INT NOT NULL,
    CodMaquina INT NOT NULL,
    MontoFinal INT,
    Fecha DATE,
    ValorAsesoramiento INT,
    PRIMARY KEY(CodFactura, CodCli, CodMaquina),
    FOREIGN KEY (CodCli) REFERENCES Clientes(CodCli),
    FOREIGN KEY (CodMaquina) REFERENCES Maquinas(CodMaquina)
);


CREATE TABLE MetodosDePago(
    CodMetodoPago INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Tipo VARCHAR(20)
);
INSERT INTO MetodosDePago(tipo) VALUES
("Efectivo"), ("MercadoPago"), ("Transferencia"), ("Debito");


CREATE TABLE Pagos(
    CodPago INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    CodFactura INT NOT NULL,
    CodMetodoPago INT NOT NULL,
    FechaPago DATE,
    NumTarjeta INT,
    Alias VARCHAR(20),
    CVU INT,
    CuentaBancaria INT
);
