package com.example.appv1.jframes;

import com.example.appv1.modules.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel login;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton homeButton;
    private JLabel jl;

    public Login(JFrame frame) {

        jl.setIcon(new ImageIcon("E:\\IRISI S3\\Programmation Concurentille\\Chat App\\Realisation\\appV1\\src\\main\\java\\com\\example\\appv1\\images\\login.png"));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                User user;
                if (!username.equals("") && !password.equals("")) {
                    String msg = new User(username, password).login();
                    if (msg.equals("OK")) {
                        frame.dispose();
                        UserInterface.usernameConnected = username;
                        UserInterface.showUserInterface();
                    } else {
                        JOptionPane.showMessageDialog(frame, msg, "CHATAPP", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "incomplete informations", "CHATAPP", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Home.showHome();
            }
        });
    }

    // la fonction showLogin
    public static void showLogin() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login(frame).login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
