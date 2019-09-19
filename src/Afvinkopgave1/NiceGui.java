package Afvinkopgave1;

import javax.swing.*;
import java.awt.*;

public class NiceGui {
    public static void main(String[] args) {
        JDialog f = new JDialog(); //instance of the frame
        JButton button = new JButton("click!");
        JLabel label = new JLabel("INPUT1");
        JTextField field = new JTextField();
        ImageIcon img = new ImageIcon("C:\\Users\\armin\\IdeaProjects\\Unclassified\\out\\production\\Unclassified\\star_3d_6814876.jpg");
        f.setIconImage(img.getImage());
        button.setBounds(250, 50, 100, 40);
        label.setBounds(25, 50, 100, 40);
        field.setBounds(100, 50, 100, 40);
        f.add(button);
        f.add(label);
        f.add(field);
        f.getContentPane().setBackground(Color.BLUE);
        label.setForeground(Color.red);
        f.setSize(400, 200);
        f.setLayout(null);
        f.setVisible(true);
    }
}
