# How Many Numbers Are Smaller Than the Current Number - <a href="https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/" target="_blank">Link</a>

## Question Description
Given the array `nums`, for each `nums[i]` find out how many numbers in the array are smaller than it. That is, for each `nums[i]` you have to count the number of valid j's such that `j != i` and `nums[j] < nums[i]`.

Return the answer in an array.

---

## Constraints
- `2 <= nums.length <= 500`
- `0 <= nums[i] <= 100`

---

## Approach 1: Brute Force

### Explanation
Use a brute force approach with nested loops. For each element at index `i`, iterate through all other elements and count how many are smaller than `nums[i]`.

This approach works because we need to compare each element with every other element in the array. Given the constraints (array length up to 500), the O(n²) time complexity is acceptable.

### Dry Run
Example Input: `nums = [8,1,2,2,3]`

Step-by-step execution:
- i=0, nums[0]=8: Compare with [1,2,2,3], count=4 (all are smaller)
- i=1, nums[1]=1: Compare with [8,2,2,3], count=0 (none are smaller)
- i=2, nums[2]=2: Compare with [8,1,2,3], count=1 (only 1 is smaller)
- i=3, nums[3]=2: Compare with [8,1,2,3], count=1 (only 1 is smaller)
- i=4, nums[4]=3: Compare with [8,1,2,2], count=3 (1,2,2 are smaller)

Final Answer = `[4,0,1,1,3]`

### Solution
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

### Complexity Analysis
- **Time Complexity:** O(n²) - nested loops comparing each element with every other element
- **Space Complexity:** O(n) - output array of size n (excluding input)

---

## Approach 2: Counting Sort with Prefix Sum

### Explanation
Since the values in the array are constrained to the range `[0, 100]`, we can use a counting sort approach combined with prefix sums to achieve linear time complexity.

The algorithm works in three steps:
1. **Count frequencies**: Create a count array of size 101 (to cover values 0-100) and count the frequency of each number in the input array.
2. **Build prefix sum**: Transform the count array into a prefix sum array where `count[i]` represents the total count of numbers less than or equal to `i`.
3. **Build result**: For each number in the original array, the count of smaller numbers is `count[nums[i] - 1]` (or 0 if `nums[i]` is 0).

This approach leverages the fact that the prefix sum at index `i-1` gives us exactly how many numbers are strictly less than `i`.

### Dry Run
Example Input: `nums = [8,1,2,2,3]`

**Step 1: Count frequencies**
```
count[1] = 1  (one 1)
count[2] = 2  (two 2s)
count[3] = 1  (one 3)
count[8] = 1  (one 8)
All other indices = 0
```

**Step 2: Build prefix sum**
```
count[0] = 0
count[1] = 0 + 1 = 1  (one number ≤ 1)
count[2] = 1 + 2 = 3  (three numbers ≤ 2)
count[3] = 3 + 1 = 4  (four numbers ≤ 3)
count[4] = 4 + 0 = 4
...
count[8] = 4 + 1 = 5  (five numbers ≤ 8)
```

**Step 3: Build result**
```
nums[0] = 8: res[0] = count[7] = 4  (4 numbers smaller than 8)
nums[1] = 1: res[1] = count[0] = 0  (0 numbers smaller than 1)
nums[2] = 2: res[2] = count[1] = 1  (1 number smaller than 2)
nums[3] = 2: res[3] = count[1] = 1  (1 number smaller than 2)
nums[4] = 3: res[4] = count[2] = 3  (3 numbers smaller than 3)
```

Final Answer = `[4,0,1,1,3]`

### Solution
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];

        // Step 1: Count frequency of each number
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        // Step 2: Build prefix sum - count[i] will store count of numbers <= i
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build result using prefix sum
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res[i] = 0;  // No number can be smaller than 0
            } else {
                res[i] = count[nums[i] - 1];  // Count of numbers < nums[i]
            }
        }
        return res;
    }
}
```

### Complexity Analysis
- **Time Complexity:** O(n + k) where n is the length of the array and k is the range of values (101 in this case). We iterate through the array once to count frequencies, once through the range to build prefix sums, and once more through the array to build the result.
- **Space Complexity:** O(k) - the count array of size 101 (constant space since k is fixed at 101), plus O(n) for the output array.  
[Back to All Problems](../README.md) 
