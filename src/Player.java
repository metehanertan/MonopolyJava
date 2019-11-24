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
    private int transportCount;
    private int utilityCount;


    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
        this.properties = new ArrayList<Square>();
        this.isInJail = false;
        this.transportCount = 0;
        this.utilityCount = 0;
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

    public void increaseJailTurnCounter(){
        this.jailTurnCounter++;
    }

    public void addProperty(Square property){
        this.properties.add(property);
    }

    public int getTransportCount(){
        return transportCount;
    }
    public void setTransportCount(int transportCount){
        this.transportCount = transportCount;
    }

    public int getUtilityCount(){
        return utilityCount;
    }
    public void setUtilityCount(int utilityCount){
        this.utilityCount = utilityCount;
    }

    public void increaseTransportCounter(){
        this.transportCount++;
    }

    public void increseUtilityCounter(){
        this.utilityCount++;
    }

}