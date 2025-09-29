/**
 * Title: Positions of Large Groups
 * LeetCode Link: https://leetcode.com/problems/positions-of-large-groups/
 *
 * Question Info:
 * In a string s of lowercase letters, a group is a consecutive run of the same character.
 * A large group is defined as a group that has length >= 3.
 * Return the intervals [start, end] of every large group sorted in increasing order of start index.
 *
 * Example:
 * Input: s = "abbxxxxzzy"
 * Output: [[3,6]]
 *
 * Approach:
 * - Use two pointers to track the start of a group and the current index.
 * - Iterate through the string from i = 1 to n.
 * - If we reach the end OR the current char differs from the start char:
 *     - Check if the group length >= 3.
 *     - If yes, record the interval [start, i-1].
 *     - Move start = i to begin a new group.
 * - Return the list of all intervals.
 *
 * Dry Run (s = "abbxxxxzzy"):
 * i=1: s[1]='b', s[0]='a' => different, group size=1 (<3), skip, start=1
 * i=2: s[2]='b', s[1]='b' => same, continue
 * i=3: s[3]='x', s[1]='b' => different, group size=2 (<3), skip, start=3
 * i=4,5,6: still 'x', continue
 * i=7: s[7]='z', s[3]='x' => different, group size=4 (>=3), record [3,6], start=7
 * i=8: s[8]='z', s[7]='z' => same
 * i=9: s[9]='y', s[7]='z' => different, group size=2 (<3), skip, start=9
 * i=10: reached end, group size=1 (<3), skip.
 * Result: [[3,6]]
 *
 * Time Complexity: O(n), where n = length of string (single pass).
 * Space Complexity: O(1), ignoring output list.
 */

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length();
        int start = 0;

        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) != s.charAt(start)) {
                if (i - start >= 3) {
                    ans.add(Arrays.asList(start, i - 1));
                }
                start = i;
            }
        }

        return ans;
    }
}
