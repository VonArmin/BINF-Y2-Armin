package Blok6.Afvinkopgave6;

public class GraphTask {
    private static String[] reads = {"AAATAAA", "AAATTTT", "TTTTCCC", "AAATCCC", "GGGTGGG"};

    public static void main(String[] args) {
        new Graph();
        for (String s : reads) {
            Graph.addVertex(s);
        }
        Graph.addEdges();
        Graph.getOverlap();
    }
}
