package day2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatePasswordTest {
    ValidatePassword validatePassword;

    @BeforeEach
    public void setUp() {
        validatePassword = new ValidatePassword();
    }

    @Test
    public void testPartOne() throws IOException {
        int someInt = validatePassword.partOne();
        assertEquals(454, someInt);
    }

    @Test
    public void testPartTwo() throws IOException {
        int someInt = validatePassword.partTwo();
        assertEquals(649, someInt);
    }
}
