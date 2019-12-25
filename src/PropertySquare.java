public class PropertySquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private int fine;
    private int price;
    private String squareName;
    private String color;
    private Player owner;
    private boolean hasOwner;
    private int houseCount;
    private int hotelCount;
    private int housePrice;
    private int hotelRent;
    private int rent1;
    private int rent2;
    private int rent3;
    private int rent4;
    private int mortgage;


    // Constructor
    public PropertySquare(int squareID, String squareName, int fine, String color, int price, int housePrice,
                          int hotelRent, int rent1, int rent2, int rent3, int rent4, int mortgage) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.color = color;
        this.fine = fine;
        this.price = price;
        this.hasOwner = false;
        this.housePrice = housePrice;
        this.hotelRent = hotelRent;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.mortgage = mortgage;
        this.houseCount = 0;
        this.hotelCount = 0;

    }

    // Return square id
    public int getSquareID() {
        return squareID;
    }

    // Set square id
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    // Return square name
    public String getSquareName() {
        return squareName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    // Getter and setter methods
    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public void payRent(Player player, Board board) {

        if (player != owner) {
            if (!owner.isInJail()) {
                int tempFine = fine;
                if (owner.hasItAll(this, board)) {
                    tempFine = 2 * tempFine;
                    System.out.println("Player " + owner.getPlayerName() + " has all " + color + " colors.");
                }

                if (player.isAbleDecreaseMoney(tempFine)) {
                    player.payMoneyToOwner(owner, tempFine);
                } else {
                    player.playerGoesToBankrupt(tempFine, owner);
                }
            } else {
                System.out.println("*The owner " + owner.getPlayerName() + " is in Jail*");
            }
        }
    }


    public void sellHouse(Player player, MonopolyGame mpGame) {
        player.rollChoiceDice();

        if (player.getChoiceDice().getTotal() > mpGame.getThreshold()) {
            // BURADA SIKINTI ÇIKABİLİR GİRİLEN SAYILARA GÖRE DOUBLE OLMASINI İSTEYECEK
            player.getMoney().increaseMoney((int) (housePrice / 2));
            houseCount--;
        }
    }

    public void sellHotel(Player player, MonopolyGame mpGame) {
        player.rollChoiceDice();

        if (player.getChoiceDice().getTotal() > mpGame.getThreshold()) {
            // BURADA SIKINTI ÇIKABİLİR GİRİLEN SAYILARA GÖRE DOUBLE OLMASINI İSTEYECEK
            player.getMoney().increaseMoney((int) (housePrice / 2));
            houseCount = 4;
            hotelCount--;
        }
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    public void setHotelCount(int hotelCount) {
        this.hotelCount = hotelCount;
    }

    public void decreaseHouseCount(){
        this.houseCount--;
    }

    public void increaseHouseCount(){
        this.houseCount++;
    }

    public void decreaseHotelCount(){
        this.hotelCount--;
    }

    public void increaseHotelCount(){
        this.hotelCount++;
    }

    public int getRent1() {
        return rent1;
    }

    public int getRent2() {
        return rent2;
    }

    public int getHotelRent() {
        return hotelRent;
    }

    public int getRent3() {
        return rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public int getMortgage() {
        return mortgage;
    }
}
