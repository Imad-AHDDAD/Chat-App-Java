package com.example.appv1.modules;

import java.sql.*;

public class Friendship {
    String username1;
    String username2;

    // create connection to database
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public Friendship(String username1 , String username2){
        this.username1 = username1;
        this.username2 = username2;
    }

    // la fonction saveFriendship
    public int saveFriendship(){
        int cpt = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "INSERT INTO friendship(username1,username2) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.username1);
            preparedStatement.setString(2, this.username2);

            cpt = preparedStatement.executeUpdate();
            if (cpt > 0) {
                System.out.println("freiendship saved");
            } else {
                System.out.println("error data base");
            }

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cpt;
    }

}
