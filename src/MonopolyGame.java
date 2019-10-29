public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private int playerSize, taxValue, startMoney, taxNumber;
    private Dice dice;

    //Default constructor of MonopolyGame
    public MonopolyGame() {
    }

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int taxValue, int startMoney, int taxNumber) {
        this.playerSize = playerSize;
        this.taxValue = taxValue;
        this.startMoney = startMoney;
        this.taxNumber = taxNumber;
        this.board = new Board();
        this.dice = new Dice();
    }

    //Play method to play the game.
    public void Play() {

        this.playerList = new Player[this.playerSize];

        board.setSquareList();

        for (int i = 0; i < playerSize; i++){
            playerList[i] = new Player(NAMES[i], PIECES[i], startMoney);
        }
        int diceValue;
        int count = 0;
        while(true){
            for(int i = 0; i < playerSize; i++){
                diceValue = dice.roll();
                System.out.println(playerList[i].getPlayerName() + "'s old position : " + playerList[i].getPosition());
                playerList[i].move(diceValue);
                System.out.println("Dice is " + diceValue);
                System.out.println(playerList[i].getPlayerName() + "'s position is " + playerList[i].getPosition());
            }
            count++;
            if(count == 10) {break;}
        }

    }

}
