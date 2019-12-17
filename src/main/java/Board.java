// This class represents a Monopoly board
public class Board {

    private final int BOARD_SIZE = 40; // Board size. It is fixed to 40.
    private Square[] squareList; // Square list to keep all squares on the board.
    private String[] properties; // String array that contains property names.
    private int[] propertyFine; // Integer array that contains properties fine values.
    private int[] propertyPrice; // Integer array that contains properties price for buying functionality
    private String[] propertyColor; // String array that contains properties colors.
    private String[] utilityName; // String array that contains utilities names.
    private int[] utilityRate; // Integer array that contains utilities rate.
    private int[] utilityPrice; // Integer array that contains utilities prices.
    private String[] transportName; // String array that contains transport names.
    private int[] transportFine; // Integer array that contains transports fine values.
    private int[] transportPrice; // Integer array that contains transports prices for buying.
    private int[] taxFine; // Integer array that contains tax squares fine values.
    private String[] taxSquares; // String array that contains type of taxes.
    private int goToJailNumber; // Go to jail squares numbers in the board.
    private int jailFine; // Amount for go out from the jail

    // Default constructor of Board Class.
    public Board(String[] properties,
                 int[] propertyFine, int[] propertyPrice, String[] propertyColor, String[] utilityName,
                 int[] utilityRate, int[] utilityPrice, String[] transportName, int[] transportFine,
                 int[] transportPrice, int[] taxFine, String[] taxSquares, int goToJailNumber, int jailFine) {
        this.squareList = new Square[BOARD_SIZE]; // Set the square list's size to board size.
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
        this.jailFine = jailFine;
    }

    // Getter method for board size.
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    // Getter method for square list
    public Square[] getSquareList() {
        return this.squareList;
    }

    // Set the square list
    public void setSquareList() {

        int id = 0;
        int propertyIndex = 0;
        int colorIndex = 0;
        int colorCount = 0;
        int transportIndex = 0;
        int utilityIndex = 0;
        int taxIndex = 0;

        while (true) {

            //Set the first square as GO square
            if (id == 0) {
                this.squareList[id] = new GoSquare(0, "GO");
            }

            //Set the squares with id number 2,7,17,22,33 and 36 as NORMAL square.
            else if (id == 2 || id == 7 || id == 17 || id == 22 || id == 33 || id == 36) {
                this.squareList[id] = new NormalSquare(id, "NORMAL");
            }

            //Set the squares with id number 4 and 38 as TAX square.
            else if (id == 4 || id == 38) {
                this.squareList[id] = new TaxSquare(id, taxSquares[taxIndex], taxFine[taxIndex]);
                taxIndex++;
            }

            //Set the squares with id number 10 as JAIL square.
            else if (id == 10) {
                this.squareList[id] = new JailSquare(id, "JAIL", jailFine);
            }

            //Set the 20. square as FREE PARKING square.
            else if (id == 20) {
                this.squareList[id] = new FreeParkingSquare(id, "FREEPARKING");
            }

            //Set the squares with id number 5,15,25 and 35 as TRANSPORT sqaures.
            else if (id == 5 || id == 15 || id == 25 || id == 35) {

                this.squareList[id] = new TransportSquare(id, transportName[transportIndex],
                        transportFine[transportIndex], transportPrice[transportIndex]);
                transportIndex++;
            }

            //Set the squares with id number 12 and 28 as UTILITY square.
            else if (id == 12 || id == 28) {
                this.squareList[id] = new UtilitySquare(id, utilityName[utilityIndex],
                        utilityRate[utilityIndex], utilityPrice[utilityIndex]);
                utilityIndex++;
            }

            //Set the 30. square as GO TO JAIL square
            else if (id == 30) {
                //GO TO JAIL
                this.squareList[id] = new GoToJailSquare(id, "GOTOJAIL");
            }

            //Set the remaining squares as PROPERTY squares.
            else {
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

            if (id == 40) {
                break;
            }
        }


        //Place GO TO JAIL squares with the given input value of go to jail.
        while (true) {
            int rand = (int) (Math.random() * 38) + 1;
            if(goToJailNumber == 0){
                System.out.println("goToJailNumber can be at least 1, the variable was set to 1.");
                goToJailNumber = 1;
                break;
            }
            else if (goToJailNumber == 1) {
                break;
            } else if (squareList[rand] instanceof PropertySquare || squareList[rand] instanceof TransportSquare || squareList[rand] instanceof UtilitySquare) {
                this.squareList[rand] = new GoToJailSquare(rand, "GOTOJAIL");
                goToJailNumber--;
            }
        }

        for (Square i : squareList) {
            System.out.print(i.getSquareID());
            System.out.print(" : " + i.getSquareName());
            if (i instanceof PropertySquare) {
                System.out.println(" Color : " + ((PropertySquare) i).getColor());
            } else {
                System.out.println();
            }
        }
    }

    public JailSquare getJailSquare(){
        return (JailSquare) squareList[10];
    }
}
