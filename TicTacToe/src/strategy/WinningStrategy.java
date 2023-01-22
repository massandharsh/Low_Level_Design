package strategy;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    boolean checkWin(Board board, Move lastMove);
    void undo(Board board,Move lastMove);
}
