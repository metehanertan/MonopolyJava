//Piece class.
public class Piece extends Player{

    private String pieceType; //type of the piece e.g. car.

    //Constructor of Piece Class.
    public Piece(String pieceType){

        this.pieceType = pieceType;
    }

    //getter method for piece.
    public String getPieceType() {

        return pieceType;
    }

    //setter method for piece.
    public void setPieceType(String pieceType) {

        this.pieceType = pieceType;
    }
}