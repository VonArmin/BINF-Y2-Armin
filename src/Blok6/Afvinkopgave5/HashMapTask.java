package Blok6.Afvinkopgave5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class HashMapTask {
    private static HashMap<String, String> one = new HashMap<>();
    private static HashMap<String, String> three = new HashMap<>();
    private static HashMap<String, String> full = new HashMap<>();

    public static void main(String[] args) throws IOException {
        createMaps();
        while (true) {
            System.out.print("voer iets in: ");
            System.out.println((makeAnimoDic(getInput())));
        }
    }

    private static void createMaps() {
        String[] oneLetterCode = {"A", "R", "N", "D", "C", "E", "Q", "G", "H",
                "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V"};

        String[] threeLeterCode = {"Ala", "Arg", "Asn", "Asp", "Cys", "Glu", "Gln", "Gly", "His",
                "Ile", "Leu", "Lys", "Met", "Phe", "Pro", "Ser", "Thr", "Trp", "Tyr", "Val"};

        String[] fullName = {"Alanine", "Arginine", "Asparagine", "Aspartate", "Cysteine", "Glutamate", "Glutamine",
                "Glycine", "Histidine", "Isoleucine", "Leucine", "Lysine", "Methionine", "Phenylalanine", "Proline",
                "Serine", "Threonine", "Tryptophan", "Tyrosine", "Valine"};

        for (int i = 0; i < oneLetterCode.length; i++) {
            one.put(oneLetterCode[i], (threeLeterCode[i] + " " + fullName[i]));
            three.put(threeLeterCode[i], (oneLetterCode[i] + " " + fullName[i]));
            full.put(fullName[i], oneLetterCode[i] + " " + threeLeterCode[i]);
        }
    }

    private static String makeAnimoDic(String input) {
        input = input.substring(0, 1).toUpperCase() + input.substring(1);
        switch (input.length()) {
            case 1:
                if (one.get(input) != null)
                    return one.get(input);
            case 3:
                if (three.get(input) != null)
                    return three.get(input);
            default:
                if (full.get(input) != null)
                    return full.get(input);
                return "unknown";
        }
    }


    private static String getInput() throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
