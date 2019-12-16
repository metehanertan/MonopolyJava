public class PropertySquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private int fine;
    private int price;
    private String squareName;
    private String color;
    private Player owner;
    private boolean hasOwner;

    // Constructor
    public PropertySquare(int squareID, String squareName, int fine, String color, int price) {
        this.squareID = squareID;
        this.squareName = squareName;
        this.color = color;
        this.fine = fine;
        this.price = price;
        this.hasOwner = false;
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

    public void payRent(Player player, Board board, MonopolyGame mpGame) {

        if (player != owner) {
            if (!owner.isInJail()) {
                int tempFine = fine;
                if (owner.hasItAll(this, board)) {
                    tempFine = 2 * tempFine;
                    System.out.println("Player " + owner.getPlayerName() + " has all " + color + " colors.");
                }

                // Player sells his properties if he has not enough money to pay fine
                while (player.getMoney().getCurrentMoney() <= tempFine) {
                    if (!player.sellCheapest()){
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

                    System.out.println("!!! " + player.getPlayerName() + "  has gone bankrupt!!!\n");

                    player.setIsBankrupted(true);
                    for (int i = 0; i < mpGame.getPlayerSize(); i++) {
                        if (player == mpGame.getPlayerList()[i]) {
                            mpGame.getPlayerList()[i] = null;
                        }
                    }
                    mpGame.decreasePlayerSize();

                } else {
                    System.out.println("***" + player.getPlayerName() + " HAS PAID \'"
                            + tempFine + "$\' TO " + owner.getPlayerName() + "***");
                }
                owner.getMoney().increaseMoney(tempFine);
            }
        }
    }

    public void buyProperty(Player player, MonopolyGame mpGame) {
        // If square has not an owner
        // Player roll the choice dice
        player.rollChoiceDice();
        int choiceDiceValue = player.getChoiceDice().getTotal();

        // If player wants to take this square and has enough money to buy
        if (choiceDiceValue > mpGame.getThreshold() && player.getMoney().getCurrentMoney() > price) {
            setOwner(player);
            player.addProperty(this);
            System.out.println("***" + player.getPlayerName() + " BOUGHT " + squareName + "***");
        }
    }


}
