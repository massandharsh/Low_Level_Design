package strategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private Map<Integer, HashMap<Player, Integer>> colMatcher;

    public ColumnWinningStrategy() {
        colMatcher = new HashMap<>();
    }
    @Override
    public boolean checkWin(Board board, Move lastMove) {
        int col = lastMove.getCol();
        Player player = lastMove.getPlayer();
        if (!colMatcher.containsKey(col)) {
            colMatcher.put(col, new HashMap<>());
        }
        Map<Player, Integer> colMap = colMatcher.get(col);
        if (!colMap.containsKey(player)) {
            colMap.put(player, 0);
        }
        int count = colMap.get(player) + 1;
        colMap.put(player, count);
        return count == board.getSize();
    }

    @Override
    public void undo(Board board, Move lastMove) {
        int row = lastMove.getCol();
        Player player = lastMove.getPlayer();
        if(!colMatcher.containsKey(row)) return;
        Map<Player, Integer> colMap = colMatcher.get(row);
        if (colMap == null) {
            return;
        }
        int count = colMap.get(player);
        if (count == 1) {
            colMap.remove(player);
        } else {
            colMap.put(player, count - 1);
        }
    }
}
