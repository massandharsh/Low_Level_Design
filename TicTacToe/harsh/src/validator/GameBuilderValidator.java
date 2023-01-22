package validator;

import models.Player;
import models.PlayerType;
import reader.PropertiesReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameBuilderValidator {
    public static boolean validateMinNumberOfPlayers(int players){
       return players >= Integer.parseInt(PropertiesReader.readProps("players.min"));
    }
    public static boolean validateMaxNumberOfPlayers(int boardSize,int noOfPlayers){
        return noOfPlayers <= Integer.parseInt(PropertiesReader.readProps("players.max"))
                && noOfPlayers < boardSize;
    }
    public static boolean validateMinBoardSize(int boardSize){
        return boardSize >= Integer.parseInt(PropertiesReader.readProps("board.minSize"));
    }
    public static boolean validateMaxBoardSize(int boardSize){
        return boardSize <= Integer.parseInt(PropertiesReader.readProps("board.maxSize"));
    }
    public static boolean validateBotCounter(List<Player> players){
        int maxBots = Integer.parseInt(PropertiesReader.readProps("players.bot"));
        int noOfBots = 0;
        for(Player player : players){
            if(player.getPlayerType() == PlayerType.BOT){
                noOfBots++;
            }
            if(noOfBots > maxBots) return false;
        }
        return true;
    }
    public static boolean validateUniqueSymbols(List<Player> players){
        Set<Character> set = new HashSet<>();
        for(Player player : players){
           if(set.contains(player.getSymbol().getSymbol())){
               return false;
           }
           set.add(player.getSymbol().getSymbol());
        }
        return true;
    }
}
