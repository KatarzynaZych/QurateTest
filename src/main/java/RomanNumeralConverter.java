import java.util.HashMap;

public class RomanNumeralConverter {

    public static int convertRomanNumber(String romanNumeral) {

        validRomanNumber(romanNumeral );

        char[] symbols = romanNumeral .toCharArray();
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

    private static HashMap<String, Integer> romanMap = new HashMap<String, Integer>() {{
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

                        if (isCharacterReplayToManyTimes(s, counter, 3, "C", "X", "I") ||
                                isCharacterReplayToManyTimes(s, counter, 1, "D", "V", "L")) {
                            {
                                throwException("Too many repetitions of a character " + s + " .The number " + number + " is not valid.");
                            }
                        }
                    }
                }
        );
    }

    private static boolean isCharacterReplayToManyTimes(String s, int counter, int allowableNumberOfRepetitions, String c, String x, String i) {
        return counter > allowableNumberOfRepetitions && (s.equals(c) || s.equals(x) || s.equals(i));
    }

    private static void throwException(String message) {
        throw new RuntimeException(message);
    }

    private static void validRomanNumber(String roman) {
        hasValidCharacters(roman);
        hasCharactersCorrectRepetitions(roman);
        hasCharacterCorrectOrder(roman);
    }

}