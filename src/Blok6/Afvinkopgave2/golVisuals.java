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
    private static final int size = 100;
    private static final int scale = 6;
    private static boolean run = false;
    private static int speed = 5;
    private static final int[] speeds = {275, 225, 175, 125, 100, 80, 60, 40, 25, 10};
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
        label.setText(String.format("Speed: %s", speed));

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
        String[] buttonNames = {"⏭", "⏯", "⏹", "⏪", "⏩", "⏏", "⏺"};
        for (int i = 0; i < controlButtons.length; i++)
        {
            controlButtons[i] = new JButton();
            controlButtons[i].addActionListener(this);
            controlButtons[i].setBackground(backgroundCol);
            controlButtons[i].setForeground(foregroundCol);
            controlButtons[i].setBorder(borderStyle);
            controlButtons[i].setPreferredSize(buttonSize);
            controlButtons[i].setText(buttonNames[i]);
            controlButtons[i].setFont(new Font(controlButtons[i].getFont().getName(),
                    controlButtons[i].getFont().getStyle(), 20));
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
            stepGrid();
        }
        else if (e.getSource() == controlButtons[1])
        {// start thread
            runGrid();
        }
        else if (e.getSource() == controlButtons[2])
        {//clear button
            clearProcedure();
        }
        else if (e.getSource() == controlButtons[3])
        {// increase delay (decrease speed)
            decreaseSpeed();
        }
        else if (e.getSource() == controlButtons[4])
        {// decrease delay (increase speed)
            increaseSpeed();
        }
        else if (e.getSource() == controlButtons[5])
        {// reset speed
            resetSpeed();
        }
        else if (e.getSource() == controlButtons[6])
        {// randomise grid
            RandomiseGrid();

        }
        else
            updateGrid(e);
    }

    private static void updateGrid(ActionEvent e)
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

    private void resetSpeed()
    {
        speed = 5;
        label.setText(String.format("Speed: %s", speed));
    }

    private void increaseSpeed()
    {
        if (speed < speeds.length)
        {
            speed += 1;
        }
        label.setText(String.format("Speed: %s", speed));
    }

    private void decreaseSpeed()
    {
        if (speed > 0)
        {
            speed -= 1;
        }
        label.setText(String.format("Speed: %s", speed));
    }

    private void clearProcedure()
    {
        run = false;
        game.clearGrid();
        update();
        controlButtons[1].setText("⏯");
    }

    private void runGrid()
    {
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

    private static void stepGrid()
    {
        run = false;
        game.step();
        update();
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
        update();
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
                    Thread.sleep(speeds[speed]);
                } catch (Exception ignore)
                {
                }
            }
        }
    }
}
