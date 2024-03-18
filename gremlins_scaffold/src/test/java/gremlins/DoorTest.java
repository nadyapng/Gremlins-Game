package gremlins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    
    @Test
    public void constructorTest() {
        assertNotNull(new Door(null, 0, 0, 0, 0));
    }

    @Test
    public void isReachedTest() {
        Door d = new Door(null, 0, 0, 0, 0);
        d.reach();
        assertTrue(d.isReached());
    }
}
