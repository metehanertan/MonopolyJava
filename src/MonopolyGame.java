public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private int playerSize, taxValue, startMoney, taxNumber;
    private Dice dice;
    private Piece[] pieceList;


    //Default constructor of MonopolyGame
    public MonopolyGame() {
    }

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int taxValue, int startMoney, int taxNumber) {
        this.playerSize = playerSize;
        this.taxValue = taxValue;
        this.startMoney = startMoney;
        this.taxNumber = taxNumber;
        this.board = new Board(taxNumber, taxValue);
        this.dice = new Dice();
    }

    //Play method to play the game.
    public void Play() {

        this.playerList = new Player[this.playerSize];
        this.pieceList = new Piece[this.playerSize];

        board.setSquareList();

        for (int i = 0; i < playerSize; i++) {
            playerList[i] = new Player(NAMES[i], startMoney);
            pieceList[i] = new Piece(PIECES[i], this.board);
            playerList[i].setPiece(pieceList[i]);
        }

        int diceValue;
        int firstDice;
        int secondDice;

        for (int i = 0; i < playerSize; i++) {
            System.out.print("Player : " + playerList[i].getPlayerName() + " -- ");
            System.out.println("Piece: " + pieceList[i].getPieceType());
        }

        boolean check = true;
        int currentPlayerSize = playerSize;
        System.out.println("Current = " + currentPlayerSize);

        while (check) {

            System.out.println("----------------");
            for (int i = 0; i < playerSize; ) {

                if (playerList[i] == null) {
                    i++;
                    continue;
                }

                firstDice = dice.getFirstValue();
                secondDice = dice.getSecondValue();
                diceValue = firstDice + secondDice;

                System.out.println("\nCurrent players: " + currentPlayerSize);

                System.out.println("\n" + playerList[i].getPlayerName() + "'s money: " + playerList[i].getMoney().getCurrentMoney());
                System.out.println(playerList[i].getPlayerName() + "'s old position : " + playerList[i].getPiece().getSquare().getSquareID());
                playerList[i].getPiece().move(diceValue);
                System.out.println("Dice is " + diceValue);

                System.out.println(playerList[i].getPlayerName() + "'s position is " + playerList[i].getPiece().getSquare().getSquareID());

                if (playerList[i].getPiece().getSquare().getSquareName() == "TAX") {
                    playerList[i].getMoney().decreaseMoney(taxValue);
                    System.out.println(playerList[i].getPlayerName() + "'s money: " + playerList[i].getMoney().getCurrentMoney());
                    if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                        playerList[i] = null;
                        currentPlayerSize--;

                        if (currentPlayerSize == 1) {
                            check = false;
                            break;
                        }
                    }
                }

                if (firstDice == secondDice) {
                    System.out.println("First dice is : " + firstDice + " Second dice is : " + secondDice);
                    continue;
                } else {
                    i++;
                }
            }
        }

        for (int i = 0; i < playerSize; i++) {
            if (playerList[i] != null) {
                System.out.println("\nWINNER : " + playerList[i].getPlayerName());
            }
        }
    }
}
