// LeetCode Problem: 3021. Alice and Bob Playing Flower Game
// https://leetcode.com/problems/alice-and-bob-playing-flower-game/
//
// Description:
// Alice and Bob have flowers in a garden. Alice has n flowers and Bob has m flowers.
// They play a game where in each round, they both pick one flower each. If the sum of
// the number of petals of their flowers is odd, Alice wins. Otherwise, Bob wins.
// Return the total number of rounds where Alice wins.
//
// Example 1:
// Input: n = 3, m = 2
// Output: 3
//
// Example 2:
// Input: n = 1, m = 1
// Output: 0
//
// Constraints:
// 1 <= n, m <= 10^5

class Solution {
    public long flowerGame(int n, int m) {
        return (long) n * m / 2;
    }
}

// For local testing
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.flowerGame(3, 2)); // Expected: 3
        System.out.println(sol.flowerGame(1, 1)); // Expected: 0
        System.out.println(sol.flowerGame(5, 4)); // Quick check
    }
}
