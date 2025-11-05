# Vowel Spellchecker - <a href="https://leetcode.com/problems/vowel-spellchecker/" target="_blank">Link</a>

## Question Description
Given a wordlist and a list of queries, return a list of words from wordlist for each query based on the following rules in order of priority:

1. Exact match
2. Case-insensitive match  
3. Vowel error match (replace vowels with any vowel)
4. If no match found, return empty string

---

## Constraints
- `1 <= wordlist.length <= 5000`
- `1 <= queries.length <= 5000`
- `1 <= wordlist[i].length <= 7`
- `1 <= queries[i].length <= 7`
- All strings consist of only English letters

---

## Approach
- Store exact words in a HashSet for O(1) look-up
- Build two maps:
  1. caseInsensitiveMap: maps lowercase words to the original word (first occurrence)
  2. vowelErrorMap: maps vowel-masked lowercase words (vowels replaced by '*') to the original word (first occurrence)
- For each query, try exact match → case-insensitive match → vowel error match → "" (if no match)

**Why this approach works:**
- HashSet provides O(1) lookup for exact matches
- Maps handle case-insensitive and vowel error matching efficiently
- Processes queries in priority order as specified
- Uses putIfAbsent to ensure first occurrence is preserved

**Alternative approaches considered:**
- Could use single map with more complex keys, but separate maps are clearer
- Could use trie for more complex matching, but overkill for this problem

---

## Dry Run
Example Input:
```
wordlist = ["KiTe","kite","hare","Hare"]
queries = ["kite","Kite","kiTe","Hare","KARE"]
```

Step-by-step execution:
- Step 1: Build exactWords set: ["KiTe","kite","hare","Hare"]
- Step 2: Build caseInsensitiveMap: {"kite":"kite","hare":"hare"}
- Step 3: Build vowelErrorMap: {"k*t*":"kite","h*r*":"hare"}
- Step 4: Process queries:
  - "kite" -> exact match "kite"
  - "Kite" -> case-insensitive match "kite"
  - "kiTe" -> exact match "kiTe"
  - "Hare" -> exact match "Hare"
  - "KARE" -> vowel error match "hare"

Final Answer = `["kite","kite","kiTe","Hare","hare"]`

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:** O(N + Q * L) where N = wordlist length, Q = queries length, L = average word length
- **Space Complexity:** O(N * L) for storing words in HashSet and maps
