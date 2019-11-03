//Player class.
public class Player extends MonopolyGame {

    private final int INITIAL_POSITION = 0; //initial position of each player.
    private int position; //current position of each player.
    private Piece piece; //piece types
    private Money money;
    private Square square;
    private boolean inJail;
    private boolean isBankrupt;
    private String playerName;


    //Default constructor
    public Player() {

    }

    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
    }

    public String report() {
        return "";
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Money getMoney(){
        return this.money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}