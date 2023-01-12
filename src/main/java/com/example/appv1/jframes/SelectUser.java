package com.example.appv1.jframes;

import com.example.appv1.modules.User;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class SelectUser {

    private JList<String> usersList;
    private JList<String> statusList;
    private JPanel selectUSer;
    private JButton start;
    private JButton homeButton;
    private DefaultListModel<String> model;
    private DefaultListModel<String> model2;

    String receiver = "";


    public SelectUser(JFrame frame) {

        model = new DefaultListModel<>();
        model2 = new DefaultListModel<>();
        usersList.setModel(model);
        statusList.setModel(model2);

        ArrayList<User> friends = new User().getFriends(UserInterface.usernameConnected);
        for (User user : friends) {
            model.addElement(user.getUsername());
            model2.addElement(user.getStatus());
        }


        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                new User().setStatus(UserInterface.usernameConnected, "OFFLINE");
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

        usersList.getSelectionModel().addListSelectionListener(e -> {
            receiver = usersList.getSelectedValue();
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (receiver.equals("")) {
                    JOptionPane.showMessageDialog(frame, "select a user to chat with");
                } else {
                    frame.dispose();
                    Message.showMessage(receiver);
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
    }

    // la fonction showAllUsers
    public static void showSelectUser() {
        JFrame frame = new JFrame("Slect User");
        frame.setContentPane(new SelectUser(frame).selectUSer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        showSelectUser();
    }

}
