public class TaxSquare extends Square {

    private int squareID;
    private String squareName;
    private int fine;

    public TaxSquare(int squareID, String squareName, int fine) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.fine = fine;

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

    public int getFine() {
        return this.fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
