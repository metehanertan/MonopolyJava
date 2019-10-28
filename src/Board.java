public class Board extends MonopolyGame{

    private final int BOARD_SIZE = 40;
    private Square[] squareList;

    public void Board(){

        this.squareList = new Square[BOARD_SIZE];
        setSquareList();

    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public Square[] getSquareList() {
        return squareList;
    }

    public void setSquareList() {

        for (int i = 0; i < BOARD_SIZE;i++)
        {
            this.squareList[i] = new Square(i);
        }

        this.squareList[0].setSquareName("GO");
    }
}
