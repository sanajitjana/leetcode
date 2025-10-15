/**
 * LeetCode Problem: 1995. Count Special Quadruplets
 *
 * Difficulty: Easy
 *
 * Problem Statement:
 * You are given a 0-indexed integer array nums. Return the number of distinct quadruplets (a, b, c, d) such that:
 *
 *   - nums[a] + nums[b] + nums[c] == nums[d], and
 *   - a < b < c < d
 *
 * Example 1:
 * Input: nums = [1,2,3,6]
 * Output: 1
 * Explanation: The only quadruplet is (0,1,2,3) because 1 + 2 + 3 == 6.
 *
 * Example 2:
 * Input: nums = [3,3,6,4,5]
 * Output: 0
 * Explanation: No such quadruplet exists.
 *
 * Example 3:
 * Input: nums = [1,1,1,3,5]
 * Output: 4
 * Explanation: The quadruplets are (0,1,2,3), (0,1,2,4), (0,2,1,3), (0,2,1,4).
 *
 * Constraints:
 *  - 4 <= nums.length <= 50
 *  - 1 <= nums[i] <= 100
 *
 * Approach:
 *  We need to count all ordered quadruplets (a, b, c, d) where:
 *      nums[a] + nums[b] + nums[c] == nums[d] and a < b < c < d
 *
 *  Brute force solution:
 *    - Iterate through all possible quadruplets using 4 nested loops.
 *    - For each (i, j, k, l), compute sum = nums[i] + nums[j] + nums[k].
 *    - If sum == nums[l], increment the count.
 *
 *  Optimization:
 *    - Since nums[i] <= 100, if sum > 100 we can break early (small pruning).
 *    - This helps avoid unnecessary checks.
 *
 *  Complexity:
 *    - Time Complexity: O(n^4) in the worst case (since we check all quadruplets).
 *      With n <= 50, this is acceptable (50^4 = 6.25 million iterations).
 *    - Space Complexity: O(1) since we only use a counter.
 */

class Solution {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    // small pruning: since nums[d] ≤ 100, skip if sum > 100
                    if (sum > 100) continue; 
                    for (int l = k + 1; l < nums.length; l++) {
                        if (sum == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
