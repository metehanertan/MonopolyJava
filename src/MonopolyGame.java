public class MonopolyGame {

    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private int playerSize, taxValue, startMoney, taxNumber;

    //Default constructor of MonopolyGame
    public MonopolyGame() {

    }


    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int taxValue, int startMoney, int taxNumber) {
        this.playerSize = playerSize;
        this.taxValue = taxValue;
        this.startMoney = startMoney;
        this.taxNumber = taxNumber;

    }

    //Play method to play the game.
    public void Play() {

        this.playerList = new Player[this.playerSize];
        this.board = new Board();







    }

}
