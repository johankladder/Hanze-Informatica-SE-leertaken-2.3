package madeexercises.cards;


import java.util.Stack;

/**
 * the solution is a sequence of cards placed on the board according to the card positions
 * example without border
 */
public class Solution extends Stack<Candidate> {
    // The board is an 2D array.
    // 0123
    // 0..-.
    // 1---.
    // 2.---
    // 3..-.

    private static final int ROW_COUNT = 4;
    private static final int COLUMN_COUNT = 4;
    private Candidate[][] board = new Candidate[ROW_COUNT][COLUMN_COUNT];

    // card positions on the board
    // the first card position on the board are
    // {0,2}, {1,0}. {1,1}
    private int[] row = {0, 1, 1, 1, 2, 2, 2, 3};
    private int[] column = {2, 0, 1, 2, 1, 2, 3, 2};

    //
    // array with adjacent card positions lower than the card that is placed
    //                        0   1   2       3    4       5    6   7
    //int [] [] adjacent  = { {}, {}, {1}, {0,2}, {2},  {3,4}, {5},{5}  };
    //
    // array with all adjacent card positions
    //                         0    1        2        3      4          5     6   7
    //int [] [] adjacent  = { {3}, {2}, {1,3,4}, {0,2,5}, {2,5},  {3,4,6,7}, {5},{5}  };
    //
    // array with positions to check for bordering
    //
    //  indices cards that must be checked.
    //  e.g. when the 5th card is placed the cards 3&4 can be checked
    //                 0   1  2   3   4     5   6    7
    int[][] check = {{}, {}, {1}, {0}, {2}, {3, 4}, {6}, {5, 7}};


    public Solution() {
    }


    // Checks whether a candidate with card CardChar is in
    // an adjacent position of the board position (row, column)
    // @param row, column, candidate
    // @return Boolean indicating if cardChar is found.
    // can be used in the methods fits and isCorrect
    private boolean bordersCard(int row, int column, char cardChar) {

        if (row > 0) {
            char northChar = board[row - 1][column].getCardChar();
            if (northChar == cardChar) {
                return true;
            }
        }

        if (column > 0) {
            char westChar = board[row][column - 1].getCardChar();
            if (westChar == cardChar) {
                return true;
            }
        }

        if ((column + 1) < COLUMN_COUNT) {
            char eastChar = board[row][column + 1].getCardChar();
            if (eastChar == cardChar) {
                return true;
            }
        }

        if ((row + 1) < ROW_COUNT) {
            char southChar = board[row + 1][column].getCardChar();
            if (southChar == cardChar) {
                return true;
            }
        }

        return false;
    }


    /**
     * Checks whether candidate card of same kind.
     * Checks whether by placing candidate the solution sofar still complies with the rules
     *
     * @param candidate
     * @return boolean indicating whether this candidate can be put in the
     * next free position.
     */
    public boolean fits(Candidate candidate) {
        //TODO
        return true;
    }

    public void record(Candidate candidate) {
        int i = this.size(); // i= index in this stack of next for the next candidate
        board[row[i]][column[i]] = candidate; //x=row, y=column
        this.push(candidate);

    }

    public boolean complete() {
        return this.size() == 8;
    }

    public void show() {
        System.out.println(this);
    }

    public Candidate eraseRecording() {
        int i = this.size() - 1;           // i= index of the candidate that is removed from this Stack;
        board[row[i]][column[i]] = null; // remove candidate from board
        return this.pop();
    }

    // can be used in method isCorrect
    private char mustBeAdjacentTo(char card) {
        if (card == 'A') return 'K';
        if (card == 'K') return 'Q';
        if (card == 'Q') return 'J';
        return '?'; //error
    }

    /**
     * Checks whether the rules below are fulfilled
     * For the positions that can be checked for solution sofar.
     * Rules:
     * Elke aas (ace) grenst (horizontaal of verticaal) aan een heer (king).
     * Elke heer grenst aan een vrouw (queen).
     * Elke vrouw grenst aan een boer (jack).
     *
     * @return true if all checks are correct.
     */
    private boolean isCorrect() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {

                // Get char of position
                char foundCard = board[row][column].getCardChar();

                // Get the adjacent card of found chard
                char adjacentCard = mustBeAdjacentTo(foundCard);

                // Check if foundCard is bordering the adjacent card
                if (!bordersCard(row, column, adjacentCard)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * @return a representation of the solution on the board
     */
    public String toString() {
        //TODO
        return "";
    }

}
