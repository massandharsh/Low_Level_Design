package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private BoardColor boardColor;
    private int size;
    private List<List<Cell>> board;
    public Board(int size,BoardColor boardColor){
        this.size = size;
        this.boardColor = boardColor;
        board = new ArrayList<>();
        for(int i = 0 ; i < size ; ++i){
            board.add(new ArrayList<>());
            for(int j = 0 ; j < size ; ++j)
            {
                board.get(i).add(new Cell(i,j));
            }
        }
    }
    public void display(){
        System.out.print("   ");
        for(int i=0;i<size;i++)
            System.out.print(i+"   ");
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < size; j++) {
                Optional<Player> player = board.get(i).get(j).getPlayer();
                System.out.print((player.map(value -> value.getSymbol().getSymbol()).orElse('-')) + " | ");
            }
            System.out.println();
            for (int k = 0; k <= size; k++)
                System.out.print("----");
            System.out.println();
        }

    }

    public BoardColor getBoardColor() {
        return boardColor;
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }
}
