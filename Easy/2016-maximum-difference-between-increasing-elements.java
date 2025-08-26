/**
 * Problem: 2016. Maximum Difference Between Increasing Elements
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/maximum-difference-between-increasing-elements/
 * 
 * Approach:
 * - Use two pointers (left, right).
 * - Keep track of the minimum element seen so far at `left`.
 * - For each `right`, calculate difference with `left`.
 * - Update maximum difference if current difference is larger.
 * - If `nums[right]` is smaller or equal, move `left` to `right`.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int maximumDifference(int[] nums) {
        int max = -1;
        int left = 0;
        int right = 1;

        while (right < nums.length && left < nums.length) {
            if (nums[left] < nums[right]) {
                int diff = nums[right] - nums[left];
                max = Math.max(max, diff);
            } else {
                left = right; // move left to the new minimum
            }
            right++;
        }

        return max;
    }
}

// Another solution

class Solution {
    public int maximumDifference(int[] nums) {
        int min = nums[0];
        int max = -1;
        for(int i=0; i<nums.length; i++){
            min = Math.min(min, nums[i]);
            if(min<nums[i]){
                max=Math.max(max, nums[i]-min);
            }
        }
        return max;
    }
}
