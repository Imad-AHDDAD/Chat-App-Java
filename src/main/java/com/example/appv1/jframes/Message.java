package com.example.appv1.jframes;

import com.example.appv1.modules.Msg;
import com.example.appv1.modules.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Message {
    private JPanel message;
    private JTextField msgInput;
    private JButton sendButton;
    private JTextArea msgArea;
    private JButton homeButton;

    private DatagramSocket clientSocket;
    private InetAddress address;
    private DatagramPacket datagramPacket;

    String sender = "";
    String receiever = "";

    public Message(JFrame frame , String rec) {

        try {
            this.clientSocket = new DatagramSocket();
            this.address = InetAddress.getLocalHost();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                // sender and receicer
                sender = UserInterface.usernameConnected;
                receiever = rec;

                // update port
                String messagePort = sender;
                byte[] b1 = messagePort.getBytes();
                datagramPacket = new DatagramPacket(b1, b1.length, address, 8888);
                try {
                    clientSocket.send(datagramPacket);
                }catch (Exception ex){
                    // TODO : handle
                }

                //recieve data
                try {
                    clientSocket = new DatagramSocket();
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // print all previous messages
                            ArrayList<Msg> prev = new Msg(sender,receiever,"").getAllMsg();
                            for (Msg msg : prev) {
                                msgArea.append(msg.getSender()+" : "+msg.getContent()+"\n");
                            }
                            while (true) {
                                try {
                                    byte[] receivedBytes = new byte[1024];
                                    DatagramPacket receivedDataPacket = new DatagramPacket(receivedBytes,receivedBytes.length);
                                    clientSocket.receive(receivedDataPacket);
                                    // print data
                                    String messageReceived = new String(receivedDataPacket.getData(),0,receivedDataPacket.getLength());
                                    msgArea.append(receiever+" : " + messageReceived + "\n");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                    thread.start();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new User().setStatus(UserInterface.usernameConnected,"OFFLINE");
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        // send data
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageToSend = msgInput.getText();
                if (!messageToSend.equals("")) {

                    String messageToSendToServer = sender+","+receiever+","+messageToSend;

                    byte[] b = messageToSendToServer.getBytes();
                    datagramPacket = new DatagramPacket(b, b.length, address, 4567);
                    try {
                        clientSocket.send(datagramPacket);
                        msgArea.append(sender+" : " + messageToSend + "\n");
                        msgInput.setText("");
                        new Msg(sender,receiever,messageToSend).saveMessage();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        // go Home
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserInterface.showUserInterface();
            }
        });
    }

    // la fonction showMessage
    public static void showMessage(String reciver) {

        JFrame frame = new JFrame("Send Message");
        frame.setContentPane(new Message(frame,reciver).message);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
