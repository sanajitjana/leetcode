/*
 * Problem Link:
 * https://leetcode.com/problems/largest-perimeter-triangle/?envType=daily-question&envId=2025-09-28
 *
 * Q: Largest Perimeter Triangle
 *
 * You are given an array nums of non-negative integers.
 * Return the largest perimeter of a triangle with non-zero area, formed from three of these lengths.
 * If it is impossible to form any triangle of non-zero area, return 0.
 *
 * ------------------------------------------------------
 * Approach 1: Brute Force (O(n^3))
 * ------------------------------------------------------
 * Idea:
 *  - Sort the array.
 *  - Try every possible triplet (i, j, k).
 *  - Check the triangle inequality: 
 *      a + b > c, a + c > b, b + c > a
 *  - Keep track of the maximum perimeter.
 *
 * Dry Run Example:
 * nums = [2, 1, 2]
 * After sort = [1, 2, 2]
 * Pick (1,2,2):
 *    1 + 2 > 2 (yes)
 *    1 + 2 > 2 (yes)
 *    2 + 2 > 1 (yes)
 * Valid triangle, perimeter = 5
 * Answer = 5
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(1)
 */

import java.util.Arrays;

class SolutionBruteForce {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int x = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int y = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int z = nums[k];
                    if (x + y > z && x + z > y && y + z > x) {
                        max = Math.max(max, x + y + z);
                    }
                }
            }
        }
        return max;
    }
}


/*
 * ------------------------------------------------------
 * Approach 2: Optimized Greedy (O(n log n))
 * ------------------------------------------------------
 * Idea:
 *  - Sort the array.
 *  - The largest perimeter will always come from the three largest numbers
 *    that can form a valid triangle.
 *  - Starting from the end of the sorted array, check triplets (i, i-1, i-2).
 *  - As soon as we find a valid triangle, return its perimeter.
 *
 * Why consecutive triplets are enough:
 *  - After sorting, if nums[i-2] + nums[i-1] > nums[i], then
 *    (nums[i-2], nums[i-1], nums[i]) is valid and maximizes perimeter.
 *  - If not, moving left reduces the largest side, so we must keep checking.
 *
 * Dry Run Example:
 * nums = [2, 1, 2]
 * After sort = [1, 2, 2]
 * Start from i = 2:
 *   nums[0] + nums[1] = 1 + 2 = 3 > 2 → valid
 *   perimeter = 1 + 2 + 2 = 5
 * Answer = 5
 *
 * Time Complexity: O(n log n) (due to sorting)
 * Space Complexity: O(1)
 */

class SolutionOptimized {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }
}
