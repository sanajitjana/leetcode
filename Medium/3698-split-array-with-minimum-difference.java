/**
 * Link: https://leetcode.com/problems/split-array-with-minimum-difference/
 * Title: Split Array With Minimum Difference
 *
 * Question:
 * You are given a 0-indexed integer array nums of length n.
 * Split the array into two non-empty parts such that:
 *   - The left part contains the first i + 1 elements.
 *   - The right part contains the remaining n - i - 1 elements.
 *
 * The difference between the two parts is the absolute difference 
 * between the sum of the left part and the sum of the right part.
 *
 * Return the minimum difference possible between the two parts.
 *
 * --------------------------------------------------------------------
 * Approach:
 * - Precompute prefix sums (left sums) and track if the left part is strictly increasing.
 * - Precompute suffix sums (right sums) and track if the right part is strictly decreasing.
 * - For each possible split index i:
 *      - If left[0..i] is strictly increasing and right[i+1..n-1] is strictly decreasing,
 *        calculate the difference = |leftSum - rightSum|.
 *      - Keep track of the minimum difference found.
 * - If no valid split exists, return -1.
 *
 * --------------------------------------------------------------------
 * Dry Run:
 * nums = [2, 3, 1]
 * n = 3
 * left:
 *   leftSum = [2, 5, 6]
 *   leftValid = [true, true, false] (since 3 > 1 fails)
 * right:
 *   rightSum = [6, 4, 1]
 *   rightValid = [false, true, true]
 *
 * Possible splits:
 * i = 0 → leftValid[0] && rightValid[1] = true && true
 *   diff = |2 - 4| = 2
 * i = 1 → leftValid[1] && rightValid[2] = true && true
 *   diff = |5 - 1| = 4
 * Answer = min(2, 4) = 2
 *
 * --------------------------------------------------------------------
 * Time Complexity: O(n)
 *   - One pass for prefix sums and checks, one pass for suffix sums and checks,
 *     and one pass for evaluating splits.
 * Space Complexity: O(n)
 *   - Arrays leftSum, rightSum, leftValid, rightValid each take O(n).
 */

class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;

        // prefix sums + strictly increasing check
        boolean[] left = new boolean[n];
        long[] leftSum = new long[n];
        left[0] = true;
        leftSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] && nums[i - 1] < nums[i];
            leftSum[i] = leftSum[i - 1] + nums[i];
        }

        // suffix sums + strictly decreasing check
        boolean[] right = new boolean[n];
        long[] rightSum = new long[n];
        right[n - 1] = true;
        rightSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] && nums[i] > nums[i + 1];
            rightSum[i] = rightSum[i + 1] + nums[i];
        }

        // evaluate all splits
        long min = Long.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] && right[i + 1]) {
                min = Math.min(min, Math.abs(leftSum[i] - rightSum[i + 1]));
                found = true;
            }
        }

        return found ? min : -1;
    }
}
