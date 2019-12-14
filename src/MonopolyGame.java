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

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int threshold, int startMoney, int goMoney, String[] properties,
                        int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                        int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                        int[] transportPrice, int[] taxFine, String[] taxSquares, int jailFine, int goToJailNumber) {
        checkPlayerSize(playerSize);
        this.playerSize = playerSize;
        this.threshold = threshold;
        this.startMoney = startMoney;
        this.board = new Board(properties, propertyFine, propertyPrice, propertyColor,
                utilityName, utilityRate, utilityPrice, transportName, transportFine, transportPrice,
                taxFine, taxSquares, goToJailNumber); //Create Board object.
        this.goMoney = goMoney; //Assign GO money.
        this.jailFine = jailFine;
        this.goToJailNumber = goToJailNumber;
        this.currentPlayerSize = playerSize;
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
            playerOldList[i] = new Player(NAMES[i], startMoney); //Create first player list (not ordered).
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

                            // Check if player have enough money
                            if (playerList[i].getMoney().getCurrentMoney() > jailFine) {

                                playerList[i].getMoney().decreaseMoney(jailFine);
                                System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'" + jailFine
                                        + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
                                playerList[i].setInJail(false);

                            }
                        } else {
                            // Check the player how many turns in the jail
                            if (playerList[i].getJailTurnCounter() < 3) {
                                playerList[i].increaseJailTurnCounter();
                                i++;
                                break;
                            } else {
                                playerList[i].getMoney().decreaseMoney(jailFine);
                                System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'" + jailFine
                                        + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
                                if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                                    System.out.println("!!! " + playerList[i].getPlayerName()
                                            + "  has gone bankrupt!!!\n");
                                    playerList[i] = null;
                                    currentPlayerSize--;

                                    //If only one player left in the game, finish the game.
                                    if (currentPlayerSize == 1) {
                                        check = false;
                                    }
                                    break;
                                }

                                playerList[i].setInJail(false);
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
                if (tempSquare instanceof PropertySquare) {
                    if(((PropertySquare) tempSquare).getHasOwner()){
                        ((PropertySquare) tempSquare).payRent(playerList[i], board, this);
                    }
                    else{
                        ((PropertySquare) tempSquare).buyProperty(playerList[i], this);
                    }

                    if(currentPlayerSize == 1){
                        check = false;
                        break;
                    }
                    /*
                    gameFinish = propertySquareActions((PropertySquare) tempSquare, i);

                    if (gameFinish) {
                        check = false;
                        break;
                    }*/
                } else if (tempSquare instanceof TransportSquare) {
                    gameFinish = transportSquareActions((TransportSquare) tempSquare, i);

                    if (gameFinish) {
                        check = false;
                        break;
                    }
                } else if (tempSquare instanceof UtilitySquare) {
                    gameFinish = utilitySquareActions((UtilitySquare) tempSquare, i);

                    if (gameFinish) {
                        check = false;
                        break;
                    }
                }

                // If player's new square is Go To Jail Square
                if (tempSquare instanceof GoToJailSquare) {
                    ((GoToJailSquare)playerList[i].getPiece().getSquare()).goToJail(playerList[i].getPiece().getSquare(), board.getJailSquare());
                    playerList[i].getPiece().setSquare(board.getSquareList()[10]);
                    playerList[i].setJailTurnCounter(0);
                    playerList[i].setInJail(true);
                    System.out.println("!!" + playerList[i].getPlayerName() + " HAS GONE TO JAIL!!");
                }

                // If player's new square is Tax Square
                if (tempSquare instanceof TaxSquare) {
                    gameFinish = taxSquareActions((TaxSquare) tempSquare, i);

                    if (gameFinish) {
                        check = false;
                        break;
                    }
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

    // This method apply the transport square actions
    private boolean transportSquareActions(TransportSquare tempSquare, int i) {
        // If square has a owner
        if (tempSquare.getHasOwner()) {
            // Check if the owner is himself
            if (!(tempSquare.getOwner().getPlayerName().equals(playerList[i].getPlayerName()))) {
                // Check if the owner is in jail
                if (!(tempSquare.getOwner().isInJail())) {
                    int tempFine = tempSquare.getOwner().getTransportCount() * tempSquare.getFine();

                    // Player sells his properties if he has not enough money to pay fine
                    while (playerList[i].getMoney().getCurrentMoney() <= tempFine) {
                        if (!playerList[i].sellCheapest())
                            break;
                    }

                    playerList[i].getMoney().decreaseMoney(tempFine);

                    // If player goes to bankruptcy
                    if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                        tempSquare.getOwner().getMoney().increaseMoney(tempFine + playerList[i].getMoney().getCurrentMoney());
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                                + (tempFine + playerList[i].getMoney().getCurrentMoney()) + "$\' TO "
                                + tempSquare.getOwner().getPlayerName() + "***");
                        System.out.println("!!! " + playerList[i].getPlayerName() + "  has gone bankrupt!!!\n");
                        playerList[i] = null;
                        currentPlayerSize--;

                        // If only one player left in the game, finish the game.
                        if (currentPlayerSize == 1) {
                            return true;
                        }
                    } else {
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'" + tempFine
                                + "$\' TO " + tempSquare.getOwner().getPlayerName() + "***");
                    }
                    tempSquare.getOwner().getMoney().increaseMoney(tempSquare.getFine());
                }
            }
        } else { // If square has not an owner
            // Player roll the choice dice
            playerList[i].rollChoiceDice();
            int choiceDiceValue = playerList[i].getChoiceDice().getTotal();

            // If player wants to take this square and has enough money to buy
            if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > tempSquare.getPrice()) {
                tempSquare.setOwner(playerList[i]);
                playerList[i].addTransportLister(tempSquare);
                playerList[i].addProperty(tempSquare);
                System.out.println("***" + playerList[i].getPlayerName() + " BOUGHT " + tempSquare.getSquareName()
                        + "***");
            }
        }
        return false;
    }

    // This method apply the utility square actions
    private boolean utilitySquareActions(UtilitySquare tempSquare, int i) {
        // If square has a owner
        if (tempSquare.getHasOwner()) {
            // Check if the owner is himself
            if (!(tempSquare.getOwner().getPlayerName().equals(playerList[i].getPlayerName()))) {
                // Check if the owner is in jail
                if (!(tempSquare.getOwner().isInJail())) {
                    int tempFine = tempSquare.getOwner().getUtilityCount()
                            * tempSquare.getFine(playerList[i].getMoveDice().getTotal());

                    // Player sells his properties if he has not enough money to pay fine
                    while (playerList[i].getMoney().getCurrentMoney() <= tempFine) {
                        if (!playerList[i].sellCheapest())
                            break;
                    }

                    playerList[i].getMoney().decreaseMoney(tempFine);

                    // If player goes to bankruptcy
                    if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                        tempSquare.getOwner().getMoney().increaseMoney(tempFine
                                + playerList[i].getMoney().getCurrentMoney());
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                                + (tempFine + playerList[i].getMoney().getCurrentMoney()) + "$\' TO "
                                + tempSquare.getOwner().getPlayerName() + "***");
                        System.out.println("!!! " + playerList[i].getPlayerName() + "  has gone bankrupt!!!\n");
                        playerList[i] = null;
                        currentPlayerSize--;

                        // If only one player left in the game, finish the game.
                        if (currentPlayerSize == 1) {
                            return true;
                        }
                    } else {
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                                + tempFine + "$\' TO " + tempSquare.getOwner().getPlayerName() + "***");
                    }
                    tempSquare.getOwner().getMoney().increaseMoney(tempFine);
                }
            }
        }else { // If square has not an owner
            // Player roll the choice dice
            playerList[i].rollChoiceDice();
            int choiceDiceValue = playerList[i].getChoiceDice().getTotal();

            // If player wants to take this square and has enough money to buy
            if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > tempSquare.getPrice()) {
                tempSquare.setOwner(playerList[i]);
                playerList[i].addUtilityList(tempSquare);
                playerList[i].addProperty(tempSquare);
                System.out.println("***" + playerList[i].getPlayerName() + " BOUGHT "
                        + tempSquare.getSquareName() + "***");
            }
        }
        return false;
    }

    // This method apply the transport square actions
    private boolean propertySquareActions(PropertySquare tempSquare, int i) {
        // If square has a owner
        if (tempSquare.getHasOwner()) {
            // Check if the owner is himself
            if (!(tempSquare.getOwner().getPlayerName().equals(playerList[i].getPlayerName()))) {
                // Check if the owner is in jail
                if (!(tempSquare.getOwner().isInJail())) {
                    int tempFine = ((PropertySquare) tempSquare).getFine();
                    // BURASI YANLIŞ OWNER OLACAK
                    if (playerList[i].hasItAll((PropertySquare) tempSquare, board)) {
                        tempFine = 2 * tempFine;
                        System.out.println("Player " + tempSquare.getOwner().getPlayerName() + " has all " + tempSquare.getColor() + " colors.");
                    }

                    // Player sells his properties if he has not enough money to pay fine
                    while (playerList[i].getMoney().getCurrentMoney() <= tempFine) {
                        if (!playerList[i].sellCheapest())
                            break;
                    }

                    playerList[i].getMoney().decreaseMoney(tempFine);

                    // If player goes to bankruptcy
                    if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                        tempSquare.getOwner().getMoney().increaseMoney(tempFine
                                + playerList[i].getMoney().getCurrentMoney());
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                                + (tempFine + playerList[i].getMoney().getCurrentMoney()) + "$\' TO "
                                + tempSquare.getOwner().getPlayerName() + "***");

                        System.out.println("!!! " + playerList[i].getPlayerName() + "  has gone bankrupt!!!\n");
                        playerList[i] = null;
                        currentPlayerSize--;

                        //If only one player left in the game, finish the game.
                        if (currentPlayerSize == 1) {
                            return true;
                        }
                    } else {
                        System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                                + tempFine + "$\' TO " + tempSquare.getOwner().getPlayerName() + "***");
                    }
                    tempSquare.getOwner().getMoney().increaseMoney(tempFine);
                }
            }
        } else { // If square has not an owner
            // Player roll the choice dice
            playerList[i].rollChoiceDice();
            int choiceDiceValue = playerList[i].getChoiceDice().getTotal();

            // If player wants to take this square and has enough money to buy
            if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > tempSquare.getPrice()) {
                tempSquare.setOwner(playerList[i]);
                playerList[i].addProperty(tempSquare);
                System.out.println("***" + playerList[i].getPlayerName() + " BOUGHT "
                        + tempSquare.getSquareName() + "***");
            }
        }
        return false;
    }

    // This method apply tax square actions
    private boolean taxSquareActions(TaxSquare tempSquare, int i) {
        playerList[i].getMoney().decreaseMoney(tempSquare.getFine());

        // If player goes to bankruptcy
        if (playerList[i].getMoney().getCurrentMoney() <= 0) {
            playerList[i].emptyOwnedSquares();
            System.out.println("***" + playerList[i].getPlayerName() + " HAS PAID \'"
                    + tempSquare.getFine() + "$\' TO BANK***");
            System.out.println("!!! " + playerList[i].getPlayerName() + "  has gone bankrupt!!!\n");
            playerList[i] = null;
            currentPlayerSize--;

            if (currentPlayerSize == 1) {
                return true;
            }
        }
        return false;
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

    public void decreasePlayerSize(){
        this.currentPlayerSize--;
    }

    public int getPlayerSize(){
        return this.playerSize;
    }

    public int getCurrentPlayerSize(){
        return this.currentPlayerSize;
    }

    public int getThreshold(){
        return this.threshold;
    }

}
