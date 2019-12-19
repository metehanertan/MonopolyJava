public class CommunityChest {
    private String action;
    private int id;

    public CommunityChest(int id, String action) {
        this.action = action;
        this.id = id;
    }

    public void chooseAction(int id, Player player, Board board, MonopolyGame mpGame) {
        switch (id) {

            case 1:
                player.getPiece().setSquare(board.getSquareList()[0]);
                break;
            case 2:
                player.getMoney().increaseMoney(200);
                break;
            case 3:
            case 12:
            case 13:
                player.getMoney().decreaseMoney(50);
                break;
            case 4:
                player.getMoney().increaseMoney(50);
                break;
            case 5:
                //KART EKLENECEK
                player.setOutOfJailCard(true);
                break;
            case 6:
                player.getPiece().setSquare(board.getSquareList()[10]);
                break;
            case 7:
                int tempFine = 50;
                collectMoneyFromAllPlayers(player, mpGame, tempFine);
                break;
            case 8:
            case 11:
            case 17:
                player.getMoney().increaseMoney(100);
                break;
            case 9:
                player.getMoney().increaseMoney(20);
                break;
            case 10:
                tempFine = 10;
                collectMoneyFromAllPlayers(player, mpGame, tempFine);
                break;
            case 14:
                player.getMoney().increaseMoney(25);
                break;
            case 15:
                //Pay $40 per house and $115 per hotel you own."
                break;
            case 16:
                player.getMoney().increaseMoney(10);
                break;


        }
    }

    public void collectMoneyFromAllPlayers(Player player, MonopolyGame mpGame, int tempFine){
        for (int i = 0; i < mpGame.getPlayerList().length; i++) {
            if (mpGame.getPlayerList()[i] != null) {
                if (mpGame.getPlayerList()[i] != player) {
                    // Player sells his properties if he has not enough money to pay fine
                    while (mpGame.getPlayerList()[i].getMoney().getCurrentMoney() <= tempFine) {
                        if (!mpGame.getPlayerList()[i].sellCheapest()) {
                            break;
                        }
                    }

                    mpGame.getPlayerList()[i].getMoney().decreaseMoney(tempFine);

                    // If player goes to bankruptcy
                    if (mpGame.getPlayerList()[i].getMoney().getCurrentMoney() <= 0) {
                        player.getMoney().increaseMoney(tempFine
                                + mpGame.getPlayerList()[i].getMoney().getCurrentMoney());
                        System.out.println("***" + mpGame.getPlayerList()[i].getPlayerName() + " HAS PAID \'"
                                + (tempFine + mpGame.getPlayerList()[i].getMoney().getCurrentMoney()) + "$\' TO "
                                + player.getPlayerName() + "***");

                        System.out.println("!!! " + mpGame.getPlayerList()[i].getPlayerName() + " HAS GONE BANKRUPT!!!\n");

                        mpGame.getPlayerList()[i].setIsBankrupted(true);

                    }
                }
            }
        }
    }

    //ACTION METHODS
}