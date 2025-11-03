# Maximum Total Subarray Value I - [Leetcode Link](https://leetcode.com/problems/maximize-subarray-sum/)

## Question Description
You are given an integer array nums of length n and an integer k.

You must select exactly k distinct non-empty subarrays nums[l..r] of nums. Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.

The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r])

The total value is the sum of the values of the selected subarrays.

Return the maximum total value you can achieve.

---

## Constraints
- `1 <= n <= 10^4`
- `1 <= nums[i] <= 10^6`
- `1 <= k <= 10^4`

---

## Approach
To maximize the total, the best difference comes from (global max - global min). Since you must pick k subarrays, the optimal total is simply (max - min) * k. This avoids the need to explicitly construct subarrays.

**Why this approach works:**
- The maximum possible difference for any subarray is always (global max - global min)
- Since we need exactly k subarrays, and we want to maximize the total value
- Using the same maximum difference k times gives the optimal result
- Any other combination would give smaller or equal total value

**Alternative approaches considered:**
- Could try to find k different subarrays with good differences, but would be more complex
- Could use dynamic programming, but O(n*k) might be too slow for large constraints

---

## Dry Run
Example Input: `nums = [1, 2, 3], k = 2`

Step-by-step execution:
- Step 1: Find max = 3, min = 1
- Step 2: Calculate difference = 3 - 1 = 2
- Step 3: Total value = 2 * 2 = 4

Explanation: Choose [1,2,3] with value (3-1)=2, but since we need exactly 2 subarrays and want to maximize, we can choose two subarrays that both achieve this maximum difference.

Final Answer = `4`

Example Input: `nums = [5, 1, 3, 2, 4], k = 3`

Step-by-step execution:
- Step 1: Find max = 5, min = 1
- Step 2: Calculate difference = 5 - 1 = 4
- Step 3: Total value = 4 * 3 = 12

Final Answer = `12`

---

## Solution
```java
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int max = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return (long)(max - min) * k;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single scan to compute max and min of nums
- **Space Complexity:** O(1) - only a few variables used
