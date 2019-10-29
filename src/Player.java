//Player class.
public class Player extends MonopolyGame {

    private final int INITIAL_POSITION = 0; //initial position of each player.
    private int position; //current position of each player.
    private Piece pieceType; //piece types
    private Money money;
    private Square square;
    private boolean inJail;
    private boolean isBankrupt;
    private String playerName;

    //Default constructor
    public Player(){

    }

    //Constructor of Player Class with given parameters.
    public Player(String playerName, String pieceType, int startMoney) {
        this.playerName = playerName;
        this.pieceType = new Piece(pieceType);
        this.money = new Money(startMoney);
        this.square = new Square(INITIAL_POSITION);
    }

    //move method.
    public void move(int move) {

        this.position += move;
    }

    public String report(){

        return "";
    }

    public int getPosition() {

        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMovePosition(){
        this.position -= 40;
    }

    public Square getSquare() {

        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}