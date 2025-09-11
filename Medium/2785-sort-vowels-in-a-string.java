/**
 * Problem: Sort Vowels in a String
 * LeetCode Link: https://leetcode.com/problems/sort-vowels-in-a-string/
 * 
 * Given a string s, sort only the vowels in ascending order and keep other characters in place.
 * 
 * Approach 1:
 * - Extract vowels by checking "AEIOUaeiou".indexOf(c).
 * - Sort the extracted vowels.
 * - Rebuild the string by replacing vowels in order.
 * 
 * Approach 2:
 * - Use a Set<Character> of vowels for O(1) lookup.
 * - Extract vowels into a list.
 * - Sort the list.
 * - Reconstruct the string by replacing vowels from the sorted list.
 * 
 * Time Complexity (both approaches): O(n log n)
 *   - O(n) to extract vowels.
 *   - O(k log k) to sort vowels (k ≤ n).
 *   - O(n) to rebuild the string.
 * 
 * Space Complexity (both approaches): O(n)  
 *   For storing the extracted vowels and the result string.
 */

class Solution {

    // Approach 1
    public String sortVowelsApproach1(String s) {
        List<Character> vowels = new ArrayList<>();
        
        // Extract vowels
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) {
                vowels.add(c);
            }
        }

        // Sort vowels
        Collections.sort(vowels);

        // Rebuild string with sorted vowels
        StringBuilder result = new StringBuilder();
        int vIndex = 0;
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) {
                result.append(vowels.get(vIndex++));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Approach 2
    public String sortVowelsApproach2(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<Character> extractedVowels = new ArrayList<>();

        // Extract vowels
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                extractedVowels.add(c);
            }
        }

        // Sort vowels
        Collections.sort(extractedVowels);

        // Rebuild string with sorted vowels
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append(extractedVowels.get(index++));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
