package madeexercises.dynProg.solvers;

import madeexercises.dynProg.Solver;

/**
 * Created by kevin on 5-3-2016.
 */
public class RecursiveSolver implements Solver {

    public boolean solve(int[] numbers, int sum) {
        // always true
        if (sum == 0) {
            return true;
        }
        // not possible
        if( numbers.length == 0 ){
            return false;
        }

        // copy values from array in shorter array
        int n = numbers.length - 1;
        int[] shorterNumbersCopy = new int[n];
        for(int i = 0; i < n; i++){
            shorterNumbersCopy[i] = numbers[i];
        }

        // check if sum is possible with shorter array or sum minus current number will result in zero
        return solve(shorterNumbersCopy, sum)
                || solve(shorterNumbersCopy, sum - numbers[n]);
    }
}
