package strategy;

import models.Board;
import models.Move;

import java.util.List;
import java.util.Optional;

public class SimpleUndoRedoStrategy implements UndoRedoStrategy{
    @Override
    public Board undo(Board board, List<Move> moves, int undoIndex) {
        int row = moves.get(undoIndex).getRow();
        int col = moves.get(undoIndex).getCol();
        board.getBoard().get(row).get(col).setPlayer(Optional.empty());
        return board;
    }

    @Override
    public Board redo(Board board, List<Move> moves, int redoIndex) {
        int row = moves.get(redoIndex).getRow();
        int col = moves.get(redoIndex).getCol();
        board.getBoard().get(row).get(col).setPlayer(Optional.of(moves.get(redoIndex).getPlayer()));
        return board;
    }
}
