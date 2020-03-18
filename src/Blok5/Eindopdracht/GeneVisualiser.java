package Blok5.Eindopdracht;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GeneVisualiser extends JFrame implements ActionListener {
    private static File selectedFile = null;
    private static JFileChooser fileChooser = new JFileChooser();
    private static JButton openexplorer = new JButton();
    //    private static JScrollBar scrollBar = new JScrollBar();
    private static Chromosoom[] chromosooms = new Chromosoom[25];
    private static GeneVisualiser frame = new GeneVisualiser();
    private static JPanel panel = new JPanel();
    private static JButton analyse = new JButton("analyse");
    private static JTextArea textField = new JTextArea();
    private static JTextField filepath = new JTextField();

    /**
     * @param args lijst van invoer via terminal/cmd
     * @author Armin van Eldik
     * @since 31-10-2019
     */

    public static void main(String[] args) {

        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Chromosome analyser by Armin van Eldik");
        frame.myGui();
    }

    /**
     * methode maakt de gui aan
     */

    private void myGui() {
        int i = 80;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\armin\\IdeaProjects\\Unclassified\\src\\Eindopdracht"));
        filepath.setBounds(10 + i, 10, 360, 30);
        frame.add(filepath);

        openexplorer.setBounds(380 + i, 10, 100, 30);
        frame.add(openexplorer);
        openexplorer.setText("Browse");
        openexplorer.addActionListener(this);

        analyse.setBounds(20 + i, 45, 400, 30);
        analyse.addActionListener(this);
        frame.add(analyse);

        textField.setLineWrap(true);
        textField.setBounds(10, 80, 280, 450);
        frame.add(textField);
    }

    /**
     * methode loopt door data heen, maakt er objecten van en zorgt dat de juiste data aan het juiste chromosoom toegevoegd wordt.
     *
     * @throws IOException readline methode throwed deze exeption en moet dus afgevangen worden
     */
    private static void makeData() throws IOException {
        int chrNr;
        int start;
        int end;
        for (int i = 0; i < 25; i++) {
            chromosooms[i] = new Chromosoom(i);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                start = Integer.parseInt(split[1]);
                end = Integer.parseInt(split[2]);
                switch (split[4]) {
                    case "X":
                        chrNr = 22;
                        addGene(chrNr, start, end);
                        break;
                    case "Y":
                        chrNr = 23;
                        addGene(chrNr, start, end);
                        break;
                    case "MT":
                        chrNr = 24;
                        addGene(chrNr, start, end);
                        break;
                    default:
                        try {
                            chrNr = Integer.parseInt(split[4]) - 1;
                            addGene(chrNr, start, end);
                        } catch (NullPointerException | NumberFormatException ignored) { }
                }
            }
        }
    }
    //ik weet het een lange functie met een hoop lelijke if statements, een case structuur zou mooier zijn.

    /**
     * methode voegt het gen toe in het chromosoom
     *
     * @param chrNR chromosoom nummer
     * @param start gen-start pos
     * @param end   gen-einde pos
     */
    private static void addGene(int chrNR, int start, int end) {
        try {
            chromosooms[chrNR].addGene(start, end);
        } catch (geneException e) {
            e.printStackTrace();
        }
    }


    /**
     * methode faciliteerd het openen van een file en vangt toepasselijke exceptions af.
     */
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

    /**
     * methode voert de info van de chromosoom objecten in het textveld in
     */
    private static void setTextField() {
        textField.setText(null);
        for (Chromosoom chr : chromosooms) {
            String info = String.format("Chr %s: avg: %s, max: %s, min: %s\n", chr.getChrNumber(), chr.getAverageGenLength(), chr.getLongest(), chr.getShortest());
            textField.append(info);
        }
        textField.append("\nChr 23, 24, 25 zijn X, Y, MT");
    }

    /**
     * methode maakt het panel aan om de data in te visualiseren
     */
    private static void makePanel() {
        Graphics paper = panel.getGraphics();
        panel.update(paper);
        Graphics2D paper2d = (Graphics2D) paper;
    }
    //niet af, geen tijd meer maar ahd af kunnen zijn aangezien alle data aanwezig is.

    /**
     * actionlistener verantwoordlijk voor het afvangen van de knoppen en het aanroepen van de juiste functies
     *
     * @param e actionevent-parameter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openexplorer) {
            getfiles();
        }
        if (e.getSource() == analyse) {
            try {
                makeData();
                setTextField();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
