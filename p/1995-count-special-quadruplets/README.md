# Count Special Quadruplets - [Leetcode Link](https://leetcode.com/problems/count-special-quadruplets/)

## Question Description
You are given a 0-indexed integer array nums. Return the number of distinct quadruplets (a, b, c, d) such that nums[a] + nums[b] + nums[c] == nums[d], and a < b < c < d.

---

## Constraints
- 4 <= nums.length <= 50
- 1 <= nums[i] <= 100

---

## Approach
We need to count all ordered quadruplets (a, b, c, d) where nums[a] + nums[b] + nums[c] == nums[d] and a < b < c < d.

Brute force solution:
- Iterate through all possible quadruplets using 4 nested loops.
- For each (i, j, k, l), compute sum = nums[i] + nums[j] + nums[k].
- If sum == nums[l], increment the count.

Optimization:
- Since nums[i] <= 100, if sum > 100 we can break early (small pruning).
- This helps avoid unnecessary checks.

---

## Dry Run
Example Input: nums = [1,2,3,6]

Step-by-step execution:
- i=0, j=1, k=2, sum=1+2+3=6, l=3, 6==6, count=1
- Other combinations don't match.

Final Answer = 1

---

## Solution
```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    // small pruning: since nums[d] ≤ 100, skip if sum > 100
                    if (sum > 100) continue; 
                    for (int l = k + 1; l < nums.length; l++) {
                        if (sum == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n^4) in the worst case (since we check all quadruplets). With n <= 50, this is acceptable (50^4 = 6.25 million iterations).
- **Space Complexity:** O(1) since we only use a counter.
