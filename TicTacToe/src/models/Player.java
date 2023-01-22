package models;

import java.util.Objects;

public abstract class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(String name,Symbol symbol,PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public abstract Move move(Board board);

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Player setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public Player setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.playerType,this.symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Player player)){
            return false;
        }
        return player.playerType == this.playerType && player.symbol == this.symbol;
    }
}
