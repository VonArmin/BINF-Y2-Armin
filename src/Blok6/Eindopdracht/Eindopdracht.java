package Blok6.Eindopdracht;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import statements

/**
 * main class of Eindopdracht blok 6
 *
 * @author Armin van Eldik
 * @version 1.2
 */

public class Eindopdracht {
    private static File varfile;
    private static File snpfile;
    private static String baseURL = "ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/tab_delimited/";
    private static String varFileName = "variant_summary.txt";
    private static String zippedFileName = varFileName + ".gz";
    private static String MD5FileName = zippedFileName + ".md5";
    private static String snpFileName = "9313.23andme.7630";
    private static HashMap<String, Variant> Variants = new HashMap<>();
    private static ArrayList<Variant> hits = new ArrayList<>();

    public static void main(String[] args) {
        startChecks();
    }

    /**
     * method used to run and re-run the checks needed to run the program
     */
    private static void startChecks() {
        checkFiles();
        if (checkMD5()) {
            MD5accepted();
        } else {
            MD5rejected();
        }
    }

    /**
     * method calls Tools.MD5Checker
     *
     * @return true if MD5 succeeds
     * @see Tools.MD5Checker
     */
    private static boolean checkMD5() {
        return new Tools.MD5Checker().call();
    }

    /**
     * if the MD5 check is false, this method will be called, this will restart the checks
     */
    public static void MD5rejected() {
        System.out.println("MD5 checksum rejected");
        downloadFile(baseURL + zippedFileName, zippedFileName);
        startChecks();
    }

    /**
     * if the MD5 succeeds, this method will be called, this will call the methods to analyse the files
     * and print the relevant information
     */
    public static void MD5accepted() {
        System.out.println("MD5 checksum accepted");
        unzipFile(zippedFileName, varFileName);
        varfile = new File(varFileName);
        snpfile = new File(snpFileName);
        createData();
    }

    public static void createData() {
        System.out.print("Creating data...");
        readFile(varfile, 1);
        readFile(snpfile, 2);
        System.out.println("Done");

        Collections.sort(hits);
        for (Variant s : hits) {
            System.out.println(s);
        }
        System.out.println("Results: " + hits.size());
    }

    /**
     * calls Tools.UnZip to unzip the downloaded file
     *
     * @param compressedFile   path to zip file
     * @param decompressedFile filename of decompressed file
     * @see Tools.UnZip
     */
    public static void unzipFile(String compressedFile, String decompressedFile) {
        new Tools.UnZip(compressedFile, decompressedFile);
    }

    /**
     * method calls the method which checks whether variant_file.txt and/or the 23andme file exist
     * this method is used to feed the right file and name into the checkIfExists method
     */
    private static void checkFiles() {
        downloadFile(baseURL + MD5FileName, MD5FileName);
        Path[] paths = {Paths.get(zippedFileName), Paths.get(snpFileName)};
        for (int i = 0; i < paths.length; i++) {
            checkIfExists(paths[i], i);
        }
    }

    /**
     * checks if file exist if no it will call the downloadFile method
     *
     * @param filepath path of file to check
     * @param type     name of file to check
     */
    private static void checkIfExists(Path filepath, int type) {
        if (Files.notExists(filepath)) {
            System.out.print("File not found: ");
            switch (type) {
                case 0: {
                    System.out.println(zippedFileName);
                    downloadFile(baseURL + zippedFileName, zippedFileName);
                    break;
                }
                case 1: {
                    System.out.println(snpFileName);
                    downloadFile("https://opensnp.org/data/9313.23andme.7630?1577427270", snpFileName);
                    break;
                }
            }
        }
    }

    /**
     * method calls Tools.FileDownloader to download specified file
     *
     * @param cURL     URL of the file which needs to be downloaded
     * @param filename name(path) of the file which needs to be downloaded
     * @see Tools.FileDownloader
     */
    private static void downloadFile(String cURL, String filename) {
        new Tools.FileDownloader(cURL, filename);
    }

    /**
     * methods opens specified file and reads the lines, type is used to specify where to switch to
     * (type is used in splitnSwitch which determines whether to call addVariant of addSNP method)
     *
     * @param file varfilename/snpfilename
     * @param type 1: varFile, 2: snpfFile
     */
    private static void readFile(File file, int type) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) != '#') {
                    splitnSwitch(line, type);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method splits the lines and switches to appropriate method based on type
     *
     * @param line line in file
     * @param type 1: varFile, 2: snpFile
     */
    private static void splitnSwitch(String line, int type) {
        String[] splLine = line.split("\t");
        switch (type) {
            case 1:
                addVariant(splLine);
                break;
            case 2:
                checkSNP(splLine);
                break;
        }
    }

    /**
     * checks if the variant is from correct version, creates an object Variant and places it in Variants HashMap
     * key = chromosome# - position# - rsID - alt allele ; value = Variant
     *
     * @param line line in varFile
     */
    private static void addVariant(String[] line) {
        if (line[16].equals("GRCh37"))
            try {
                Variant variant = new Variant(Integer.parseInt(line[0]), Integer.parseInt(line[19]),
                        Integer.parseInt(line[7]), Integer.parseInt(line[3]), line[1], line[22],
                        line[21], line[18], line[13]);
                Variants.put(String.format("%s-%s", line[9], line[22]), variant);

                /*
                the key is used to match snpFile to varFile, because a HashMap is used this is quite fast
                 */
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
    }

    /**
     * method checks the Variants HasMap for specified key, formatted like Variant, when found its added to hits
     * match strategy: rsID and alt allele from snpFile;
     * String.format those; Variants.get(formatted string)
     * if for instance AA or TT is found in snpFile it gets shortened by 1 so it will match with varFile
     *
     * @param line line in snpFile
     * @since 1.2
     */
    private static void checkSNP(String[] line) {
        if (line[3].length() > 1 && line[3].charAt(0) == (line[3].charAt(1)))
            line[3] = String.valueOf(line[3].charAt(0));
        String keyword = String.format("%s-%s", line[0].replaceFirst("(rs|i)", ""), line[3]);
        Variant match = Variants.get(keyword);
        if (match != null) {
            hits.add(match);
        }
    }
}
