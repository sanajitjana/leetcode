/**
 * LeetCode Problem: [Custom / Example]
 * Title: Find Closest
 *
 * Problem Statement:
 * Given three integers x, y, and z, return:
 *   - 1 if x is closer to z,
 *   - 2 if y is closer to z,
 *   - 0 if both are equally close.
 *
 * Example:
 * Input: x = 3, y = 7, z = 5
 * Output: 1
 *
 * Explanation: |3 - 5| = 2, |7 - 5| = 2 → equal distance → return 0
 *
 * Approach:
 * - Use absolute difference to measure distance from z.
 * - Compare xStepNeed and yStepNeed.
 * - Return accordingly.
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

class Solution {
    public int findClosest(int x, int y, int z) {
        int xStepNeed = Math.abs(x - z);
        int yStepNeed = Math.abs(y - z);

        if (xStepNeed < yStepNeed) return 1;
        else if (xStepNeed > yStepNeed) return 2;
        else return 0;
    }
}
