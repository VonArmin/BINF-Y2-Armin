package Afvinkopgave1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class demo extends JFrame implements ActionListener {

    private JTextField textfield;
    private JButton button;
    private JPanel panel;

    public static void main(String[] args) {
        demo frame = new demo();
        frame.setSize(200, 200);
        frame.creategui();
        frame.setVisible(true);
    }

    private void creategui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.green);
        textfield = new JTextField("Afvinkopgave1.Hello world");
        button = new JButton("text");
        panel = new JPanel();
        panel.setPreferredSize(new Dimension( 100,100));
        panel.setBackground(Color.blue);
        window.add(textfield);
        window.add(button);
        window.add(panel);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("hello");
        Graphics paper = panel.getGraphics();
        paper.drawLine(10,10,50,80);
    }
}
