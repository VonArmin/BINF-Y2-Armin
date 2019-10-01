package Afvinkopgave5;

class makethree {
    private static final String[] ONE = {"A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K"
            , "M", "F", "P", "S", "T", "W", "Y", "V"};
    private static final String[] THREE = {"ALA", "ARG", "ASN", "ASP", "CYS", "GLN", "GLU", "GLY"
            , "HIS", "ILE", "LEU", "LYS", "MET", "PHE", "PRO", "SER"
            , "THR", "TRP", "TYR", "VAL"
    };

    static String getthree(String A) throws NotAnAA {
        for (int i = 0; i < ONE.length; i++) {
            if (A.equals(ONE[i])) {
                return THREE[i];
            }
        }
        throw new NotAnAA("dit is geen AA: "+A);
    }
}

class NotAnAA extends Exception {

    NotAnAA() {
        super();
    }

    NotAnAA(String err) {
        super(err);
    }
}