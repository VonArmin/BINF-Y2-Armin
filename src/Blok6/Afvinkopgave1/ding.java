package Blok6.Afvinkopgave1;

import java.time.Duration;
import java.time.Instant;

public class ding {
    public static void main(String[] args) {
        int n =14;
        Instant before = Instant.now();
        fac(n);
        Instant after   = Instant.now();
        long delta = Duration.between(before, after).toMillis(); // .toWhatsoever()
        System.out.println(delta);
    }private static void fac(int n){
        for (int i=0;i<n;i++){
            fac(n-1);
        }
    }
}
