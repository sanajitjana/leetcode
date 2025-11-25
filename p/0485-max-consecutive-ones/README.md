# Max Consecutive Ones - <a href="https://leetcode.com/problems/max-consecutive-ones/" target="_blank">Link</a>

## Question Description
Given a binary array `nums`, return the maximum number of consecutive `1`'s in the array.

---

## Constraints
- `1 <= nums.length <= 10^5`
- `nums[i]` is either `0` or `1`

---

## Approach
Use a sliding window approach with two counters:
1. `freq` - tracks the current count of consecutive 1's
2. `count` - tracks the maximum count seen so far

For each element in the array:
- If it's `1`, increment `freq`
- If it's `0`, reset `freq` to `0`
- Update `count` with the maximum of `count` and `freq`

This approach works because we only need to track the current streak of consecutive 1's and the maximum streak encountered. When we encounter a 0, the current streak breaks and we start counting from 0 again.

---

## Dry Run
Example Input: `nums = [1,1,0,1,1,1]`

Step-by-step execution:
- i=0, nums[0]=1, freq=1, count=1
- i=1, nums[1]=1, freq=2, count=2  
- i=2, nums[2]=0, freq=0, count=2
- i=3, nums[3]=1, freq=1, count=2
- i=4, nums[4]=1, freq=2, count=2
- i=5, nums[5]=1, freq=3, count=3

Final Answer = `3`

---

## Solution
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count=0;
        int freq=0;
        for(int i=0; i<nums.length; i++){
            if(nums[i]==1) freq++;
            else freq=0;
            count=Math.max(count, freq);
        }   
        return count;     
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - constant space usage with just two integer variables