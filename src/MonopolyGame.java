public class MonopolyGame {

    private Board board;
    private Player[] playerList;
    private int playerSize, taxValue, startMoney, taxNumber;

    public MonopolyGame() {

    }

    public MonopolyGame(int playerSize, int taxValue, int startMoney, int taxNumber) {
        this.playerSize = playerSize;
        this.taxValue = taxValue;
        this.startMoney = startMoney;
        this.taxNumber = taxNumber;

    }

    public void Play() {

        this.playerList = new Player[this.playerSize];
        this.board = new Board();







    }

}
