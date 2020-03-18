package Blok5.biotools.bioseq;

import Blok5.biotools.bioapp.*;

public class SeqVis {
    public static void main(String[] args) {
        DNA seq1 = new DNA("AACGTACGTACGTACGATCGATCG");
        RNA seq2 = new RNA("AUUGAUAGUAGAUGAGUAGAUAUA");
        System.out.println(seq1.getSeq());
        System.out.println(seq1.getLen());
        System.out.println(seq1.getColor());
        System.out.println(seq2.getColor());
        System.out.println(seq1.getGC());

    }
}
