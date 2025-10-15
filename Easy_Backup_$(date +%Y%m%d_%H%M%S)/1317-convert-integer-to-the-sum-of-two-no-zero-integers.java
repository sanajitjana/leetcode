/**
 * Problem:
 * https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
 * 
 * Given an integer n, return two integers a and b such that:
 * - a + b == n
 * - Neither a nor b contains the digit zero in their decimal representation.
 * You can return any valid pair [a, b].
 * 
 * Example:
 * Input: n = 11
 * Output: [2, 9]
 * Explanation: 2 + 9 = 11 and neither 2 nor 9 contains zero.
 * 
 * Constraints:
 * - 2 <= n <= 10^4
 * 
 * Approach:
 * We iterate from i = 1 to n - 1. For each i, we check if both i and (n - i)
 * do not contain any zero digit by using the containsZero helper method.
 * As soon as we find such a pair, we return it.
 * The containsZero method checks if a number has the digit 0 by iterating
 * through its digits using modulo and division.
 * 
 * Time Complexity:
 * O(n * log(n)) — In the worst case, we iterate up to n and for each number,
 * checking if it contains zero takes O(log n) time (based on the number of digits).
 * 
 * Space Complexity:
 * O(1) — We only use a constant amount of extra space for variables.
 */
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            if (!containsZero(i) && !containsZero(n - i)) {
                return new int[] { i, n - i };
            }
        }
        return new int[] { 1, n - 1 };
    }

    private boolean containsZero(int num) {
        while (num > 0) {
            int digit = num % 10;
            if (digit == 0) {
                return true;
            }
            num = num / 10;
        }
        return false;
    }
}
