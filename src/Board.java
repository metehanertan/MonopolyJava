//Board Class to create a board.
public class Board {

    private final int BOARD_SIZE = 40; //board size. it is fixed to 40.
    private Square[] squareList; //Square list to keep all squares on the board.
    private int taxNumber; //Number of tax squares on the board.
    private int taxValue; //Amount of tax value.
    private String[] properties;
    private int[] propertyFine;
    private int[] propertyPrice;
    private String[] propertyColor;
    private String[] utilityName;
    private int[] utilityRate;
    private int[] utilityPrice;
    private String[] transportName;
    private int[] transportFine;
    private int[] transportPrice;
    private int[] taxFine;
    private String[] taxSquares;

    //Default constructor of Board Class.
    public Board(int taxNumber, int taxValue, String[] properties,
                 int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                 int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                 int[] transportPrice, int[] taxFine, String[] taxSquares) {
        this.squareList = new Square[BOARD_SIZE]; //set the square list's size to board size.
        this.taxNumber = taxNumber;
        this.taxValue = taxValue;
        this.properties = properties;
        this.propertyFine = propertyFine;
        this.propertyColor = propertyColor;
        this.propertyPrice = propertyPrice;
        this.utilityName = utilityName;
        this.utilityPrice = utilityPrice;
        this.utilityRate = utilityRate;
        this.transportFine = transportFine;
        this.transportName = transportName;
        this.transportPrice = transportPrice;
        this.taxFine = taxFine;
        this.taxSquares = taxSquares;
    }

    //getter method for board size.
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    //getter method for square list
    public Square[] getSquareList() {
        return this.squareList;
    }

    //set the square list
    public void setSquareList() {

        int random, count = 0;
        while (true) {
            random = (int) (Math.random() * 39 + 1); //Generate random numbers between 0-39.

            if (count < taxNumber) {
                if (squareList[random] == null) {
                    squareList[random] = new TaxSquare(random, "TAX", taxValue); //Create tax square.
                    count++;
                }
            } else {
                for (int i = 0; i < BOARD_SIZE; i++) {
                    if (squareList[i] == null) {
                        squareList[i] = new NormalSquare(i, "NORMAL"); //Create normal square.
                    }
                }
                break;
            }
        }
        this.squareList[0] = new GoSquare(0, "GO"); //set the first square as GoSquare.

    }
}
