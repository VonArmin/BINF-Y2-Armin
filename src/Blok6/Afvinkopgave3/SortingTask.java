package Blok6.Afvinkopgave3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SortingTask {
    private static File file;

    private static ArrayList<String> chromosomes = new ArrayList<>();
    private static ArrayList<ArrayList<String>> genes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            file = new File("C:\\Users\\armin\\IdeaProjects\\Unclassified\\src\\Blok6\\Afvinkopgave3\\Homo_sapiens.gene_info");
        } catch (Exception e) {
            e.printStackTrace();
        }
        readFile();
    }

    private static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String chrom = line.split("\t")[6];
                String gene = line.split("\t")[2];
                if (!chrom.contains("-") && !chrom.contains("_") && !chrom.contains("ch")) {
                    if (!chromosomes.contains(chrom)) {
                        chromosomes.add(chrom);
                        genes.add(new ArrayList<>());
                    }
                    genes.get(chromosomes.indexOf(chrom)).add(gene);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < chromosomes.size(); i++) {
            System.out.print(chromosomes.get(i) + " ");
            System.out.println(genes.get(i));
        }
    }
}
