package factories;

import models.BotDifficulty;
import strategy.BotMoveStrategy;
import strategy.RandomMoveStrategy;

public class BotMoveStrategyFactory {
    public static BotMoveStrategy getInstance(BotDifficulty botDifficulty){
        return new RandomMoveStrategy();
    }
}
