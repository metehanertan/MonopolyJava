public class TransportSquare extends Square {

    private int squareID;
    private String squareName;
    private int transportFine;

    public TransportSquare(int id,String name,int fine){
        this.squareID = id;
        this.squareName = name;
        this.transportFine = fine;

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
