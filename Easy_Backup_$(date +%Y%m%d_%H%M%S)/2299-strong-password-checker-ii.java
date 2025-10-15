/**
 * LeetCode Problem: 2299. Strong Password Checker II
 * Link: https://leetcode.com/problems/strong-password-checker-ii/
 *
 * Description:
 * A password is said to be strong if:
 * - It has at least 8 characters.
 * - It contains at least one lowercase letter.
 * - It contains at least one uppercase letter.
 * - It contains at least one digit.
 * - It contains at least one special character from the string "!@#$%^&*()-+".
 * - It does not contain two identical characters in adjacent positions.
 *
 * Given a string password, return true if it is a strong password. Otherwise, return false.
 *
 * ------------------------------------------------------------------------
 * Time Complexity: O(n)  // iterate through the string once
 * Space Complexity: O(1) // only a few boolean flags used
 * ------------------------------------------------------------------------
 */

class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) return false;

        boolean lower = false, upper = false, digit = false, special = false;
        String specials = "!@#$%^&*()-+";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else if (specials.indexOf(c) >= 0) special = true;

            // check adjacent characters
            if (i > 0 && c == password.charAt(i - 1)) return false;
        }

        return lower && upper && digit && special;
    }
}
