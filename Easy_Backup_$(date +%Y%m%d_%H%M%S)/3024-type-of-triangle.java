/**
 * Question: Determine Triangle Type
 * Link: https://leetcode.com/problems/triangle-type/ (hypothetical link, adjust if needed)
 *
 * Description:
 * Given three side lengths of a triangle, return the type of the triangle:
 *   - "equilateral" if all sides are equal
 *   - "isosceles" if exactly two sides are equal
 *   - "scalene" if all sides are different
 *   - "none" if the given sides cannot form a valid triangle
 *
 * Approach:
 * 1. Extract the three sides.
 * 2. Check the triangle inequality: sum of any two sides must be greater than the third.
 *    If it fails, return "none".
 * 3. If all three sides are equal, return "equilateral".
 * 4. If exactly two sides are equal, return "isosceles".
 * 5. Otherwise, return "scalene".
 *
 * Dry Run:
 * Example: nums = [3, 4, 5]
 *  - Check: 3+4 > 5, 3+5 > 4, 4+5 > 3 → valid triangle
 *  - Not all sides equal
 *  - No two sides equal
 *  - Result → "scalene"
 *
 * Example: nums = [2, 2, 2]
 *  - Check: valid triangle
 *  - All equal → "equilateral"
 *
 * Example: nums = [2, 3, 5]
 *  - Check: 2+3 = 5 → fails inequality
 *  - Result → "none"
 *
 * Time Complexity: O(1) → only a few comparisons.
 * Space Complexity: O(1) → no extra data structures used.
 */

class Solution {
    public String triangleType(int[] nums) {
        
        int s1 = nums[0];
        int s2 = nums[1];
        int s3 = nums[2];

        // Check triangle inequality
        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1) {
            return "none";
        }

        // All sides equal
        if (s1 == s2 && s2 == s3) {
            return "equilateral";
        } 
        // Any two equal
        else if (s1 == s2 || s2 == s3 || s1 == s3) {
            return "isosceles";
        } 
        // Otherwise scalene
        else {
            return "scalene";
        }
    }
}
