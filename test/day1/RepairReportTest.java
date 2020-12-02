package day1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepairReportTest {
    RepairReport repairReport;

    @BeforeEach
    public void setUp() {
        repairReport = new RepairReport();
    }

    @Test
    public void findTwoEntriesWithSum2020() throws IOException {
        int result = repairReport.findTwoEntriesAndMultiplyValues(2020);
        assertEquals(921504, result);
    }

    @Test
    public void findThreeEntriesWithSum2020() throws IOException {
        int result = repairReport.findThreeEntriesAndMultiplyValues(2020);
        assertEquals(195700142, result);
    }
}
