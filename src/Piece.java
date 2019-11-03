//Piece class.
public class Piece extends MonopolyGame {

    private final int INITIAL_POSITION = 0; //initial position of each player.
    private String pieceType; //type of the piece e.g. car.
    private Board board;
    private Square position;

    public Piece() {
    }

    //Constructor of Piece Class.
    public Piece(String pieceType, Board board) {
        this.pieceType = pieceType;
        this.board = board;
        this.position = board.getSquareList()[INITIAL_POSITION];
    }

    //getter method for piece.
    public String getPieceType() {

        return pieceType;
    }

    //setter method for piece.
    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    //move method.
    public void move(int move) {
        int moving = getSquare().getSquareID() + move;

        if (moving >= 40) {
            moving = moving - 40;
        }

        this.position = board.getSquareList()[moving];
    }

    public Square getSquare() {
        return position;
    }

    public void setSquare(Square square) {
        this.position = square;
    }
}