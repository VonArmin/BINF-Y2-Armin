//auteur  : Armin van Eldik
//nummer  : 618604
//DOC     : 17-09-2019
//opdracht: afvinkopgave 4
//klas    : BIN-2B
//functie : paardenrace spelletje

package Afvinkopgave4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paardenrace extends JFrame implements ActionListener {
    private static int horseamt = 5;
    private static Paardenrace frame = new Paardenrace();
    private static Paard[] horse = new Paard[horseamt];
    private static JTextField[] tfield = new JTextField[horseamt];
    private JPanel panel;

    public static void main(String[] args) {
        frame.setSize(700, 100 + horseamt * 30);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.myGui();
    }


    private void myGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(550, 100 + horseamt * 28));
        panel.setBackground(new Color(50, 100, 50));
        window.add(panel);
        window.setLayout(new FlowLayout());
        window.setBackground(new Color(160, 160, 160));
        for (int i = 0; i < horseamt; i++) {
            tfield[i] = new JTextField(5);
            tfield[i].setText("paard " + (i + 1));
            window.add(tfield[i]);
            tfield[i].setBounds(0, 15 + i * 30, 60, 18);
        }
        JButton start = new JButton("start");
        start.setVisible(true);
        start.setBounds(2, 20 + horseamt * 30, 100, 35);
        start.addActionListener(this);
        window.add(start);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setnames();
        startrace();
    }

    private void setnames() {
        for (int i = 0; i < horseamt; i++) {
            horse[i] = new Paard(tfield[i].getText());
        }
    }

    private void startrace() {
        Graphics paper = panel.getGraphics();
        paper.setColor(Color.BLACK);
        paper.drawLine(500, 10, 500, 10 + horseamt * 30);
        while (checkwinner()) {
            for (int i = 0; i < horseamt; i++) {
                paper.setColor(horse[i].getColor());
                horse[i].run();
                paper.drawLine(0, 20 + i * 30, horse[i].getDistance() - 1, 20 + i * 30);
                paper.drawLine(0, 21 + i * 30, horse[i].getDistance() - 1, 21 + i * 30);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }
    }


    private boolean checkwinner() {
        for (int i = 0; i < horseamt; i++) {
            if (horse[i].getDistance() >= 500) {
                windialog(horse[i].getName());
                return false;
            }
        }
        return true;
    }

    private void windialog(String paard) {
        JDialog winnaar = new JDialog();
        JLabel text = new JLabel(String.format("%s heeft gewonnen!!", paard));
        winnaar.setSize(180, 100);
        text.setLocation(20, 50);
        winnaar.add(text);
        winnaar.setVisible(true);
    }
}