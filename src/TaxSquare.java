// Class of tax square
public class TaxSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int fine;

    // Constructor
    public TaxSquare(int squareID, String squareName, int fine) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.fine = fine;
    }

    // Return square id
    public int getSquareID() {
        return squareID;
    }

    // Set square id
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    // Return square name
    public String getSquareName() {
        return squareName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    // Return fine
    public int getFine() {
        return this.fine;
    }
    
}
