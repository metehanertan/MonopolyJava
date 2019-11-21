public class JailSquare extends Square {

    private int squareID;
    private String squareName;

    public JailSquare(int id,String name){
        this.squareID = id;
        this.squareName = name;

    }


    @Override
    int getSquareID() {
        return this.squareID;
    }

    @Override
    void setSquareID(int squareID) {

    }

    @Override
    String getSquareName() {
        return this.squareName;
    }

    @Override
    void setSquareName(String squareName) {

    }

    @Override
    int getFine() {
        return 0;
    }
}
