import java.util.ArrayList;

//Player class.
public class Player {

    private Piece piece; //Piece types
    private Money money; //Money amount
    private Square square; //Square object
    private String playerName; //Player name
    private int turn; //Turn
    private boolean isInJail;
    private int jailTurnCounter;
    private ArrayList<PurchasableSquare> properties;
    private ArrayList<PurchasableSquare> transportList;
    private ArrayList<PurchasableSquare> utilityList;
    private Dice moveDice;
    private Dice choiceDice;
    private boolean isBankrupted;
    private boolean outOfJailCard;

    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
        this.properties = new ArrayList<PurchasableSquare>();
        this.utilityList = new ArrayList<PurchasableSquare>();
        this.transportList = new ArrayList<PurchasableSquare>();
        this.isInJail = false;
        this.moveDice = new Dice();
        this.choiceDice = new Dice();
        this.isBankrupted = false;
        this.outOfJailCard = false;
    }


    //Print report about current player before roll the dice
    public void reportBeforeRoll() {
        System.out.println("Player : " + this.playerName);
        System.out.println("Turn : " + this.turn);
        System.out.println("Current balance : " + this.money.getCurrentMoney());
        System.out.println("Location : Square " + this.piece.getSquare().getSquareID());
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
    }

    //Print report about current player after roll the dice
    public void reportAfterRoll() {
        System.out.println();
        System.out.println("New location : Square " + this.piece.getSquare().getSquareID());
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
        if(this.piece.getSquare() instanceof TaxSquare){
            System.out.println("The amount of tax : " + ((TaxSquare)this.piece.getSquare()).getFine());
        }
        System.out.println();
    }

    public void reportAfterAction(){
        System.out.println(playerName + "'s New balance : " + this.money.getCurrentMoney());
        System.out.println();
    }

    //Getter method of Piece
    public Piece getPiece() {
        return this.piece;
    }

    //Setter method of Piece
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //Getter method of Piece
    public Money getMoney() {
        return this.money;
    }

    //Getter method of player Name
    public String getPlayerName() {
        return playerName;
    }

    //Setter method of turn
    public void setTurn(int turn) {
        this.turn = turn;
    }

    // Some getter and setter methods
    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public int getJailTurnCounter() {
        return jailTurnCounter;
    }

    public void setJailTurnCounter(int jailTurnCounter) {
        this.jailTurnCounter = jailTurnCounter;
    }

    public ArrayList<PurchasableSquare> getProperties() {
        return properties;
    }

    public void increaseJailTurnCounter() {
        this.jailTurnCounter++;
    }

    public void addProperty(PurchasableSquare property) {
        this.properties.add(property);
    }

    public int getTransportCount() {
        return transportList.size();
    }

    public int getUtilityCount() {
        return utilityList.size();
    }

    public boolean getOutOfJailCard() {
        return outOfJailCard;
    }

    public void setOutOfJailCard(boolean outOfJailCard) {
        this.outOfJailCard = outOfJailCard;
    }

    // Add new properties to the transportList
    public void addTransportLister(PurchasableSquare transportSquare) {

        this.transportList.add(transportSquare);
    }

    // Add new properties to the utilityList
    public void addUtilityList(PurchasableSquare utilitySquare) {

        this.utilityList.add(utilitySquare);
    }

    // If player goes to bankrupt, this method empty his properties
    public void emptyOwnedSquares() {


        for (int i = 0; i < properties.size(); i++) {

            if (properties.get(i) instanceof PropertySquare) {
                (properties.get(i)).setOwner(null);
                (properties.get(i)).setHasOwner(false);

            }
        }

        for (int k = 0; k < transportList.size(); k++) {

            if (transportList.get(k) instanceof TransportSquare) {

                (transportList.get(k)).setOwner(null);
                (transportList.get(k)).setHasOwner(false);

            }
        }

        for (int j = 0; j < utilityList.size(); j++) {
            if (utilityList.get(j) instanceof UtilitySquare) {
                (utilityList.get(j)).setOwner(null);
                (utilityList.get(j)).setHasOwner(false);

            }
        }
    }

    // Some getter and setter methods
    public Dice getMoveDice() {
        return moveDice;
    }

    public Dice getChoiceDice() {
        return choiceDice;
    }

    public boolean getIsBankrupted(){
        return this.isBankrupted;
    }

    public void setIsBankrupted(boolean set){
        this.isBankrupted = set;
    }

    public void rollMoveDice() {
        this.moveDice.rollDice();
    }

    public void rollChoiceDice() {
        this.choiceDice.rollDice();
    }

    // Check if player has all same property colors
    public boolean hasItAll(PropertySquare square, Board board) {

        PropertySquare tempProp;
        int tempIdPos;
        int tempIdNeg;
        for (int i = 1; i <= 5; ) {

            if (square.getSquareID() + i >= 40) {
                tempIdPos = square.getSquareID() + i - 40;
            } else {
                tempIdPos = square.getSquareID() + i;
            }

            if (board.getSquareList()[tempIdPos] instanceof PropertySquare) {
                tempProp = (PropertySquare) board.getSquareList()[tempIdPos];
                if (tempProp.getColor().equals(square.getColor())) {
                    if (tempProp.getHasOwner()) {
                        if (!(tempProp.getOwner().equals(square.getOwner()))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            if (square.getSquareID() - i < 0) {
                tempIdNeg = square.getSquareID() - i + 40;
            } else {
                tempIdNeg = square.getSquareID() - i;
            }

            if (board.getSquareList()[tempIdNeg] instanceof PropertySquare) {
                tempProp = (PropertySquare) board.getSquareList()[tempIdNeg];
                if (tempProp.getColor().equals(square.getColor())) {
                    if (tempProp.getHasOwner()) {
                        if (!(tempProp.getOwner().equals(square.getOwner()))) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
            i++;
        }
        return true;
    }

    // Sell the cheapest property
    public boolean sellCheapest() {

        if (properties.size() == 0) {
            return false;
        }

        PurchasableSquare tempSquare = properties.get(0);

        for (int i = 1; i < properties.size(); i++) {
            if (tempSquare.getPrice() > properties.get(i).getPrice()) {
                tempSquare = properties.get(i);
            }
        }

        money.increaseMoney(tempSquare.getPrice());
        tempSquare.setOwner(null);
        tempSquare.setHasOwner(false);
        properties.remove(tempSquare);
        return true;
    }

    public void buyProperty(PurchasableSquare pSquare,MonopolyGame mpGame) {
        // If square has not an owner
        // Player roll the choice dice
        this.rollChoiceDice();
        int choiceDiceValue = this.getChoiceDice().getTotal();

        // If player wants to take this square and has enough money to buy
        if (choiceDiceValue > mpGame.getThreshold() && this.getMoney().getCurrentMoney() > pSquare.getPrice()) {
            pSquare.setOwner(this);
            this.addProperty(pSquare);
            this.getMoney().decreaseMoney(pSquare.getPrice());
            System.out.println("***" + this.getPlayerName() + " BOUGHT " + pSquare.getSquareName() + "***");
        }
    }



}