package day4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatePassPortTest {
    ValidatePassPort validatePassPort;

    @BeforeEach
    public void setUp() {
        validatePassPort = new ValidatePassPort();
    }

    @Test
    public void dummyTest() {
        // First answer
        assertEquals(226, 226);
        // Second answer
        assertEquals(160, validatePassPort.getNumberOfValidPasswords());
    }
}
