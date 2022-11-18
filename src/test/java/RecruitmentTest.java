import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RecruitmentTest {

    @Test
    @DisplayName("Test assert converting Roman Number")
    public void testConvertRomanNumber() {
        int result1 = RomanNumeralConverter.convertRomanNumber("MCMXCVIII");
        Assert.assertEquals(1998, result1);

        int result2 = RomanNumeralConverter.convertRomanNumber("VIII");
        Assert.assertEquals(8, result2);

        int result3 = RomanNumeralConverter.convertRomanNumber("M");
        Assert.assertEquals(1000, result3);

        int result4 = RomanNumeralConverter.convertRomanNumber("IX");
        Assert.assertEquals(9, result4);

        int result5 = RomanNumeralConverter.convertRomanNumber("XLIV");
        Assert.assertEquals(44, result5);

        int result6 = RomanNumeralConverter.convertRomanNumber("xliv");
        Assert.assertEquals(44, result6);

        int result7 = RomanNumeralConverter.convertRomanNumber("x l i v");
        Assert.assertEquals(44, result7);

        int result8 = RomanNumeralConverter.convertRomanNumber("       xli v");
        Assert.assertEquals(44, result8);

        int result9 = RomanNumeralConverter.convertRomanNumber("MMMCCCXXXIII");
        Assert.assertEquals(3333, result9);

        int result10 = RomanNumeralConverter.convertRomanNumber("CMXC IX");
        Assert.assertEquals(999, result10);

        int result11 = RomanNumeralConverter.convertRomanNumber("MMCDXLIV");
        Assert.assertEquals(2444, result11);
    }

    @Test
    @DisplayName("Test assert has Valid Characters")
    void testHasValidCharacters() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.hasValidCharacters("ABC"));
        assertEquals("The number ABC does not contain valid characters.", exception.getMessage());
    }

    @Test
    @DisplayName("Test assert has Character Correct Order")
    void testHasCharacterCorrectOrder() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.hasCharacterCorrectOrder("LILI"));
        assertEquals("The number LILI is not correct roman number.", exception.getMessage());
    }

    @Test
    @DisplayName("Test assert has Characters Correct Repetitions")
    void testHasCharactersCorrectRepetitions() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.hasCharactersCorrectRepetitions("IIIIMCMXCIXX"));
        assertEquals("Too many repetitions of a character I .The number IIIIMCMXCIXX is not valid.", exception.getMessage());
    }

    @Test
    @DisplayName("Test assert is number null or blank")
    void testIsNumberEqualNullOrBlank() {

        RuntimeException exception1 = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.isNumberEqualNullOrBlank(null));
        assertEquals("The number can not be null.", exception1.getMessage());

        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.isNumberEqualNullOrBlank("    "));
        assertEquals("The number can not be blank.", exception2.getMessage());
    }

    @Test
    @DisplayName("Test assert is number negative")
    void testIsNegativeNumber() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.isNegativeNumber("-XX"));
        assertEquals("The number roman can not be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Test assert check Repeated Character Pairs")
    void testCheckRepeatedCharacterPairs() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.checkRepeatedCharacterPairs("XIXIXI"));
        assertEquals("The number XIXIXI has repeared characters pairs.", exception.getMessage());

    }

    @Test
    @DisplayName("Test check if Characters Has Proper Antecedent")
    void testCheckCharactersHasProperAntecedent() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.checkCharactersHasProperAntecedent("VL"));
        assertEquals("The number VL does not contain valid characters.", exception.getMessage());
    }

    @Test
    @DisplayName("Test assert if number has Two The Same Incorrect Characters Next To Each Other")
    void testHasTwoTheSameIncorrectCharactersNextToEachOther() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> RomanNumeralConverter.hasTwoTheSameIncorrectCharactersNextToEachOther("IIV"));
        assertEquals("The number contains to many number of repetitions of I characters.", exception.getMessage());
    }
}
