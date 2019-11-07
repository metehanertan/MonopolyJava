//Player class.
public class Player{

    private final int INITIAL_POSITION = 0; //initial position of each player.
    private int position; //current position of each player.
    private Piece piece; //piece types
    private Money money;
    private Square square;
    private boolean inJail;
    private boolean isBankrupt;
    private String playerName;
    private int turn;
    private int cycleCounter;

    //Constructor of Player Class with given parameters.
    public Player(String playerName, int startMoney) {
        this.playerName = playerName;
        this.money = new Money(startMoney);
        this.cycleCounter = 0;
    }

    public void reportBeforeRoll() {
        System.out.println("Player : " + this.playerName);
        System.out.println("Turn : " + this.turn);
        System.out.println("Current balance : " + this.money.getCurrentMoney());
        System.out.println("Location : Square " + this.piece.getSquare().getSquareID());
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
    }

    public void reportAfterRoll(){
        System.out.println();
        System.out.println("New location : Square " + this.piece.getSquare().getSquareID());
        System.out.println("Type of square : " + this.piece.getSquare().getSquareName());
        System.out.println("The amount of tax : " + this.piece.getSquare().getFine());
        System.out.println("Current balance : " + this.money.getCurrentMoney());
        System.out.println();
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

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void increaseCycleCounter(){
        this.cycleCounter++;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn){
        this.turn = turn;
    }
}