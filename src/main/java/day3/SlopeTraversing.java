package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SlopeTraversing {

    private List<String> getInput() {
        Path workingDir = Paths.get("");
        String path = workingDir.toAbsolutePath().toString() + "/src/main/resources/input/day3.txt";

        AtomicInteger iteration = new AtomicInteger(0);
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(element -> {
                iteration.getAndIncrement();
                String line = element.repeat(iteration.intValue());
                lines.add(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public int getNumberOfTreesVisited(int right, int down) {
        int position = 0;
        int numTrees = 0;
        List<String> input = getInput();

        int current = 0;
        while (current < input.size()) {
            if (current != 0) {
                String line = input.get(current);
                if (line.charAt(position) == '#') {
                    numTrees++;
                }
            }
            position = position + right;
            current = current + down;
        }
        System.out.println(numTrees);
        return numTrees;
    }
}
