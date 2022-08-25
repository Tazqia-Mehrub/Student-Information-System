/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servers;

import java.io.DataInputStream;
import java.util.List;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import studeninfo.MysqlConnection;

/**
 *
 * @author touhe
 */
public class DepartmentServer {

    public static void main(String[] args) {

        try {
            System.out.println("Department Sever Running...");
            ServerSocket serverSocket = new ServerSocket(6665);
            Socket socket = serverSocket.accept();
            //received data
            DataInputStream din = new DataInputStream(socket.getInputStream());
            String receive = din.readUTF();
            String sql="";
            if(receive.equals("ICT") || receive.equals("ES")){
                sql = "select * from students where department = ?";
            }else{
                sql = "select * from students where student_id = ?";
            }
            //checking the database
            Connection connection = MysqlConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, receive);
            ResultSet resultSet = preparedStatement.executeQuery();
            //sending data
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            while (resultSet.next()) {
                dout.writeUTF(resultSet.getString("student_id"));
                dout.writeUTF(resultSet.getString(1));
                dout.writeUTF(resultSet.getString(2));
                dout.writeUTF(resultSet.getString(3));
                dout.writeUTF(resultSet.getString(4));
                dout.flush();
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
