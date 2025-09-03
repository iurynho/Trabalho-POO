/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conn;

    private static final String URL = "jdbc:mysql://localhost:3306/campeonato";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConexao() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
