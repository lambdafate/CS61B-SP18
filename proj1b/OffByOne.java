public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }

    public boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (!equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
            i = i + 1;
            j = j - 1;
        }
        return true;
    }
}
