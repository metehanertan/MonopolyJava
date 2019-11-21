public class Utility extends Square {

    // Create necessary variables
    private int squareID;
    private String utilityName;
    private int rate;
    private Player owner;
    private int price;

    // Constructor
    public Utility(int squareID, String utilityName, int rate, int price) {
        this.squareID = squareID;
        this.utilityName = utilityName;
        this.rate = rate;
        this.price = price;
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

    @Override
    int getFine() {
        // RATE X ZAR
        return rate;
    }

    public int getRate() {
        return rate;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
