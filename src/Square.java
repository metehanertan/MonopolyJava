//This class creates squares on the board

public abstract class Square extends Board {

    abstract int getSquareID();
    abstract void setSquareID(int squareID);
    abstract String getSquareName();
    abstract void setSquareName(String squareName);
    abstract int getFine();

}
