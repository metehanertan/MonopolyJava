import org.junit.Test;
import static org.junit.Assert.*;

public class testTransportSquare {

    TransportSquare us = new TransportSquare(15, "METRO", 1, 15);
    Player player = new Player("Minel", 200);


    @Test
    public void testOwnerPlayer(){
        us.setOwner(player);
        assertEquals("Error occured while setting owner.", us.getOwner(), player);
    }

    @Test
    public void testHasOwner(){
        us.setOwner(player);
        assertTrue("Error occured while setting owner.", us.getHasOwner());
    }

}
