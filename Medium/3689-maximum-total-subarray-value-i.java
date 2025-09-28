/**
 * Q2. Maximize Subarray Sum (Custom Variation)
 * Medium - ? pt
 *
 * You are given an integer array nums of length n and an integer k.
 * You must select exactly k distinct non-empty subarrays nums[l..r] of nums. 
 * Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.
 *
 * The value of a subarray nums[l..r] is defined as:
 *   max(nums[l..r]) - min(nums[l..r])
 *
 * The total value is the sum of the values of the selected subarrays.
 *
 * Return the maximum total value you can achieve.
 *
 * Example 1:
 * Input: nums = [1,2,3], k = 2
 * Output: 4
 * Explanation: Choose [1,2] with value (2-1)=1 and [2,3] with value (3-2)=1, 
 * but better is to pick [1,2,3] with value (3-1)=2 and then repeat logic to maximize → total = 4.
 *
 * ------------------------------------------------------------------
 * Approach:
 * - To maximize the total, the best difference comes from (global max - global min).
 * - Since you must pick k subarrays, the optimal total is simply (max - min) * k.
 * - This avoids the need to explicitly construct subarrays.
 *
 * ------------------------------------------------------------------
 * Time Complexity: O(n)  
 * (Single scan to compute max and min of nums)
 *
 * Space Complexity: O(1)  
 * (Only a few variables used)
 *
 * ------------------------------------------------------------------
 * LeetCode link: https://leetcode.com/problems/maximize-subarray-sum/
 */

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return (long)(max - min) * k;
    }
}
