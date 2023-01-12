package com.example.appv1.server;

import com.example.appv1.modules.User;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(4567);

            while(true) {
                // receive data
                byte[] receivedBytes = new byte[1024];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBytes, receivedBytes.length);
                server.receive(receivedPacket);

                // print data
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

                //extract infos
                String[] infos = message.split(",",3);
                String sender = infos[0];
                String receiver = infos[1];
                String content = infos[2];

                System.out.println("sender = "+sender);
                System.out.println("receiver = "+receiver);
                System.out.println("message : " + content);

                // send data
                int p = User.getPort(receiver);
                String addr = User.getAddress(receiver);
                InetAddress inetAddress = InetAddress.getByName(addr);

                String messageToSend = content;
                byte[] bytesToSend = messageToSend.getBytes();
                DatagramPacket packetToSend = new DatagramPacket(bytesToSend,bytesToSend.length,inetAddress,p+1);
                server.send(packetToSend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
