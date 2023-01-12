package com.example.appv1.jframes;

import com.example.appv1.modules.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription {
    private JPanel inscription;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JTextField passwordConfirmInput;
    private JButton loginButton;
    private JButton homeButton;
    private JButton sendButton;
    private JLabel jl;

    public Inscription(JFrame frame) {

        jl.setIcon(new ImageIcon("E:\\IRISI S3\\Programmation Concurentille\\Chat App\\Realisation\\appV1\\src\\main\\java\\com\\example\\appv1\\images\\register.png"));


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                String passwordConf = passwordConfirmInput.getText();
                User user;
                if (!username.equals("") && !password.equals("") && !passwordConf.equals("")) {
                    if (password.equals(passwordConf)) {
                        user = new User(username, password);
                        String msg = user.regsiterUser(Inscription.this);
                        JOptionPane.showMessageDialog(frame, msg);
                    } else {
                        JOptionPane.showMessageDialog(frame, "password and its confirmation don't match !", "CHATAPP", JOptionPane.ERROR_MESSAGE);
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login.showLogin();
            }
        });
    }

    // la fonction showInscription
    public static void showInscription() {
        JFrame frame = new JFrame("Inscription");
        frame.setContentPane(new Inscription(frame).inscription);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //setUsernameInput
    public void setUsernameInput(String usernameInput) {
        this.usernameInput.setText(usernameInput);
    }

    //setPasswordInput
    public void setPasswordInput(String text) {
        this.passwordInput.setText(text);
    }

    //setPasswordConfirmInput
    public void setPasswordConfirmInput(String text) {
        this.passwordConfirmInput.setText(text);
    }

}
