package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ValidatePassword {
    private Map<String, List<String>> getInput() throws IOException {
        Path workingDir = Paths.get("");
        String path = workingDir.toAbsolutePath().toString() +  "/src/main/resources/input/day2.txt";

        Map<String, List<String>> map = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(element -> {
                List<String> arr = Arrays.asList(element.split(":"));
                map.computeIfAbsent(arr.get(0), k -> new ArrayList<>()).add(arr.get(1));
            });
        }
        return map;
    }

    public int partOne() throws IOException {
        Map<String, List<String>> input = getInput();
        AtomicInteger validPasswords = new AtomicInteger();

        input.forEach((key, value) -> {
            String[] splitPolicy = key.split(" ");
            String[] minMaxCharacters = splitPolicy[0].split("-");
            int min = Integer.parseInt(minMaxCharacters[0]);
            int max = Integer.parseInt(minMaxCharacters[1]);
            char characterValue = splitPolicy[1].charAt(0);

            for (String s : value) {
                int count = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == characterValue) {
                        count++;
                    }
                }

                if (count >= min && count <= max) {
                    validPasswords.getAndIncrement();
                }
            }
        });

        return validPasswords.intValue();
    }

    public int partTwo() throws IOException {
        Map<String, List<String>> input = getInput();
        AtomicInteger validPasswords = new AtomicInteger();

        input.forEach((key, value) -> {
            String[] splitPolicy = key.split(" ");
            String[] minMaxValues = splitPolicy[0].split("-");
            int firstIndex = Integer.parseInt(minMaxValues[0]);
            int lastIndex = Integer.parseInt(minMaxValues[1]);
            char characterValue = splitPolicy[1].charAt(0);

            for (String c : value) {
                char first = c.charAt(firstIndex);
                char last = c.charAt(lastIndex);

                if ((first == characterValue || last == characterValue) && first != last) {
                    validPasswords.getAndIncrement();
                }
            }
        });

        return validPasswords.intValue();
    }
}
