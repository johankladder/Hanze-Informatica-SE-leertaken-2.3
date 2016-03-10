package madeexercises.dynProg.solvers;

import madeexercises.dynProg.Solver;

/**
 * Created by kevin on 5-3-2016.
 */
public class BottomUpSolver implements Solver {

    public boolean solve(int[] numbers, int sum) {
        int n = numbers.length - 1;
        boolean[][] solution = new boolean[n + 1][sum + 1];
        // Sum is true if i is equal to number
        for(int i = 0; i < sum + 1; i++){
            solution[0][i] = i == 0 || numbers[0] == i ;
        }

        int row = 1;
        while(n >= row){
            for(int col = 0; col < sum + 1; col++){
                // true if equal to self or in previous row
                solution[row][col] = col == numbers[row] || solution[row - 1][col];
                if(col - numbers[row] >= 0){
                    // true if row above and column minus number is true
                    solution[row][col] |= solution[row - 1][col - numbers[row]];
                }
            }
            row++;
        }

        return solution[n][sum];
    }
}
