public class Piece extends Player{

    private String pieceType;

    public Piece(String pieceType){
        this.pieceType = pieceType;
    }

    public String getPieceType() {
        return pieceType;
    }

    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }
}