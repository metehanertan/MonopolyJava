import org.junit.Test;

import static org.junit.Assert.*;

public class MonopolyGameTest {

    int playerSize = 5;
    int taxValue = 50;
    int startMoney = 200;
    int taxNumber = 6;

    MonopolyGame mpGame = new MonopolyGame(playerSize, taxValue, startMoney, taxNumber);

    @Test
    public void testPlayerSize() {
        controlPlayerSize(mpGame, playerSize);
    }

    @Test
    public void testTaxValue() {
        controlTaxValue(mpGame, taxValue);
    }

    @Test
    public void testStartMoney() {
        controlStartMoney(mpGame, startMoney);
    }

    @Test
    public void testTaxNumber() {
        controlTaxNumber(mpGame, taxNumber);
    }

    void controlPlayerSize(MonopolyGame mpGame, int playerSize) {
        assertEquals("Player size is not assigned properly.", mpGame.getPlayerSize(), playerSize);
    }

    void controlTaxValue(MonopolyGame mpGame, int taxValue) {
        assertEquals("Tax value is not assigned properly.", mpGame.getTaxValue(), taxValue);
    }

    void controlStartMoney(MonopolyGame mpGame, int startMoney) {
        assertEquals("Start money is not assigned properly.", mpGame.getStartMoney(), startMoney);
    }

    void controlTaxNumber(MonopolyGame mpGame, int taxNumber) {
        assertEquals("Tax number is not assigned properly.", mpGame.getTaxNumber(), taxNumber);
    }

}
