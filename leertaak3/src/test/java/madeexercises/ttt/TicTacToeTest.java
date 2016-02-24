package madeexercises.ttt;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevin on 24-2-2016.
 */
public class TicTacToeTest {

    private TicTacToe game;

    public TicTacToeTest(){
        game = new TicTacToe();
    }

    @Test
    public void testPositionValue() {
        game = new TicTacToe();
        game.setComputerPlays();
        assertEquals(TicTacToe.UNCLEAR, game.positionValue());

        game.playMove(0);
        game.playMove(6);
        game.playMove(1);
        game.playMove(7);
        game.playMove(2);
        assertEquals(TicTacToe.COMPUTER_WIN, game.positionValue());

        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(6);
        game.playMove(1);
        game.playMove(7);
        game.playMove(2);
        assertEquals(TicTacToe.HUMAN_WIN, game.positionValue());

        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(1);
        game.playMove(2);
        game.playMove(5);
        game.playMove(3);
        game.playMove(6);
        game.playMove(7);
        game.playMove(8);
        game.playMove(4);
        assertEquals(TicTacToe.DRAW, game.positionValue());
    }

    @Test
    public void testIsAWin() {
        game = new TicTacToe();
        game.setHumanPlays();
        game.playMove(0);
        game.playMove(6);
        game.playMove(4);
        game.playMove(7);
        game.playMove(8);
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