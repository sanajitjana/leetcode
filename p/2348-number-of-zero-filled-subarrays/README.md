# Number of Zero-Filled Subarrays - [Link](https://leetcode.com/problems/number-of-zero-filled-subarrays/)

## Question Description
Given an integer array nums, return the number of subarrays filled with 0.

A subarray is a contiguous non-empty sequence of elements within an array.

---

## Constraints
- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach
Two approaches are provided:

**Approach 1: Brute Force**
- Generate all possible subarrays using two loops
- For each subarray, check if all elements are zero
- Convert subarray to string and check if all characters are '0'

**Approach 2: Optimized (Mathematical Counting)**
- For a streak of k consecutive zeros, number of zero-subarrays is k*(k+1)/2
- Traverse array and keep track of consecutive zeros
- When non-zero encountered, add contribution of current streak
- Handle final streak after loop ends

**Why this approach works:**
- Mathematical formula k*(k+1)/2 gives count of all possible subarrays in a streak of k zeros
- Much more efficient than generating all subarrays
- Single pass through array is sufficient

**Alternative approaches considered:**
- Could use sliding window, but mathematical approach is most efficient

---

## Dry Run
Example Input: `nums = [0, 0, 1, 0]`

**Approach 2 Dry Run:**
- Step 1: Start with count = 0, streak = 0
- Step 2: i=0, nums[0]=0, streak=1
- Step 3: i=1, nums[1]=0, streak=2
- Step 4: i=2, nums[2]=1, count += (2*3)/2 = 3, streak=0
- Step 5: i=3, nums[3]=0, streak=1
- Step 6: End of loop, count += (1*2)/2 = 1
- Step 7: Total count = 4

Final Answer = `4`

---

## Solution
```java
/**
 * ---------------------
 * Approach 1: Brute Force
 * ---------------------
 */
class SolutionBruteForce {
    public long zeroFilledSubarray(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            String str = "";
            for (int j = i; j < nums.length; j++) {
                str += nums[j] + "";
                boolean flag = true;
                for (int k = 0; k < str.length(); k++) {
                    if (str.charAt(k) != '0') {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    count++;
                }
            }
        }
        return count;
    }
}

/**
 * ---------------------
 * Approach 2: Optimized (Mathematical Counting)
 * ---------------------
 */
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        long streak = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                streak++;
            } else {
                count += (streak * (streak + 1)) / 2;
                streak = 0;
            }
        }
        // Handle streak at the end
        if (streak > 0) {
            count += (streak * (streak + 1)) / 2;
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) for optimized approach, O(n^3) for brute force
- **Space Complexity:** O(1) for both approaches
