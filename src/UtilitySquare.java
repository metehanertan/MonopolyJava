// This class represents the utility squares on the board
public class UtilitySquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private String utilityName;
    private int rate;
    private Player owner;
    private int price;
    private int fine;
    private boolean hasOwner;

    // Constructor
    public UtilitySquare(int squareID, String utilityName, int rate, int price) {
        this.squareID = squareID;
        this.utilityName = utilityName;
        this.rate = rate;
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
        return utilityName;
    }

    // Set square name
    public void setSquareName(String squareName) {
        this.utilityName = squareName;
    }

    // Calculate the exact fine for utilities
    public int getFine(int diceValue) {
        // RATE X DICE
        this.fine = rate * diceValue;
        return fine;
    }

    // Getter and setter methods
    public int getFine() {
        return fine;
    }

    public int getRate() {
        return rate;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
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

    @Override
    public void payRent(Player player, Board board, MonopolyGame mpGame) {
        // Check if the owner is himself
        if (player != owner) {
            // Check if the owner is in jail
            if (!owner.isInJail()) {
                int tempFine = owner.getUtilityCount() * getFine(player.getMoveDice().getTotal());

                // Player sells his properties if he has not enough money to pay fine
                while (player.getMoney().getCurrentMoney() <= tempFine) {
                    if (!player.sellCheapest())
                        break;
                }

                player.getMoney().decreaseMoney(tempFine);

                // If player goes to bankruptcy
                if (player.getMoney().getCurrentMoney() <= 0) {
                    owner.getMoney().increaseMoney(tempFine + player.getMoney().getCurrentMoney());
                    System.out.println("***" + player.getPlayerName() + " HAS PAID \'"
                            + (tempFine + player.getMoney().getCurrentMoney()) + "$\' TO "
                            + owner.getPlayerName() + "***");
                    System.out.println("!!! " + player.getPlayerName() + "  has gone bankrupt!!!\n");

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

    @Override
    public void buyProperty(Player player, MonopolyGame mpGame) {
        // Player roll the choice dice
        player.rollChoiceDice();
        int choiceDiceValue = player.getChoiceDice().getTotal();

        // If player wants to take this square and has enough money to buy
        if (choiceDiceValue > mpGame.getThreshold() && player.getMoney().getCurrentMoney() > price) {
            setOwner(player);
            player.addUtilityList(this);
            player.addProperty(this);
            System.out.println("***" + player.getPlayerName() + " BOUGHT "
                    + utilityName + "***");
        }
    }


}
