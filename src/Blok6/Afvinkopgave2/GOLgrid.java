package Blok6.Afvinkopgave2;

import java.util.Arrays;

class GOLgrid {
    private static int size;
    private static boolean run;
    private static boolean[][] oldgrid;
    private static boolean[][] newgrid;

    GOLgrid(int size) {
        GOLgrid.size = size;
        oldgrid = new boolean[size][size];
        newgrid = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                oldgrid[row][col] = false;
                newgrid[row][col] = false;
            }
        }
    }

    static void tick(int row, int col) {
        oldgrid[row][col] = true;
        newgrid[row][col] = true;
    }

    static void untick(int row, int col) {
        oldgrid[row][col] = false;
        newgrid[row][col] = true;

    }

    @Override
    public String toString() {
        return Arrays.deepToString(oldgrid);
    }

    static boolean[][] getGrid() {
        return oldgrid;
    }

    static void clearGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                oldgrid[i][j] = false;
                newgrid[i][j] = false;
            }
        }

    }

    static void step() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                livecells(row, col);
            }
        }
        for (int row = 0; row < size; row++) {
            System.arraycopy(newgrid[row], 0, oldgrid[row], 0, size);
        }
    }

    private static void livecells(int row, int col) {
        int live = 0;
        boolean state = oldgrid[row][col];
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    if (oldgrid[(row + i)][(col + j)]) {
                        live++;
                    }
                } catch (IndexOutOfBoundsException ignore) {
                }
            }
        }
        if (state) {
            live--;
        }
        mutate(live, row, col, state);
    }

    private static void mutate(int live, int row, int col, boolean state) {
        if (state) {
            if (live != 2 && 3 != live) {
                newgrid[row][col] = false;
            }
        } else if (live == 3) {
            newgrid[row][col] = true;
        }
    }
}
