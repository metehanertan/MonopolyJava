public class TransportSquare extends PurchasableSquare {

    private int squareID;
    private String squareName;
    private int transportFine;
    private Player owner;
    private int price;


    private boolean hasOwner;

    public TransportSquare(int id, String name, int fine, int price) {
        this.squareID = id;
        this.squareName = name;
        this.transportFine = fine;
        this.hasOwner = false;
        this.price = price;
    }

    @Override
    public int getSquareID() {
        return this.squareID;
    }

    @Override
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    @Override
    public String getSquareName() {
        return this.squareName;
    }

    @Override
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }


    public int getFine() {
        return this.transportFine;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFine(int fine) {
        this.transportFine = fine;
    }

}
