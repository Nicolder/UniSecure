package Unisecure.dao; //Data Acess Object

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/Unisecure";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345";

    public static Connection conectar() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException exception) {
            System.err.println("Erro ao conectar ao banco de dados: " + exception.getMessage());
            exception.printStackTrace();

            throw exception;
        }
    }
}