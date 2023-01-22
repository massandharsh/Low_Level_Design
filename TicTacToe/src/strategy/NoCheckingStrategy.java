package strategy;

import models.Board;
import models.Move;

public class NoCheckingStrategy implements WinningStrategy{
    @Override
    public boolean checkWin(Board board, Move lastMove) {
        return false;
    }

    @Override
    public void undo(Board board, Move lastMove) {

    }
}
