import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int playerSize = 5;
        int taxValue = 50;
        int startMoney = 200;
        int taxNumber = 6;

        MonopolyGame monopolyGame = new MonopolyGame(playerSize, taxValue, startMoney, taxNumber);
        monopolyGame.Play();

    }
}
