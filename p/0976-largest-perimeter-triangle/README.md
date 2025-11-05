# Largest Perimeter Triangle - <a href="https://leetcode.com/problems/largest-perimeter-triangle/" target="_blank">Link</a>

## Question Description
Given an array `nums` of non-negative integers, return the **largest perimeter** of a triangle with **non-zero area** that can be formed from three of these lengths.

If it is impossible to form any triangle with non-zero area, return `0`.

A triangle is valid if the sum of any two sides is **greater than** the third side.

---

## Constraints
- `3 <= nums.length <= 10^4`
- `1 <= nums[i] <= 10^6`

---

## Approaches

### Approach 1: Brute Force (Check All Triplets)
Check every possible combination of three numbers to find the largest perimeter triangle.

**Algorithm:**
1. Sort the array in ascending order
2. Use three nested loops to check all possible triplets (i, j, k)
3. For each triplet, check triangle inequality conditions:
   - `nums[i] + nums[j] > nums[k]`
   - `nums[i] + nums[k] > nums[j]`
   - `nums[j] + nums[k] > nums[i]`
4. If all conditions satisfied, calculate perimeter and track maximum
5. Return the maximum perimeter found, or 0 if no valid triangle

---

## Dry Run (Brute Force)
Example Input: `nums = [2, 1, 2]`

Sorted: `[1, 2, 2]`

Step-by-step execution:
- Triplet (1,2,2):
  - 1 + 2 > 2 (3 > 2) ✓
  - 1 + 2 > 2 (3 > 2) ✓
  - 2 + 2 > 1 (4 > 1) ✓
  - Valid triangle! Perimeter = 1 + 2 + 2 = 5
  - max = 5

No other triplets to check.

Result: `5`

---

## Solution (Brute Force)
```java
class SolutionBruteForce {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int x = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int y = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int z = nums[k];
                    if (x + y > z && x + z > y && y + z > x) {
                        max = Math.max(max, x + y + z);
                    }
                }
            }
        }
        return max;
    }
}
```

---

## Time and Space Complexity (Brute Force)
- **Time Complexity:** O(n³) - three nested loops check all combinations
- **Space Complexity:** O(1) - only using constant extra space

---

### Approach 2: Optimized Greedy (Check from Largest)
Sort the array and check consecutive triplets starting from the largest numbers.

**Algorithm:**
1. Sort the array in ascending order
2. Start from the largest numbers (end of sorted array)
3. For each possible triplet (i, i-1, i-2) where i goes from n-1 down to 2:
   - Check if `nums[i-2] + nums[i-1] > nums[i]`
   - If true, return the perimeter immediately (this will be maximum)
   - If false, continue checking smaller triplets
4. If no valid triangle found, return 0

---

## Dry Run (Optimized)
Example Input: `nums = [2, 1, 2]`

Sorted: `[1, 2, 2]`

Step-by-step execution:
- i = 2 (largest position):
  - Check nums[0] + nums[1] > nums[2]
  - 1 + 2 > 2 (3 > 2) ✓
  - Valid triangle! Perimeter = 1 + 2 + 2 = 5
  - Return 5 immediately

**Why this works:**
- The largest perimeter will always use the largest possible sides
- After sorting, the maximum perimeter triangle must include the largest number
- We check triplets including the largest number first
- If `nums[i-2] + nums[i-1] > nums[i]` for the largest i, that's the maximum perimeter
- If not, we need to check smaller numbers

---

## Solution (Optimized)
```java
class SolutionOptimized {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i] + nums[i - 1] + nums[i - 2];
            }
        }
        return 0;
    }
}
```

---

## Time and Space Complexity (Optimized)
- **Time Complexity:** O(n log n) - sorting dominates the single pass
- **Space Complexity:** O(1) - only using constant extra space

---

## Comparison of Approaches

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Brute Force** | O(n³) | O(1) | Small arrays (n ≤ 100) |
| **Optimized** | O(n log n) | O(1) | Large arrays (n ≤ 10^4) |

**Key Insight:** After sorting, the maximum perimeter triangle must include the largest number. We only need to check consecutive triplets starting from the largest numbers.

**Triangle Inequality Theorem:** For three lengths a ≤ b ≤ c to form a triangle, we need a + b > c.

**Greedy Strategy:** The optimal triangle will always be among the largest possible numbers that satisfy the triangle inequality.
