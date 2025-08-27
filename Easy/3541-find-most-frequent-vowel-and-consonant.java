/**
 * Problem: Maximum Frequency Sum (custom problem)
 * Difficulty: Easy
 * 
 * Description:
 * - Given a string, count the frequencies of vowels and consonants.
 * - Find the maximum frequency among vowels and the maximum frequency among consonants.
 * - Return the sum of these two maximum frequencies.
 * 
 * Approach:
 * - Use two HashMaps: one for vowels, one for consonants.
 * - Iterate over each character in the string.
 *   - If it's a vowel, update the vowel map.
 *   - Otherwise, update the consonant map.
 * - Find the maximum frequency from vowels and from consonants.
 * - Return their sum.
 * 
 * Time Complexity: O(n) where n = length of string
 * Space Complexity: O(k) where k = number of distinct characters (at most 26 for lowercase English letters)
 */

class Solution {
    HashMap<Character, Integer> vMap = new HashMap<>();
    HashMap<Character, Integer> cMap = new HashMap<>();

    public int maxFreqSum(String s) {
        for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vMap.put(ch, vMap.getOrDefault(ch, 0) + 1);
            } else {
                cMap.put(ch, cMap.getOrDefault(ch, 0) + 1);
            }
        }

        int maxV = 0;
        // find max vowel frequency
        for (int val : vMap.values()) {
            if (val > maxV) {
                maxV = val;
            }
        }

        int maxC = 0;
        // find max consonant frequency
        for (int val : cMap.values()) {
            if (val > maxC) {
                maxC = val;
            }
        }

        return maxV + maxC;
    }
}
