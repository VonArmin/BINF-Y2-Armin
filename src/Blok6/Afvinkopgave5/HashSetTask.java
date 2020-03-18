package Blok6.Afvinkopgave5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;

public class HashSetTask extends JFrame implements ActionListener {

    private static HashSetTask frame = new HashSetTask();

    private static HashSet<String> column1set = new HashSet<>(100);
    private static HashSet<String> column2set = new HashSet<>(100);
    private static HashSet<String> column3set = new HashSet<>(100);

    private static JTextArea column1area = new JTextArea();
    private static JTextArea column2area = new JTextArea();
    private static JTextArea column3area = new JTextArea();

    private static JComboBox<String> dropmenu = new JComboBox<>(new String[]{"all", "1 & 2", "1 & 3", "2 & 3"});

    private static JScrollPane column1scroll = new JScrollPane(column1area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JScrollPane column2scroll = new JScrollPane(column2area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JScrollPane column3scroll = new JScrollPane(column3area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private static JTextArea outputField = new JTextArea();

    private static JButton check = new JButton("analyse");

    private static int width = 700;

    public static void main(String[] args) {

        frame.setSize(width + 50, 550);
        frame.setTitle("overlap checker");
        frame.myGui();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void myGui() {
        JPanel toppanel = new JPanel();
        JPanel bottompanel = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        toppanel.setPreferredSize(new Dimension(width, 300));
        bottompanel.setPreferredSize(new Dimension(width, 200));
        column1scroll.setPreferredSize(new Dimension(200, 280));
        column2scroll.setPreferredSize(new Dimension(200, 280));
        column3scroll.setPreferredSize(new Dimension(200, 280));
        toppanel.add(column1scroll);
        toppanel.add(column2scroll);
        toppanel.add(column3scroll);
        outputField.setPreferredSize(new Dimension(200, 180));
        outputField.setLineWrap(true);
        dropmenu.setPreferredSize(new Dimension(200, 30));
        check.setPreferredSize(new Dimension(200, 30));
        check.addActionListener(this);
        bottompanel.add(dropmenu);
        bottompanel.add(check);
        bottompanel.add(outputField);
        toppanel.setVisible(true);
        bottompanel.setVisible(true);
        window.add(toppanel);
        window.add(bottompanel);
    }

    private static void analyse() {
        String line = "selected columns contain: \n\n";
        switch (dropmenu.getSelectedIndex()) {
            case 0:
                for (String s : column1set)
                    if (column2set.contains(s) && column3set.contains(s))
                        line += s + "\n";
                break;
            case 1:
                for (String s : column1set)
                    if (column2set.contains(s))
                        line += s + "\n";
                break;
            case 2:
                for (String s : column1set)
                    if (column3set.contains(s))
                        line += s + "\n";
                break;
            case 3:
                for (String s : column2set)
                    if (column3set.contains(s))
                        line += s + "\n";
                break;
        }
        insertOverlap(line);
        column2set.clear();
        column1set.clear();
        column3set.clear();
    }

    private static void insertOverlap(String keuze) {
        outputField.setText(keuze);
    }

    private static String[] toArray(String string) {
        return string.replaceAll("[\n,. ]", "\t").split("\t");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Collections.addAll(column1set, toArray(column1area.getText()));
        Collections.addAll(column2set, toArray(column2area.getText()));
        Collections.addAll(column3set, toArray(column3area.getText()));
        outputField.setText("");
        analyse();
    }
}
