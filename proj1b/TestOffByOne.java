import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('Z', '['));
        assertTrue(offByOne.equalChars('&', '%'));

        assertFalse(offByOne.equalChars('B', 'B'));
        assertFalse(offByOne.equalChars('A', 'b'));
    }

    @Test
    public void testIsPalindrome() {
        OffByOne offByOne = new OffByOne();
        assertTrue(offByOne.isPalindrome(""));
        assertTrue(offByOne.isPalindrome("a"));
        assertTrue(offByOne.isPalindrome("aZ&a%[b"));

        assertFalse(offByOne.isPalindrome("abc"));
    }
}
