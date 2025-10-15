/**
 * 1323. Maximum 69 Number
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/maximum-69-number/description/
 *
 * ---------------------
 * Approach: Greedy (First '6' to '9')
 * ---------------------
 * - Convert the number to a character array.
 * - Traverse from left to right and change the **first '6'** to '9'.
 * - Break immediately since changing the leftmost '6' maximizes the number.
 * - Convert the updated character array back to an integer and return it.
 *
 * Time Complexity:  O(n)   // where n = number of digits
 * Space Complexity: O(n)   // for storing char array (can be seen as O(1) since digits ≤ 4)
 */

class Solution {
    public int maximum69Number(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(digits));
    }
}
