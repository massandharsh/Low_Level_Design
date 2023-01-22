package models;

import java.util.Optional;

public class Cell {
    private int row;
    private int col;
    private Optional<Player> player;
    public Cell(int row,int col){
        this.row = row;
        this.col = col;
        this.player = Optional.empty();
    }

    public int getRow() {
        return row;
    }

    public Cell setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Optional<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Optional<Player> player) {
        this.player = player;
    }
}
