public class PropertySquare extends Square {

    // Create necessary variables
    private int squareID;
    private int fine;
    private int price;
    private String squareName;
    private String color;
    private Player owner;

    private boolean hasOwner;

    // Constructor
    public PropertySquare(int squareID, String squareName, int fine, String color, int price) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.color = color;
        this.fine = fine;
        this.price = price;
        this.hasOwner = false;
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

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Player getOwner() {

        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

}
