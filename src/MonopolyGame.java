import java.awt.*;

public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private Player[] playerOldList;
    private int playerSize, taxValue, startMoney, taxNumber;
    private Dice dice;
    private Piece[] pieceList;
    private int goMoney;
    private int cycle;
    private int[] dices;

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
        this.goMoney = 200;
    }

    //Play method to play the game.
    public void Play() {
        int diceValue;
        int firstDice;
        int secondDice;

        this.playerList = new Player[this.playerSize];
        this.pieceList = new Piece[this.playerSize];
        this.playerOldList = new Player[this.playerSize];
        this.dices = new int[this.playerSize];

        board.setSquareList();

        for (int i = 0; i < playerSize; i++) {
            playerOldList[i] = new Player(NAMES[i], startMoney);
            pieceList[i] = new Piece(PIECES[i], this.board);
            playerOldList[i].setPiece(pieceList[i]);
            dices[i]= dice.getFirstValue() + dice.getSecondValue();
        }

        for (int i = 0; i < playerSize; i++) {
            System.out.println(playerOldList[i].getPlayerName() + " rolled " + dices[i]+".");
        }
        System.out.println("----------------");

        for (int i = 0; i < playerSize; i++) {
            int biggest = 0;
            int place = 0;

            for (int a = 0; a < playerSize; a++) {
                if (dices[a] >= biggest) {
                    biggest = dices[a];
                    place = a;
                }
            }
            playerList[i] = playerOldList[place];
            playerList[i].setPiece(pieceList[i]);
            dices[place]= 0;

        }

        for (int i = 0; i < playerSize; i++) {
            System.out.print("Player : " + playerList[i].getPlayerName() + " -- ");
            System.out.println("Piece: " + pieceList[i].getPieceType());
        }

        boolean check = true;
        int currentPlayerSize = playerSize;
        System.out.println("Current = " + currentPlayerSize);

        while (check) {
            cycle++;
            System.out.println("----------------");
            System.out.println("Cycle: " + cycle + "\n");

            for (int i = 0; i < playerSize; ) {

                if (playerList[i] == null) {
                    i++;
                    continue;
                }

                playerList[i].increaseTurnCounter();
                playerList[i].reportBeforeRoll();

                firstDice = dice.getFirstValue();
                secondDice = dice.getSecondValue();
                diceValue = firstDice + secondDice;

                playerList[i].getPiece().move(diceValue,playerList[i].getMoney().getCurrentMoney(),goMoney);
                System.out.println("\nFirst dice is : " + firstDice + " Second dice is : " + secondDice);
                System.out.println("Sum of dices is " + diceValue);
                playerList[i].reportAfterRoll();

                if (playerList[i].getPiece().getSquare().getSquareName() == "TAX") {
                    playerList[i].getMoney().decreaseMoney(taxValue);
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
