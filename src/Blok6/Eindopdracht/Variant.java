package Blok6.Eindopdracht;

/**
 * Variant class of Eindopdracht blok 6
 *
 * @author Armin van Eldik
 * @version 1.1
 */
public class Variant implements Comparable<Variant> {

    private int alleleId;
    private int position;
    private int pathogenicity;
    private int geneId;
    private int chrnr;

    private String type;
    private String alternateAllele;
    private String referenceAllele;
    private String chromosome;
    private String disease;

    /**
     * constructor for Variant Object
     * params are discribed which column they use from varFile
     *
     * @param alleleId        #AlleleID
     * @param position        Position; position on chromosome.
     * @param pathogenicity   ClinSigSimple; 1: Pathogenic, 0: Benign, -1: no interpretation.
     * @param geneId          GeneID
     * @param type            Type; Type of mutation (SNP, deletion, insertion etc.).
     * @param alternateAllele AlternateAllele; Mutation.
     * @param referenceAllele ReferenceAllele; Allele found in refseq.
     * @param chromosome      Chromosome; Chromosome number (can be 1-22 X, Y, MT)
     * @param disease         Phenotype; Phenotype (disease) associated with this variant.
     */
    Variant(int alleleId, int position, int pathogenicity, int geneId,
            String type, String alternateAllele, String referenceAllele, String chromosome, String disease) {
        this.alleleId = alleleId;
        this.position = position;
        this.pathogenicity = pathogenicity;
        this.geneId = geneId;
        this.type = type;
        this.alternateAllele = alternateAllele;
        this.referenceAllele = referenceAllele;
        this.chromosome = chromosome;
        this.disease = disease;
        setChrnr(chromosome);
    }

    /**
     * to make the sorting work correctly all chromosomes get an int with chrnr=chromosome and chrnr23,24,25=X,Y,MT
     *
     * @param chromosome String of chromosome-number/symbol
     */
    public void setChrnr(String chromosome) {
        switch (chromosome) {
            case "X":
                this.chrnr = 23;
                break;
            case "Y":
                this.chrnr = 24;
                break;
            case "MT":
                this.chrnr = 25;
                break;
            default:
                this.chrnr = Integer.parseInt(chromosome);
        }
    }

    /**
     * override toString method
     */
    @Override
    public String toString() {
        return String.format("pathogenicity: %s, chromosome: %s, position: %s, disease: %s", pathogenicity, chromosome, position, disease);
    }

    /**
     * method used to sort Variants; pathogenicity, chromosome, position on chromosome
     *
     * @param variant supplied by Collections when called
     * @return int of position (can be + 0 -)
     * @since 1.1
     */
    @Override
    public int compareTo(Variant variant) {
        int i = Integer.compare(variant.pathogenicity, this.pathogenicity);
        if (i != 0) return i;
        i = Integer.compare(this.chrnr, variant.chrnr);
        if (i != 0) return i;

        return Integer.compare(this.position, variant.position);
    }
}