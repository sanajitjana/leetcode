# Find the Winner of an Array Game - <a href="https://leetcode.com/problems/find-the-winner-of-an-array-game/" target="_blank">Link</a>

## Question Description
Given an integer array arr of distinct integers and an integer k.

A game will be played between the first two elements of the array (arr[0] and arr[1]). In each round, the larger integer wins and remains at the front of the array, and the smaller integer moves to the end of the array.

The game ends when an integer wins k consecutive rounds.

Return the integer that will win the game.

---

## Constraints
- `2 <= arr.length <= 10^5`
- `1 <= arr[i] <= 10^6`
- `arr` contains distinct integers
- `1 <= k <= 10^9`

---

## Approach
Track the current winner and its consecutive win count. Iterate through the array starting from index 1, comparing each element with the current winner. If current element is larger, update winner and reset count to 1. Otherwise, increment the count. If count reaches k, return the current winner.

**Why this approach works:**
- The maximum element will eventually dominate and win all remaining rounds
- We don't need to simulate all rounds when k is large - once an element wins k consecutive times, it's the winner
- For k >= array length - 1, the maximum element will always be the winner

**Alternative approaches considered:**
- Could simulate the entire game, but that would be inefficient for large k
- Could find the maximum element directly when k is large, but need to handle consecutive wins properly

---

## Dry Run
Example Input: `arr = [2, 1, 3, 5, 4, 6, 7], k = 3`

Step-by-step execution:
- Step 1: Start with ans = 2, count = 0
- Step 2: Compare 1 vs 2: 2 wins, count = 1
- Step 3: Compare 3 vs 2: 3 wins, ans = 3, count = 1
- Step 4: Compare 5 vs 3: 5 wins, ans = 5, count = 1
- Step 5: Compare 4 vs 5: 5 wins, count = 2
- Step 6: Compare 6 vs 5: 6 wins, ans = 6, count = 1
- Step 7: Compare 7 vs 6: 7 wins, ans = 7, count = 1

Final Answer = `7` (Note: In actual game, 7 would continue winning all rounds)

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the array in worst case
- **Space Complexity:** O(1) - only using constant extra space
  
[Back to All Problems](../README.md) 
