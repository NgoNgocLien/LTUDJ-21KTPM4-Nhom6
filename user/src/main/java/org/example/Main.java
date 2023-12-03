//import java.awt.*;
//import javax.swing.*;
//
//public class Main extends JFrame {
//    private final int WINDOW_WIDTH = 350;
//    private final int WINDOW_HEIGHT = 250;
//
//    public BorderFactory() {
//        setTitle("Border Factory");
//        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(2, 1));
//
//        JPanel panel1 = new JPanel();
//        JPanel panel2 = new JPanel();
//
//        JRadioButton radio1 = new JRadioButton("Radio 1");
//        JRadioButton radio2 = new JRadioButton("Radio 2");
//        JRadioButton radio3 = new JRadioButton("Radio 3");
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(radio1);
//        group.add(radio2);
//        group.add(radio3);
//
//        panel1.add(radio1);
//        panel1.add(radio2);
//        panel1.add(radio3);
//
//        JCheckBox check1 = new JCheckBox("Check 1");
//        JCheckBox check2 = new JCheckBox("Check 2");
//        JCheckBox check3 = new JCheckBox("Check 3");
//
//        panel2.add(check1);
//        panel2.add(check2);
//        panel2.add(check3);
//
//        add(panel1);
//        add(panel2);
//
//        panel1.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
//
//        setVisible(true);
//    }
//
//    static public void main(String[] args) {
//        new Main();
//    }
//
//}


package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Client_Socket.start();
        SwingUtilities.invokeLater(() -> new LoginSignUpScreen(Client_Socket.getSocket()).setVisible(true));
    }
}