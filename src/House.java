public class House {
    private String name;
    private Player owner;
    private boolean hasOwner;
    private PropertySquare square;

    public House() {
        this.name = "HOUSE";
        this.hasOwner = false;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        if (player == null) {
            this.hasOwner = false;
        } else {
            this.hasOwner = true;
        }
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setSquare(PropertySquare square) {
        if (square == null) {
            setOwner(null);
        } else {
            this.square = square;
            setOwner(square.getOwner());
        }
    }

    public PropertySquare getSquare() {
        return square;
    }

}
