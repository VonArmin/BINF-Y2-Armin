package AIGold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TicTacToe extends JFrame implements ActionListener {
    private static final int[] ticked = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final JButton[] buttons = new JButton[9];
    private static final TicTacToe frame = new TicTacToe();
    private static int player = 1;
    private boolean gameEnded = false;

    public static void main(String[] args) {
        frame.setSize(500, 500);
        String playerstr = String.format("Player %s's Turn!", player);
        frame.setTitle(playerstr);
        frame.creategui();
        frame.setVisible(true);
    }

    private void creategui() {
        Container window = getContentPane();
        window.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            if (ticked[i] == 0) {
                buttons[i] = new JButton();
                window.add(buttons[i]);
                buttons[i].setFont(new Font("Gill Sans MT", Font.PLAIN, 150));
                buttons[i].setBackground(Color.white);
                buttons[i].addActionListener(this);
            }
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked(e.getSource());
    }

    private void clicked(Object clickedBtn) {
        for (int i = 0; i < 9; i++) {
            if (player == 1) {
                if (clickedBtn == buttons[i]) {
                    tick(i, "X");
                    player = 2;
                }
            }
        }
        if (movesLeft(ticked) && !gameEnded) {
            AI();
        }
        String playerstr = String.format("Player %s's Turn!", player);
        frame.setTitle(playerstr);
    }

    private void tick(int tickThis, String XO) {

        ticked[tickThis] = player;
        buttons[tickThis].setText(XO);
        buttons[tickThis].setEnabled(false);
        if (!movesLeft(ticked) && !Test(ticked, player)) {
            Dialog("Draw!");
        }
        if (Test(ticked, player)) {
            gameEnded = true;
            switch (player) {
                case 1:
                    Dialog("You won!");
                    break;
                case 2:
                    Dialog("You lost...");
                    break;
            }
        }
    }

    private boolean Test(int[] array, int player) {
        return ((array[0] == player && array[1] == player && array[2] == player) ||
                (array[3] == player && array[4] == player && array[5] == player) ||
                (array[6] == player && array[7] == player && array[8] == player) ||
                (array[0] == player && array[3] == player && array[6] == player) ||
                (array[1] == player && array[4] == player && array[7] == player) ||
                (array[2] == player && array[5] == player && array[8] == player) ||
                (array[0] == player && array[4] == player && array[8] == player) ||
                (array[2] == player && array[4] == player && array[6] == player));
    }

    private void Dialog(String textvar) {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        JDialog dialog = new JDialog();
        JLabel text = new JLabel(textvar);
        text.setLocation(20, 50);
        dialog.add(text);
        dialog.setSize(180, 100);
        dialog.setVisible(true);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                reset();
            }
        });
    }

    private void reset() {
        gameEnded = false;
        player = 1;
        String playerstr = String.format("Player %s's Turn!", player);
        frame.setTitle(playerstr);
        for (int i = 0; i < 9; i++) {
            ticked[i] = 0;
            buttons[i].setEnabled(true);
            buttons[i].setText(null);
        }
    }

    private void AI() {
        tick(bestMove(ticked.clone()), "O");
        player = 1;
    }

    private int getWinner(int[] state) {
        if (Test(state, 2)) {
            return 2;
        } else if (Test(state, 1)) {
            return 1;
        }
        return 0;
    }

    private boolean movesLeft(int[] state) {
        for (int i = 0; i < 9; i++) {
            if (state[i] == 0) {
                return true;
            }
        }
        return false;
    }

    private int bestMove(int[] state) {
        int bestScore = -100;
        int move = 0;
        for (int i = 0; i < 9; i++) {
            if (state[i] == 0) {
                state[i] = 2;
                int score = miniMax(state, false);
                state[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }

            }
        }
        return move;
    }

    private int miniMax(int[] state, boolean isMax) {
        int winner = getWinner(state);
        int bestScore;
        switch (winner) {
            case 2:
                return 10;
            case 1:
                return -10;
        }
        if (!movesLeft(state)) {
            return 0;
        }
        if (isMax) {
            bestScore = -100;
            for (int i = 0; i < 9; i++) {
                if (state[i] == 0) {
                    state[i] = 2;
                    bestScore = Math.max(bestScore, miniMax(state, false));
                    state[i] = 0;
                }
            }
        } else {
            bestScore = 100;
            for (int i = 0; i < 9; i++) {
                if (state[i] == 0) {
                    state[i] = 1;
                    bestScore = Math.min(bestScore, miniMax(state, true));
                    state[i] = 0;
                }
            }
        }
        return bestScore;
    }
}
