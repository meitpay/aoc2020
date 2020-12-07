package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlightSeatFinder {
    private final int NUM_ROWS = 128;
    private final int NUM_COLUMNS = 8;
    private final int MULTIPLIER = 8;

    private final char LOWER_HALF_ROW = 'F';
    private final char UPPER_HALF_ROW = 'B';

    private final char LOWER_HALF_COLUMN = 'L';
    private final char UPPER_HALF_COLUMN = 'R';

    private List<String> getInput() {
        Path workingDir = Paths.get("");
        String path = workingDir.toAbsolutePath().toString() + "/src/main/resources/input/day5.txt";

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<Integer> getSeatIds() {
        List<String> boardingPasses = getInput();
        List<Integer> seatIds = new ArrayList<>();

        int rowNumber, columnNumber;
        for (String boardingPass : boardingPasses) {
            String row = boardingPass.substring(0, 7);
            String column = boardingPass.substring(7);

            rowNumber = getRowOrColumn(row, NUM_ROWS);
            columnNumber = getRowOrColumn(column, NUM_COLUMNS);

            int seatId = calculateSeatId(rowNumber, columnNumber);
            seatIds.add(seatId);
        }
        return seatIds.stream().sorted().collect(Collectors.toList());
    }

    public int getHighestSeatId() {
        List<Integer> seatIds = getSeatIds();
        return seatIds.get(seatIds.size() - 1);
    }

    public int getMySeat() {
        List<Integer> seatIds = getSeatIds();
        int mySeat = Integer.MIN_VALUE;
        for (int i = 0; i < seatIds.size() -1; i++) {
            int current = seatIds.get(i);
            int next = seatIds.get(i + 1);

            if (current + 1 != next) {
                mySeat = current + 1;
            }
        }
        return mySeat;
    }

    private List<Integer> generateRowsOrColumns(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    private int calculateSeatId(int row, int column) {
        return (row * MULTIPLIER) + column;
    }

    private int getRowOrColumn(String subString, int size) {
        List<Integer> integerList = generateRowsOrColumns(size);

        for (int i = 0; i < subString.length(); i++) {
            if (subString.charAt(i) == UPPER_HALF_COLUMN || subString.charAt(i) == UPPER_HALF_ROW) {
                integerList = keepUpperHalf(integerList);
            } else {
                integerList = keepLowerHalf(integerList);
            }
        }
        return integerList.get(0);
    }

    private List<Integer> keepLowerHalf(List<Integer> list) {
        int lower = list.size() / 2;
        return IntStream.range(0, lower).mapToObj(list::get).collect(Collectors.toList());
    }

    private List<Integer> keepUpperHalf(List<Integer> list) {
        int upper = list.size() / 2;
        return IntStream.range(upper, list.size()).mapToObj(list::get).collect(Collectors.toList());
    }
}
