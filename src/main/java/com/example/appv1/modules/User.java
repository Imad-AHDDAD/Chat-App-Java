package com.example.appv1.modules;

import com.example.appv1.jframes.Inscription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String ipAddress;
    private int port;
    private String status;

    // create connection to database
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public User(String username , String status , String s){
        this.username = username;
        this.status = status;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    // register user
    public String regsiterUser(Inscription inscription) {
        int cpt = 0;
        String message = "";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM User WHERE username = '" + this.username + "'");
            while (resultSet.next()) {
                cpt++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cpt > 0) {
            message = "error : this username already exists !";
        } else {
            String hashedPAssword = hashPassword(this.password);
            String sql = "INSERT INTO User(username,password) VALUES ('" + this.username + "','" + hashedPAssword + "')";
            try {
                int count = statement.executeUpdate(sql);
                if (count > 0) {
                    inscription.setUsernameInput("");
                    inscription.setPasswordInput("");
                    inscription.setPasswordConfirmInput("");
                    message = "successfully registred ! you can login now";
                } else {
                    message = "database error";
                }

                connection.close();
                statement.close();
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // return the message
        return message;
    }

    // login
    public String login() {
        int cpt = 0;
        String message = "";
        String username = "";
        String password = "";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM User WHERE username = '" + this.username + "'");
            while (resultSet.next()) {
                cpt++;
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }

            if (cpt == 1) {
                String hashedPassword = hashPassword(this.password);
                if (password.equals(hashedPassword)) {
                    message = "OK";
                } else {
                    message = "password incorrect";
                }
            } else {
                message = "username incorrect !";
            }

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // hashPassword
    public String hashPassword(String passwordToHash) {
        String generatedPassword = null;

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // getPort
    public static int getPort(String user) {
        int p = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM User WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = rs.getInt(5);
            } else {
                System.out.println("none");
            }

            conn.close();
            ps.close();
            rs.close();

        } catch (Exception ex) {
            // TODO: handle
        }

        return p;
    }

    // getAddress
    public static String getAddress(String user) {
        String ad = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM User WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ad = rs.getString(4);
            } else {
                System.out.println("none");
            }

        } catch (Exception ex) {
            // TODO: handle
        }

        return ad;
    }

    // get allUsers
    public ArrayList<User> getAllUsers() {
        ArrayList<User> all = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM User;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getString(2),resultSet.getString(6),"");
                all.add(u);
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return all;
    }

    // setStatus
    public void setStatus(String username, String stat) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "UPDATE User SET status = ? WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,stat);
            preparedStatement.setString(2,username);
            int cpt = preparedStatement.executeUpdate();

            if(cpt == 1) System.out.println(username + " is now " + stat);
            else System.out.println("error : status not changed !!");

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    // get allUsers
    public ArrayList<User> getFriends(String username) {
        ArrayList<User> allUsers = new ArrayList<>();
        ArrayList<User> allFriendsAndStatus = new ArrayList<>();
        ArrayList<String> allFriends = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM User;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getString(2),resultSet.getString(6),"");
                allUsers.add(u);
            }

            String sql2 = "SELECT * FROM Friendship WHERE username1 = ? OR username2 = ?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if(resultSet.getString("username1").equals(username)){
                    allFriends.add(resultSet.getString("username2"));
                }else{
                    allFriends.add(resultSet.getString("username1"));
                }
            }

            for (User u : allUsers) {
                if(allFriends.contains(u.getUsername())){
                    allFriendsAndStatus.add(u);
                }
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allFriendsAndStatus;
    }

}
