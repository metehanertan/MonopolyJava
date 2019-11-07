// Class of normal square
public class NormalSquare extends Square{

    // Create necessary variables
    private int squareID;
    private String squareName;
    private final int FINE = 0;

    // Constructor
    public NormalSquare(int squareID, String squareName){
        this.squareID = squareID;
        this.squareName = squareName;
    }

    // Return square id
    public int getSquareID(){
        return squareID;
    }

    // Set square id
    public void setSquareID(int squareID){
        this.squareID = squareID;
    }

    // Return square name
    public String getSquareName(){
        return squareName;
    }

    // Set square name
    public void setSquareName(String squareName){
        this.squareName = squareName;
    }

    // Return fine
    public int getFine() {
        return this.FINE;
    }
}
