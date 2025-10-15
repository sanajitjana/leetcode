/**
 * 342. Power of Four
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/power-of-four/
 *
 * ---------------------
 * Approach: Iterative Division
 * ---------------------
 * - If n <= 0, return false (negative numbers and zero can’t be powers of 4).
 * - Repeatedly divide n by 4 while it is divisible by 4.
 * - After the loop, if n reduces to 1 → it’s a power of 4, otherwise not.
 *
 * Time Complexity:  O(log₄ n)  // dividing by 4 each time
 * Space Complexity: O(1)
 */


class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0)
            return false;
        while (n % 4 == 0)
            n = n / 4;
        return n == 1;
    }
}
