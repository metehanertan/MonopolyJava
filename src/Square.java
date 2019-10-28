//This class creates squares on the board

public class Square extends Board {

    private int squareID; //ID of every square
    private String squareName; //name of every square

    public Square(int squareID) {
        this.squareID = squareID;

    }

    public int getSquareID() {
        return squareID;
    }

    public void setSquareID(int squareID) {

        this.squareID = squareID;
    }

    public String getSquareName() {
        return squareName;
    }

    public void setSquareName(String squareName) {
        this.squareName = squareName;

    }

}
