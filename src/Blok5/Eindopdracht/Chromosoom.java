package Blok5.Eindopdracht;

public class Chromosoom {
    private int chrNumber;
    private double totalLength;
    private int geneCount;
    private int shortest = 0;
    private int longest;

    Chromosoom(int chrNumber) {
        setChrNumber(chrNumber);

    }

    int getChrNumber() {
        return chrNumber;
    }

    private void setChrNumber(int chrNumber) {
        this.chrNumber = chrNumber;
    }

    public double getTotalLength() {
        return totalLength;
    }

    int getShortest() {
        return shortest;
    }

    /**
     * omdat shortest op 0 staat, en je kijkt of het kleiner is dan 0 wordt deze eerst op de hoogste value gezet
     *
     * @param num verschil tussen end en start.
     */
    private void setShortest(int num) {
        if (this.shortest == 0) {
            this.shortest = this.longest;
        }
        if (num < this.shortest) {
            this.shortest = num;
        }
    }

    int getLongest() {
        return longest;
    }

    private void setLongest(int num) {
        if (num > this.longest) {
            this.longest = num;
        }
    }

    double getAverageGenLength() {
        return Math.round((totalLength) / geneCount);
    }

    void addGene(int start, int end) throws geneException {
        if ((end - start) < 1) {
            throw new geneException("lengthe is lager of gelijk aan 0");
        }
        this.totalLength += (end - start);
        setLongest(end - start);
        setShortest(end - start);
        geneCount++;
    }

    public int getgeneCount() {
        return geneCount;
    }

}

class geneException extends Exception {
    geneException(String err) {
        super(err);
    }
}