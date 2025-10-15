# [Type of Triangle](https://leetcode.com/problems/type-of-triangle/)

## Question Description
You are given a **0-indexed** integer array `nums` of size `3` representing the side lengths of a triangle.

Return the type of the triangle:
- `"equilateral"` if all sides are equal
- `"isosceles"` if exactly two sides are equal
- `"scalene"` if all sides are different
- `"none"` if the given sides cannot form a valid triangle

---

## Constraints
- `nums.length == 3`
- `1 <= nums[i] <= 100`

---

## Approach
Check triangle inequality first, then classify the triangle based on side length equality.

**Algorithm:**
1. Extract the three sides: `s1`, `s2`, `s3`
2. **Triangle Inequality Check**: Verify all three conditions:
   - `s1 + s2 > s3`
   - `s1 + s3 > s2`
   - `s2 + s3 > s1`
3. If any condition fails, return `"none"`
4. **Classification**:
   - If `s1 == s2 == s3`, return `"equilateral"`
   - Else if `s1 == s2 || s2 == s3 || s1 == s3`, return `"isosceles"`
   - Otherwise, return `"scalene"`

This approach works because:
- Triangle inequality ensures the sides can form a closed triangle
- Side equality determines the specific type
- Order of checks matters: inequality first, then equality

---

## Dry Run
Example 1: `nums = [3, 4, 5]`

Step-by-step execution:
- s1=3, s2=4, s3=5
- Check triangle inequality:
  - 3 + 4 > 5 (7 > 5) ✓
  - 3 + 5 > 4 (8 > 4) ✓
  - 4 + 5 > 3 (9 > 3) ✓
- Check equality:
  - 3 != 4 != 5 (not all equal)
  - No two sides equal
- Result: `"scalene"`

Example 2: `nums = [2, 2, 2]`

Step-by-step execution:
- s1=2, s2=2, s3=2
- Check triangle inequality:
  - 2 + 2 > 2 (4 > 2) ✓
  - 2 + 2 > 2 (4 > 2) ✓
  - 2 + 2 > 2 (4 > 2) ✓
- Check equality:
  - 2 == 2 == 2 (all equal)
- Result: `"equilateral"`

Example 3: `nums = [2, 3, 2]`

Step-by-step execution:
- s1=2, s2=3, s3=2
- Check triangle inequality:
  - 2 + 3 > 2 (5 > 2) ✓
  - 2 + 2 > 3 (4 > 3) ✓
  - 3 + 2 > 2 (5 > 2) ✓
- Check equality:
  - Not all equal
  - s1 == s3 (2 == 2) ✓
- Result: `"isosceles"`

Example 4: `nums = [1, 2, 5]`

Step-by-step execution:
- s1=1, s2=2, s3=5
- Check triangle inequality:
  - 1 + 2 > 5 (3 > 5) ✗
- Failed inequality check
- Result: `"none"`

---

## Solution
```java
class Solution {
    public String triangleType(int[] nums) {

        int s1 = nums[0];
        int s2 = nums[1];
        int s3 = nums[2];

        // Check triangle inequality
        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1) {
            return "none";
        }

        // All sides equal
        if (s1 == s2 && s2 == s3) {
            return "equilateral";
        }
        // Any two equal
        else if (s1 == s2 || s2 == s3 || s1 == s3) {
            return "isosceles";
        }
        // Otherwise scalene
        else {
            return "scalene";
        }
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(1) - only a few constant-time comparisons
- **Space Complexity:** O(1) - no extra space used

---

## Alternative Approaches

### Sorting-Based Approach
```java
class Solution {
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0], b = nums[1], c = nums[2];

        // Check triangle inequality
        if (a + b <= c) {
            return "none";
        }

        // Check side equality
        if (a == c) {
            return "equilateral";
        } else if (a == b || b == c) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
```

**Explanation:** Sort sides first, then check conditions in order.

**Time Complexity:** O(1) - sorting 3 elements is constant
**Space Complexity:** O(1) - constant space

### Mathematical Approach with Sets
```java
class Solution {
    public String triangleType(int[] nums) {
        // Check triangle inequality
        if (nums[0] + nums[1] <= nums[2] ||
            nums[0] + nums[2] <= nums[1] ||
            nums[1] + nums[2] <= nums[0]) {
            return "none";
        }

        // Count unique sides
        Set<Integer> uniqueSides = new HashSet<>();
        for (int side : nums) {
            uniqueSides.add(side);
        }

        // Classify based on unique count
        switch (uniqueSides.size()) {
            case 1: return "equilateral";
            case 2: return "isosceles";
            case 3: return "scalene";
            default: return "none";
        }
    }
}
```

**Explanation:** Use a Set to count unique side lengths for classification.

**Time Complexity:** O(1) - constant operations
**Space Complexity:** O(1) - set size ≤ 3

### Bit Manipulation Approach
```java
class Solution {
    public String triangleType(int[] nums) {
        int s1 = nums[0], s2 = nums[1], s3 = nums[2];

        // Check triangle inequality
        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1) {
            return "none";
        }

        // Check equality using XOR
        if ((s1 ^ s2) == 0 && (s2 ^ s3) == 0) {
            return "equilateral";
        } else if ((s1 ^ s2) == 0 || (s2 ^ s3) == 0 || (s1 ^ s3) == 0) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
```

**Explanation:** Use bitwise XOR to check equality (XOR of equal numbers is 0).

**Time Complexity:** O(1) - constant operations
**Space Complexity:** O(1) - constant space

---

## Triangle Classification Rules

| Type | Side Equality | Triangle Inequality |
|------|---------------|-------------------|
| **Equilateral** | All three sides equal | Must satisfy |
| **Isosceles** | Exactly two sides equal | Must satisfy |
| **Scalene** | All three sides different | Must satisfy |
| **None** | Any classification | Fails inequality |

**Triangle Inequality Theorem:** For any triangle with sides a, b, c:
- a + b > c
- a + c > b
- b + c > a

**Key Insights:**
- Equilateral triangles are a special case of isosceles triangles
- The order of checking matters for correct classification
- Triangle inequality must be checked before side equality