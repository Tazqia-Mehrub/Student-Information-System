/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import java.awt.List;
import java.io.DataInputStream;
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
public class StudentServer {

    public static void main(String[] args) {

        try {
            System.out.println("Student Sever Running...");
            ServerSocket serverSocket = new ServerSocket(6667);
            Socket socket = serverSocket.accept();
            //received data
            DataInputStream din = new DataInputStream(socket.getInputStream());
            String studentId = din.readUTF();
            //checking the database
            Connection connection = MysqlConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where student_id = ? ");
            preparedStatement.setString(1, studentId);
            preparedStatement.executeUpdate();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
