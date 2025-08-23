/**
 * Problem: 1796. Second Largest Digit in a String
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/second-largest-digit-in-a-string/
 * 
 * Approach:
 * - Track the maximum and second maximum digits found while iterating.
 * - Update second maximum when a new maximum appears or when finding a digit between them.
 * 
 * Time Complexity: O(n)  // scan string once
 * Space Complexity: O(1) // only a few integer variables
 */

class Solution {
    public int secondHighest(String s) {
        int max = -1;
        int sMax = -1;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                if (num > max) {
                    sMax = max;
                    max = num;
                } else if (num < max && num > sMax) {
                    sMax = num;
                }
            }
        }
        return sMax;
    }
}
