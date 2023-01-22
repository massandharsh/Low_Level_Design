package models;

public class Move {
    private int row;
    private int col;
    private Player player;

    public Move(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public Move setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }

    public Move setCol(int col) {
        this.col = col;
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public Move setPlayer(Player player) {
        this.player = player;
        return this;
    }
}
