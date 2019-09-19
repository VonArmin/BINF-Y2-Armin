package Afvinkopgave4;//auteur  : Armin van Eldik
//nummer  : 618604
//DOC     : 17-09-2019
//opdracht: afvinkopgave 4
//klas    : BIN-2B
//functie : paardenrace spelletje

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paardenrace extends JFrame implements ActionListener {
    private static Paardenrace frame = new Paardenrace();
    private static Paard[] paarden= new Paard[1];

    public static void main(String[] args) {
        for (int i=0;i<2;i++){
            paarden[i].setNaam("willy");
            paarden[i].setPaardNummer(i);
            System.out.println(paarden[i].getinfo());
        }
    }

    private void myGui() {
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.green);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}