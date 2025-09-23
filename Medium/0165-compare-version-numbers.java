/**
 * Question: Compare Version Numbers
 * Link: https://leetcode.com/problems/compare-version-numbers/
 *
 * Description:
 * Given two version strings, version1 and version2, compare them.
 * A version string consists of revisions separated by dots '.'.
 * Each revision is an integer ignoring leading zeros.
 *
 * Return:
 *   - If version1 < version2, return -1
 *   - If version1 > version2, return 1
 *   - Otherwise, return 0
 *
 * ------------------------------------------------------------------
 * Approach:
 * 1. Split both version strings into integer lists by '.'.
 * 2. Normalize lengths by padding the shorter list with zeros.
 * 3. Compare the lists index by index:
 *      - If any revision differs, return -1 or 1 accordingly.
 *      - If all are equal, return 0.
 *
 * ------------------------------------------------------------------
 * Dry Run:
 * version1 = "1.01", version2 = "1.001"
 * list1 = [1, 1], list2 = [1, 1]
 * Compare:
 *   index 0: 1 == 1
 *   index 1: 1 == 1
 * Final → return 0
 *
 * version1 = "1.0.1", version2 = "1"
 * list1 = [1, 0, 1], list2 = [1, 0, 0] (after padding)
 * Compare:
 *   index 0: 1 == 1
 *   index 1: 0 == 0
 *   index 2: 1 > 0 → return 1
 *
 * ------------------------------------------------------------------
 * Time Complexity:
 * O(n + m) where n = length of version1, m = length of version2
 * (we parse both strings fully once).
 *
 * Space Complexity:
 * O(n + m) for storing split parts in two lists.
 */

import java.util.*;

class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> list1 = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < version1.length(); i++) {
            char ch = version1.charAt(i);
            if (ch == '.') {
                list1.add(Integer.parseInt(st.toString()));
                st = new StringBuilder();
            } else {
                st.append(ch);
            }
        }
        list1.add(Integer.parseInt(st.toString()));

        List<Integer> list2 = new ArrayList<>();
        StringBuilder st2 = new StringBuilder();
        for (int i = 0; i < version2.length(); i++) {
            char ch = version2.charAt(i);
            if (ch == '.') {
                list2.add(Integer.parseInt(st2.toString()));
                st2 = new StringBuilder();
            } else {
                st2.append(ch);
            }
        }
        list2.add(Integer.parseInt(st2.toString()));

        if (list1.size() > list2.size()) {
            for (int i = 0; i < list1.size() - list2.size()+1; i++) {
                list2.add(0);
            }
        } else if (list1.size() < list2.size()) {
            for (int i = 0; i < list2.size() - list1.size()+1; i++) {
                list1.add(0);
            }
        }

        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int num1 = list1.get(i);
            int num2 = list2.get(j);
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
            i++;
            j++;
        }
        return 0;
    }
}
