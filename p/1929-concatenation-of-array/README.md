# 1929. Concatenation of Array

**Difficulty:** Easy

**Topics:** Arrays

**Link:** https://leetcode.com/problems/concatenation-of-array/description/

## Problem Description

Given an integer array `nums` of length `n`, you want to create an array `ans` of length `2n` where `ans[i] == nums[i]` and `ans[i + n] == nums[i]` for `0 <= i < n` (0-indexed).

Specifically, `ans` is the concatenation of two `nums` arrays.

Return the array `ans`.

## Examples

**Example 1:**
```
Input: nums = [1,2,1]
Output: [1,2,1,1,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
- ans = [1,2,1,1,2,1]
```

**Example 2:**
```
Input: nums = [1,3,2,1]
Output: [1,3,2,1,1,3,2,1]
Explanation: The array ans is formed as follows:
- ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]]
- ans = [1,3,2,1,1,3,2,1]
```

## Constraints

- `n == nums.length`
- `1 <= n <= 1000`
- `1 <= nums[i] <= 1000`

## Solutions

### Solution 1: Using ArrayList (Basic Approach)
```java
class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        for (int num : nums) {
            list.add(num);
        }
        int[] ans = new int[2 * n];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

### Solution 2: Direct Array Assignment (Optimized)
```java
class Solution {
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            ans[index++] = nums[i];
        } 
        for (int i = 0; i < nums.length; i++) {
            ans[index++] = nums[i];
        }  
        return ans;      
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

### Solution 3: Single Loop Assignment (Most Optimal)
```java
class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];

        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[n + i] = nums[i];
        }
        return result;
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

### Solution 4: Using System.arraycopy (Java Standard Library)
```java
class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n * 2];
        System.arraycopy(nums, 0, ans, 0, n);
        System.arraycopy(nums, 0, ans, n, n);
        return ans;      
    }
}
```

**Time Complexity:** O(n)  
**Space Complexity:** O(n)

## Explanation

The problem asks for the concatenation of an array with itself. All solutions follow the same fundamental approach:

1. Create a result array of size `2n` (twice the length of the input array)
2. Fill the first `n` positions with the original array elements
3. Fill the next `n` positions with the same array elements again

**Solution 1** uses an ArrayList as an intermediate step, which is less efficient due to the overhead of wrapper classes and additional copying.

**Solution 2** directly fills the result array using an index counter, which is more efficient than Solution 1.

**Solution 3** is the most optimal as it uses a single loop to assign both parts of the result array simultaneously.

**Solution 4** leverages Java's built-in `System.arraycopy()` method, which is implemented natively and can be highly optimized by the JVM.

All solutions have the same time complexity O(n) and space complexity O(n), but Solution 3 is generally preferred for its simplicity and efficiency.