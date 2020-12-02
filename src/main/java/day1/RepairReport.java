package day1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RepairReport {
    private List<Integer> getInput() throws IOException {
        Path workingDir = Paths.get("");
        String path = workingDir.toAbsolutePath().toString() +  "/src/main/java/day1/input.txt";

        List<Integer> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(value -> result.add(Integer.valueOf(value)));
        }
        return result;
    }

    public int findTwoEntriesAndMultiplyValues(int sum) throws IOException {
        List<Integer> entries = getInput();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            for (Integer entry : entries) {
                if (entries.get(i) + entry == sum) {
                    result.add(entries.get(i));
                    result.add(entry);
                    return calculateResult(result, 2);
                }
            }
        }

        return Integer.MIN_VALUE;
    }

    public int findThreeEntriesAndMultiplyValues(int sum) throws IOException {
        List<Integer> entries = getInput();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            for (int j = 0; j < entries.size(); j++) {
                for (Integer entry : entries) {
                    if (entries.get(i) + entries.get(j) + entry == sum) {
                        result.add(entries.get(i));
                        result.add(entries.get(j));
                        result.add(entry);
                        return calculateResult(result, 3);
                    }
                }
            }
        }

        return Integer.MIN_VALUE;
    }

    private int calculateResult(List<Integer> result, int numEntries) {
        return switch (numEntries) {
            case 2 -> result.get(0) * result.get(1);
            case 3 -> result.get(0) * result.get(1) * result.get(2);
            default -> throw new IllegalStateException("Unexpected value: " + numEntries);
        };
    }
}
