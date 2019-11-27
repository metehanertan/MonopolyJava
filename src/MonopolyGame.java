
public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"}; //String array which contains name of players
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"}; //String array which contains piece types of players
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private Player[] playerOldList; //Not ordered player list
    private int playerSize, threshold, startMoney;
    private Dice dice; //Dice object
    private Dice choiceDice;
    private Piece[] pieceList; //Piece array which contains Piece objects.
    private int goMoney; //Given money amount while passing through GO square
    private int cycle; //Cycle counter
    private int[] dices; //Dice values array to order turns of players
    private int jailFine;
    private int goToJailNumber;

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int threshold, int startMoney, int taxNumber, int goMoney, String[] properties,
                        int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                        int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                        int[] transportPrice, int[] taxFine, String[] taxSquares, int jailFine,int goToJailNumber) {
        checkPlayerSize(playerSize);
        this.playerSize = playerSize;
        this.threshold = threshold;
        this.startMoney = startMoney;
        this.board = new Board(properties, propertyFine, propertyPrice, propertyColor,
                utilityName, utilityRate, utilityPrice, transportName, transportFine, transportPrice,
                taxFine, taxSquares,goToJailNumber); //Create Board object.
        this.dice = new Dice(); //Create Dice object
        this.goMoney = goMoney; //Assign GO money.
        this.choiceDice = new Dice(); //Dice for buying chance
        this.jailFine = jailFine;
        this.goToJailNumber = goToJailNumber;

    }

    //Play method to play the game.
    public void Play() {

        int diceValue;
        int firstDice;
        int secondDice;

        createPlayerList();
        createPieceList();
        createPlayerOldList();
        createDices();

        board.setSquareList();

        for (int i = 0; i < playerSize; i++) {
            playerOldList[i] = new Player(NAMES[i], startMoney); //Create first player list (not ordered).
            pieceList[i] = new Piece(PIECES[i], this.board); //Create Piece List.
            playerOldList[i].setPiece(pieceList[i]); //Set players' pieces'.
            dices[i] = dice.getFirstRandomValue() + dice.getSecondRandomValue(); //Roll dices for each player to set the turn order.
        }

        printDiceRoll();
        rollDiceBeginning();
        printPlayerAndPiece();

        boolean check = true;
        int currentPlayerSize = playerSize;

        Square tempSquare;

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

                dice.rollDice();
                firstDice = dice.getFirstValue(); //Roll first dice
                secondDice = dice.getSecondValue(); //Roll second dice
                diceValue = firstDice + secondDice; //Add dice values to move the player.

                tempSquare = playerList[i].getPiece().getSquare();

                // If player in the jail
                if (tempSquare instanceof JailSquare) {

                    if (firstDice != secondDice) {

                        // Check if player wants to pay fine and go out to jail
                        choiceDice.rollDice();

                        if (choiceDice.getTotal() > 8) {

                            if (playerList[i].getMoney().getCurrentMoney() > jailFine) {

                                playerList[i].getMoney().decreaseMoney(jailFine);
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

                                if (playerList[i].getMoney().getCurrentMoney() <= 0) {
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

                playerList[i].getPiece().move(diceValue, playerList[i], goMoney); //Move the player according to dice values.
                System.out.println("\nFirst dice is : " + firstDice + " Second dice is : " + secondDice);
                System.out.println("Sum of dices is " + diceValue);
                playerList[i].reportAfterRoll();

                tempSquare = playerList[i].getPiece().getSquare();

                if (tempSquare instanceof PropertySquare) {
                    if (((PropertySquare) tempSquare).getHasOwner()) {
                        if (((PropertySquare) tempSquare).getOwner().getPlayerName() != playerList[i].getPlayerName()) {
                            if (!(((PropertySquare) tempSquare).getOwner().isInJail())) {
                                playerList[i].getMoney().decreaseMoney(((PropertySquare) tempSquare).getFine());
                                if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                                    ((PropertySquare) tempSquare).getOwner().getMoney().increaseMoney(((PropertySquare) tempSquare).getFine() + playerList[i].getMoney().getCurrentMoney());
                                    playerList[i] = null;
                                    currentPlayerSize--;

                                    //If only one player left in the game, finish the game.
                                    if (currentPlayerSize == 1) {
                                        check = false;
                                        break;
                                    }

                                }
                                ((PropertySquare) tempSquare).getOwner().getMoney().increaseMoney(((PropertySquare) tempSquare).getFine());
                            }
                        }
                    } else {

                        choiceDice.rollDice();
                        int choiceDiceValue = choiceDice.getTotal();

                        //treshold değeri inputtan alınacak!!!!!
                        if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > ((PropertySquare) tempSquare).getPrice()) {
                            ((PropertySquare) tempSquare).setOwner(playerList[i]);
                            playerList[i].addProperty(tempSquare);
                        }
                    }

                } else if (tempSquare instanceof TransportSquare) {
                    //ownerı varsa
                    if (((TransportSquare) tempSquare).getHasOwner()) {
                        if (((TransportSquare) tempSquare).getOwner().getPlayerName() != playerList[i].getPlayerName()) {


                            if (!(((TransportSquare) tempSquare).getOwner().isInJail())) {
                                int tempFine = ((TransportSquare) tempSquare).getOwner().getTransportCount() * ((TransportSquare) tempSquare).getFine();
                                playerList[i].getMoney().decreaseMoney(tempFine);

                                if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                                    ((TransportSquare) tempSquare).getOwner().getMoney().increaseMoney(tempFine + playerList[i].getMoney().getCurrentMoney());
                                    playerList[i] = null;
                                    currentPlayerSize--;

                                    //If only one player left in the game, finish the game.
                                    if (currentPlayerSize == 1) {
                                        check = false;
                                        break;
                                    }

                                }
                                ((TransportSquare) tempSquare).getOwner().getMoney().increaseMoney(((TransportSquare) tempSquare).getFine());
                            }
                        }
                    }

                    //ownerı yoksa
                    else {
                        choiceDice.rollDice();
                        int choiceDiceValue = choiceDice.getTotal();

                        //treshold değeri inputtan alınacak!!!!!
                        if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > ((TransportSquare) tempSquare).getPrice()) {
                            ((TransportSquare) tempSquare).setOwner(playerList[i]);
                            playerList[i].addTransportLister(tempSquare);
                            playerList[i].addProperty(tempSquare);
                        }
                    }

                } else if (tempSquare instanceof UtilitySquare) {
                    //ownerı varsa
                    if (((UtilitySquare) tempSquare).getHasOwner()) {
                        if (((UtilitySquare) tempSquare).getOwner().getPlayerName() != playerList[i].getPlayerName()) {
                            if (!(((UtilitySquare) tempSquare).getOwner().isInJail())) {

                                int tempFine = ((UtilitySquare) tempSquare).getOwner().getUtilityCount() * ((UtilitySquare) tempSquare).getFine(diceValue);
                                playerList[i].getMoney().decreaseMoney(tempFine);

                                if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                                    ((UtilitySquare) tempSquare).getOwner().getMoney().increaseMoney(tempFine + playerList[i].getMoney().getCurrentMoney());
                                    playerList[i] = null;
                                    currentPlayerSize--;

                                    //If only one player left in the game, finish the game.
                                    if (currentPlayerSize == 1) {
                                        check = false;
                                        break;
                                    }

                                }
                                ((UtilitySquare) tempSquare).getOwner().getMoney().increaseMoney(tempFine);
                            }
                        }
                    }

                    //ownerı yoksa
                    else {
                        choiceDice.rollDice();
                        int choiceDiceValue = choiceDice.getTotal();

                        if (choiceDiceValue > threshold && playerList[i].getMoney().getCurrentMoney() > ((UtilitySquare) tempSquare).getPrice()) {
                            ((UtilitySquare) tempSquare).setOwner(playerList[i]);
                            playerList[i].addUtilityList( tempSquare);
                            playerList[i].addProperty(tempSquare);
                        }
                    }
                }
                if (tempSquare instanceof GoToJailSquare) {

                    playerList[i].getPiece().setSquare(board.getSquareList()[10]);
                    playerList[i].setJailTurnCounter(0);
                    playerList[i].setInJail(true);

                }

                if(tempSquare instanceof TaxSquare){
                    playerList[i].getMoney().decreaseMoney(((TaxSquare) tempSquare).getFine());

                    if(playerList[i].getMoney().getCurrentMoney() <= 0){
                        playerList[i].emptyOwnedSquares();
                        playerList[i] = null;
                        currentPlayerSize--;

                        if (currentPlayerSize == 1) {
                            check = false;
                            break;
                        }

                    }
                }

                if (i != playerSize - 1) {
                    System.out.println("***********");
                }

                //Check if dices are equal each other.
                if (firstDice == secondDice) {
                    continue;
                } else {
                    i++;
                }
            }
        }

        for (int i = 0; i < playerSize; i++) {
            if (playerList[i] != null) {
                System.out.println("WINNER : " + playerList[i].getPlayerName());
            }
        }
    }

    //Create player list.
    public void createPlayerList() {
        this.playerList = new Player[this.playerSize];
    }

    //Create piece list.
    public void createPieceList() {
        this.pieceList = new Piece[this.playerSize];
    }

    //Create first player list.
    public void createPlayerOldList() {
        this.playerOldList = new Player[this.playerSize];
    }

    //Create dices.
    public void createDices() {
        this.dices = new int[this.playerSize];
    }

    //Print dice values.
    public void printDiceRoll() {
        System.out.println("Players roll dices to determine their turns.");
        for (int i = 0; i < playerSize; i++) {
            System.out.println(playerOldList[i].getPlayerName() + " rolled " + dices[i] + ".");
        }
        System.out.println("----------------");
    }

    //Roll the dice for turn order and set the turn order of players from biggest to smallest.
    public void rollDiceBeginning() {
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
    public void printPlayerAndPiece() {
        for (int i = 0; i < playerSize; i++) {
            System.out.print("Player : " + playerList[i].getPlayerName() + " -- ");
            System.out.println("Piece: " + pieceList[i].getPieceType());
        }
    }

    public void checkPlayerSize(int intPlayerSize) {
        if (intPlayerSize < 2 || intPlayerSize > 8) {
            System.out.println("Player size must be from 2 to 8.");
            System.exit(1);
        }
    }

    //Getter method for player sizes.
    public int getPlayerSize() {
        return playerSize;
    }


    //Getter method for amount of start money.
    public int getStartMoney() {
        return startMoney;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

}
