package madeexercises.ttt;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Random;

public class TicTacToe {

    private static final int ROW_COUNT = 3;
    private static final int COLUMN_COUNT = 3;

    public static final int HUMAN = 2;
    public static final int COMPUTER = 1;
    private static final int EMPTY = 0;

    public static final int HUMAN_WIN = 0;
    public static final int DRAW = 1;
    public static final int UNCLEAR = 2;
    public static final int COMPUTER_WIN = 3;

    private Random random = new Random();

    private int side = random.nextInt(2);
    private int position = UNCLEAR;

    private char computerChar, humanChar;

    private int[][] board = new int[ROW_COUNT][COLUMN_COUNT];

    // Constructor
    public TicTacToe() {
        clearBoard();
        initSide();
    }

    private void initSide() {
        if (this.side == COMPUTER) {
            computerChar = 'X';
            humanChar = 'O';
        } else {
            computerChar = 'O';
            humanChar = 'X';
        }
    }

    public void setComputerPlays() {
        this.side = COMPUTER;
        initSide();
    }

    public void setHumanPlays() {
        this.side = HUMAN;
        initSide();
    }

	public int chooseMove() {
		Best best = chooseMove(COMPUTER);
		return best.row * 3 + best.column;
		//return 0;
	}

    public boolean computerPlays() {
        return side == COMPUTER;
    }

    // Find optimal move
    private Best chooseMove(int side) {
        int opp;              // The other side
        Best reply;           // Opponent's best reply
        int simpleEval;       // Result of an immediate evaluation
        int bestRow = 0;
        int bestColumn = 0;
        int value;

        if ((simpleEval = positionValue()) != UNCLEAR)
            return new Best(simpleEval);

        // TODO: implementeren m.b.v. recursie/backtracking
        return null;
    }


    //check if move ok
    public boolean moveOk(int move) {
        //return ( move>=0 && move <=8 && board[move/3 ][ move%3 ] == EMPTY );
        return true;
    }

    // play move
    public void playMove(int move)
    {
		board[move/3][ move%3] = this.side;
		if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
	}


	// Simple supporting routines
	/**
	 * Clears board -> initialise board as an new int array with ROW_COUNT and COLUMN_COUNT
	 */
	private void clearBoard() {
		board = new int[ROW_COUNT][COLUMN_COUNT];
	}


	/**
	 * Check whether the board is full. The method is doing this with checking the entries in the two arrays from
	 * the board and will directly return it's result when it finds out if there is still place for another move.
	 *
	 * @return Boolean if the board is full or not
	 */
	private boolean boardIsFull() {

		for (int row = 0; row < ROW_COUNT; row++) {
			for (int column = 0; column < COLUMN_COUNT; column++) {
				// -> A '0' value means that there is still space on the board
				if (board[row][column] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// Returns whether 'side' has won in this position
	public boolean isAWin( int side )
	{
		if ((isAWinHorizontal(side)) || (isAWinVertical(side)) || (isAWinDiagonal(side))) {
			return true;
		} else {
			return false;
		}
    }

	private boolean isAWinDiagonal(int side) {
		Boolean isAWinDiagonal = false;
		for (int i = 0; i < 3 && !isAWinDiagonal; i++) {
			Boolean temp = true;
			int row = 0;
			for (int j = 0; j <= 1 && temp; j++) {
				if (j == 0) {
					row = i;
				} else if (j == 1) {
					if (i == 0) {
						row = 2;
					} else if (i == 1) {
						row = 1;
					} else if (i == 2) {
						row = 0;
					}
				}
				if (board[row][i] != side) {
					temp = false;
				}
				isAWinDiagonal = temp;
			}
		}
		return isAWinDiagonal;
	}

	private boolean isAWinVertical(int side) {
		Boolean isAWinVertical = false;
		for (int i = 0; i < 3 && !isAWinVertical; i++) {
			Boolean temp = true;
			for (int j = 0; j < 3 && temp; j++) {
				if (board[j][i] != side) {
					temp = false;
				}
			}
			isAWinVertical = temp;
		}
		return isAWinVertical;
	}

	private boolean isAWinHorizontal(int side) {
		Boolean isAWinHorizontal = false;
		for (int i = 0; i < 3 && !isAWinHorizontal; i++) {
			Boolean temp = true;
			for (int j = 0; j < 3 && temp; j++) {
				if (board[i][j] != side) {
					temp = false;
				}
			}
			isAWinHorizontal = temp;
		}
		return isAWinHorizontal;
	}

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue( ) {
		if(isAWin(COMPUTER)){
			return COMPUTER_WIN;
		} else if(isAWin(HUMAN)){
			return HUMAN_WIN;
		} else if(boardIsFull()){
			return DRAW;
		} else{
			return UNCLEAR;
		}
	}
	
	
	public String toString()
	{
	    //TODO:
		return "...\n...\n...\n";   
	}  
	
	public boolean gameOver()
	{
	    this.position=positionValue();
	    return this.position!=UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
	private class Best
    {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    }
}

