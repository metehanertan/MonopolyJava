import java.util.ArrayList;

// Simulation continues in this class
public class MonopolyGame {
    //String array which contains name of players
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"};
    //String array which contains piece types of players
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"};
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private Player[] playerOldList; //Not ordered player list
    private int playerSize, threshold, startMoney;
    private Piece[] pieceList; //Piece array which contains Piece objects.
    private int goMoney; //Given money amount while passing through GO square
    private int cycle; //Cycle counter
    private int[] dices; //Dice values array to order turns of players
    private int jailFine; // Fine for go our from the jail
    private int goToJailNumber; // Number of GoToJail squares
    private int currentPlayerSize; // Non-bankrupted player number
    private int[] rent1;
    private int[] rent2;
    private int[] rent3;
    private int[] rent4;
    private int[] hotel;
    private int[] mortgage;
    private int[] pricePerHouse;
    private int freeHouseNumber;
    private int freeHotelNumber;
    private Dice moveDice;
    private Dice choiceDice;
    private ArrayList<House> houseList;
    private ArrayList<Hotel> hotelList;

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int threshold, int startMoney, int goMoney, String[] properties,
                        int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                        int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                        int[] transportPrice, int[] taxFine, String[] taxSquares, int jailFine, int goToJailNumber,
                        int[] rent1, int[] rent2, int[] rent3, int[] rent4, int[] hotel, int[] mortgage, int[] pricePerHouse,
                        int houseNumber, int hotelNumber, ArrayList<String> comChest, ArrayList<String> chanceCard) {
        checkPlayerSize(playerSize);
        this.playerSize = playerSize;
        this.threshold = threshold;
        this.startMoney = startMoney;
        this.board = new Board(properties, propertyFine, propertyPrice, propertyColor,
                utilityName, utilityRate, utilityPrice, transportName, transportFine, transportPrice,
                taxFine, taxSquares, goToJailNumber, jailFine, rent1, rent2, rent3, rent4, hotel, mortgage,
                pricePerHouse, houseNumber, hotelNumber, comChest, chanceCard); //Create Board object.
        this.goMoney = goMoney; //Assign GO money.
        this.jailFine = jailFine;
        this.goToJailNumber = goToJailNumber;
        this.currentPlayerSize = playerSize;
        this.freeHotelNumber = hotelNumber;
        this.freeHouseNumber = houseNumber;
        this.moveDice = new Dice();
        this.choiceDice = new Dice();
        this.houseList = new ArrayList<>();
        this.hotelList = new ArrayList<>();

        for(int i = 0; i < houseNumber; i++){
            this.houseList.add(new House());
        }

        for(int i = 0; i < hotelNumber; i++ ){
            this.hotelList.add(new Hotel());
        }
    }

    // Play method to play the game.
    public void Play() {

        // Create variables for dice results
        int diceValue;
        int firstDice;
        int secondDice;

        // Call some functions to create necessary objects
        createPlayerList();
        createPieceList();
        createPlayerOldList();
        createDices();

        // Set the board
        board.setSquareList();

        // Determine player turns
        for (int i = 0; i < playerSize; i++) {
            playerOldList[i] = new Player(NAMES[i], startMoney, moveDice, choiceDice); //Create first player list (not ordered).
            pieceList[i] = new Piece(PIECES[i], this.board); //Create Piece List.
            playerOldList[i].setPiece(pieceList[i]); //Set players' pieces'.
            //Roll dices for each player to set the turn order.
            dices[i] = playerOldList[i].getMoveDice().getFirstRandomValue()
                    + playerOldList[i].getMoveDice().getSecondRandomValue();
        }

        // Print some infos before the start
        printDiceRoll();
        rollDiceBeginning();
        printPlayerAndPiece();

        boolean check = true;
        boolean gameFinish;

        Square tempSquare;

        // The simulation continues in this loop
        while (check) {
            cycle++; //Increase cycle counter for each cycle.
            System.out.println("-----NEXT CYCLE-----");
            System.out.println("Cycle: " + cycle + "\n");

            //Check players.
            for (int i = 0; i < playerSize; ) {

                if (playerList[i] == null) {
                    i++;
                    continue;
                }
                playerList[i].reportBeforeRoll();

                // Current player roll dices
                playerList[i].rollMoveDice();
                firstDice = playerList[i].getMoveDice().getFirstValue(); //Roll first dice
                secondDice = playerList[i].getMoveDice().getSecondValue(); //Roll second dice
                diceValue = firstDice + secondDice; //Add dice values to move the player.

                tempSquare = playerList[i].getPiece().getSquare();

                // If player in the jail
                if (tempSquare instanceof JailSquare) {

                    if (firstDice != secondDice) {

                        // Check if player wants to pay fine and go out to jail
                        playerList[i].rollChoiceDice();

                        if (playerList[i].getChoiceDice().getTotal() > threshold) {
                            ((JailSquare) tempSquare).playerWantsPayForJail(playerList[i]);

                        } else {
                            int tempI = i;
                            i = ((JailSquare) tempSquare).playerDoesntWantPayForJail(playerList[i], this, i);

                            if (tempI != i) {
                                break;
                            }
                            if (playerList[i].getIsBankrupted()) {
                                currentPlayerSize--;
                                playerList[i] = null;

                                if (currentPlayerSize == 1) {
                                    check = false;
                                    break;
                                }
                                i++;
                                continue;
                            }
                        }
                    } else {
                        playerList[i].setInJail(false);
                    }
                }

                //Move the player according to dice values.
                playerList[i].getPiece().move(diceValue, playerList[i], goMoney);
                System.out.println("\nFirst dice is : " + firstDice + " Second dice is : " + secondDice);
                System.out.println("Sum of dices is " + diceValue);
                playerList[i].reportAfterRoll();

                tempSquare = playerList[i].getPiece().getSquare();

                // Check current square and end of the game
                if (tempSquare instanceof PurchasableSquare) {
                    if (((PurchasableSquare) tempSquare).getHasOwner()) {
                        ((PurchasableSquare) tempSquare).payRent(playerList[i], board);

                        if (playerList[i].getIsBankrupted()) {
                            currentPlayerSize--;
                            playerList[i] = null;

                            if (currentPlayerSize == 1) {
                                check = false;
                                break;
                            }
                            i++;
                            continue;
                        }

                    } else {
                        playerList[i].buyProperty((PurchasableSquare) tempSquare, this);
                    }
                }

                if (currentPlayerSize == 1) {
                    check = false;
                    break;
                }

                // If player's new square is Go To Jail Square
                if (tempSquare instanceof GoToJailSquare) {
                    ((GoToJailSquare) playerList[i].getPiece().getSquare()).goToJail(playerList[i].getPiece().getSquare(), board.getJailSquare());
                    playerList[i].getPiece().setSquare(board.getSquareList()[10]);
                    playerList[i].setJailTurnCounter(0);
                    playerList[i].setInJail(true);
                    System.out.println("!!" + playerList[i].getPlayerName() + " HAS GONE TO JAIL!!");
                }

                // If player's new square is Tax Square
                if (tempSquare instanceof TaxSquare) {
                    ((TaxSquare) tempSquare).payTax(playerList[i]);

                    if (playerList[i].getIsBankrupted()) {
                        currentPlayerSize--;
                        playerList[i] = null;

                        if (currentPlayerSize == 1) {
                            check = false;
                            break;
                        }
                        i++;
                        continue;
                    }
                }

                if (playerList[i] != null) {
                    playerList[i].reportAfterAction();
                }

                if (i != playerSize - 1) {
                    System.out.println("***********");
                }

                //Check if dices are equal each other.
                if (firstDice != secondDice) {
                    i++;
                }
            }
        }

        // Print the winner
        for (int i = 0; i < playerSize; i++) {
            if (playerList[i] != null) {
                System.out.println("WINNER : " + playerList[i].getPlayerName());
            }
        }
    }

    //Create player list.
    private void createPlayerList() {
        this.playerList = new Player[this.playerSize];
    }

    //Create piece list.
    private void createPieceList() {
        this.pieceList = new Piece[this.playerSize];
    }

    //Create first player list.
    private void createPlayerOldList() {
        this.playerOldList = new Player[this.playerSize];
    }

    //Create dices.
    private void createDices() {
        this.dices = new int[this.playerSize];
    }

    //Print dice values.
    private void printDiceRoll() {
        System.out.println("Players roll dices to determine their turns.");
        for (int i = 0; i < playerSize; i++) {
            System.out.println(playerOldList[i].getPlayerName() + " rolled " + dices[i] + ".");
        }
        System.out.println("----------------");
    }

    //Roll the dice for turn order and set the turn order of players from biggest to smallest.
    private void rollDiceBeginning() {
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
            playerList[i].setTurn(i + 1);
            playerList[i].setPiece(pieceList[i]);
            dices[place] = 0;
        }
    }

    //Print Player name and Piece name.
    private void printPlayerAndPiece() {
        for (int i = 0; i < playerSize; i++) {
            System.out.print("Player : " + playerList[i].getPlayerName() + " -- ");
            System.out.println("Piece: " + pieceList[i].getPieceType());
        }
    }

    // Check if the given player size is wrong
    private void checkPlayerSize(int intPlayerSize) {
        if (intPlayerSize < 2 || intPlayerSize > 8) {
            System.out.println("Player size must be from 2 to 8.");
            System.exit(1);
        }
    }

    // Getter and setter methods
    public Player[] getPlayerOldList() {
        return playerOldList;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public void decreasePlayerSize() {
        this.currentPlayerSize--;
    }

    public int getPlayerSize() {
        return this.playerSize;
    }

    public int getCurrentPlayerSize() {
        return this.currentPlayerSize;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public int getGoMoney() {
        return goMoney;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public void setHouseList(ArrayList<House> houseList) {
        this.houseList = houseList;
    }

    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }
}
