public class GoToJailSquare extends Square {
    private int squareID;
    private String squareName;

    public GoToJailSquare(int id,String name){
        this.squareID = id;
        this.squareName = name;

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
        return 0;
    }

    public void goToJail(Square square, JailSquare jailSquare){
        square = jailSquare;
    }
}
