/**
 * Problem: 1. Two Sum
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/two-sum/
 * 
 * Approach:
 * - Use a HashMap to store visited numbers and their indices.
 * - Check if target - current exists in map.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}

