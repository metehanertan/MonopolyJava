import org.junit.Test;
import static org.junit.Assert.*;

public class testTransportSquare {

    TransportSquare ts = new TransportSquare(15, "METRO", 1, 15);
    Player player = new Player("Minel", 200);


    @Test
    public void testOwnerPlayer(){
        ts.setOwner(player);
        assertEquals("Error occured while setting owner.", ts.getOwner(), player);
    }

    @Test
    public void testHasOwner(){
        ts.setOwner(player);
        assertTrue("Error occured while setting owner.", ts.getHasOwner());
    }

}
