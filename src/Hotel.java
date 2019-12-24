public class Hotel {
    private String name;
    private Player owner;
    private boolean hasOwner;
    private PropertySquare square;

    public Hotel() {

        this.name = "HOTEL";
        this.hasOwner = false;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        this.owner = player;
        if (player == null) {
            this.hasOwner = false;
        } else {
            this.hasOwner = true;
        }
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public void setSquare(PropertySquare square) {
        if (square == null) {
            setOwner(null);
        } else {
            this.square = square;
            setOwner(square.getOwner());
        }
    }

    public PropertySquare getSquare() {
        return square;
    }

    public void sellHotel(Player player, MonopolyGame mpGame) {
        int sellPrice = this.square.getHousePrice() / 2;
        int count = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < mpGame.getHouseList().size(); i++) {
                if (!mpGame.getHouseList().get(i).getHasOwner()) {
                    mpGame.getHouseList().get(i).setOwner(player);
                    mpGame.getHouseList().get(i).setSquare(square);
                    player.getMoney().decreaseMoney(square.getHousePrice());
                    square.setHouseCount(square.getHouseCount() + 1);
                    player.setHouseCount(player.getHouseCount() + 1); // player için
                    System.out.println("HOUSE COUNT: " + square.getHouseCount());
                    System.out.println("HOTEL COUNT: " + square.getHotelCount());
                    count++;
                    break;
                }
                if (i == mpGame.getHouseList().size() - 1) {
                    System.out.println("There is no available house.");
                }
            }
        }
        System.out.println("--" + count + "house added on " + square);
        for (int i = 0; i < count; i++) {
            player.getMoney().increaseMoney(sellPrice);
        }
        System.out.println("**" + player.getPlayerName() + " took " + sellPrice * count + "$ from selling.**");
    }

}
