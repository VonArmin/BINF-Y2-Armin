package Blok6.Afvinkopgave6;

import java.util.ArrayList;

public class Graph {
    private static ArrayList<Edge> edges;
    private static ArrayList<Vertex> vertexes;

    Graph() {
        edges = new ArrayList<>();
        vertexes = new ArrayList<>();
    }

    public static void addVertex(String read) {
        vertexes.add(new Vertex(read));
    }

    public static void addEdge(Vertex dest, Vertex src, int weight) {
        edges.add(new Edge(dest, src, weight));
    }

    public static void addEdges() {
        for (Vertex src : vertexes) {
            for (Vertex dst : vertexes) {
                int overlap = 0;
                if (src != dst) {
                    for (int i = 1; i < src.getVertex().length() + 1; i++) {
                        if (src.getVertex().substring(0, i).equals(dst.getVertex().substring(dst.getVertex().length() - i))) {
                            overlap = i;
                        }
                    }
                    addEdge(src, dst, overlap);
                }
            }
        }
    }

    public static void getOverlap() {
        for (Edge e : edges) {
            if (e.getWeight() > 0) {
                String substring = e.getV1()+e.getDst().substring(e.getWeight());
                System.out.println(String.format("%s to %s with %s overlap: %s", e.getV1(), e.getDst(), e.getWeight(),substring));
            }
        }
    }

    static class Vertex {
        private String read;

        Vertex(String read) {
            this.read = read;
        }

        String getVertex() {
            return this.read;
        }

        @Override
        public String toString() {
            return this.read;
        }
    }

    static class Edge {
        private Vertex V1, V2;
        private int weight;

        Edge(Vertex V2, Vertex V1, int weight) {
            this.V1 = V1;
            this.V2 = V2;
            this.weight = weight;
        }

        public int getWeight() {
            return this.weight;
        }

        public String getV1() {
            return this.V1.toString();
        }

        public String getDst() {
            return this.V2.toString();
        }

        @Override
        public String toString() {
            return String.format("%s to %s, overlap: %s", this.V1, this.V2, this.weight);
        }
    }
}
