/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import java.awt.List;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import studeninfo.MysqlConnection;

/**
 *
 * @author touhe
 */
public class LoginServer {

    public static void main(String[] args) {
        

            try {
                System.out.println("Login Sever Running...");
                ServerSocket serverSocket = new ServerSocket(6666);
                Socket socket = serverSocket.accept();
                //received data
                Scanner scanner = new Scanner(socket.getInputStream());
                String username = scanner.next();
                String password = scanner.next();
                //checking the database
                Connection connection = MysqlConnection.connect();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from students where student_id = ? "
                        + "and password = ?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                while (resultSet.next()) {
                    //sending data
                    printStream.println(1);
                }
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        

    }
}
