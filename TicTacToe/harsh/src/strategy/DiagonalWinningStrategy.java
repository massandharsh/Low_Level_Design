package strategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private enum DiagonalType{
        MAIN,
        COUNTER
    }
    private Map<DiagonalType, Map<Player,Integer>> diagonalMatcher;
    public DiagonalWinningStrategy(){
        this.diagonalMatcher = new HashMap<>();
    }
    @Override
    public boolean checkWin(Board board, Move lastMove) {
       int row = lastMove.getRow();
       int col = lastMove.getCol();
       Player player = lastMove.getPlayer();
       if(row != col && row + col != board.getSize() - 1){
           return false;
       }
       DiagonalType diagonalType = ((row == col) ? DiagonalType.MAIN : DiagonalType.COUNTER);
       int counterDiagonal = 0;
       if(row == col && row + col == board.getSize() - 1){
           if(!diagonalMatcher.containsKey(DiagonalType.COUNTER)){
               diagonalMatcher.put(DiagonalType.COUNTER,new HashMap<>());
           }
           Map<Player, Integer> diagonalMap = diagonalMatcher.get(DiagonalType.COUNTER);
           if (!diagonalMap.containsKey(player)) {
               diagonalMap.put(player, 0);
           }
           counterDiagonal= diagonalMap.get(player) + 1;
           diagonalMap.put(player, counterDiagonal);
       }
       if(!diagonalMatcher.containsKey(diagonalType)){
           diagonalMatcher.put(diagonalType,new HashMap<>());
       }
       Map<Player, Integer> diagonalMap = diagonalMatcher.get(diagonalType);
        if (!diagonalMap.containsKey(player)) {
            diagonalMap.put(player, 0);
        }
        int count = diagonalMap.get(player) + 1;
        diagonalMap.put(player, count);
        return count == board.getSize() || counterDiagonal == board.getSize();
    }

    @Override
    public void undo(Board board, Move lastMove) {
        int row = lastMove.getRow();
        int col = lastMove.getCol();
        Player player = lastMove.getPlayer();
        if(row != col && row + col != board.getSize() - 1){
            return;
        }
        DiagonalType diagonalType = ((row == col) ? DiagonalType.MAIN : DiagonalType.COUNTER);
        int counterDiagonal = 0;
        if(row == col && row + col == board.getSize() - 1){
            Map<Player, Integer> diagonalMap = diagonalMatcher.get(DiagonalType.COUNTER);
            counterDiagonal= diagonalMap.get(player);
            if(counterDiagonal == 1){
                diagonalMap.remove(player);
            }
            else{
                diagonalMap.put(player, counterDiagonal - 1);
            }
        }
        Map<Player, Integer> diagonalMap = diagonalMatcher.get(diagonalType);
        if(diagonalMap == null) return;
        int count = diagonalMap.get(player);
        if(count == 1){
            diagonalMap.remove(player);
        }
        else{
            diagonalMap.put(player, count -1);
        }
    }
}
