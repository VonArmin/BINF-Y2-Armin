import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class watermolecuul extends JFrame implements ActionListener {

    private JTextField textFieldy;
    private JTextField textFieldx;
    private JPanel panel;


    public static void main(String[] args) {
        watermolecuul frame = new watermolecuul();
        frame.setSize(500, 500);
        frame.creategui();
        frame.setVisible(true);

    }

    private void creategui() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        window.setBackground(Color.gray);
        textFieldy = new JTextField(3);
        textFieldx = new JTextField(3);
        panel = new JPanel();
        JLabel label = new JLabel("tekenus dingus");

        JButton button = new JButton("GO");
        panel.setPreferredSize(new Dimension(450, 400));
        panel.setBackground(Color.white);
        window.add(label);
        window.add(panel);
        window.add(textFieldx);
        window.add(textFieldy);
        window.add(button);

        button.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            int xval = Integer.parseInt(textFieldx.getText());
            int yval = Integer.parseInt(textFieldy.getText());
            drawthing(xval, yval);
        } catch (NumberFormatException q) {
            int xval = 0;
            int yval = 0;
            drawthing(xval, yval);
        }

    }



    private void drawthing(int x, int y) {
        int widthO = 60;
        int withH = 30;
        Graphics paper = panel.getGraphics();
        panel.update(paper);
        paper.drawLine(x + 25, y + 75, x + 100, y + 15);
        paper.drawLine(x + 25, y + 75, x + 100, y + 125);
        paper.setColor(Color.BLUE);
        paper.fillOval(x, y + 50, widthO, widthO);
        paper.setColor(Color.gray);
        paper.fillOval(x + 90, y, withH, withH);
        paper.fillOval(x + 90, y + 115, withH, withH);

    }
}
