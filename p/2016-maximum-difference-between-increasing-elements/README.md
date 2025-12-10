# Maximum Difference Between Increasing Elements - <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/" target="_blank">Link</a>

## Question Description
Given a **0-indexed** integer array `nums` of size `n`, find the **maximum difference** between any two **increasing elements** `nums[j] - nums[i]` where `i < j` and `nums[i] < nums[j]`.

If no such pair exists, return `-1`.

An **increasing element** means that `nums[i] < nums[j]` for indices `i < j`.

---

## Constraints
- `n == nums.length`
- `2 <= n <= 1000`
- `1 <= nums[i] <= 10^9`

---

## Approaches

### Approach 1: Two-Pointer Technique
Use two pointers to track the minimum element seen so far and find the maximum difference with later elements.

**Algorithm:**
1. Initialize `left = 0` and `right = 1`
2. Initialize `max = -1` to store maximum difference
3. While `right < nums.length` and `left < nums.length`:
   - If `nums[left] < nums[right]`: calculate difference and update max
   - If `nums[left] >= nums[right]`: move `left` to `right` (new minimum candidate)
   - Increment `right`
4. Return `max`

---

## Dry Run (Two-Pointer)
Example Input: `nums = [7, 1, 5, 4]`

Step-by-step execution:
- left=0, right=1, nums[0]=7, nums[1]=1
- 7 >= 1, so left = 1 (move to smaller element)
- right=2, nums[1]=1, nums[2]=5
- 1 < 5, diff = 5-1 = 4, max = 4
- right=3, nums[1]=1, nums[3]=4
- 1 < 4, diff = 4-1 = 3, max = 4 (4 > 3)
- right=4, end of loop

Result: `4`

---

## Solution (Two-Pointer)
```java
class Solution {
    public int maximumDifference(int[] nums) {
        int max = -1;
        int left = 0;
        int right = 1;

        while (right < nums.length && left < nums.length) {
            if (nums[left] < nums[right]) {
                int diff = nums[right] - nums[left];
                max = Math.max(max, diff);
            } else {
                left = right; // move left to the new minimum
            }
            right++;
        }

        return max;
    }
}
```

---

## Time and Space Complexity (Two-Pointer)
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - only using constant extra space

---

### Approach 2: Track Minimum Element (More Intuitive)
Track the minimum element seen so far and calculate the maximum difference with each subsequent element.

**Algorithm:**
1. Initialize `min = nums[0]` and `max = -1`
2. For each element from index 1 to n-1:
   - Update `min = min(min, nums[i])`
   - If `min < nums[i]`, calculate difference and update `max`
3. Return `max`

---

## Dry Run (Track Minimum)
Example Input: `nums = [7, 1, 5, 4]`

Step-by-step execution:
- i=0: min=7, max=-1
- i=1: min=min(7,1)=1, 1 < 1? No, max=-1
- i=2: min=min(1,5)=1, 1 < 5? Yes, diff=5-1=4, max=4
- i=3: min=min(1,4)=1, 1 < 4? Yes, diff=4-1=3, max=4 (4 > 3)

Result: `4`

**Why this works:**
- For each element, we want the maximum difference where it's larger than some earlier element
- The minimum element seen so far gives the best chance for maximum difference
- We update minimum whenever we find a smaller element

---

## Solution (Track Minimum)
```java
class Solution {
    public int maximumDifference(int[] nums) {
        int min = nums[0];
        int max = -1;
        for(int i = 1; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            if(min < nums[i]){
                max = Math.max(max, nums[i] - min);
            }
        }
        return max;
    }
}
```

---

## Time and Space Complexity (Track Minimum)
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - only using constant extra space

---

## Comparison of Approaches

| Approach | Time Complexity | Space Complexity | Readability | Intuition |
|----------|----------------|------------------|-------------|-----------|
| **Two-Pointer** | O(n) | O(1) | Medium | Tracks minimum with pointers |
| **Track Minimum** | O(n) | O(1) | High | Explicitly tracks minimum |

**Key Insight:** Both approaches solve the same problem: find the maximum `nums[j] - nums[i]` where `i < j` and `nums[i] < nums[j]`.

**Edge Cases:**
- All elements decreasing: return -1
- All elements equal: return -1
- Only two elements: return positive difference if nums[1] > nums[0], else -1

**Why O(n) is optimal:** We must examine each element at least once to find the global minimum and maximum difference.

**Alternative Brute Force (O(n²)):**
```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (nums[j] > nums[i]) {
            max = Math.max(max, nums[j] - nums[i]);
        }
    }
}
```
This works but is too slow for n ≤ 1000.
  
[Back to All Problems](../README.md) 
