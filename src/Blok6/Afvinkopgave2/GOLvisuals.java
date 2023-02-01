package Blok6.Afvinkopgave2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GOLvisuals extends JFrame implements ActionListener
{
    private static final Color backgroundCol = new Color(65, 65, 65);
    private static final Color foregroundCol = new Color(180, 180, 180);
    private static final Border borderStyle = BorderFactory.createLineBorder(Color.BLACK);
    private static final Dimension buttonSize = new Dimension(50, 25);
    private static final int size = 150;
    private static final int scale = 5;
    private static boolean run = false;
    private static final int delay = 10;
    private static int speed = 10;
    private static final JPanel GOLgridPanel = new JPanel();
    private static final GOLvisuals frame = new GOLvisuals();
    private static final JButton[][] grid = new JButton[size][size];
    private final JButton[] Controlbuttons = new JButton[7];
    private final JLabel label = new JLabel();

    public static void main(String[] args)
    {
        frame.setSize(size * scale + 30, size * scale + 80);
        frame.setTitle("GOL visuals app");
        GOLgrid game = new GOLgrid(size);
        frame.myGui();
        frame.setVisible(true);
    }

    private void myGui()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();

        FormatButtons();

        window.setLayout(new FlowLayout());
        GOLgridPanel.setLayout(new GridLayout(size, size));
        window.setBackground(new Color(35, 35, 35));
        GOLgridPanel.setPreferredSize(new Dimension(size * (scale), size * (scale)));
        GOLgridPanel.setVisible(true);
        label.setForeground(foregroundCol);
        label.setText(String.format("Delay: %s", delay * speed));

        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                grid[row][col] = new JButton();
                grid[row][col].setBorderPainted(false);
                grid[row][col].setBackground(backgroundCol);
                GOLgridPanel.add(grid[row][col]);
                grid[row][col].addActionListener(this);
            }
        }

        window.add(GOLgridPanel);
        for (JButton jbutton : Controlbuttons)
        {
            window.add(jbutton);
        }
        window.add(label);
    }

    private void FormatButtons()
    {
        String[] buttonNames = {"⏯", "⏭", "⏏", "⏪", "⏩", "⏴", "⏺"};
        for (int i = 0; i < Controlbuttons.length; i++)
        {
            Controlbuttons[i] = new JButton();
            Controlbuttons[i].addActionListener(this);
            Controlbuttons[i].setBackground(backgroundCol);
            Controlbuttons[i].setForeground(foregroundCol);
            Controlbuttons[i].setBorder(borderStyle);
            Controlbuttons[i].setPreferredSize(buttonSize);
            Controlbuttons[i].setText(buttonNames[i]);
        }
    }

    private static void update()
    {
        boolean[][] currGrid = GOLgrid.getGrid();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (currGrid[i][j])
                {
                    grid[i][j].setBackground(foregroundCol);
                }
                else
                {
                    grid[i][j].setBackground(backgroundCol);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == Controlbuttons[0])
        {// step button
            run = false;
            GOLgrid.step();
            update();
        }
        else if (e.getSource() == Controlbuttons[1])
        {// start thread
            run = !run;
            if (run)
            {
                Controlbuttons[1].setText("⏸");
                startrun();
            }
            else
            {
                Controlbuttons[1].setText("⏵");
            }
        }
        else if (e.getSource() == Controlbuttons[2])
        {//clear button
            run = false;
            GOLgrid.clearGrid();
            update();
        }
        else if (e.getSource() == Controlbuttons[3])
        {// increase delay (decrease speed)
            speed += 10;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == Controlbuttons[4])
        {// decrease delay (increase speed)
            speed -= 1;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == Controlbuttons[5])
        {// reset speed
            speed = 10;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == Controlbuttons[6])
        {// randomise grid
            RandomiseGrid();
            update();
        }
        else
        {
            for (int row = 0; row < size; row++)
            {
                for (int col = 0; col < size; col++)
                {
                    if (e.getSource() == grid[row][col])
                    {
                        if (!GOLgrid.getGrid()[row][col])
                        {
                            GOLgrid.tick(row, col);
                        }
                        else
                        {
                            GOLgrid.untick(row, col);
                        }
                        update();
                    }
                }
            }
        }
    }

    private static void RandomiseGrid()
    {
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                if (getRandom() == 1)
                {
                    GOLgrid.tick(row, col);
                }
                else
                {
                    GOLgrid.untick(row, col);
                }
            }
        }
    }

    private static int getRandom()
    {
        return new Random().nextInt(2);
    }

    private static void startrun()
    {
        gridLoop thread1 = new gridLoop();
        thread1.start();
    }

    static class gridLoop extends Thread
    {
        @Override
        public void run()
        {
            while (GOLvisuals.run)
            {
                GOLgrid.step();
                GOLvisuals.update();
                try
                {
                    Thread.sleep(delay * speed);
                } catch (Exception ignore)
                {
                }
            }
        }
    }
}
