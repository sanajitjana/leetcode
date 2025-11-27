# How Many Numbers Are Smaller Than the Current Number - <a href="https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/" target="_blank">Link</a>

## Question Description
Given the array `nums`, for each `nums[i]` find out how many numbers in the array are smaller than it. That is, for each `nums[i]` you have to count the number of valid j's such that `j != i` and `nums[j] < nums[i]`.

Return the answer in an array.

---

## Constraints
- `2 <= nums.length <= 500`
- `0 <= nums[i] <= 100`

---

## Approach
Use a brute force approach with nested loops. For each element at index `i`, iterate through all other elements and count how many are smaller than `nums[i]`.

This approach works because we need to compare each element with every other element in the array. Given the constraints (array length up to 500), the O(n²) time complexity is acceptable.

An optimized approach could use counting sort (since values are 0-100) to achieve O(n + k) time complexity, where k is the range of values.

---

## Dry Run
Example Input: `nums = [8,1,2,2,3]`

Step-by-step execution:
- i=0, nums[0]=8: Compare with [1,2,2,3], count=4 (all are smaller)
- i=1, nums[1]=1: Compare with [8,2,2,3], count=0 (none are smaller)
- i=2, nums[2]=2: Compare with [8,1,2,3], count=1 (only 1 is smaller)
- i=3, nums[3]=2: Compare with [8,1,2,3], count=1 (only 1 is smaller)
- i=4, nums[4]=3: Compare with [8,1,2,2], count=3 (1,2,2 are smaller)

Final Answer = `[4,0,1,1,3]`

---

## Solution
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] < nums[i]) {
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n²) - nested loops comparing each element with every other element
- **Space Complexity:** O(n) - output array of size n (excluding input)