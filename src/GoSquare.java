public class GoSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private final int FINE = 0;

    // Constructor
    public GoSquare(int squareID, String squareName) {
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

    public int getFine(){
        return FINE;
    }

    public void passGoMoney(int money, Player player){
        player.getMoney().increaseMoney(money);
    }

}
