package Blok6.Afvinkopgave2;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GOLvisuals extends JFrame implements ActionListener {
    private static int size = 50;
    private static int scale = 10;
    private static boolean run = false;
    private static int delay = 100;

    private static JPanel panel = new JPanel();
    private static GOLvisuals frame = new GOLvisuals();
    private static JButton[][] grid = new JButton[size][size];


    private JButton startbutton = new JButton(">>");
    private JButton step = new JButton(">");
    private JButton clear = new JButton("x");
    private JButton increase = new JButton("+");
    private JButton decrease = new JButton("-");
    private JButton setdelay = new JButton("=");
    private JButton randomise = new JButton("R");

    private JLabel label = new JLabel();


    public static void main(String[] args) {
        frame.setSize(size * scale + scale, size * scale + 80);
        frame.setTitle("GOL visuals app");
        GOLgrid game = new GOLgrid(size);
        frame.myGui();
        frame.setVisible(true);
    }

    private void myGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();

        step.setBackground(new Color(6, 112, 39));
        startbutton.setBackground(new Color(14, 173, 64));
        clear.setBackground(new Color(158, 8, 5));
        clear.setForeground(Color.white);
        randomise.setBackground(new Color(14, 93, 173));
        randomise.setForeground(Color.white);

        clear.addActionListener(this);
        startbutton.addActionListener(this);
        step.addActionListener(this);
        increase.addActionListener(this);
        decrease.addActionListener(this);
        setdelay.addActionListener(this);
        randomise.addActionListener(this);

        window.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(size, size));
        panel.setPreferredSize(new Dimension(size * (scale), size * (scale)));
        panel.setVisible(true);
        label.setText(String.format("Delay: %s", delay));


        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new JButton();
                grid[row][col].setBackground(Color.black);
                panel.add(grid[row][col]);
                grid[row][col].addActionListener(this);
            }
        }

        window.add(panel);
        window.add(step);
        window.add(startbutton);
        window.add(clear);
        window.add(decrease);
        window.add(increase);
        window.add(setdelay);
        window.add(label);
        window.add(randomise);

    }

    private static void update() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (GOLgrid.getGrid()[i][j]) {
                    grid[i][j].setBackground(Color.white);
                } else {
                    grid[i][j].setBackground(Color.black);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == step) {
            run = false;
            GOLgrid.step();
            update();
        } else if (e.getSource() == startbutton) {
            run = !run;
            startrun();
        } else if (e.getSource() == clear) {
            run = false;
            GOLgrid.clearGrid();
            update();
        } else if (e.getSource() == increase) {
            delay += 100;
            label.setText(String.format("Delay: %s", delay));
        } else if (e.getSource() == decrease) {
            delay /= 2;
            label.setText(String.format("Delay: %s", delay));
        } else if (e.getSource() == setdelay) {
            delay = 100;
            label.setText(String.format("Delay: %s", delay));
        } else if (e.getSource() == randomise) {
            RandomiseGrid();
            update();
        } else {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (e.getSource() == grid[row][col]) {
                        if (!GOLgrid.getGrid()[row][col]) {
                            GOLgrid.tick(row, col);
                        } else {
                            GOLgrid.untick(row, col);
                        }
                        update();
                    }
                }
            }
        }
    }

    private static void RandomiseGrid() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (getRandom() == 1) {
                    GOLgrid.tick(row, col);
                } else {
                    GOLgrid.untick(row, col);
                }
            }
        }
    }

    private static int getRandom() {
        return new Random().nextInt(2);
    }

    private static void startrun() {
        gridLoop thread1 = new gridLoop();
        thread1.start();
    }

    static class gridLoop extends Thread {


        @Override
        public void run() {
            while (GOLvisuals.run) {
                GOLgrid.step();
                GOLvisuals.update();
                try {
                    Thread.sleep(delay);
                } catch (Exception ignore) {
                }
            }
        }
    }
}
