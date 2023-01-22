package strategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Player,Integer>> rowMatcher;
    public RowWinningStrategy(){
        this.rowMatcher = new HashMap<>();
    }
    @Override
    public boolean checkWin(Board board, Move lastMove) {
        int row = lastMove.getRow();
        Player player = lastMove.getPlayer();
        if (!rowMatcher.containsKey(row)) {
            rowMatcher.put(row, new HashMap<>());
        }
        Map<Player, Integer> rowMap = rowMatcher.get(row);
        if (!rowMap.containsKey(player)) {
            rowMap.put(player, 0);
        }
        int count = rowMap.get(player) + 1;
        rowMap.put(player, count);
        return count == board.getSize();
    }

    @Override
    public void undo(Board board, Move lastMove) {
        int row = lastMove.getRow();
        Player player = lastMove.getPlayer();
        if(!rowMatcher.containsKey(row)) return;
        Map<Player, Integer> rowMap = rowMatcher.get(row);
        if (rowMap == null) {
            return;
        }
        int count = rowMap.get(player);
        if (count == 1) {
            rowMap.remove(player);
        } else {
            rowMap.put(player, count - 1);
        }
    }
}
