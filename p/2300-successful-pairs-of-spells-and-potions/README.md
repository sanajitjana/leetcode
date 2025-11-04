# Successful Pairs of Spells and Potions - [Link](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/)

## Question Description
You are given two positive integer arrays `spells` and `potions`, of lengths `n` and `m` respectively, where `spells[i]` represents the strength of the `ith` spell and `potions[j]` represents the strength of the `jth` potion.

You are also given an integer `success`. A spell and potion pair is considered **successful** if the product of their strengths is **at least** `success`.

Return an integer array `pairs` of length `n`, where `pairs[i]` is the number of potions that will form a successful pair with the `ith` spell.

---

## Constraints
- `n == spells.length`
- `m == potions.length`
- `1 <= n, m <= 10^5`
- `1 <= spells[i], potions[i] <= 10^4`
- `1 <= success <= 10^14`

---

## Approaches

### Approach 1: Brute Force
Check every possible spell-potion pair and count how many satisfy the success condition.

**Algorithm:**
1. For each spell in the spells array
2. For each potion in the potions array
3. Check if `spell[i] * potion[j] >= success`
4. If true, increment the count for that spell
5. Store the count in the result array

---

## Dry Run (Brute Force)
Example Input: `spells = [5,1,3], potions = [1,2,3,4,5], success = 7`

Step-by-step execution:
- For spell=5 (i=0):
  - 5 * 1 = 5 < 7 → skip
  - 5 * 2 = 10 < 7 → skip
  - 5 * 3 = 15 >= 7 → count = 1
  - 5 * 4 = 20 >= 7 → count = 2
  - 5 * 5 = 25 >= 7 → count = 3
  - Result: arr[0] = 3 ✓
- For spell=1 (i=1):
  - 1 * 1 = 1 < 7 → skip
  - 1 * 2 = 2 < 7 → skip
  - 1 * 3 = 3 < 7 → skip
  - 1 * 4 = 4 < 7 → skip
  - 1 * 5 = 5 < 7 → skip
  - Result: arr[1] = 0 ✓
- For spell=3 (i=2):
  - 3 * 1 = 3 < 7 → skip
  - 3 * 2 = 6 < 7 → skip
  - 3 * 3 = 9 >= 7 → count = 1
  - 3 * 4 = 12 >= 7 → count = 2
  - 3 * 5 = 15 >= 7 → count = 3
  - Result: arr[2] = 3 ✓

Final Answer = `[3,0,3]`

---

## Solution (Brute Force)
```java
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int arr[] = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            int count = 0;
            for (int j = 0; j < potions.length; j++) {
                if (spells[i] * potions[j] >= success) {
                    count++;
                }
            }
            arr[i] = count;
        }
        return arr;
    }
}
```

---

## Time and Space Complexity (Brute Force)
- **Time Complexity:** O(n * m) - nested loops iterate through all spell-potion pairs
- **Space Complexity:** O(1) - excluding the output array, only using constant extra space

---

### Approach 2: Binary Search (Optimal)
Sort the potions array first. For each spell, use binary search to find the smallest index where `potions[mid] * spell >= success`. This gives us the first potion that can form a successful pair with the current spell. The number of successful potions is then `m - firstSuccessfulPotionIndex`.

**Algorithm:**
1. Sort the potions array
2. For each spell, use binary search to find the minimum index where the condition is satisfied
3. The count is `m - firstSuccessfulPotionIndex`

This approach works because:
1. Sorting potions allows us to use binary search for efficient lookup
2. For each spell, we need to find how many potions are strong enough
3. Binary search finds the minimum potion index that satisfies the condition
4. All potions from that index to the end will also satisfy the condition

---

## Dry Run (Binary Search)
Example Input: `spells = [5,1,3], potions = [1,2,3,4,5], success = 7`

Sorted potions: `[1,2,3,4,5]`

Step-by-step execution:
- For spell=5: Find smallest index where potions[mid] * 5 >= 7
  - potions[2] * 5 = 3 * 5 = 15 >= 7 ✓
  - potions[1] * 5 = 2 * 5 = 10 >= 7 ✓
  - potions[0] * 5 = 1 * 5 = 5 < 7 ✗
  - First successful index = 1, pairs = 5 - 1 = 4
- For spell=1: Find smallest index where potions[mid] * 1 >= 7
  - potions[4] * 1 = 5 < 7 ✗
  - potions[3] * 1 = 4 < 7 ✗
  - potions[2] * 1 = 3 < 7 ✗
  - potions[1] * 1 = 2 < 7 ✗
  - potions[0] * 1 = 1 < 7 ✗
  - First successful index = 5, pairs = 5 - 5 = 0
- For spell=3: Find smallest index where potions[mid] * 3 >= 7
  - potions[2] * 3 = 3 * 3 = 9 >= 7 ✓
  - potions[1] * 3 = 2 * 3 = 6 < 7 ✗
  - First successful index = 2, pairs = 5 - 2 = 3

Final Answer = `[4,0,3]`

---

## Solution (Binary Search)
```java
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        Arrays.sort(potions);
        int m = potions.length;
        int n = spells.length;

        int[] results = new int[n];

        for (int i = 0; i < n; i++) {
            int currentSpell = spells[i];

            int low = 0;
            int high = m - 1;
            int firstSuccessfulPotionIndex = m;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if ((long) potions[mid] * currentSpell >= success) {
                    firstSuccessfulPotionIndex = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            results[i] = m - firstSuccessfulPotionIndex;
        }

        return results;
    }
}
```

---

## Time and Space Complexity (Binary Search)
- **Time Complexity:** O((n + m) * log m) - O(m log m) for sorting potions + O(n log m) for binary searches
- **Space Complexity:** O(1) - excluding the output array, only using constant extra space
