//Board Class to create a board.
public class Board {

    private final int BOARD_SIZE = 40; //board size. it is fixed to 40.
    private Square[] squareList; //Square list to keep all squares on the board.
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
    private int goToJailNumber;

    //Default constructor of Board Class.
    public Board(String[] properties,
                 int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                 int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                 int[] transportPrice, int[] taxFine, String[] taxSquares,int goToJailNumber) {
        this.squareList = new Square[BOARD_SIZE]; //set the square list's size to board size.
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
        this.goToJailNumber = goToJailNumber;
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

        int id = 0;
        int propertyIndex = 0;
        int colorIndex = 0;
        int colorCount = 0;
        int transportIndex = 0;
        int utilityIndex = 0;
        int taxIndex = 0;

        while (true) {

            if (id == 0) {
                this.squareList[id] = new GoSquare(0, "GO"); //set the first square as GoSquare.
            } else if (id == 2 || id == 7 || id == 17 || id == 22 || id == 33 || id == 36) {
                this.squareList[id] = new NormalSquare(id, "NORMAL");
            } else if (id == 4 || id == 38) {
                this.squareList[id] = new TaxSquare(id, taxSquares[taxIndex], taxFine[taxIndex]);
                taxIndex++;
            } else if (id == 10) {
                //Jail
                this.squareList[id] = new JailSquare(id, "JAIL");
            } else if (id == 20) {
                //Free Parking
                this.squareList[id] = new FreeParkingSquare(id, "FREEPARKING");
            } else if (id == 5 || id == 15 || id == 25 || id == 35) {
                //Transports
                this.squareList[id] = new TransportSquare(id, "TRANSPORT",transportFine[transportIndex]);
                transportIndex++;
            } else if (id == 12 || id == 28) {
                this.squareList[id] = new UtilitySquare(id, utilityName[utilityIndex],
                        utilityRate[utilityIndex], utilityPrice[utilityIndex]);
                utilityIndex++;
            } else if (id == 30) {
                //GO TO JAIL
                this.squareList[id] = new GoToJailSquare(id, "GOTOJAIL");
            } else {
                this.squareList[id] = new PropertySquare(id, properties[propertyIndex], propertyFine[propertyIndex],
                        propertyColor[colorIndex], propertyPrice[propertyIndex]);
                propertyIndex++;
                colorCount++;
                if (colorCount == 3) {
                    colorIndex++;
                    colorCount = 0;
                }
            }

            id++;

            if(id == 40){
                break;
            }
        }


        while(true){
            int rand = (int) (Math.random()*38)+1;
            if (goToJailNumber==1){
                break;
            } else if(squareList[rand] instanceof PropertySquare || squareList[rand] instanceof TransportSquare || squareList[rand] instanceof UtilitySquare){
                this.squareList[rand] = new GoToJailSquare(rand, "GOTOJAIL");
                goToJailNumber--;
            }
        }

        for(Square i : squareList){
            System.out.print(i.getSquareID());
            System.out.println(" : " + i.getSquareName());
        }

    }
}
