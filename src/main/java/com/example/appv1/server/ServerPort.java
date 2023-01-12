package com.example.appv1.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.*;

public class ServerPort {
    public static void main(String[] args) {
        // create connection to database
        Connection connection;
        PreparedStatement ps;
        try {
            DatagramSocket server = new DatagramSocket(8888);

            while (true) {
                // receive data
                byte[] receivedBytes = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBytes, receivedBytes.length);
                server.receive(receivedPacket);


                String username = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                String port = Integer.toString(receivedPacket.getPort());
                String address = receivedPacket.getAddress().getHostAddress();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "root");
                String sql = "UPDATE User SET ipAddress = ? , port = ? WHERE username = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, address);
                ps.setString(2, port);
                ps.setString(3, username);
                int count = ps.executeUpdate();
                System.out.println("count = " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
