package madeexercises.dynProg.solvers;

import madeexercises.dynProg.Solver;

/**
 * Created by kevin on 5-3-2016.
 */
public class TopDownSolver implements Solver {

    private Boolean[][] solution;

    public boolean solve(int[] numbers, int sum) {
        solution = new Boolean[numbers.length][sum + 1];
        return findSolution(numbers, sum);
    }

    private  boolean findSolution(int[] numbers, int sum) {
        if( sum == 0 ) {
            return true;
        }
        if( numbers.length == 0 || sum < 0) {
            return false;
        }
        if(solution[numbers.length - 1][sum] == null) {
            solution[numbers.length - 1][sum] = doSolve(numbers, sum);
        }
        return solution[numbers.length - 1][sum];
    }

    private boolean doSolve(int[] numbers, int sum) {
        int n = numbers.length - 1;
        int[] shorterNumbersCopy = new int[n];
        for(int i = 0; i < n; i++) {
            shorterNumbersCopy[i] = numbers[i];
        }
        return findSolution(shorterNumbersCopy, sum)
                || findSolution(shorterNumbersCopy, sum - numbers[n]);
    }
}
