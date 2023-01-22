package strategy;

import models.Board;
import models.Move;

import java.util.List;

public interface UndoRedoStrategy{

    Board undo(Board board, List<Move> moves, int undoIndex);

    Board redo(Board board, List<Move> moves, int redoIndex);
}
