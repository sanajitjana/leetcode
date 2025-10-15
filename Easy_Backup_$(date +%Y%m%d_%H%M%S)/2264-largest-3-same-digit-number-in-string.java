/**
 * Problem: 2264. Largest 3-Same-Digit Number in String
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/largest-3-same-digit-number-in-string/
 *
 * ---------------------
 * Approach 1: Brute Force (Substring + Parse)
 * ---------------------
 * - Iterate through the string and check if three consecutive characters are the same.
 * - If so, parse them as a number and track the maximum.
 * 
 * Time Complexity:  O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public String largestGoodInteger(String num) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < num.length() - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) &&
                num.charAt(i + 1) == num.charAt(i + 2)) {
                
                long val = Long.parseLong(num.substring(i, i + 3));
                if (val > max) max = val;
            }
        }
        if (max == 0) return "000";
        return max > Long.MIN_VALUE ? String.valueOf(max) : "";
    }
}

/**
 * ---------------------
 * Approach 2: Brute Force with Counting
 * ---------------------
 * - Iterate through the string with a counter for consecutive identical digits.
 * - When count reaches 3, update the max digit.
 * - Avoid parsing; directly compare characters for efficiency.
 * 
 * Time Complexity:  O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public String largestGoodInteger(String num) {
        int count = 0;
        char max = ' ';
        char prev = ' ';
        
        for (char ch : num.toCharArray()) {
            if (ch == prev) count++;
            else count = 1;
            
            if (count == 3) {
                max = (char) Math.max(max, ch);
            }
            prev = ch;
        }
        return max == ' ' ? "" : String.valueOf(max).repeat(3);
    }
}

/**
 * ---------------------
 * Approach 3: Optimal (Predefined Candidates)
 * ---------------------
 * - Pre-store all possible valid 3-digit identical numbers from largest to smallest.
 * - Check if the input contains each one; return the first match.
 * - Since the array is constant size (10 elements), this is very fast.
 * 
 * Time Complexity:  O(n)   [10 × O(n) => O(n)]
 * Space Complexity: O(1)
 */
class Solution {
    public String largestGoodInteger(String num) {
        String[] candidates = { "999", "888", "777", "666", "555", 
                                 "444", "333", "222", "111", "000" };
        for (String c : candidates) {
            if (num.contains(c)) return c;
        }
        return "";
    }
}
