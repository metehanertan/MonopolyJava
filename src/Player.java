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
        //System.out.println("The amount of tax : " + this.piece.getSquare().getFine());
        System.out.println("Current balance : " + this.money.getCurrentMoney());
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

    public void addTransportLister(PurchasableSquare transportSquare) {

        this.transportList.add(transportSquare);
    }

    public void addUtilityList(PurchasableSquare utilitySquare) {

        this.utilityList.add(utilitySquare);
    }

    public void emptyOwnedSquares() {


        for (int i = 0; i < properties.size(); i++) {

            if (properties.get(i) instanceof PropertySquare) {
                ((PropertySquare) properties.get(i)).setOwner(null);
                ((PropertySquare) properties.get(i)).setHasOwner(false);

            }
        }

        for (int k = 0; k < transportList.size(); k++) {

            if (transportList.get(k) instanceof TransportSquare) {

                ((TransportSquare) transportList.get(k)).setOwner(null);
                ((TransportSquare) transportList.get(k)).setHasOwner(false);

            }
        }

        for (int j = 0; j < utilityList.size(); j++) {
            if (utilityList.get(j) instanceof UtilitySquare) {
                ((UtilitySquare) utilityList.get(j)).setOwner(null);
                ((UtilitySquare) utilityList.get(j)).setHasOwner(false);

            }
        }
    }

    public Dice getMoveDice() {
        return moveDice;
    }

    public Dice getChoiceDice() {
        return choiceDice;
    }

    public void rollMoveDice() {
        this.moveDice.rollDice();
    }

    public void rollChoiceDice() {
        this.choiceDice.rollDice();
    }

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
                    }else{
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
                    }else{
                        return false;
                    }
                }
            }
            i++;
        }
        return true;
    }

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

}