# Two Sum - <a href="https://leetcode.com/problems/two-sum/" target="_blank">Link</a>

## Question Description
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`. You may assume that each input would have exactly one solution, and you may not use the same element twice.

---

## Constraints
- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Only one valid answer exists.

---

## Approach
Use a HashMap to store visited numbers and their indices. For each number, check if `target - current` exists in the map. If it does, return the stored index and current index. Otherwise, store the current number with its index.

This approach works because we need to find two numbers that sum to target, and using a hashmap allows O(1) lookups for the complement. Alternative approaches like brute force (O(n²)) would be too slow for large inputs, and sorting would require additional complexity to track original indices.

---

## Dry Run
Example Input: `nums = [2,7,11,15], target = 9`

Step-by-step execution:
- i=0, nums[0]=2, complement=7, map is empty, put (2,0) in map
- i=1, nums[1]=7, complement=2, map contains 2, return [0,1]

Final Answer = `[0,1]`

---

## Solution
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(n) - hashmap stores up to n elements in worst case
  
[Back to All Problems](../README.md) 
