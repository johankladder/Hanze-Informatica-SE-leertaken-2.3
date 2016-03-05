package madeexercises.dynProg.solvers;

import madeexercises.dynProg.Solver;

/**
 * Created by kevin on 5-3-2016.
 */
public class RecursiveSolver implements Solver {

    public boolean solve(int[] numbers, int sum) {
        if (sum == 0) {
            return true;
        }
        if( numbers.length == 0 ){
            return false;
        }

        int n = numbers.length - 1;
        int[] shorterNumbersCopy = new int[n];
        for(int i = 0; i < n; i++){
            shorterNumbersCopy[i] = numbers[i];
        }

        return solve(shorterNumbersCopy, sum)
                || solve(shorterNumbersCopy, sum - numbers[n]);
    }
}
