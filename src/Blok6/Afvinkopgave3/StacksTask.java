package Blok6.Afvinkopgave3;
// theoretische opdracht
// opdracht 1.
// stap. Stack
// 1. 5
// 2. 35
// 3. 5
// 4. 25
// 5. 825
// 6. 25
// 7. 5
// 8. 95
// 9. 195

// opdracht 2.
// als er elke keer een push("{[(") gedaan wordt en elke keer dat een pop{} gedaan wordt welke past bij de stack.peek()
// zou de stack leeg moeten zijn,
// als dit niet het geval is (dus er nog iets op de stack) moet er dus nog een ongesloten )]} zijn

import java.util.*;

public class StacksTask {
    private static Stack<Character> stack = new Stack<>();

    private static char[] left = {'(', '{', '['};
    private static char[] right = {')', '}', ']'};

    public static void main(String[] args) {
        checkString();
    }

    private static void checkString() {
        String string = "GOLgrid(int size) {\n" +
                "        GOLgrid.size = size;\n" +
                "        oldgrid = new boolean[size][size];\n" +
                "        newgrid = new boolean[size][size];\n" +
                "        for (int row = 0; row < size; row++) {\n" +
                "            for (int col = 0; col < size; col++) {\n" +
                "                oldgrid[row][col] = false;\n" +
                "                newgrid[row][col] = false;\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        String missing = "";
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < 3; j++) {
                if (string.charAt(i) == left[j]) {
                    stack.push(string.charAt(i));
                } else if (string.charAt(i) == right[j] && stack.size() == 0) {
                    missing += left[indexRight(string.charAt(i))];
                }
            }
            if (stack.size() > 0) {
                if (indexLeft(stack.peek()) == indexRight(string.charAt(i))) {
                    stack.pop();
                }
            }
        }
        if (missing.length() > 0) {
            System.out.println("je mist: " + missing);
        }
        else if (stack.size() > 0) {
            System.out.println("verwacht: "+ right[indexLeft(stack.peek())]);
        }else{
            System.out.println("hij compiled!");
        }
    }

    private static int indexRight(char obj) {
        int index = left.length+1;
        for (int i = 0; i < left.length; i++) {
            if (right[i] == obj) {
                index = i;
            }
        }
        return index;
    }

    private static int indexLeft(char obj) {
        int index = left.length+2;
        for (int i = 0; i < left.length; i++) {
            if (left[i] == obj) {
                index = i;
            }
        }
        return index;
    }
}
