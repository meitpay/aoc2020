package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidatePassPort {
    private List<String> getInput() {
        Path workingDir = Paths.get("");
        String path = workingDir.toAbsolutePath().toString() + "/src/main/java/day4/input.txt";

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public int validator() {
        List<String> formatInput = formatInput();

        AtomicInteger validPasswords = new AtomicInteger();
        formatInput.forEach(element -> {
            List<KeyValuePair> kvpList = new ArrayList<>();
            List<String> keyVal = Arrays.stream(element.split(" ")).collect(Collectors.toList());
            keyVal.forEach(e -> {
                KeyValuePair kvp = new KeyValuePair();
                List<String> pair = Arrays.stream(e.split(":")).collect(Collectors.toList());
                kvp.setKey(pair.get(0));
                kvp.setValue(pair.get(1));
                kvpList.add(kvp);
            });
            if (validatePassword(kvpList)) {
                validPasswords.getAndIncrement();
            }
        });

        System.out.println("valid passwords = " + validPasswords.intValue());
        return validPasswords.intValue();
    }

    private List<String> formatInput() {
        List<String> input = getInput();
        List<String> formatInput = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        int iteration = 0;
        for (String element : input) {
            builder.append(element).append(" ");

            if (element.isEmpty() || iteration == input.size() -1) {
                formatInput.add(builder.toString());
                builder.setLength(0);
            }
            iteration++;
        }
        return formatInput;
    }

    private boolean validatePassword(List<KeyValuePair> kvpList) {
        if (kvpList.size() < 7) {
            return false;
        }

        List<String> validKeys = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        String optional = "cid";
        List<String> inputKeys = new ArrayList<>();
        for (KeyValuePair kvp : kvpList) {
            String key = kvp.key.strip();
            String value = kvp.value.strip();
            if (!key.equals(optional)) {
                inputKeys.add(kvp.key.strip());
            }

            boolean validValue = switch (key) {
                case "byr" -> validateBirthYeah(value);
                case "iyr" -> validateIssueYear(value);
                case "eyr" -> validateExpirationYear(value);
                case "hgt" -> validateHeight(value);
                case "hcl" -> validateHairColor(value);
                case "ecl" -> validateEyeColor(value);
                case "pid" -> validatePassPortId(value);
                case "cid" -> true;
                default -> false;
            };
            if (!validValue) {
                return false;
            }
        }

        List<String> inputSorted = inputKeys.stream().sorted().collect(Collectors.toList());
        List<String> validKeysSorted = validKeys.stream().sorted().collect(Collectors.toList());
        return inputSorted.equals(validKeysSorted);
    }

    private boolean validatePassPortId(String value) {
        if (value.length() != 9) {
            return false;
        }

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateEyeColor(String value) {
        List<String> colors = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return colors.contains(value);
    }

    private boolean validateHairColor(String value) {
        if (value.length() != 7) {
            return false;
        }
        if (value.charAt(0) != '#') {
            return false;
        }
        return value.substring(1).matches("([aA-zZ0-9])+");
    }

    private boolean validateHeight(String value) {
        StringBuilder alphabetic = new StringBuilder();
        StringBuilder numeric = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) {
                numeric.append(value.charAt(i));
            } else if (Character.isAlphabetic(value.charAt(i))) {
                alphabetic.append(value.charAt(i));
            }
        }

        int height = Integer.parseInt(numeric.toString());
        return switch (alphabetic.toString()) {
            case "cm" -> height >= 150 && height <= 193;
            case "in" -> height >= 59 && height <= 76;
            default -> false;
        };
    }

    private boolean validateExpirationYear(String value) {
        int year = Integer.parseInt(value);
        return year >= 2020 && year <= 2030;
    }

    private boolean validateIssueYear(String value) {
        int year = Integer.parseInt(value);
        return year >= 2010 && year <= 2020;
    }

    private boolean validateBirthYeah(String value) {
        int year = Integer.parseInt(value);
        return year >= 1920 && year <= 2002;
    }

    static class KeyValuePair {
        String key;
        String value;

        public KeyValuePair() {}

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
