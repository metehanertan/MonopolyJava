// This class represents Jail Square on the board
public class JailSquare extends Square {

    // Create necessary variables
    private int squareID;
    private String squareName;
    private int jailFine;

    // Constructor
    public JailSquare(int id, String name, int jailFine) {
        this.squareID = id;
        this.squareName = name;
        this.jailFine = jailFine;
    }

    // Getter and setter methods of the class
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

    public void playerWantsPayForJail(Player player) {
        // Check if player have enough money
        if (player.getMoney().getCurrentMoney() > jailFine) {

            player.getMoney().decreaseMoney(jailFine);
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + jailFine
                    + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
            player.setInJail(false);

        }
    }

    public int playerDoesntWantPayForJail(Player player, MonopolyGame mpGame, int i){
        // Check the player how many turns in the jail
        if (player.getJailTurnCounter() < 3) {
            player.increaseJailTurnCounter();
            i++;
            return i;
        } else {
            // Player sells his properties if he has not enough money to pay fine
            while (player.getMoney().getCurrentMoney() <= jailFine) {
                if (!player.sellCheapest()){
                    break;
                }
            }

            player.getMoney().decreaseMoney(jailFine);
            System.out.println("***" + player.getPlayerName() + " HAS PAID \'" + jailFine
                    + "$\' TO THE BANK FOR GO OUT FROM JAIL***");
            if (player.getMoney().getCurrentMoney() <= 0) {
                System.out.println("!!! " + player.getPlayerName() + " HAS GONE BANKRUPT!!!\n");

                player.setIsBankrupted(true);

            }
            player.setInJail(false);
        }
        return i;
    }


}
