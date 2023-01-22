import factories.WinningStrategyFactory;
import models.*;
import strategy.WinningStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayTicTacToe {
    public static Scanner scanner = HumanPlayer.sc;
    public static void main(String[] args) throws Exception{
        System.out.println("!!Game Started!!");
        System.out.println("!!Size of Board !!");
        int sizeOfBoard = Integer.parseInt(scanner.nextLine());
        System.out.println("!!No. of Bots!!");
        int noOfBots = Integer.parseInt(scanner.nextLine());

        List<Player> players = new ArrayList<>();
        for(int i = 0 ; i < noOfBots ; ++i){
            System.out.println("!!Enter the name!!");
            String name = scanner.nextLine();
            System.out.println("!!Enter the symbol!!");
            char s = scanner.nextLine().charAt(0);
            System.out.println("!!Enter the difficulty level!!");
            String difficulty = scanner.nextLine();
            players.add(new BotPlayer(name,new Symbol(s), BotDifficulty.valueOf(difficulty)));
        }
        System.out.println("!!No. of Players!!");
        int noOfPlayers = Integer.parseInt(scanner.nextLine());
        for(int i = 0 ; i < noOfPlayers ; ++i){
            System.out.println("!!Enter the name!!");
            String name = scanner.nextLine();
            System.out.println("!!Enter the symbol!!");
            char s = scanner.nextLine().charAt(0);
            players.add(new HumanPlayer(name,new Symbol(s)));
        }

        System.out.println("!! Enter number of winning strategies !!");
        int noWinningStrategies = Integer.parseInt(scanner.nextLine());

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        for(int i = 0 ; i < noWinningStrategies ; ++i){
            System.out.println("!! Enter winning strategy : " + i);
            String winningStrategy = scanner.nextLine();
            winningStrategies.add(WinningStrategyFactory
                    .getInstance(WinningStrategyType
                            .valueOf(winningStrategy)));
        }
        Game game = Game.builder()
                .setBoardSize(sizeOfBoard)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
        game.start();
        while(game.getGameStatus() == GameStatus.IN_PROGRESS){
            System.out.println("Please enter");
            System.out.println("1 ---> play");
            System.out.println("2 ---> undo");
            System.out.println("3 ---> redo");
            System.out.println("4 ---> exit");
            int action = Integer.parseInt(scanner.nextLine());
            try {
                switch (action) {
                    case 1 -> game.play();
                    case 2 -> game.undo();
                    case 3 -> game.redo();
                    case 4 -> {
                        game.setGameStatus(GameStatus.FINISHED);
                    }
                }
            }
            catch (RuntimeException e){
                e.printStackTrace();
            }
        }
        game.end();
        scanner.close();
    }
}
