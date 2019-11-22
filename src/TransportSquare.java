public class TransportSquare extends Square {

    private int squareID;
    private String squareName;
    private int transportFine;
    private Player owner;

    public TransportSquare(int id,String name,int fine){
        this.squareID = id;
        this.squareName = name;
        this.transportFine = fine;
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

    @Override
    public int getFine() {
        return 0;
    }

    public void setOwner(Player player){
        this.owner = player;
    }
}
