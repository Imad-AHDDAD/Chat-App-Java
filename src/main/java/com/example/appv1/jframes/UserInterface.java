package com.example.appv1.jframes;

import com.example.appv1.modules.User;

import javax.swing.*;
import java.awt.event.*;

public class UserInterface {

    // usrname connected
    public static String usernameConnected = "";

    private JPanel userinterface;
    private JButton invitationsButton;
    private JButton sendMessageButton;
    private JButton logoutButton;
    private JLabel userInfo;
    private JLabel jl;

    public UserInterface(JFrame frame) {

        jl.setIcon(new ImageIcon("E:\\IRISI S3\\Programmation Concurentille\\Chat App\\Realisation\\appV1\\src\\main\\java\\com\\example\\appv1\\images\\logo.png"));


        userInfo.setText("user : "+usernameConnected);
        new User().setStatus(usernameConnected,"ONLINE");

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                new User().setStatus(usernameConnected,"OFFLINE");
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

        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SelectUser.showSelectUser();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User().setStatus(usernameConnected,"OFFLINE");
                usernameConnected = "";
                frame.dispose();
                Home.showHome();
            }
        });

        invitationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Invitation.showInvitation();
            }
        });

    }

    // la fonction showInscription
    public static void showUserInterface() {
        JFrame frame = new JFrame("User Interface");
        frame.setContentPane(new UserInterface(frame).userinterface);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
