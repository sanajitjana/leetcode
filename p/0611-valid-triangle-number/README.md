# Valid Triangle Number - [Leetcode Link](https://leetcode.com/problems/valid-triangle-number/)

## Question Description
Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Conditions for a valid triangle:
- The sum of any two sides must be greater than the third side
- For three numbers a, b, c: a + b > c, a + c > b, b + c > a

---

## Constraints
- `1 <= nums.length <= 1000`
- `0 <= nums[i] <= 1000`

---

## Approach
Multiple approaches are provided, from brute force to optimized:

**Approach 1: Brute Force (3 nested loops)**
- Check all possible triplets (i, j, k)
- For each triplet, test the triangle inequality conditions

**Approach 2: Sort + Triple Nested Loop**
- Sort the array first
- Because sorted array ensures nums[i] <= nums[j] <= nums[k], only need to check: nums[i] + nums[j] > nums[k]

**Approach 3: Sort + Two Pointers (Optimized)**
- Sort the array
- Fix the largest side (nums[i])
- Use two pointers (j, k) to count valid pairs
- If nums[j] + nums[k] > nums[i], then all elements between j..k-1 with nums[k] also form valid triangles

**Why this approach works:**
- Sorting helps reduce the number of conditions to check
- Two pointers approach is most efficient for this problem
- Takes advantage of sorted order to count multiple valid combinations efficiently

**Alternative approaches considered:**
- Could use binary search instead of two pointers, but two pointers is more efficient

---

## Dry Run
Example Input: `nums = [2, 2, 3, 4]`

**Approach 3 (Optimized) Dry Run:**
- Step 1: Sort array -> [2, 2, 3, 4]
- Step 2: Fix i=3 (nums[i]=4), j=0, k=2
- Step 3: nums[0]+nums[2]=5 > 4 -> count += 2 (k-j=2), k=1
- Step 4: nums[0]+nums[1]=4 !> 4 -> j=1
- Step 5: Done. Count=2

Final Answer = `2`

---

## Solution
```java
/**
 * --------------------------------------------------------------------------- 
 * Approach 1: Brute Force (3 nested loops)
 * ---------------------------------------------------------------------------
 */
class SolutionBruteForce {
    public int triangleNumber(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];
                    if ((a + b > c) && (a + c > b) && (b + c > a)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

/**
 * ---------------------------------------------------------------------------
 * Approach 2: Sort + Triple Nested Loop
 * ---------------------------------------------------------------------------
 */
class SolutionSortedTriple {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

/**
 * ---------------------------------------------------------------------------
 * Approach 3: Sort + Two Pointers (Optimized)
 * ---------------------------------------------------------------------------
 */
class SolutionOptimized {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int j = 0, k = i - 1;
            while (j < k) {
                if (nums[j] + nums[k] > nums[i]) {
                    count += k - j;
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n^2) for optimized approach (sorting O(n log n) negligible)
- **Space Complexity:** O(1) - only using constant extra space
