package factories;

import models.WinningStrategyType;
import strategy.*;

public class WinningStrategyFactory {
    public static WinningStrategy getInstance(WinningStrategyType winningStrategyType){
        switch(winningStrategyType) {
            case COL:
                return new ColumnWinningStrategy();
            case ROW:
                return new RowWinningStrategy();
            case DIAGONAL:
                return new DiagonalWinningStrategy();
            default:
                return new NoCheckingStrategy();
        }
    }
}
