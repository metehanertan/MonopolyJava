//Board Class to create a board.
public class Board{

    private final int BOARD_SIZE = 40; //board size. it is fixed to 40.
    private Square[] squareList; //Square list to keep all squares on the board.
    private int taxNumber; //Number of tax squares on the board.
    private int taxValue; //Amount of tax value.

    //Default constructor of Board Class.
    public Board(int taxNumber,int taxValue){
        this.squareList = new Square[BOARD_SIZE]; //set the square list's size to board size.
        this.taxNumber = taxNumber;
        this.taxValue = taxValue;
    }

    //getter method for board size.
    public int getBoardSize(){
        return BOARD_SIZE;
    }

    //getter method for square list
    public Square[] getSquareList() {
        return this.squareList;
    }

    //set the square list
    public void setSquareList() {

        int random, count = 0;
        while(true)
        {
            random = (int) (Math.random() * 39 + 1 ); //Generate random numbers between 0-39.

            if(count < taxNumber){
                if(squareList[random] == null){
                    squareList[random] = new TaxSquare(random,"TAX",taxValue); //Create tax square.
                    count++;
                }
            }
            else {
                for(int i = 0; i < BOARD_SIZE; i++){
                    if(squareList[i] == null){
                        squareList[i] = new NormalSquare(i,"NORMAL"); //Create normal square.
                    }
                }
                break;
            }
        }
        this.squareList[0] = new GoSquare(0, "GO"); //set the first square as GoSquare.

    }
}
