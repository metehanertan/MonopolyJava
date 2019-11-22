public class JailSquare extends Square {

    private int squareID;
    private String squareName;

    public JailSquare(int id,String name){
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

    @Override
    public int getFine() {
        return 0;
    }
}
