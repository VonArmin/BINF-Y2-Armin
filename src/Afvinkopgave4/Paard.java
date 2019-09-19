package Afvinkopgave4;

import java.awt.*;
import java.util.Random;

class Paard {
    private int afstand;

    public void setPaardNummer(int paardNummer) {
        this.paardNummer = paardNummer;
    }

    private int paardNummer;
    private static int aantalpaarden = 0;
    private String naam;
    private Color kleur;
    private Image plaatje;
    Random random = new Random();

    Paard(String name, Color color) {
        this.naam = name;
        this.kleur = color;
        this.afstand = 4;
        aantalpaarden++;
    }

    Paard(String name) {
        this.naam = name;
        this.kleur = Color.green;
    }

    String getName() {
        return this.naam;
    }

    int getDistance() {
        return this.afstand;
    }

    Color getColor() {
        return this.kleur;
    }

    Image getImage() {
        return this.plaatje;
    }

    int getPaardNummer() {
        return this.paardNummer;
    }

    void setNaam(String naam) {
        this.naam = naam;
    }

    Random getRandom() {
        return this.random;
    }

    String getinfo() {
        String streep = "-------------------------------------------";
        System.out.println(streep);
        return String.format("%s %s %s %s \n %s", this.naam, this.paardNummer, this.kleur, this.afstand, streep);
    }
}

