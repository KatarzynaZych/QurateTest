import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class RomanNumeralConverter {

    public static int convertRomanNumber(String number) {
        isNumberEqualNullOrBlank(number);
        String refactoredRomanNumber = number.replaceAll(" ", "").toUpperCase(Locale.ROOT);
        validRomanNumber(refactoredRomanNumber);

        char[] symbols = refactoredRomanNumber.toCharArray();
        int numberAsInt = 0;

        for (int i = 0; i < symbols.length; i++) {
            String currentSymbol = String.valueOf(symbols[i]);
            if (i > 0) {
                String previous = String.valueOf(symbols[i - 1]);
                if (romanMap.get(currentSymbol) > romanMap.get(previous)) {
                    numberAsInt = numberAsInt + romanMap.get(currentSymbol) - 2 * romanMap.get(previous);
                } else {
                    numberAsInt = numberAsInt + romanMap.get(currentSymbol);
                }
            } else {
                numberAsInt = numberAsInt + romanMap.get(currentSymbol);
            }
        }
        return numberAsInt;
    }

    private static HashMap<String, Integer> romanMap = new HashMap<>() {{
        put("M", 1000);
        put("D", 500);
        put("C", 100);
        put("L", 50);
        put("X", 10);
        put("V", 5);
        put("I", 1);
    }};

    public static void hasValidCharacters(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!romanMap.containsKey(String.valueOf(number.charAt(i)))) {
                throwException("The number " + number + " does not contain valid characters.");
            }
        }
    }

    private static void checkIfCharacterRepeated(String previous, String current, String character) {
        if (previous.equals(character) && current.equals(character)) {
            throwException("The number contains to many number of repetitions of " + character + " characters.");
        }
    }

    public static void hasTwoTheSameIncorrectCharactersNextToEachOther(String number) {
        if (number.length() > 2) {
            for (int i = 0; i < number.length() - 2; i++) {
                String previous = String.valueOf(number.charAt(i));
                String current = String.valueOf(number.charAt(i + 1));
                String next = String.valueOf(number.charAt(i + 2));

                if (!(previous.equals(current) && current.equals(next))) {
                    switch (next) {
                        case "X":
                        case "V":
                            checkIfCharacterRepeated(previous, current, "I");
                            break;
                        case "L":
                        case "C":
                            checkIfCharacterRepeated(previous, current, "X");
                            break;
                        case "D":
                        case "M":
                            checkIfCharacterRepeated(previous, current, "C");
                            break;
                    }
                }
            }
        }
    }

    public static void checkCharactersHasProperAntecedent(String number) {
        for (int i = 0; i < number.length() - 1; i++) {
            String currentCharacter = String.valueOf(number.charAt(i));
            String antecedentCharacter = String.valueOf(number.charAt(i + 1));

            switch (currentCharacter) {
                case "I":
                    validationForCharacterI(number, antecedentCharacter);
                    break;
                case "V":
                    validationForCharacterV(number, antecedentCharacter);
                    break;
                case "X":
                    validationForCharacterX(number, antecedentCharacter);
                    break;
                case "L":
                    validationForCharacterL(number, antecedentCharacter);
                    break;
                case "D":
                    validationForCharacterD(number, antecedentCharacter);
                    break;
            }
        }
    }

    private static void validationForCharacterD(String number, String antecedentCharacter) {
        if ("M".equals(antecedentCharacter)) {
            throwException("The number " + number + " does not contain valid characters.");
        }
    }

    private static void validationForCharacterL(String number, String antecedentCharacter) {
        if (!("I".equals(antecedentCharacter) || "V".equals(antecedentCharacter) || "X".equals(antecedentCharacter))) {
            throwException("The number " + number + " does not contain valid characters.");
        }
    }

    private static void validationForCharacterI(String number, String antecedentCharacter) {
        if (!("X".equals(antecedentCharacter) || "I".equals(antecedentCharacter) || "V".equals(antecedentCharacter))) {
            throwException("The number " + number + " does not contain valid characters.");
        }
    }

    private static void validationForCharacterX(String number, String antecedentCharacter) {
        if (("M".equals(antecedentCharacter) || "D".equals(antecedentCharacter))) {
            throwException("The number " + number + " does not contain valid characters.");
        }
    }

    private static void validationForCharacterV(String number, String antecedentCharacter) {
        if (!"I".equals(antecedentCharacter)) {
            throwException("The number " + number + " does not contain valid characters.");
        }
    }


    public static void checkRepeatedCharacterPairs(String number) {
        String newString = number;
        Set<String> pairSet = new HashSet<>();

        if (number.length() % 2 != 0) {
            newString = number.substring(1);
        }

        for (int i = 0; i < newString.length(); i += 2) {
            String currentPair = newString.substring(i, i + 2);
            if (!pairSet.contains(currentPair)) {
                pairSet.add(currentPair);
            } else {
                throwException("The number " + number + " has repeared characters pairs.");
            }
        }

    }

    public static void hasCharacterCorrectOrder(String number) {
        for (int i = 0; i < number.length() - 1; i++) {
            String current = String.valueOf(number.charAt(i));
            String next = String.valueOf(number.charAt(i + 1));
            if (romanMap.get(current) * 10 < romanMap.get(next)) {
                throwException("The number " + number + " is not correct roman number.");
            }
        }
    }


    public static void hasCharactersCorrectRepetitions(String number) {
        romanMap.keySet().forEach(
                s -> {
                    if (number.contains(s)) {
                        int counter = 1;
                        for (int j = 0; j < number.length() - 1; j++) {
                            String currentLetter = String.valueOf(number.charAt(j));
                            if (s.equals(currentLetter) && currentLetter.equals(String.valueOf(number.charAt(j + 1)))) {
                                counter++;
                            }
                        }

                        if (isCharacterReplayToManyTimes(s, counter, 3, "C", "X", "I", "M") ||
                                isCharacterReplayToManyTimes(s, counter, 1, "D", "V", "L", null)) {
                            {
                                throwException("Too many repetitions of a character " + s + " .The number " + number + " is not valid.");
                            }
                        }
                    }
                }
        );
    }

    private static boolean isCharacterReplayToManyTimes(String s, int counter, int allowableNumberOfRepetitions, String c, String x, String i, String m) {
        return counter > allowableNumberOfRepetitions && (s.equals(c) || s.equals(x) || s.equals(i) || s.equals(m));
    }

    private static void throwException(String message) {
        throw new RuntimeException(message);
    }

    private static void validRomanNumber(String roman) {
        hasValidCharacters(roman);
        hasCharactersCorrectRepetitions(roman);
        hasCharacterCorrectOrder(roman);
        isNegativeNumber(roman);
        checkCharactersHasProperAntecedent(roman);
        checkRepeatedCharacterPairs(roman);
        hasTwoTheSameIncorrectCharactersNextToEachOther(roman);

    }

    public static void isNumberEqualNullOrBlank(String roman) {
        if (roman == null) {
            throw new NullPointerException("The number can not be null.");
        } else if (roman.isBlank()) {
            throw new RuntimeException("The number can not be blank.");
        }
    }

    public static void isNegativeNumber(String roman) {
        if (roman.charAt(0) == '-') {
            throw new RuntimeException("The number roman can not be negative.");
        }
    }

}
