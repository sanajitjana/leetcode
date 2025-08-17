/**
 * 231. Power of Two
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/power-of-two/description/
 *
 * ---------------------
 * Approach 1: Iterative Division
 *
 * - A number `n` is a power of two if it can be written as 2^k (where k ≥ 0).
 * - If `n` is positive and divisible by 2, we keep dividing it by 2.
 * - If after dividing repeatedly, the result becomes 1, then it was a power of two.
 * - Otherwise, it is not.
 *
 * Example:
 *   n = 16 → 16/2=8 → 8/2=4 → 4/2=2 → 2/2=1 ✅ Power of Two
 *   n = 12 → 12/2=6 → 6/2=3 ❌ not 1 → Not a Power of Two
 *
 * ---------------------
 * Time Complexity: O(log n)
 *   - In each step we divide by 2 → about log₂(n) steps.
 *
 * Space Complexity: O(1)
 *   - Only using a few variables, no extra space.
 */
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        while (n % 2 == 0) {
            n = n / 2;
        }
        return n == 1;
    }
}
