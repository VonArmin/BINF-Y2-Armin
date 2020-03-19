package Blok6.Eindopdracht;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
//import statements

/**
 * collection of tools mostly copied from internet, cleaned up by @author.
 *
 * @author Armin van Eldik
 * @version 1.1
 */
public class Tools {
    public static class FileDownloader {
        /**
         * method downloads file from specified URL with specified filename
         *
         * @param cURL     URL of file
         * @param filename filename
         * @since 1.0
         */
        FileDownloader(String cURL, String filename) {
            try {
                System.out.print(String.format("Downloading %s...", filename));
                URL url = new URL(cURL);
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(filename);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("Done");
            } catch (IOException e) {
                System.out.println(" An Error occured; are you connected to the internet? ");

            }
        }
    }

    public static class UnZip {
        /**
         * method unzips file
         *
         * @param compressedFile   location of zip(gz)
         * @param decompressedFile location to extract to
         * @since 1.0
         */
        UnZip(String compressedFile, String decompressedFile) {
            try {
                byte[] buffer = new byte[1024];
                FileInputStream fileIn = new FileInputStream(compressedFile);
                GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
                FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);
                int bytes_read;
                System.out.println("Stand-by for decompression");
                while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, bytes_read);
                }

                gZIPInputStream.close();
                fileOutputStream.close();
                System.out.println("Decompression successful");
            } catch (IOException ex) {
                System.out.println(String.format("%s denied access, when encountering errors; delete the file and rerun.", decompressedFile));
                System.out.println("Program will continue");
                /*
                function throws a lot of exceptions when for instance the file is in use by something else,
                not sure how to solve, I just made it so it declares it when an exception occurs
                 */
            }
        }
    }

    public static class MD5Checker implements Callable<Boolean> {
        private static boolean MD5accepted;

        /**
         * method digests .gz file and compares the generated MD5 with the downloaded one,
         * sets MD5 accepted to true or false
         *
         * @since 1.0
         */
        MD5Checker() {
            try {
                String fileToCheck = "variant_summary.txt.gz";
                String generatedMD5 = buildString(Digest(fileToCheck));
                String MD5checksum = getMD5(fileToCheck);

                MD5accepted = generatedMD5.equals(MD5checksum);

            } catch (IOException | NoSuchAlgorithmException e) {
                System.out.println("MD5 file not found; halting program");
                System.exit(0);
            }
        }

        /**
         * method opens the downloaded file, digests it and returns the raw data
         *
         * @param fileToCheck file of which the MD5 checksum needs to be generated
         * @return raw digested data
         * @throws IOException              when using a FileInputStream this needs to be handled
         * @throws NoSuchAlgorithmException when selecting a digesting algorithm this needs to be handled
         * @since 1.1
         */
        private static byte[] Digest(String fileToCheck) throws IOException, NoSuchAlgorithmException {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(fileToCheck);

            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }

            fis.close();
            return digest.digest();
        }

        /**
         * creates a String of raw data
         *
         * @param bytes raw digested data
         * @return String of MD5 checksum
         * @since 1.1
         */
        private static String buildString(byte[] bytes) {
            StringBuilder generatedMD5 = new StringBuilder();
            for (byte aByte : bytes) {
                generatedMD5.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return generatedMD5.toString();
        }

        /**
         * method obtains MD5 checksum from .MD5 file
         *
         * @param fileToCheck base name of file
         * @return String of MD5 checksum
         * @throws FileNotFoundException when using Scanner class this needs to be handled.
         * @since 1.1
         */
        private static String getMD5(String fileToCheck) throws FileNotFoundException {
            File MD5fileTemp = new File(fileToCheck + ".md5");
            Scanner sc = new Scanner(MD5fileTemp);
            return sc.nextLine().split(" ")[0];
        }

        /**
         * classes cant return variables like methods can, thus Callable is implemented, which can return variables
         *
         * @return true/false (MD5Accepted)
         * @since 1.0
         */
        @Override
        public Boolean call() {
            System.out.print("Generating MD5 checksum...");
            new MD5Checker();
            System.out.println("Done");
            return MD5accepted;
        }
    }
}
