# Find Minimum Operations to Make All Elements Divisible by Three - <a href="https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/" target="_blank">Link</a>

## Question Description
You are given an integer array `nums`. In one operation, you can add or subtract 1 from any element of `nums`.

Return the minimum number of operations to make all elements of `nums` divisible by 3.

---

## Constraints
- `1 <= nums.length <= 50`
- `1 <= nums[i] <= 50`

---

## Example 1:
```
Input: nums = [1,2,3,4]
Output: 3

Explanation:
All array elements can be made divisible by 3 using 3 operations:
    Subtract 1 from 1.
    Add 1 to 2.
    Subtract 1 from 4.
```

## Example 2:
```
Input: nums = [3,6,9]
Output: 0
```

---

## Approach

### Mathematical Insight
For any number `x`, we want to make it divisible by 3 with minimum operations. The remainder when `x` is divided by 3 can be:
- `0`: Already divisible by 3, no operations needed
- `1`: Can either subtract 1 (to get 0) or add 2 (to get 3), so minimum is `min(1, 2) = 1`
- `2`: Can either add 1 (to get 3) or subtract 2 (to get 0), so minimum is `min(1, 2) = 1`

The formula `Math.min(x % 3, 3 - x % 3)` gives us the minimum operations for each element.

### Algorithm
1. Initialize a counter to 0
2. For each element in the array:
   - Calculate the remainder when divided by 3
   - Add the minimum of (remainder, 3 - remainder) to the counter
3. Return the total counter value

---

## Dry Run
Example: `nums = [1,2,3,4]`

- Element 1: `1 % 3 = 1`, `min(1, 3-1) = min(1, 2) = 1` → operations += 1
- Element 2: `2 % 3 = 2`, `min(2, 3-2) = min(2, 1) = 1` → operations += 1
- Element 3: `3 % 3 = 0`, `min(0, 3-0) = min(0, 3) = 0` → operations += 0
- Element 4: `4 % 3 = 1`, `min(1, 3-1) = min(1, 2) = 1` → operations += 1

Total operations = 1 + 1 + 0 + 1 = 3 ✓

---

## Solution
```java
class Solution {
    public int minimumOperations(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            count += Math.min(ele % 3, 3 - ele % 3);
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - Single pass through the array
- **Space Complexity:** O(1) - Constant extra space