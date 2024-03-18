package gremlins;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoneTileTest {
    
    @Test
    public void constructorTest() {
        assertNotNull(new StoneTile(null, 0, 0, 0, 0));
    }
}
