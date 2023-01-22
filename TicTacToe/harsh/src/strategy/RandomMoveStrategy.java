package strategy;

import models.Board;
import models.Move;
import models.Player;

public class RandomMoveStrategy implements BotMoveStrategy{
    @Override
    public Move makeMove(Board board, Player player) {
        for(int i = 0 ; i < board.getSize() ; ++i){
            for(int j = 0 ; j < board.getSize(); ++j){
                if(board.getBoard().get(i).get(j).getPlayer().isEmpty()){
                    return new Move(i,j,player);
                }
            }
        }
        return null;
    }
}
