// This class represents purchasable squares on the board
public abstract class PurchasableSquare extends Square {

    public abstract int getFine();

    public abstract void setOwner(Player player);

    public abstract Player getOwner();

    public abstract int getPrice();

    public abstract void setPrice(int price);

    public abstract void setHasOwner(boolean hasOwner);

}