/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alessandro
 */
public class AdministradorDeConecciones {
    public static Connection obtenerConeccion()throws SQLException{
        String dbURL = "jdbc:mysql://localhost:3306/bair?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPassword = "";
        return DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }
}
