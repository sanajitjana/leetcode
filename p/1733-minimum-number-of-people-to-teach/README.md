# Minimum Number of People to Teach - [Link](https://leetcode.com/problems/minimum-number-of-people-to-teach/)

## Question Description
You are given n languages numbered from 1 to n, and for each user, a list of languages they know. Some pairs of users are friends but might not share a common language. You can teach one language to some users so that all friends can communicate. Return the minimum number of users you need to teach.

---

## Constraints
- `1 <= n <= 500`
- `1 <= languages.length <= 500`
- `1 <= languages[i].length <= 10`
- `1 <= languages[i][j] <= n`
- `1 <= friendships.length <= 1000`
- `1 <= friendships[i][j] <= languages.length`

---

## Approach
1. Build a mapping from each user to the set of languages they know
2. Find all problematic friendship pairs (where no shared language exists)
3. Collect all unique users involved in problematic pairs
4. For every language from 1 to n:
   - Count how many users in the problematic set do NOT know that language
   - Keep track of the minimum such count
5. Return the minimum number of people to teach

**Why this approach works:**
- We only need to consider users who are in problematic friendships
- For each possible language, we calculate how many problematic users don't know it
- The minimum across all languages gives us the optimal language to teach

**Alternative approaches considered:**
- Could use arrays instead of sets for language tracking, but sets are more convenient for lookup

---

## Dry Run
Example Input: `n = 3, languages = [[1,2],[2,3],[1,3]], friendships = [[1,2],[2,3]]`

Step-by-step execution:
- Step 1: Build user language map: {1: [1,2], 2: [2,3], 3: [1,3]}
- Step 2: Find problematic pairs: [1,2] (no common language), [2,3] (no common language)
- Step 3: Problematic users: [1,2,3]
- Step 4: Try each language:
  - Language 1: Users 2,3 don't know it → teach 2
  - Language 2: Users 1,3 don't know it → teach 2
  - Language 3: Users 1,2 don't know it → teach 2
- Step 5: Minimum is 2

Final Answer = `2`

---

## Solution
```java
import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Map user to their known languages
        HashMap<Integer, Set<Integer>> userLanguage = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int lang : languages[i]) {
                set.add(lang);
            }
            userLanguage.put(i + 1, set);  // User IDs are 1-based
        }

        // Find problematic friendships
        HashSet<Integer> uniqueProblematicUsers = new HashSet<>();

        for (int[] pair : friendships) {
            Set<Integer> firstUserLangs = userLanguage.get(pair[0]);
            Set<Integer> secondUserLangs = userLanguage.get(pair[1]);

            boolean hasCommonLang = firstUserLangs.stream().anyMatch(secondUserLangs::contains);

            if (!hasCommonLang) {
                uniqueProblematicUsers.add(pair[0]);
                uniqueProblematicUsers.add(pair[1]);
            }
        }

        // Try every language and compute minimum teaching effort
        int minToTeach = Integer.MAX_VALUE;

        for (int lang = 1; lang <= n; lang++) {
            int teachCount = 0;
            for (int user : uniqueProblematicUsers) {
                Set<Integer> knownLanguages = userLanguage.get(user);
                if (!knownLanguages.contains(lang)) {
                    teachCount++;
                }
            }
            minToTeach = Math.min(minToTeach, teachCount);
        }

        return minToTeach;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n × u) where n = number of languages, u = number of unique problematic users
- **Space Complexity:** O(u + p) where u = unique problematic users, p = problematic friendship pairs
