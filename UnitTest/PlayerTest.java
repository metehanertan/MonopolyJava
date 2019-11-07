import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    String name = "John";
    int money = 150;
    Player player = new Player(name, money);

    @Test
    public void testPlayerMoney(){
        controlPlayerMoney(player, money);
    }

    @Test
    public void testPlayerName(){
        controlPlayerName(player, name);
    }

    void controlPlayerName(Player player, String name){
        assertEquals("Player name is not assigned properly.", player.getPlayerName(), name);
    }
    void controlPlayerMoney(Player player, int money){
        assertEquals("Player money is not assigned properly.", player.getMoney().getCurrentMoney(), money);
    }
}