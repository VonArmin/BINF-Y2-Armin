package Blok5.oefentoets;
/** Martijn van der Bruggen/Hogeschool van Arnhem en Nijmegen
 * november 2018 Alle code is compileerbaar en uitvoerbaar */
public class DNA {
    private static int aantal=0;
    public String sequentie;
    public DNA() {
        aantal++;
    }
    public DNA(String sequentie) {
        this(); // call van No-args-constructor
        this.sequentie = sequentie;
    }
    public static int getAantal() {
        return aantal;
    }
    public String getSequentie() {
        return sequentie;
    }
    public void setSequentie(String sequentie) {
        this.sequentie = sequentie;
    }
    public int getLength() {
        return sequentie.length();
    }
    @Override
    public String toString() {
        return sequentie;
    }
}