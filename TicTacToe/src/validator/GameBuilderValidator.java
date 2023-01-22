package validator;

import reader.PropertiesReader;

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
    public static boolean validateBotCounter(int bots){
        return bots <= Integer.parseInt(PropertiesReader.readProps("players.bot"));
    }
}
