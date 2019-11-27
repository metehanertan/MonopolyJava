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
    private ArrayList<Square> properties;
    private ArrayList<Square> transportList;
    private ArrayList<Square> utilityList;
    private Dice moveDice;
    private Dice choiceDice;

    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
        this.properties = new ArrayList<Square>();
        this.utilityList = new ArrayList<Square>();
        this.transportList = new ArrayList<Square>();
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

    //Setter method of player name
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    //Getter method of turn
    public int getTurn() {
        return this.turn;
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

    public ArrayList<Square> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Square> properties) {
        this.properties = properties;
    }

    public void increaseJailTurnCounter() {
        this.jailTurnCounter++;
    }

    public void addProperty(Square property) {
        this.properties.add(property);
    }

    public int getTransportCount() {
        return transportList.size();
    }

    public void settransportList(ArrayList<Square> transportList) {
        this.transportList = transportList;
    }

    public int getUtilityCount() {
        return utilityList.size();
    }

    public void setUtilityList(ArrayList<Square> utilityList) {
        this.utilityList = utilityList;
    }

    public void addTransportLister(Square transportSquare) {

        this.transportList.add(transportSquare);
    }

    public void addUtilityList(Square utilitySquare) {

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

    public void setMoveDice(Dice moveDice) {
        this.moveDice = moveDice;
    }

    public Dice getChoiceDice() {
        return choiceDice;
    }

    public void setChoiceDice(Dice choiceDice) {
        this.choiceDice = choiceDice;
    }

    public void rollMoveDice(){
        this.moveDice.rollDice();
    }

    public void rollChoiceDice(){
        this.choiceDice.rollDice();
    }

}