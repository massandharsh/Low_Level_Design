package models;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public static Scanner sc = new Scanner(System.in);
    public HumanPlayer(String name,Symbol symbol){
        super(name,symbol,PlayerType.HUMAN);
    }


    @Override
    public Move move(Board board) {
        System.out.println("!!Enter row!!");
        int row = Integer.parseInt(sc.nextLine());
        System.out.println("!!Enter col!!");
        int col = Integer.parseInt(sc.nextLine());
        return new Move(row,col,this);
    }
}
