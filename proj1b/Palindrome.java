public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        /*
        // first solution
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i = i + 1;
            j = j - 1;
        }
        return true;
        */

        /*
        // do with wordToDeque
        Deque<Character> deque = wordToDeque(word);
        while(deque.size() > 1){
            if(deque.removeFirst() != deque.removeLast()){
                return false;
            }
        }
        return true;
        */

        // do with recursion
        return isPalindrome(word, 0, word.length() - 1);
    }

    private boolean isPalindrome(String word, int i, int j) {
        if (j < i) {
            return true;
        }
        if (word.charAt(i) != word.charAt(j)) {
            return false;
        }
        if (i < j - 1) {
            return isPalindrome(word, i + 1, j - 1);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
