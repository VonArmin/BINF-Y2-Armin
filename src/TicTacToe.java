import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TicTacToe extends JFrame implements ActionListener {
    private int[] ticked = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private JButton[] buttons = new JButton[9];
    private static int player = 1;
    private int moves = 0;
    private static TicTacToe frame = new TicTacToe();
    private boolean opnieuw = false;


    public static void main(String[] args) {
        ImageIcon img = new ImageIcon("C:\\Users\\armin\\IdeaProjects\\Unclassified\\out\\production\\Unclassified\\star_3d_6814876.jpg");
        frame.setIconImage(img.getImage());
        frame.setSize(500, 500);
        String playerstr = String.format("player %s is aan de beurt", player);
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
            }
        }
        for (int i = 0; i < 9; i++) {
            buttons[i].addActionListener(this);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (player == 1) {
                if (e.getSource() == buttons[i]) {
                    ticked[i] = 1;
                    buttons[i].setBackground(Color.BLUE);
                    buttons[i].setEnabled(false);
                    if (checkforwinner()) {
                        windialog();
                    }
                    player = 2;
                    String playerstr = String.format("player %s is aan de beurt", player);

                    frame.setTitle(playerstr);


                }
            } else {
                if (e.getSource() == buttons[i]) {
                    ticked[i] = 2;
                    buttons[i].setBackground(Color.RED);
                    buttons[i].setEnabled(false);
                    if (checkforwinner()) {
                        windialog();
                    }
                    player = 1;
                    String playerstr = String.format("player %s is aan de beurt", player);

                    frame.setTitle(playerstr);

                }
            }
        }
    }


    private boolean checkforwinner() {
        for (int i = 1; i < 3; i++) {
            if (ticked[0] == i && ticked[1] == i && ticked[2] == i) {
                return true;
            }
            if (ticked[3] == i && ticked[4] == i && ticked[5] == i) {
                return true;
            }
            if (ticked[6] == i && ticked[7] == i && ticked[8] == i) {
                return true;
            }
            if (ticked[0] == i && ticked[3] == i && ticked[6] == i) {
                return true;
            }
            if (ticked[1] == i && ticked[4] == i && ticked[7] == i) {
                return true;
            }
            if (ticked[2] == i && ticked[5] == i && ticked[8] == i) {
                return true;
            }
            if (ticked[0] == i && ticked[4] == i && ticked[8] == i) {
                return true;
            }
            if (ticked[2] == i && ticked[4] == i && ticked[6] == i) {
                return true;
            }
        }
        moves++;
        if (moves == 9) {
            enddialog();
        }
        return false;
    }

    private void windialog() {
        JDialog winnaar = new JDialog();
        JLabel text = new JLabel("speler " + player + " heeft gewonnen!!");
        winnaar.setSize(180, 100);
        text.setLocation(20, 50);
        winnaar.add(text);
        winnaar.setVisible(true);
        winnaar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                reset();
            }
        });
    }

    private void enddialog() {
        JDialog end = new JDialog();
        JLabel text = new JLabel("game over!");
        text.setLocation(20, 50);
        end.add(text);
        end.setSize(180, 100);
        end.setVisible(true);
        end.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                reset();
            }
        });
    }



    private void reset() {
        player = 1;

        String playerstr = String.format("player %s is aan de beurt", player);

        frame.setTitle(playerstr);

        for (int i = 0; i < 9; i++) {
            ticked[i] = 0;
            moves = 0;
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }
    }
}