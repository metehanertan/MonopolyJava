public class Utility extends Square {

    // Create necessary variables
    private int squareID;
    private String utilityName;
    private int fine;
    private Player owner;

    // Constructor
    public Utility(int squareID, String utilityName, int fine) {
        this.squareID = squareID;
        this.utilityName = utilityName;
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
        return utilityName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.utilityName = squareName;
    }

    public int getFine() {
        return fine;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
