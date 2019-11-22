
public class MonopolyGame {
    private final String[] NAMES = {"ARDA", "EKIN", "MINEL", "METE", "HAMZA", "MELISA", "BARIS", "EYLUL"}; //String array which contains name of players
    private final String[] PIECES = {"DOG", "HAT", "BOOT", "THIMBLE", "CAT", "CAR", "SHIP", "HORSEMAN"}; //String array which contains piece types of players
    private Board board; //Board object
    private Player[] playerList; //Player array to create given number of players
    private Player[] playerOldList; //Not ordered player list
    private int playerSize, threshold, startMoney, taxNumber;
    private Dice dice; //Dice object
    private Dice buyingDice;
    private Piece[] pieceList; //Piece array which contains Piece objects.
    private int goMoney; //Given money amount while passing through GO square
    private int cycle; //Cycle counter
    private int[] dices; //Dice values array to order turns of players

    //Constructor of MonopolyGame Class calling from Main Class.
    public MonopolyGame(int playerSize, int threshold, int startMoney, int taxNumber, int goMoney, String[] properties,
                        int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                        int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                        int[] transportPrice, int[] taxFine, String[] taxSquares) {
        checkPlayerSize(playerSize);
        this.playerSize = playerSize;
            this.threshold = threshold;
        this.startMoney = startMoney;
        //    this.taxNumber = taxNumber;
        this.board = new Board(properties, propertyFine, propertyPrice, propertyColor,
                utilityName, utilityRate, utilityPrice, transportName, transportFine, transportPrice,
                taxFine, taxSquares); //Create Board object.
        this.dice = new Dice(); //Create Dice object
        this.goMoney = goMoney; //Assign GO money.
        this.buyingDice = new Dice(); //Dice for buying chance
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
            dices[i] = dice.getFirstValue() + dice.getSecondValue(); //Roll dices for each player to set the turn order.
        }

        printDiceRoll();
        rollDiceBeginning();
        printPlayerAndPiece();

        boolean check = true;
        int currentPlayerSize = playerSize;

        Square tempSqaure;

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

                playerList[i].getPiece().move(diceValue, playerList[i], goMoney); //Move the player according to dice values.
                System.out.println("\nFirst dice is : " + firstDice + " Second dice is : " + secondDice);
                System.out.println("Sum of dices is " + diceValue);
                playerList[i].reportAfterRoll();

                tempSqaure = playerList[i].getPiece().getSquare();

                if (tempSqaure instanceof PropertySquare) {

                    if (((PropertySquare) tempSqaure).getHasOwner()) {

                        if (((PropertySquare) tempSqaure).getOwner().getPlayerName() == playerList[i].getPlayerName()) {
                            continue;
                        } else {
                            playerList[i].getMoney().decreaseMoney(tempSqaure.getFine());
                            ((PropertySquare) tempSqaure).getOwner().getMoney().increaseMoney(tempSqaure.getFine());
                        }
                    } else {

                        buyingDice.rollDice();
                        int buyingDiceValue = buyingDice.getTotal();

                        //treshold değeri inputtan alınacak!!!!!
                        if (buyingDiceValue > threshold) {
                            ((PropertySquare) tempSqaure).setOwner(playerList[i]);
                        } else {
                            continue;
                        }
                    }


                } else if (tempSqaure instanceof TransportSquare) {

                    //ownerı varsa --- birden fazla transport olması durumu control edilecek arraylist!!
                    if (((TransportSquare) tempSqaure).getHasOwner()) {

                        if (((TransportSquare) tempSqaure).getOwner().getPlayerName() == playerList[i].getPlayerName()) {

                            continue;
                        } else {
                            playerList[i].getMoney().decreaseMoney(tempSqaure.getFine());
                            ((TransportSquare) tempSqaure).getOwner().getMoney().increaseMoney(tempSqaure.getFine());
                        }
                    }

                    //ownerı yoksa
                    else {

                        buyingDice.rollDice();
                        int buyingDiceValue = buyingDice.getTotal();

                        //treshold değeri inputtan alınacak!!!!!
                        if (buyingDiceValue > threshold) {
                            ((TransportSquare) tempSqaure).setOwner(playerList[i]);
                        } else {
                            continue;
                        }
                    }



                } else if (tempSqaure instanceof UtilitySquare) {

                    //ownerı varsa
                    if (((UtilitySquare) tempSqaure).getHasOwner()) {

                        if (((UtilitySquare) tempSqaure).getOwner().getPlayerName() == playerList[i].getPlayerName()) {

                            continue;
                        } else {
                            playerList[i].getMoney().decreaseMoney(tempSqaure.getFine());
                            ((UtilitySquare) tempSqaure).getOwner().getMoney().increaseMoney(tempSqaure.getFine());
                        }
                    }

                    //ownerı yoksa
                    else {

                        buyingDice.rollDice();
                        int buyingDiceValue = buyingDice.getTotal();

                        //treshold değeri inputtan alınacak!!!!!
                        if (buyingDiceValue > threshold) {
                            ((UtilitySquare) tempSqaure).setOwner(playerList[i]);
                        } else {
                            continue;
                        }
                    }

                }


                if (i != playerSize - 1) {
                    System.out.println("***********");
                }

                //Check if current square is tax square
                if (playerList[i].getPiece().getSquare().getSquareName() == "TAX") {

                   // playerList[i].getMoney().decreaseMoney(taxValue);//Decrease money with amount of tax value.

                    //If current money of the current player is less than or equal to zero, player exits from the game.
                    if (playerList[i].getMoney().getCurrentMoney() <= 0) {
                        playerList[i] = null;
                        currentPlayerSize--;

                        //If only one player left in the game, finish the game.
                        if (currentPlayerSize == 1) {
                            check = false;
                            break;
                        }
                    }
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

    //Getter method for tax value.
   /* public int getTaxValue() {
        return taxValue;
    }*/

    //Getteer method for number of tax squares.
    public int getTaxNumber() {
        return taxNumber;
    }

    //Getter method for amount of start money.
    public int getStartMoney() {
        return startMoney;
    }

    public Player[] getPlayerList() {
        return playerList;
    }
}
