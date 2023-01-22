package models;

import exceptions.*;
import strategy.*;
import validator.GameBuilderValidator;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Game {
    private LocalDateTime dateTime;
    private Board board;
    private int undoIndex;
    private List<Player> players;
    private List<WinningStrategy> winningStrategies;
    private int playerNextMoveIndex;
    private GameStatus gameStatus;
    private Player winner;
    private int noOfOccupiedCells;

    private List<Move> moves;

    private UndoRedoStrategy undoRedoStrategy;

    public Game(GameBuilder gameBuilder) {
        this.dateTime = LocalDateTime.now();
        this.board = new Board(gameBuilder.getSize(),BoardColor.BLUE);
        this.players = gameBuilder.players;
        this.winningStrategies = gameBuilder.winningStrategies;
        this.gameStatus = GameStatus.NOT_STARTED;
        this.playerNextMoveIndex = 0;
        this.moves = new LinkedList<>();
        this.undoRedoStrategy = new SimpleUndoRedoStrategy();
        this.undoIndex = -1;
    }

    public void start() {
        this.gameStatus = GameStatus.IN_PROGRESS;
    }
    public void end(){
        this.gameStatus = GameStatus.FINISHED;
    }

    public void undo(){
        if(undoIndex < 0){
            throw new NoUndoPossibleException("undo not possible");
        }
        playerNextMoveIndex = (playerNextMoveIndex - 1 + players.size()) % players.size();
        this.board = this.undoRedoStrategy.undo(board,moves,undoIndex);
        this.board.display();
        for(WinningStrategy winningStrategy : winningStrategies){
            winningStrategy.undo(board,this.moves.get(undoIndex));
        }
        undoIndex--;
        noOfOccupiedCells--;
    }
    public void redo(){
        if(undoIndex + 1 > moves.size()){
            throw new NoRedoPossibleException("redo not possible");
        }
        playerNextMoveIndex = (playerNextMoveIndex + 1) % players.size();
        undoIndex++;
        this.board = this.undoRedoStrategy.redo(board,moves,undoIndex);
        for(WinningStrategy winningStrategy : winningStrategies){
            boolean win = winningStrategy.checkWin(board,this.moves.get(undoIndex));
            if(win){
                this.gameStatus = GameStatus.FINISHED;
                this.winner = this.moves.get(undoIndex).getPlayer();
                System.out.println("Winner Winner Chicken Dinner ----> " + this.winner.getName());
                return;
            }
        }
        noOfOccupiedCells++;
        this.board.display();
    }

    private void clearUndoHistory(){
        for(int i = undoIndex + 1 ; i < moves.size() ; ++i){
            moves.remove(moves.size() - 1);
        }
    }

    public void play(){
        board.display();
        //History needs to be cleared from last undo index as many undo's and a move should remove other undo's
        clearUndoHistory();
        System.out.println(players.get(playerNextMoveIndex).getName() + " Turn");
        Move potentialMove = players.get(playerNextMoveIndex).move(board);
        Optional<Player> p =  this.board.getBoard().get(potentialMove.getRow()).get(potentialMove.getCol()).getPlayer();
        if(p.isPresent()){
            System.out.println("!!Already occupied cell!!------> Please try again");
            return;
        }
        this.board.getBoard()
                .get(potentialMove.getRow())
                .get(potentialMove.getCol())
                .setPlayer(Optional.of(players.get(playerNextMoveIndex)));
        board.display();
        noOfOccupiedCells++;
        for(WinningStrategy winningStrategy : winningStrategies){
            boolean win = winningStrategy.checkWin(board,potentialMove);
            if(win){
                this.gameStatus = GameStatus.FINISHED;
                this.winner = potentialMove.getPlayer();
                System.out.println("Winner Winner Chicken Dinner ----> " + this.winner.getName());
                return;
            }
        }
        if(noOfOccupiedCells == board.getSize() * board.getSize()){
            System.out.println("!!Game is draw  !!");
            this.gameStatus = GameStatus.DRAW;
            return;
        }
        this.moves.add(potentialMove);
        undoIndex = this.moves.size() - 1;
       playerNextMoveIndex = (playerNextMoveIndex + 1) % players.size();
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public int getPlayerNextMoveIndex() {
        return playerNextMoveIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public Game setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        return this;
    }


    public static class GameBuilder{
        private int boardSize;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;


        public int getSize() {
            return boardSize;
        }

        public GameBuilder setBoardSize(int size) {
            this.boardSize = size;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
        public Game build(){
            if(!GameBuilderValidator.validateMaxBoardSize(boardSize)) throw new BoardSizeInvalidException("Max board size invalid");
            if(!GameBuilderValidator.validateMinBoardSize(boardSize)) throw new BoardSizeInvalidException("Min board size invalid");
            if(!GameBuilderValidator.validateMaxNumberOfPlayers(boardSize,players.size())) throw new MaxPlayerExceedException("Max Player exceeded");
            if(!GameBuilderValidator.validateMinNumberOfPlayers(players.size())) throw new InsufficientPlayersException("Min Players should be more");
            return new Game(this);
        }
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }


}
