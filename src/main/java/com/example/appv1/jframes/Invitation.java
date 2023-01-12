package com.example.appv1.jframes;

import com.example.appv1.modules.Invit;
import com.example.appv1.modules.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Invitation {
    private JPanel invitation;
    private JButton homeButton;
    private JList<String> sendList;
    private JList<String> acceptList;
    private JButton sendButton;
    private JButton acceptButton;
    private JLabel jl;

    String username = UserInterface.usernameConnected;

    String invitationToSend = "";
    String invitationToAccept = "";

    private DefaultListModel<String> model;
    private DefaultListModel<String> model2;

    public Invitation(JFrame frame){

        jl.setIcon(new ImageIcon("E:\\IRISI S3\\Programmation Concurentille\\Chat App\\Realisation\\appV1\\src\\main\\java\\com\\example\\appv1\\images\\invitations.png"));


        model = new DefaultListModel<>();
        model2 = new DefaultListModel<>();
        sendList.setModel(model);
        acceptList.setModel(model2);

        ArrayList<String> listUsersYouCanInvite = new Invit().usersYouCanInvite(username);
        for (String user : listUsersYouCanInvite) {
            model.addElement(user);
        }

        ArrayList<String> listInvitations = new Invit().getInvitations(username);
        for (String user : listInvitations) {
            model2.addElement(user);
        }

        sendList.getSelectionModel().addListSelectionListener(e -> {
            invitationToSend = sendList.getSelectedValue();
        });

        acceptList.getSelectionModel().addListSelectionListener(e -> {
            invitationToAccept = acceptList.getSelectedValue();
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invitationToSend.equals("")) {
                    JOptionPane.showMessageDialog(frame, "select a user to invite", "CHATAPP", JOptionPane.ERROR_MESSAGE);
                } else {
                    int c = new Invit(username , invitationToSend).saveInvitation();
                    if(c>0){
                        frame.dispose();
                        Invitation.showInvitation();
                        JOptionPane.showMessageDialog(frame, "Invitation Sent");
                    }else{
                        JOptionPane.showMessageDialog(frame, "not sent", "CHATAPP", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invitationToAccept.equals("")) {
                    JOptionPane.showMessageDialog(frame, "select invitation to accept", "CHATAPP", JOptionPane.ERROR_MESSAGE);
                } else {
                    int c = new Invit().acceptInvitation(username,invitationToAccept);
                    if(c>0){
                        frame.dispose();
                        Invitation.showInvitation();
                        JOptionPane.showMessageDialog(frame, "Invitation Accepted");
                    }else{
                        JOptionPane.showMessageDialog(frame, "not accepted", "CHATAPP", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UserInterface.showUserInterface();
            }
        });

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

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

    }

    // la fonction showInvitation
    public static void showInvitation() {
        JFrame frame = new JFrame("Invitations page");
        frame.setContentPane(new Invitation(frame).invitation);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
