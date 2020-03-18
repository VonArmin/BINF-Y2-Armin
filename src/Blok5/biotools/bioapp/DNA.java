package Blok5.biotools.bioapp;

import java.awt.*;

public class DNA extends sequence {

    final Color colorCG = Color.red;
    final Color colorAT = Color.yellow;

    public DNA(String seqinvoer) {
        super(seqinvoer);
    }

    public double getGC() {
        double GC = 0;
        double GCperc;
        for (char c : getSeq().toCharArray()) {
            if (c == 'C' | c == 'G') {
                GC++;
            }
        }
        GCperc = GC / getSeq().length() * 100;
        return GCperc;
    }

}
