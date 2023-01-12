package com.example.appv1.modules;

import java.sql.*;
import java.util.ArrayList;

public class Invit {
    private String invitationSender;
    private String invitationReciver;
    private String accepted;

    // create connection to database
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public Invit(String invitationSender, String invitationReciver) {
        this.invitationSender = invitationSender;
        this.invitationReciver = invitationReciver;
        this.accepted = "no";
    }

    public Invit() {
    }

    public int saveInvitation() {
        int count = 0;
        if (!this.invitationExists()) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
                String sql = "INSERT INTO invitation(invSender,invReciever,accepted) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, this.invitationSender);
                preparedStatement.setString(2, this.invitationReciver);
                preparedStatement.setString(3, this.accepted);

                count = preparedStatement.executeUpdate();
                if (count > 0) {
                    System.out.println("invitation saved");
                } else {
                    System.out.println("error data base");
                }

                connection.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            count = -2;
        }

        return count;
    }

    public boolean invitationExists() {
        boolean exists = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM invitation WHERE (invSender = ? AND invReciever = ?) OR (invSender = ? and invReciever = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.invitationSender);
            preparedStatement.setString(2, this.invitationReciver);
            preparedStatement.setString(3, this.invitationReciver);
            preparedStatement.setString(4, this.invitationSender);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            } else {
                exists = false;
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            //handle
        }

        return exists;
    }

    public ArrayList<String> usersYouCanInvite(String you) {
        ArrayList<String> all = new ArrayList<>();
        ArrayList<String> allInv = new ArrayList<>();
        ArrayList<String> invYouCan = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT * FROM user WHERE username != ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, you);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(resultSet.getString("username"));
            }

            String sql2 = "SELECT * FROM invitation WHERE invSender = ? OR invReciever = ?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, you);
            preparedStatement.setString(2, you);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("invSender").equals(you)) {
                    allInv.add(resultSet.getString("invReciever"));
                } else {
                    allInv.add(resultSet.getString("invSender"));
                }
            }

            for (String user : all) {
                if (allInv.contains(user)) {
                    continue;
                }
                invYouCan.add(user);
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            //handle
        }

        return invYouCan;
    }

    public ArrayList<String> getInvitations(String you) {
        ArrayList<String> invitations = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "SELECT* FROM invitation WHERE invReciever = ? and accepted = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, you);
            preparedStatement.setString(2, "no");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invitations.add(resultSet.getString("invSender"));
            }

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitations;
    }

    public int acceptInvitation(String you, String him) {
        int count = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
            String sql = "UPDATE invitation SET accepted = ? WHERE invSender = ? AND invReciever = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "yes");
            preparedStatement.setString(2, him);
            preparedStatement.setString(3, you);

            count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("invitation accepted");
                int c = new Friendship(you, him).saveFriendship();
                if(c>0){
                    count = 0;
                    count = c;
                }
            } else {
                System.out.println("error data base");
            }

            connection.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}
