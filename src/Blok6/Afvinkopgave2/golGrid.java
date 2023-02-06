package Blok6.Afvinkopgave2;

import java.util.Arrays;

class golGrid
{
    private static int size;
    private final boolean[][] oldGrid;
    private final boolean[][] newGrid;

    public golGrid(int size)
    {
        golGrid.size = size;
        oldGrid = new boolean[size][size];
        newGrid = new boolean[size][size];
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                oldGrid[row][col] = false;
            }
        }
    }

    public void tick(int row, int col)
    {
        oldGrid[row][col] = !oldGrid[row][col];
    }


    public boolean[][] getGrid()
    {
        return oldGrid;
    }

    @Override
    public String toString()
    {
        return Arrays.deepToString(oldGrid);
    }

    public void clearGrid()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                oldGrid[i][j] = false;
            }
        }
    }

    public void step()
    {// steps current state of the grid to the next
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                liveCells(row, col);
            }
        }
        for (int row = 0; row < size; row++)
        {
            System.arraycopy(newGrid[row], 0, oldGrid[row], 0, size);
        }
    }

    private void liveCells(int row, int col)
    {// fetches live neighbouring cells
        int live = 0;
        boolean state = oldGrid[row][col];
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                try
                {
                    if (oldGrid[(row + i)][(col + j)])
                    {
                        live++;
                    }
                } catch (IndexOutOfBoundsException ignore)
                {
                }
            }
        }
        if (state)
        {
            live--;
        }
        mutate(live, row, col, state);
    }

    private void mutate(int live, int row, int col, boolean state)
    {// mutates the cell according to Conways GOL rules in a new grid
        if (state)
        {
            if (live != 2 && 3 != live)
            {
                newGrid[row][col] = false;
            }
        }
        else if (live == 3)
        {
            newGrid[row][col] = true;
        }
    }
}
