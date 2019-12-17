package main.java;

// This class represents the transport squares on the board
public class TransportSquare extends PurchasableSquare {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int transportFine;
    private Player owner;
    private int price;
    private boolean hasOwner;

    // Constructor
    public TransportSquare(int id, String name, int fine, int price) {
        this.squareID = id;
        this.squareName = name;
        this.transportFine = fine;
        this.hasOwner = false;
        this.price = price;
    }

    // Getter and setter methods
    @Override
    public int getSquareID() {
        return this.squareID;
    }

    @Override
    public void setSquareID(int squareID) {
        this.squareID = squareID;
    }

    @Override
    public String getSquareName() {
        return this.squareName;
    }

    @Override
    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    public int getFine() {
        return this.transportFine;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        this.hasOwner = true;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFine(int fine) {
        this.transportFine = fine;
    }

    @Override
    public void payRent(Player player, Board board, MonopolyGame mpGame) {
        // Check if the owner is himself
        if (player != owner) {
            // Check if the owner is in jail
            if (!owner.isInJail()) {
                int tempFine = owner.getTransportCount() * transportFine;

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
                    System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");

                    player.setIsBankrupted(true);

                } else {
                    System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + tempFine
                            + "$\' TO " + owner.getPlayerName() + "***");
                }
                owner.getMoney().increaseMoney(tempFine);
            }
        }
    }

}
