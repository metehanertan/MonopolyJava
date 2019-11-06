import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void getFirstValue() {
        assertTrue(dice.getFirstValue()<=6 || dice.getFirstValue()>=1);
    }

    @Test
    public void getSecondValue() {

    }
}