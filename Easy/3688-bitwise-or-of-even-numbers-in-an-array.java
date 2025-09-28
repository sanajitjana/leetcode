/**
 * Q1. Bitwise OR of Even Numbers in an Array
 * Easy - 3 pt
 *
 * You are given an integer array nums.
 * Return the bitwise OR of all even numbers in the array.
 * If there are no even numbers in nums, return 0.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6]
 * Output: 6
 * Explanation: The even numbers are 2, 4, and 6. Their bitwise OR equals 6.
 *
 * Example 2:
 * Input: nums = [1,3,5]
 * Output: 0
 * Explanation: There are no even numbers in nums.
 *
 * ------------------------------------------------------------------
 * Approach:
 * - Initialize result as 0.
 * - Iterate through each number in nums.
 * - If the number is even, perform bitwise OR with result.
 * - Return the final result.
 *
 * ------------------------------------------------------------------
 * Time Complexity: O(n) 
 * (We traverse the array once)
 *
 * Space Complexity: O(1) 
 * (We use only a single integer variable for the result)
 *
 * ------------------------------------------------------------------
 * LeetCode link: https://leetcode.com/problems/bitwise-or-of-even-numbers-in-an-array/
 */

class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                res |= num;
            }
        }
        return res;
    }
}
