package madeexercises.ttt;

import junit.framework.TestCase;

/**
 * Created by kevin on 24-2-2016.
 */
public class TicTacToeTest extends TestCase {

    private TicTacToe game;

    public TicTacToeTest(String arg0){
        super(arg0);
        game = new TicTacToe();
    }

    public void testPositionValue() {

    }

    public void testIsAWin() {
        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(8);
        game.playMove(1);
        game.playMove(7);
        game.playMove(2);
        assertEquals(true, game.isAWin(TicTacToe.HUMAN));
        assertEquals(false, game.isAWin(TicTacToe.COMPUTER));

        game = new TicTacToe();
        game.setComputerPlays();
        game.playMove(0);
        game.playMove(6);
        game.playMove(1);
        game.playMove(7);
        game.playMove(2);
        assertEquals(true, game.isAWin(TicTacToe.COMPUTER));
        assertEquals(false, game.isAWin(TicTacToe.HUMAN));

        game = new TicTacToe();
        assertEquals(false, game.isAWin(TicTacToe.HUMAN));
        assertEquals(false, game.isAWin(TicTacToe.COMPUTER));
    }

    public void testChooseMove() {

    }
}