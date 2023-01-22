package strategy;

import models.Board;
import models.Move;
import models.Player;

public interface BotMoveStrategy {
    Move makeMove(Board board, Player player);

}
