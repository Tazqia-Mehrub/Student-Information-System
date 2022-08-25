/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studeninfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author touhe
 */
public class MysqlConnection {
    Connection connection;
    public static Connection connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info","root","10109914");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
            
    }
}
