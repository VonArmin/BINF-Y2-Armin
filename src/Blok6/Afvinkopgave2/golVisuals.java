package Blok6.Afvinkopgave2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class golVisuals extends JFrame implements ActionListener
{
    private static final Color backgroundCol = new Color(65, 65, 65);
    private static final Color foregroundCol = new Color(200, 200, 200);
    private static final Border borderStyle = BorderFactory.createLineBorder(Color.BLACK);
    private static final Dimension buttonSize = new Dimension(50, 25);
    private static final int size = 150;
    private static final int scale = 5;
    private static boolean run = false;
    private static final int delay = 10;
    private static int speed = 10;
    private static final JPanel GOLGridPanel = new JPanel();
    private static final golVisuals frame = new golVisuals();
    private static final golGrid game = new golGrid(size);
    private static final JButton[][] grid = new JButton[size][size];
    private final JButton[] controlButtons = new JButton[7];
    private final JLabel label = new JLabel();

    public static void main(String[] args)
    {
        frame.setSize(size * scale + 30, size * scale + 80);
        frame.setTitle("GOL visuals app");

        frame.myGui();
        frame.setVisible(true);
    }

    private void myGui()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();

        FormatButtons();

        window.setLayout(new FlowLayout());
        GOLGridPanel.setLayout(new GridLayout(size, size));
        window.setBackground(new Color(35, 35, 35));
        GOLGridPanel.setPreferredSize(new Dimension(size * (scale), size * (scale)));
        GOLGridPanel.setVisible(true);
        label.setForeground(foregroundCol);
        label.setText(String.format("Delay: %s", delay * speed));

        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                grid[row][col] = new JButton();
                grid[row][col].setBorderPainted(false);
                grid[row][col].setBackground(backgroundCol);
                GOLGridPanel.add(grid[row][col]);
                grid[row][col].addActionListener(this);
            }
        }

        window.add(GOLGridPanel);
        for (JButton jbutton : controlButtons)
        {
            window.add(jbutton);
        }
        window.add(label);
    }

    private void FormatButtons()
    {
        String[] buttonNames = {"⏯", "⏭", "⏏", "⏪", "⏩", "⏴", "⏺"};
        for (int i = 0; i < controlButtons.length; i++)
        {
            controlButtons[i] = new JButton();
            controlButtons[i].addActionListener(this);
            controlButtons[i].setBackground(backgroundCol);
            controlButtons[i].setForeground(foregroundCol);
            controlButtons[i].setBorder(borderStyle);
            controlButtons[i].setPreferredSize(buttonSize);
            controlButtons[i].setText(buttonNames[i]);
        }
    }

    private static void update()
    {
        boolean[][] currGrid = game.getGrid();
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
        if (e.getSource() == controlButtons[0])
        {// step button
            run = false;
            game.step();
            update();
        }
        else if (e.getSource() == controlButtons[1])
        {// start thread
            run = !run;
            if (run)
            {
                controlButtons[1].setText("⏸");
                startRun();
            }
            else
            {
                controlButtons[1].setText("⏵");
            }
        }
        else if (e.getSource() == controlButtons[2])
        {//clear button
            run = false;
            game.clearGrid();
            update();
        }
        else if (e.getSource() == controlButtons[3])
        {// increase delay (decrease speed)
            speed += 10;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == controlButtons[4])
        {// decrease delay (increase speed)
            speed -= 1;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == controlButtons[5])
        {// reset speed
            speed = 10;
            label.setText(String.format("Delay: %s", delay * speed));
        }
        else if (e.getSource() == controlButtons[6])
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
                        if (!game.getGrid()[row][col])
                        {
                            game.tick(row, col);
                        }
                        else
                        {
                            game.unTick(row, col);
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
                    game.tick(row, col);
                }
                else
                {
                    game.unTick(row, col);
                }
            }
        }
    }

    private static int getRandom()
    {
        return new Random().nextInt(2);
    }

    private static void startRun()
    {
        gridLoop thread1 = new gridLoop();
        thread1.start();
    }

    static class gridLoop extends Thread
    {
        @Override
        public void run()
        {
            while (run)
            {
                game.step();
                update();
                try
                {
                    Thread.sleep((long) delay * speed);
                } catch (Exception ignore)
                {
                }
            }
        }
    }
}
