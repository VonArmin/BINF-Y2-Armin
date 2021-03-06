package Blok5.Afvinkopgave4;

import java.awt.*;
import java.util.Random;

class Horse {
    private int Distance;
    private static int aantalpaarden;
    private int paardNummer = aantalpaarden;
    private String naam;
    private Color kleur;


    Horse(String name) {
        this.naam = name;
        this.setColor();
        aantalpaarden++;
    }


    String getName() {
        return this.naam;
    }

    int getDistance() {
        return this.Distance;
    }

    Color getColor() {
        return this.kleur;
    }

    int getPaardNummer() {
        return paardNummer;
    }

    void setNaam(String naam) {
        this.naam = naam;
    }

    private void setColor() {
        this.kleur = new Color(HighRandom(), HighRandom(), HighRandom());
    }

    private int HighRandom() {
        return new Random().nextInt(256);
    }

    private int getRandom() {
        return new Random().nextInt(50);
    }

    void run() {
        Distance += getRandom();
    }

    String getinfo() {
        String streep = "-------------------------------------------\n";
        System.out.println(streep);
        return String.format("%s %s %s %s \n %s %s", this.naam, paardNummer, this.kleur, this.Distance, this.getRandom(), streep);
    }
}