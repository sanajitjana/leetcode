// 🔹 Problem: 2942. Find Words Containing Character
// 🔹 Link: https://leetcode.com/problems/find-words-containing-character/
//
// 🔹 Explanation:
// We are given an array of words and a character `x`. 
// The task is to return the indices of all words that contain the character `x`.
// For each word, we check every character. 
// If we find a match, we store that index in the result list and move to the next word.
//
// 🔹 Approach:
// 1. Initialize an empty list to store indices.
// 2. Iterate through each word in the array by index.
// 3. Convert the word into a char array and check each character.
// 4. If a character matches `x`, add the index to the result list and break (to avoid duplicates).
// 5. Return the list of indices.
//
// 🔹 Complexity Analysis:
// Time Complexity: O(N * L)
//   - N = number of words
//   - L = average length of each word
//   - Worst case: we may check all characters of all words.
//
// Space Complexity: O(1)
//   - We only use a result list (output itself is not counted as extra space).
//
// 🔹 Example:
// Input: words = ["leet","code"], x = 'e'
// Output: [0,1] 
// Explanation: 'e' is found in both "leet" and "code".

class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray()) {
                if (ch == x) {
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }
}
