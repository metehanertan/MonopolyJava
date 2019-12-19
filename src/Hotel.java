public class Hotel {
    private String name;
    private Player owner;
    private boolean hasOwner;

    public Hotel(){

        this.name = "HOTEL";
        this.hasOwner = false;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player){
        this.owner = player ;
        this.hasOwner = true;

    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

}
