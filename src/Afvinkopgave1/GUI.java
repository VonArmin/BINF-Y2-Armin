package Afvinkopgave1;

import javax.swing.*;

public class GUI extends JFrame {
    public static void main(String[] args) {
        JFrame f = new JFrame("heel mooie app!"); //instance of the frame
        JButton button = new JButton("click!");
        JLabel label = new JLabel("INPUT1");
        JTextField field = new JTextField("typ dingen");


        button.setBounds(250, 50, 100, 40);
        label.setBounds(25, 50, 100, 40);
        field.setBounds(100, 50, 100, 40);
        f.add(button);
        f.add(label);
        f.add(field);

        f.setSize(400, 200);
        f.setLayout(null);
        f.setVisible(true);
    }
}
