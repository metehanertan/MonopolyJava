import org.junit.Test;

import java.awt.event.HierarchyBoundsAdapter;

import static org.junit.Assert.*;

public class PieceTest {

    String pieceName = "DOG";
    int squareID = 13;
    String squareName = "Over the Garden Wall";
    int fine = 0;
    Board board = new Board(0,0);
    Square square = new NormalSquare(squareID,squareName);
    Piece piece = new Piece(pieceName,board);
    Player player = new Player(" Wirt ", 200);


    @Test
    public void TestGetPieceType() {
        assertEquals("Piece type is not assigned properly.", piece.getPieceType(),pieceName);
    }

    @Test
    public void TestSetSquare() {
        piece.setSquare(this.square);
        assertEquals("Piece position set is not assigned properly.", this.square,piece.getSquare());
    }

    @Test
    public void TestMove() {
        piece.setSquare(square);
        int move = 5;
        int position = piece.getSquare().getSquareID() + move;
        piece.move(move,player,200);
        assertEquals("Piece move is not assigned properly.", position , piece.getSquare().getSquareID());


    }

}