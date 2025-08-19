/**
 * 2348. Number of Zero-Filled Subarrays
 * Difficulty: Medium
 * URL: https://leetcode.com/problems/number-of-zero-filled-subarrays/description/
 *
 * ---------------------
 * Approach 1: Brute Force
 *
 * - Generate all possible subarrays using two loops.
 * - For each subarray, check if all elements are zero.
 * - If yes, increase the count.
 *
 * - In the given code, this is done by converting the subarray into a string
 *   and checking if all characters are '0'.
 *
 * Time Complexity: O(n^3) → (O(n^2) subarrays × O(n) checking each)
 * Space Complexity: O(n) due to string building.
 *
 * Not practical for large inputs, but helps in understanding.
 *
 */
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            String str = "";
            for (int j = i; j < nums.length; j++) {
                str += nums[j] + "";
                boolean flag = true;
                for (int k = 0; k < str.length(); k++) {
                    if (str.charAt(k) != '0') {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    count++;
                }
            }
        }
        return count;
    }
}


/**
 * ---------------------
 * Approach 2: Optimized (Mathematical Counting)
 *
 * - Instead of generating all subarrays, notice:
 *   For a streak of `k` consecutive zeros, number of zero-subarrays is:
 *        k * (k + 1) / 2
 *   (Formula of sum of first n natural numbers).
 *
 * Example: nums = [0,0,1,0]
 * - Streak1 = "00" → 2*(2+1)/2 = 3 subarrays → [0], [0], [0,0]
 * - Streak2 = "0"  → 1*(1+1)/2 = 1 subarray → [0]
 * Total = 4
 *
 * Algorithm:
 * 1. Traverse array and keep track of consecutive zeros.
 * 2. When a non-zero is encountered, add contribution of that streak.
 * 3. After loop ends, add last streak if any.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 */
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long streak = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                streak++;
            } else {
                count += (streak * (streak + 1)) / 2;
                streak = 0;
            }
        }
        // Handle streak at the end
        if (streak > 0) {
            count += (streak * (streak + 1)) / 2;
        }
        return count;
    }
}
