public class ChanceCard {

    private String action;
    private int id;
    private boolean hasOwner;

    public ChanceCard(int id, String action) {
        this.action = action;
        this.id = id;
        this.hasOwner = false;
    }

    public void chooseAction(int id, Player player, Board board, MonopolyGame mpGame) {
        int tempFine;
        switch (id) {
            case 1:
                player.getPiece().setSquare(board.getSquareList()[0]);
                player.getMoney().increaseMoney(mpGame.getGoMoney());
                break;
            case 2:
                if (board.getSquareList()[24] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 24) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[24]);
                    break;
                }
            case 3:
                if (board.getSquareList()[11] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 11) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[11]);
                    break;
                }
            case 4:
                if (player.getPiece().getSquare().getSquareID() < 12 || player.getPiece().getSquare().getSquareID() > 28) {
                    if (board.getSquareList()[12] instanceof GoToJailSquare) {
                        if (board.getSquareList()[28] instanceof GoToJailSquare) {
                            board.getChanceCard().add(board.getChanceCard().get(0));
                            board.getChanceCard().remove(0);
                            mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                            break;
                        } else {
                            //player.getPiece().setSquare(board.getSquareList()[28]);
                            payUtility((UtilitySquare) board.getSquareList()[28], player, board);
                            /*if(((UtilitySquare)(board.getSquareList()[28])).getHasOwner()){
                                tempFine = 10 * player.getChoiceDice().getTotal() - ((UtilitySquare)board.getSquareList()[28]).getFine();
                                if(player.isAbleDecreaseMoney(tempFine, board)){
                                    player.getMoney().decreaseMoney(tempFine);
                                    ((UtilitySquare)board.getSquareList()[28]).getOwner().getMoney().increaseMoney(tempFine);
                                }
                                else{
                                    //player.setIsBankrupted(true);
                                    player.playerGoesToBankrupt(tempFine, player.getPiece().getSquare());
                                }
                            }*/
                            break;
                        }
                    } else {
                        payUtility((UtilitySquare) board.getSquareList()[12], player, board);

                      /*  player.getPiece().setSquare(board.getSquareList()[12]);

                        if(((UtilitySquare)(board.getSquareList()[12])).getHasOwner()){
                            tempFine = 10 * player.getChoiceDice().getTotal() - ((UtilitySquare)board.getSquareList()[12]).getFine();
                            if(player.isAbleDecreaseMoney(tempFine, board)){
                                player.getMoney().decreaseMoney(tempFine);
                                ((UtilitySquare)board.getSquareList()[12]).getOwner().getMoney().increaseMoney(tempFine);
                            }
                            else{
                               // player.setIsBankrupted(true);
                                player.playerGoesToBankrupt(tempFine, player.getPiece().getSquare());
                            }
                        }*/
                        break;
                    }
                } else if (player.getPiece().getSquare().getSquareID() > 12 && player.getPiece().getSquare().getSquareID() < 28) {
                    if (board.getSquareList()[28] instanceof GoToJailSquare) {
                        if (board.getSquareList()[12] instanceof GoToJailSquare) {
                            board.getChanceCard().add(board.getChanceCard().get(0));
                            board.getChanceCard().remove(0);
                            mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                        } else {
                            payUtility((UtilitySquare) board.getSquareList()[12], player, board);

                     /*      player.getPiece().setSquare(board.getSquareList()[12]);
                            if(((UtilitySquare)(board.getSquareList()[12])).getHasOwner()){
                                ((UtilitySquare)board.getSquareList()[12]).getOwner().getMoney().increaseMoney(10 * player.getChoiceDice().getTotal()- ((UtilitySquare)board.getSquareList()[12]).getFine());
                                if(player.isAbleDecreaseMoney(10 * player.getChoiceDice().getTotal() - ((UtilitySquare)board.getSquareList()[12]).getFine(), board)){
                                    player.getMoney().decreaseMoney(10 * player.getChoiceDice().getTotal() - ((UtilitySquare)board.getSquareList()[12]).getFine());
                                }
                                else{
                                    player.setIsBankrupted(true);
                                }
                            }*/
                            break;
                        }
                    } else {
                        payUtility((UtilitySquare)board.getSquareList()[28], player, board);

                     /*   player.getPiece().setSquare(board.getSquareList()[28]);
                        if (((UtilitySquare) (board.getSquareList()[28])).getHasOwner()) {
                            ((UtilitySquare) board.getSquareList()[28]).getOwner().getMoney().increaseMoney(10 * player.getChoiceDice().getTotal() - ((UtilitySquare) board.getSquareList()[28]).getFine());
                            if (player.isAbleDecreaseMoney(10 * player.getChoiceDice().getTotal() - ((UtilitySquare) board.getSquareList()[28]).getFine(), board)) {
                                player.getMoney().decreaseMoney(10 * player.getChoiceDice().getTotal() - ((UtilitySquare) board.getSquareList()[28]).getFine());
                            } else {
                                player.setIsBankrupted(true);
                            }
                        }*/
                        break;
                    }
                }
                break;
            case 5:
                if (player.getPiece().getSquare().getSquareID() < 5 || player.getPiece().getSquare().getSquareID() > 35) {
                    nearestTransport(player, board, 5);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 5 && player.getPiece().getSquare().getSquareID() < 15) {
                    nearestTransport(player, board, 15);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 15 && player.getPiece().getSquare().getSquareID() < 25) {
                    nearestTransport(player, board, 25);
                    break;
                } else if (player.getPiece().getSquare().getSquareID() > 25 && player.getPiece().getSquare().getSquareID() < 35) {
                    nearestTransport(player, board, 35);
                    break;
                }
            case 6:
                player.getMoney().increaseMoney(50);
                break;
            case 7:
                if (this.hasOwner == false) {
                    this.hasOwner = true;
                    player.setOutOfJailCard(true);
                    player.setChanceOutOfJail(this);
                    System.out.println(player.getPlayerName() + " get change go out of jail card!!");
                } else {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                }
                break;
            case 8:
                if (player.getPiece().getSquare().getSquareID() >= 3) {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() - 3]);
                } else {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() + 40 - 3]);
                }
                break;
            case 9:
                player.getPiece().setSquare(board.getSquareList()[10]);
                break;
            case 10:
                tempFine = player.getHouseCount() * 40 + player.getHotelCount() * 115;
                if (player.isAbleDecreaseMoney(tempFine, board))
                    player.getMoney().decreaseMoney(tempFine);

                else {
                   // player.setIsBankrupted(true);
                    player.playerGoesToBankrupt(tempFine, player.getPiece().getSquare());
                }
                break;
            case 11:
                if (player.isAbleDecreaseMoney(15, board)) {
                    player.getMoney().decreaseMoney(15);
                } else {
                   // player.setIsBankrupted(true);
                    player.playerGoesToBankrupt(15, player.getPiece().getSquare());
                }
                break;
            case 12:
                if (board.getSquareList()[25] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    if (player.getPiece().getSquare().getSquareID() > 25) {
                        player.getMoney().increaseMoney(mpGame.getGoMoney());
                    }
                    player.getPiece().setSquare(board.getSquareList()[25]);
                    break;
                }
            case 13:
                if (board.getSquareList()[39] instanceof GoToJailSquare) {
                    board.getChanceCard().add(board.getChanceCard().get(0));
                    board.getChanceCard().remove(0);
                    mpGame.takeChangeCard(board.getChanceCard().get(0), player);
                    break;
                } else {
                    player.getPiece().setSquare(board.getSquareList()[39]);
                    break;
                }
            case 14:
                for (int i = 0; i < mpGame.getPlayerList().length; i++) {
                    if (mpGame.getPlayerList()[i] != null && mpGame.getPlayerList()[i] != player) {
                        mpGame.getPlayerList()[i].getMoney().increaseMoney(50);
                        if (player.isAbleDecreaseMoney(50, board)) {
                            player.getMoney().decreaseMoney(50);
                        } else {
                           // player.setIsBankrupted(true);
                            player.playerGoesToBankrupt(50, player.getPiece().getSquare());
                        }
                    }
                }
                break;
            case 15:
                player.getMoney().increaseMoney(150);
                break;
            case 16:
                player.getMoney().increaseMoney(100);
                break;

        }
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    private void nearestTransport(Player player, Board board, int temp) {
        int control = 0;
        while (board.getSquareList()[temp] instanceof GoToJailSquare) {
            temp += 10;
            temp = temp % 40;
            control++;
            if (control == 4) {
                break;
            }
        }
        if (control != 4) {
            player.getPiece().setSquare(board.getSquareList()[temp]);
            if (((TransportSquare) board.getSquareList()[temp]).getHasOwner()) {
                ((TransportSquare) board.getSquareList()[temp]).getOwner().getMoney().increaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine());
                if (player.isAbleDecreaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine(), board)) {
                    player.getMoney().decreaseMoney(((TransportSquare) board.getSquareList()[temp]).getFine());
                } else {
                    player.setIsBankrupted(true);
                }
            }
            return;
        } else {
            //KART ÇEKME METHODU
            return;
        }

    }

    public void payUtility(UtilitySquare utility, Player player, Board board) {
        player.getPiece().setSquare(utility);
        int tempFine;
        if (utility.getHasOwner()) {
            tempFine = 10 * player.getChoiceDice().getTotal() - utility.getFine();
            if (player.isAbleDecreaseMoney(tempFine, board)) {
                player.getMoney().decreaseMoney(tempFine);
                utility.getOwner().getMoney().increaseMoney(tempFine);
            } else {
                //player.setIsBankrupted(true);
                player.playerGoesToBankrupt(tempFine, utility);
            }
        }
    }

    public boolean hasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }
}
