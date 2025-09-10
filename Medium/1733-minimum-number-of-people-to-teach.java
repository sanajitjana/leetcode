/**
 * Problem: Minimum Number of People to Teach
 * Link: https://leetcode.com/problems/minimum-number-of-people-to-teach/
 *
 * You are given n languages numbered from 1 to n, and for each user, a list of languages they know.
 * Some pairs of users are friends but might not share a common language.
 * You can teach one language to some users so that all friends can communicate.
 * Return the minimum number of users you need to teach.
 *
 * Approach:
 * 1. Build a mapping from each user to the set of languages they know.
 * 2. Find all problematic friendship pairs (where no shared language exists).
 * 3. Collect all unique users involved in problematic pairs.
 * 4. For every language from 1 to n:
 *      - Count how many users in the problematic set do NOT know that language.
 *      - Keep track of the minimum such count.
 * 5. Return the minimum number of people to teach.
 *
 * Time Complexity: O(n × u), where:
 *      - n = number of languages (usually small, ≤ 500)
 *      - u = number of unique problematic users (≤ 500)
 *
 * Space Complexity: O(u + p):
 *      - u: Unique problematic users (≤ 500)
 *      - p: Problematic friendship pairs (≤ 10⁴)
 */

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
