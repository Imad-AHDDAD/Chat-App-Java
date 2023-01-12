package com.example.appv1.jframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home{
    private JButton register;
    private JPanel Home;
    private JButton login;
    private JPanel image;
    private JLabel jl;
    private JPanel buttonsPanel;

    public Home(JFrame frame) {

        jl.setIcon(new ImageIcon("E:\\IRISI S3\\Programmation Concurentille\\Chat App\\Realisation\\appV1\\src\\main\\java\\com\\example\\appv1\\images\\logo.png"));
        login.setBackground(new Color(154,3,30));
        login.setForeground(Color.WHITE);

        register.setBackground(new Color(154,3,30));
        register.setForeground(Color.WHITE);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Inscription.showInscription();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login.showLogin();
            }
        });
    }

    public Home(){}

    public static void showHome(){
        JFrame frame = new JFrame("Home");
        frame.setContentPane(new Home(frame).Home);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
