import java.util.HashMap;
import java.util.Locale;

public class RomanNumeralConverter {

    public static int convertRomanNumber(String number) {
        isNumberEqualNullOrBlank(number);
        String  refactoredRomanNumber = number.replaceAll(" ","").toUpperCase(Locale.ROOT);
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
    }

    public static void isNumberEqualNullOrBlank(String roman){
        if(roman == null){
            throw new NullPointerException("The number can not be null.");
        } else if (roman.isBlank()) {
            throw new RuntimeException("The number can not be blank.");
        }
    }

    public static void isNegativeNumber(String roman){
        if(roman.charAt(0) == '-'){
            throw new RuntimeException("The number roman can not be negative.");
        }
    }

}
