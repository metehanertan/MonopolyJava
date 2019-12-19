public class ChanceCard {

    private String action;
    private int id;

    public ChanceCard(int id, String action) {
        this.action = action;
        this.id = id;
    }

    public void chooseAction(int id, Player player, Board board, MonopolyGame mpGame) {
        switch (id) {
            case 1:
                player.getPiece().setSquare(board.getSquareList()[0]);
                break;
            case 2:
                if (board.getSquareList()[24] instanceof GoToJailSquare) {
                    //kart çekme methodu
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
                    //KART ÇEKME METHODU
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
                            //KART ÇEKME METHODU
                            break;
                        } else {
                            player.getPiece().setSquare(board.getSquareList()[28]);
                            break;
                        }
                    } else {
                        player.getPiece().setSquare(board.getSquareList()[12]);
                        break;
                    }
                } else if (player.getPiece().getSquare().getSquareID() > 12 && player.getPiece().getSquare().getSquareID() < 28) {
                    if (board.getSquareList()[28] instanceof GoToJailSquare) {
                        if (board.getSquareList()[12] instanceof GoToJailSquare) {
                            //KART ÇEKME METHODU
                        } else {
                            player.getPiece().setSquare(board.getSquareList()[12]);
                            break;
                        }
                    } else {
                        player.getPiece().setSquare(board.getSquareList()[28]);
                        break;
                    }
                }
                break;
            case 5:
                if (player.getPiece().getSquare().getSquareID() < 5 || player.getPiece().getSquare().getSquareID() > 35) {
                    int temp = 5;
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
                        break;
                    } else {
                        //KART ÇEKME METHODU
                        break;
                    }

                } else if (player.getPiece().getSquare().getSquareID() > 5 && player.getPiece().getSquare().getSquareID() < 15) {
                    int temp = 15;
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
                        break;
                    } else {
                        //KART ÇEKME METHODU
                        break;
                    }

                } else if (player.getPiece().getSquare().getSquareID() > 15 && player.getPiece().getSquare().getSquareID() < 25) {
                    int temp = 25;
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
                        break;
                    } else {
                        //KART ÇEKME METHODU
                        break;
                    }

                } else if (player.getPiece().getSquare().getSquareID() > 25 && player.getPiece().getSquare().getSquareID() < 35) {
                    int temp = 35;
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
                        break;
                    } else {
                        //KART ÇEKME METHODU
                        break;
                    }

                }
            case 6:
                player.getMoney().increaseMoney(50);
                break;
            case 7:
                break;
            case 8:
                if (player.getPiece().getSquare().getSquareID() > 3) {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() - 3]);
                } else {
                    player.getPiece().setSquare(board.getSquareList()[player.getPiece().getSquare().getSquareID() + 40 - 3]);
                }
                break;
            case 9:
                player.getPiece().setSquare(board.getSquareList()[10]);
                break;
            case 10:
                break;
            case 11:
                player.getMoney().decreaseMoney(15);
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                player.getMoney().increaseMoney(150);
                break;
            case 16:
                player.getMoney().increaseMoney(100);
                break;

        }
    }

    //ACTION METHODS
}
