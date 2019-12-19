public class CommunityChest {
    private String action;
    private int id;

    public CommunityChest(int id,String action){
        this.action = action;
        this.id = id;
    }

    public void chooseAction(int id, Player player, Board board,MonopolyGame mpGame){
        switch (id){

            case 1:
                player.getPiece().setSquare(board.getSquareList()[0]);
                break;
            case 2:
                player.getMoney().increaseMoney(200);
                break;
            case 3: case 12:
                player.getMoney().decreaseMoney(50);
                break;
            case 4:
                player.getMoney().increaseMoney(50);
                break;
            case 5:
                player.setOutOfJailCard(true);
                break;
            case 6:
                player.getPiece().setSquare(board.getSquareList()[10]);
                break;
            case 7:
               int playerSize =  mpGame.getCurrentPlayerSize();
               player.getMoney().increaseMoney(playerSize * 50);
               //!!!!!!!!!!DEVAM EDECEK!!!!!!!!!!
                break;
            case 8: case 11: case 17:
                player.getMoney().increaseMoney(100);
                break;
            case 9:
                player.getMoney().increaseMoney(20);
                break;
            case 10:

                //EVERY PLAYER 10 DOLARS
                break;

            case 13:
                //Aynısı
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

    //ACTION METHODS
}
