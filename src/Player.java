public class Player extends MonopolyGame {

    private final int INITIAL_POSITION = 0;
    private int position;
    private Piece pieceType;
    private Money money;
    private Square square;
    private boolean inJail;
    private boolean isBankrupt;
    private String playerName;

    public Player(){

    }

    public Player(String playerName, String pieceType, int startMoney) {
        this.playerName = playerName;
        this.pieceType = new Piece(pieceType);
        this.money = new Money(startMoney);
        this.square = new Square(INITIAL_POSITION);
    }

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
