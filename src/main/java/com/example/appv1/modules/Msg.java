package com.example.appv1.modules;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Msg {
    private String sender;
    private String receiver;
    private String content;
    private String dateSent;

    // create connection to database
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public Msg(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.dateSent = new Date().toString();
    }

    public void saveMessage() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "INSERT INTO Msg(sender,reciever,content,dateSent) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, receiver);
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, dateSent);

            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("message saved");
            } else {
                System.out.println("error data base");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Msg> getAllMsg() {
        ArrayList<Msg> allMsg = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM Msg WHERE (sender = ? AND reciever = ?) OR (sender = ? AND reciever = ?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, receiver);
            preparedStatement.setString(3, receiver);
            preparedStatement.setString(4, sender);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String send = resultSet.getString(2);
                String reci = resultSet.getString(3);
                String cont = resultSet.getString(4);

                allMsg.add(new Msg(send,reci,cont));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMsg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getDateSent() {
        return dateSent;
    }
}
