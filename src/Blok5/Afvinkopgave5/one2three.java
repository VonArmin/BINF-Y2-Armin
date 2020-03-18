//auteur  : Armin van Eldik
//nummer  : 618604
//DOC     : 02-10-2019
//opdracht: afvinkopgave 5
//klas    : BIN-2B
//functie : vertaalt een eenletterige AA seq naar drieletterige seq.

package Blok5.Afvinkopgave5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class one2three extends JFrame implements ActionListener {
    private static one2three frame = new one2three();
    private static JTextField one = new JTextField(20);
    private static JTextField three = new JTextField(20);
    private static JLabel text = new JLabel();
    private static JButton translate=new JButton();

    public static void main(String[] args) {
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("one2three");
        frame.myGui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            translate();
        } catch (NotAnAA notAnAA) {
            text.setText(notAnAA.getMessage());
        }

    }

    private void myGui() { 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new GridLayout(2,1));
        one.setBounds(20, 20, 100, 30);
        translate.setBounds(20, 70, 100, 30);
        translate.setText("Translate");
        translate.addActionListener(this);
        one.addActionListener(this);
        three.setBounds(20, 120, 350, 30);
        text.setBounds(120,20,100,30);
//        setplaces();
        window.add(one);
        window.add(translate);
        window.add(three);
        window.add(text);
    }

    private void translate() throws NotAnAA {
        text.setText(null);
        String chain = one.getText();
        String lang = "";
        for (int i = 0; i < chain.length(); i++) {
            char c = chain.charAt(i);
            lang += makethree.getthree(String.valueOf(c).toUpperCase()) + "-";
            three.setText(lang);
        }
    }

//    private void setplaces(){
//        one.setBounds(20, 20, 100, 30);
//        translate.setBounds(20, 70, 100, 30);
//        three.setBounds(20, 120, 350, 30);
//        text.setBounds(120,20,100,30);
//    }

}

