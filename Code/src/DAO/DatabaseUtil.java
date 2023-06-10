/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

    public static Connection c;
    private static String db_url = "jdbc:mysql://localhost:3306/quanlygiaovien";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws Exception {
        if (c == null) {
            c = DriverManager.getConnection(db_url, username, password);
        }
        return c;
    }
}
