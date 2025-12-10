# Count Special Triplets - <a href="https://leetcode.com/problems/count-special-triplets/" target="_blank">Link</a>

## Question Description
You are given an integer array nums.

A special triplet is defined as a triplet of indices (i, j, k) such that:

    0 <= i < j < k < n, where n = nums.length
    nums[i] == nums[j] * 2
    nums[k] == nums[j] * 2

Return the total number of special triplets in the array.

Since the answer may be large, return it modulo 10^9 + 7.

---

## Constraints
- 3 <= n == nums.length <= 10^5
- 0 <= nums[i] <= 10^5

---

## Approach 1: Brute Force
Iterate through all possible triplets (i, j, k) with i < j < k, and check if nums[i] == nums[j] * 2 and nums[k] == nums[j] * 2. If yes, increment the count modulo MOD.

This is straightforward but inefficient for large n due to the cubic time complexity.

### Dry Run (Approach 1)
Example Input: nums = [6,3,6]

- Triplets:
  - (0,1,2): nums[0]=6, nums[1]=3, nums[2]=6; 6==3*2 and 6==3*2, yes, count=1
- No other triplets.
- Final count = 1

### Solution (Approach 1)
```java
class Solution {
    static final int MOD = 1000000007;

    public int specialTriplets(int[] nums) {
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] == nums[j] * 2 && nums[k] == nums[j] * 2) {
                        count = (count + 1) % MOD;
                    }
                }
            }
        }
        return (int) count;
    }
}
```

### Time and Space Complexity (Approach 1)
- **Time Complexity:** O(n^3) - three nested loops
- **Space Complexity:** O(1) - no extra space used

---

## Approach 2: Optimized with Frequency Maps
To solve this efficiently, we use frequency maps. First, compute the total frequency of each number in the array. Then, iterate through the array, maintaining a frequency map of numbers seen so far (left of current j). For each j, calculate the number of i < j where nums[i] == 2 * nums[j] (from the left frequency map) and the number of k > j where nums[k] == 2 * nums[j] (total frequency minus left frequency, adjusted if nums[j] equals the target). Multiply these counts and add to the result, taking modulo at each step. Finally, update the left frequency map with nums[j].

This approach ensures O(n) time complexity by avoiding nested loops, suitable for n up to 10^5.

### Dry Run (Approach 2)
Example Input: nums = [6,3,6]

- Total frequencies: 6:2, 3:1
- Initialize left_freq empty, count = 0
- j=0, nums[0]=6, target=12, left_count=0 (12 not in left), right_count=0 (total 0 for 12), add 0*0=0
- Update left_freq: 6:1
- j=1, nums[1]=3, target=6, left_count=1 (6 in left), right_count=2-1-0=1 (since nums[1]!=6), add 1*1=1, count=1
- Update left_freq: 6:1, 3:1
- j=2, nums[2]=6, target=12, left_count=0, right_count=0, add 0
- Final count = 1

Matches the example.

### Solution (Approach 2)
```java
class Solution {
    static final int MOD = 1000000007;

    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> totalFreq = new HashMap<>();
        for (int num : nums) {
            totalFreq.put(num, totalFreq.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> leftFreq = new HashMap<>();
        long count = 0;
        for (int j = 0; j < nums.length; j++) {
            int x = nums[j];
            int target = 2 * x;
            long leftCount = leftFreq.getOrDefault(target, 0);
            long rightCount = totalFreq.getOrDefault(target, 0) - leftCount - (x == target ? 1 : 0);
            count = (count + leftCount * rightCount % MOD) % MOD;
            leftFreq.put(x, leftFreq.getOrDefault(x, 0) + 1);
        }
        return (int) count;
    }
}
```

### Time and Space Complexity (Approach 2)
- **Time Complexity:** O(n) - single pass through the array with O(1) map operations on average
- **Space Complexity:** O(n) - hashmaps store up to n elements in worst case  
[Back to All Problems](../README.md) 
