package Blok5.Afvinkopgave6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.Math;


public class analysefile extends JFrame implements ActionListener {
    private static analysefile frame = new analysefile();
    private static JFileChooser fileChooser = new JFileChooser();
    private static JButton openexplorer = new JButton();

    private static JTextArea textField = new JTextArea();
    private static JTextField filepath = new JTextField();
    private static JPanel panel = new JPanel();
    private static File selectedFile = null;

    public static void main(String[] args) {
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("analyse tool");
        frame.myGui();
    }

    private void myGui() {
        int i = 80;
        JLabel bestand = new JLabel("Bestand");
        JLabel informatie = new JLabel("informatie");
        JLabel percentage = new JLabel("percentage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fileChooser.setCurrentDirectory(new File("C:\\Users\\armin\\Documents\\School\\PYTHON\\Blok 1\\Sequenties\\"));
        filepath.setBounds(10 + i, 10, 360, 30);
        frame.add(filepath);

        openexplorer.setBounds(380 + i, 10, 100, 30);
        frame.add(openexplorer);

        textField.setLineWrap(true);
        textField.setBounds(10 + i, 50, 450, 300);
        frame.add(textField);

        panel.setBackground(Color.gray);
        panel.setBounds(10 + i, 400, 450, 200);

        frame.add(panel);

        bestand.setBounds(10, 10, i, 30);
        frame.add(bestand);
        informatie.setBounds(10, 170, i, 30);
        frame.add(informatie);
        percentage.setBounds(10, 600, i, 30);
        frame.add(percentage);

        openexplorer.setText("Browse");
        openexplorer.addActionListener(this);
    }

    private static void getfiles() {

        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        assert selectedFile != null;
        try {
            selectedFile = new File(selectedFile.toString());
        } catch (NullPointerException e) {
            textField.setText("je moet wel een file selecteren!!");
            getfiles();
        }
        filepath.setText(selectedFile.getName());
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    }

    private static void setTextField() {
        textField.setText(null);
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                textField.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getinfo() {
        char[] polairAA = {'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'K', 'S', 'T', 'Y'};
        int polair = 0;
        double totaal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                for (char a : line.toCharArray()) {
                    totaal++;
                    for (char c : polairAA) {
                        if (a == c) {
                            polair++;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        int apolair = (int) (totaal - polair);
        float polairperc = (float) (polair * 100 / totaal);
        float apolairperc = (float) (apolair * 100 / totaal);
        makePanel(polairperc, apolairperc, totaal);
    }

    private static void makePanel(float polair, float apolair, double totaal) {
        int xpos = 50;
        Graphics paper = panel.getGraphics();
        panel.update(paper);
        Graphics2D paper2d = (Graphics2D) paper;

        paper2d.setColor(new Color(204, 27, 0));
        paper2d.drawString("polair", xpos, 36);
        paper2d.fillRect(0, 40, (int) polair * 450 / 100, 40);

        paper2d.setColor(new Color(204, 88, 0));
        paper2d.drawString("apolair", xpos, 126);
        paper2d.fillRect(0, 130, (int) apolair * 450 / 100, 40);

        paper2d.setColor(Color.white);
        paper2d.drawString(String.format("polair: %s, apolair: %s, totaal %s", Math.round(polair), Math.round(apolair), totaal), 30, 15);
        paper2d.drawString(String.format("%s ", polair) + "%", xpos, 60);
        paper2d.drawString(String.format("%s ", apolair) + "%", xpos, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openexplorer) {
            getfiles();
            setTextField();
            getinfo();
        }
    }
}
