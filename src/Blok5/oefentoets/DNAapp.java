package Blok5.oefentoets;
/** Martijn van der Bruggen/Hogeschool van Arnhem en Nijmegen
 * november 2018 Alle code is compileerbaar en uitvoerbaar */
public class DNAapp {
    public static void main(String[] args) {
        DNA dna1 = new DNA("CGAT");
        DNA dna2 = new DNA("CTT");
        DNA dna3 = new DNA("CGCAT");
        DNA dna4 = new DNA("CGCAT");
        String dna5 = new String("CGAT");
        DNA dna6 = new DNA("AATT");
        System.out.print("1.");
        System.out.println(DNA.getAantal());
        System.out.print("2.");
        System.out.println(dna1);
        System.out.print("3.");
        System.out.println(dna2.toString());
        System.out.print("4.");
        System.out.println(dna4.getLength() == dna5.length());
        System.out.print("5.");
        System.out.println(dna1.getSequentie() == dna5);
        System.out.print("6.");
        System.out.println(dna3.getSequentie().equals(dna4.sequentie));
        dna1.setSequentie(dna2.sequentie.toLowerCase());
        System.out.print("7.");
        System.out.println(dna1.equals(dna2));
        System.out.print("8.");
        dna1 = new DNA(dna5);
        System.out.println(dna1);
        System.out.print("9.");
        System.out.println(DNA.getAantal());
        System.out.print("10.");
        System.out.println(dna2.sequentie.replace('T', 'x'));
        System.out.print("11.");
        System.out.println(dna1.toString().charAt(2) == 'A');
        System.out.print("12.");
        System.out.println(dna2.getAantal());

        System.out.print("13.");
        char letter = dna1.getSequentie().charAt(1);
        switch (letter) {
            case 'G':
                System.out.print(dna2.sequentie.charAt(0));
            case 'A':
                System.out.print(dna2.sequentie.charAt(1));
            default:
                System.out.println(dna2.sequentie.charAt(2));
        }
        System.out.print("14.");
        int a = 2, b = 3, c = 4;
        if (++a == b++ && ++b == c++) {
            System.out.println(a);
        } else {
            System.out.println(b);
        }
        System.out.print("15.");
        int d = 2, e = 3, f = 4;
        if (++d == ++e && e++ == f++) {
            System.out.println(e);
        } else {
            System.out.println(f);
        }
        System.out.print("16.");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                new DNA();
            }
        }
        System.out.println(dna1.getAantal());
        System.out.print("17.");
        int teller = 8;
        do {
            teller++;
        } while (teller < 0);
        System.out.println(teller);
        System.out.print("18.");
        teller = 0;
        while (teller > 5) {
            teller++;
        }
        System.out.println(teller);
        System.out.print("19.");
        for (int i = 0; i < 5; i++) {
        }
        System.out.println(DNA.getAantal());
        System.out.print("20.");
        System.out.println(dna1.getSequentie().concat(dna1.getSequentie()));
    }
}