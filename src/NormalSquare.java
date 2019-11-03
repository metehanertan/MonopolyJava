public class NormalSquare extends Square{

    private int squareID;
    private String squareName;
    private final int FINE = 0;

    public NormalSquare(int squareID, String squareName){
        this.squareID = squareID;
        this.squareName = squareName;
    }

    public int getSquareID(){
        return squareID;
    }

    public void setSquareID(int squareID){
        this.squareID = squareID;
    }

    public String getSquareName(){
        return squareName;
    }

    public void setSquareName(String squareName){
        this.squareName = squareName;
    }

    public int getFine() {
        return this.FINE;
    }
}
