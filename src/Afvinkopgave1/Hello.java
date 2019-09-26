package Afvinkopgave1;

public class Hello {
    public static void main(String[] args) {
        String a = "charizard";
        String b = new String ("charizard");
        for (String s: args){
            System.out.println("hello "+s);
        }
        System.out.println(a==b);

    }
}
