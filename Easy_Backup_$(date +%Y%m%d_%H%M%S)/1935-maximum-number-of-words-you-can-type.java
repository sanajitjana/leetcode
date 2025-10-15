/**
 * LeetCode Problem: 1935. Maximum Number of Words You Can Type
 * https://leetcode.com/problems/maximum-number-of-words-you-can-type/
 * 
 * Problem:
 * There is a string `text` consisting of words separated by a single space, and a string `brokenLetters` containing unique broken keyboard letters. 
 * A word can be typed if none of its letters are in `brokenLetters`.
 * Return the number of words in `text` that can be typed.
 * 
 * Example:
 * Input: text = "hello world", brokenLetters = "ad"
 * Output: 1
 * 
 * Approach:
 * 1. Create a boolean array of size 26 to mark broken letters.
 * 2. For each broken letter, mark its corresponding index as true.
 * 3. Split the `text` into words by spaces.
 * 4. For each word, check every character:
 *    - If any character is broken, skip counting this word.
 *    - Otherwise, count it as a valid word.
 * 
 * Time Complexity: O(n + m)
 * - n = length of `text`
 * - m = length of `brokenLetters`
 * 
 * Space Complexity: O(1)
 * - Fixed array of size 26 is used for tracking broken letters.
 */

public class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] charExists = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            charExists[c - 'a'] = true;
        }

        String[] textArr = text.split(" ");
        int count = 0;

        for (String word : textArr) {
            boolean canType = true;
            for (int i = 0; i < word.length(); i++) {
                if (charExists[word.charAt(i) - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) {
                count++;
            }
        }

        return count;
    }
}
