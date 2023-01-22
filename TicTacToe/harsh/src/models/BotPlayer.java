package models;

import factories.BotMoveStrategyFactory;
import strategy.BotMoveStrategy;

public class BotPlayer extends Player{
    private BotDifficulty botDifficulty;
    private BotMoveStrategy botMoveStrategy;
    public BotPlayer(String name,Symbol symbol,BotDifficulty botDifficulty){
        super(name,symbol,PlayerType.BOT);
        this.botDifficulty = botDifficulty;
        this.botMoveStrategy = BotMoveStrategyFactory.getInstance(botDifficulty);
    }

    @Override
    public Move move(Board board) {
        return this.botMoveStrategy.makeMove(board,this);
    }
}
