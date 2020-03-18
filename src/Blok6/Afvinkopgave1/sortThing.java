package Blok6.Afvinkopgave1;

import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


public class sortThing {
    private static int numamt = 10;
    private static int[] numbers = new int[numamt];

    public static void main(String[] args) {
        makeList();
        sortList();

    }

    private static int getRandom() {
        return new Random().nextInt(1000000);
    }

    private static void makeList() {
        for (int i = 0; i < numamt; i++)
            numbers[i] = getRandom();
    }

    private static void sortList() {
        int highindex = 0;
        int lowindex = 0;
        int filter = maxValue() / 2;
        int[] sortTemp = new int[numamt];
        int[] lowTemp = new int[numamt];
        int[] highTemp = new int[numamt];
        for (int num : numbers)
            if (num > filter) {
                highTemp[highindex++] = num;
            } else {
                lowTemp[lowindex++] = num;
            }

        System.out.println(Arrays.toString(lowTemp));
        System.out.println(Arrays.toString(highTemp));

    }

    private static int maxValue() {
        int max = Arrays.stream(numbers).max().getAsInt();
        return max;
    }

}
