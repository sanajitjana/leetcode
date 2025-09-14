/*
 * LeetCode Problem: Vowel Spellchecker
 * https://leetcode.com/problems/vowel-spellchecker/
 * 
 * Given a wordlist and a list of queries, return a list of words from wordlist
 * for each query based on the following rules in order of priority:
 * 1. Exact match
 * 2. Case-insensitive match
 * 3. Vowel error match (replace vowels with any vowel)
 * 
 * Approach:
 * - Store exact words in a HashSet for O(1) look-up.
 * - Build two maps:
 *     1. caseInsensitiveMap: maps lowercase words to the original word (first occurrence).
 *     2. vowelErrorMap: maps vowel-masked lowercase words (vowels replaced by '*') to the original word (first occurrence).
 * - For each query, try exact match → case-insensitive match → vowel error match → "" (if no match).
 * 
 * Time Complexity: O(N + Q * L)
 *   - N = wordlist length, Q = queries length, L = average word length
 *   - O(N * L) to process the wordlist and build maps.
 *   - O(Q * L) to process queries.
 * 
 * Space Complexity: O(N * L)
 *   - Storing words in HashSet and maps.
 */

import java.util.*;

public class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> caseInsensitiveMap = new HashMap<>();
        Map<String, String> vowelErrorMap = new HashMap<>();

        for (String word : wordlist) {
            String wordLower = word.toLowerCase();
            caseInsensitiveMap.putIfAbsent(wordLower, word);

            String wordVowelMasked = maskVowels(wordLower);
            vowelErrorMap.putIfAbsent(wordVowelMasked, word);
        }

        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            if (exactWords.contains(query)) {
                ans[i] = query;
                continue;
            }

            String queryLower = query.toLowerCase();
            if (caseInsensitiveMap.containsKey(queryLower)) {
                ans[i] = caseInsensitiveMap.get(queryLower);
                continue;
            }

            String queryVowelMasked = maskVowels(queryLower);
            if (vowelErrorMap.containsKey(queryVowelMasked)) {
                ans[i] = vowelErrorMap.get(queryVowelMasked);
            } else {
                ans[i] = "";
            }
        }

        return ans;
    }

    private String maskVowels(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if ("aeiou".indexOf(c) != -1)
                sb.append('*');
            else
                sb.append(c);
        }
        return sb.toString();
    }
}
