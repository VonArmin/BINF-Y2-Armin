package Blok5.biotools.bioapp;

import java.awt.*;
import java.util.Objects;

abstract class sequence {

    private static String sequentie;
    public static Color color;

    sequence(String seqinvoer) {
        sequentie=seqinvoer;
    }

    public void setSeq(String sequentieInvoer){
        sequentie=sequentieInvoer;
    }

    public static String getSeq(){
        return Objects.requireNonNullElse(sequentie, "geen sequentie bekend");
    }

    public static Color getColor(){
        return color;
    }

    public static double getLen(){
        return sequentie.length();
    }
}
