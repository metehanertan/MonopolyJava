import java.util.Scanner;

//Main class to play the game
public class Main {
    public static void main(String[] args) {

        int playerSize = 8;
        int taxValue = 50;
        int startMoney = 200;
        int taxNumber = 6;

        MonopolyGame monopolyGame = new MonopolyGame(playerSize, taxValue, startMoney, taxNumber); //Create MonopolyGame object with given parameters.
        monopolyGame.Play(); //Call the Play function of MonopolyGame to start the game.

    }
}
