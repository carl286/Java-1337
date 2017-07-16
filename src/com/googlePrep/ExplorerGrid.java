package com.googlePrep;

public class ExplorerGrid {

    /*
    4.) 2D array with walls and empty cells. Find the LONGEST way out from first row to the bottom row.
Each cell can only be visited once, can only go left, right and down direction.
Can start at any cell from the top row as well as can exit from any cell in the bottom row, can only start and exit from empty cell.
     */
    public int findLongestPath(boolean [][] grid)
    {
        return -1;
    }

    public static void main(String [] args)
    {
        ExplorerGrid explorere = new ExplorerGrid();
        boolean [][] grid = new boolean[][]
        {
            {true, false, false, true},
            {true, false, false, true},
            {true, false, false, true},
            {true, false, false, true},
        };
        System.out.println(explorere.findLongestPath(grid));

    }
}
