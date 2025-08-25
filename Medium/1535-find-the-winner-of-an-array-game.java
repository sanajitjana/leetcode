/**
 * LeetCode Problem: 1535. Find the Winner of an Array Game
 * 
 * Description:
 * Given an integer array arr of distinct integers and an integer k.
 * A game will be played between the first two elements of the array (arr[0] and arr[1]).
 * In each round, the larger integer wins and remains at the front of the array, 
 * and the smaller integer moves to the end of the array.
 * 
 * The game ends when an integer wins k consecutive rounds.
 * Return the integer that will win the game.
 * 
 * Constraints:
 *  - 2 <= arr.length <= 10^5
 *  - 1 <= arr[i] <= 10^6
 *  - arr contains distinct integers
 *  - 1 <= k <= 10^9
 */

class Solution {
    public int getWinner(int[] arr, int k) {
        int ans = arr[0];
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > ans) {
                ans = arr[i];
                count = 1;
            } else {
                count++;
            }
            if (count == k)
                return ans;
        }
        return ans;
    }
}
