package my;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void succeed() {
        assertEquals(0,0);
    }

    @Test
    @Disabled
    void error() {
        throw new RuntimeException();
    }

    @Test
    @Disabled
    void failed() {
        assertEquals(0,1);
    }

    @Test
    @Disabled("Ignored")
    void ignored() {
        assertEquals(0,1);
    }
}
