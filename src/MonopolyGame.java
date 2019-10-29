public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
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

        for (int i = 0; i < playerSize; i++){
            playerList[i] = new Player(NAMES[i], PIECES[i], startMoney);
        }


    }

}
