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
    private int hotelPrice;
    private int houseCounter;

    // Constructor
    public PropertySquare(int squareID, String squareName, int fine, String color, int price, int housePrice, int hotelPrice) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.color = color;
        this.fine = fine;
        this.price = price;
        this.hasOwner = false;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        houseCount = 0;
        hotelCount = 0;
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

                // Player sells his properties if he has not enough money to pay fine
                while (player.getMoney().getCurrentMoney() <= tempFine) {
                    if (!player.sellCheapest()) {
                        break;
                    }
                }

                player.getMoney().decreaseMoney(tempFine);

                // If player goes to bankruptcy
                if (player.getMoney().getCurrentMoney() <= 0) {
                    owner.getMoney().increaseMoney(tempFine
                            + player.getMoney().getCurrentMoney());
                    System.out.println("***" + player.getPlayerName() + " HAS PAID \'"
                            + (tempFine + player.getMoney().getCurrentMoney()) + "$\' TO "
                            + owner.getPlayerName() + "***");

                    System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");

                    player.setIsBankrupted(true);

                } else {
                    System.out.println("***" + player.getPlayerName() + " HAS PAID \'"
                            + tempFine + "$\' TO " + owner.getPlayerName() + "***");
                }
                owner.getMoney().increaseMoney(tempFine);
            }
        }
    }

    public void buyHouse(Player player, MonopolyGame mpGame,Board board) {
        player.rollChoiceDice();

        if (player.getMoney().getCurrentMoney() > housePrice && player.getChoiceDice().getTotal() > mpGame.getThreshold() && player.hasItAll(this,board)) {
            for(int i = 0; i < mpGame.getHouseList().size(); i++){
                if(!mpGame.getHouseList().get(i).getHasOwner()){
                    mpGame.getHouseList().get(i).setOwner(player);
                    player.getMoney().decreaseMoney(housePrice);
                    houseCount++;
                    break;
                }
                if(i == mpGame.getHouseList().size() - 1){
                    System.out.println("There is no available house.");
                }
            }

        }
    }

    public void buyHotel(Player player, MonopolyGame mpGame) {
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
            player.getMoney().increaseMoney((int) (hotelPrice / 2));
            houseCount = 4;
            hotelCount--;
        }
    }

}
