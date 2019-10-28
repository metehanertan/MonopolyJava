//Board Class to create a board.

public class Board extends MonopolyGame{

    private final int BOARD_SIZE = 40; //board size. it is fixed to 40.
    private Square[] squareList; //Square list to keep all squares on the board.

    //Default constructor of Board Class.
    public void Board(){

        this.squareList = new Square[BOARD_SIZE]; //set the square list's size to board size.
        setSquareList(); //set the square list.

    }

    //getter method for board size.
    public int getBoardSize(){

        return BOARD_SIZE;
    }

    //getter method for square list
    public Square[] getSquareList() {
        return squareList;
    }

    //set the square list
    public void setSquareList() {

        for (int i = 0; i < BOARD_SIZE;i++)
        {
            this.squareList[i] = new Square(i);
        }

        this.squareList[0].setSquareName("GO"); //name the first square as GO square.
    }
}
