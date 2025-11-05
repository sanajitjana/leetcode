# Bitwise OR of Even Numbers in an Array - <a href="https://leetcode.com/problems/bitwise-or-of-even-numbers-in-an-array/" target="_blank">Link</a>

## Question Description
You are given an integer array `nums`. Return the **bitwise OR** of all **even numbers** in the array.

If there are **no even numbers** in `nums`, return `0`.

---

## Constraints
- `1 <= nums.length <= 1000`
- `0 <= nums[i] <= 1000`

---

## Approach
Iterate through the array and perform bitwise OR operation on all even numbers found.

**Algorithm:**
1. Initialize `result = 0`
2. For each number in the array:
   - If the number is even (`num % 2 == 0`), perform bitwise OR with result
   - If the number is odd, skip it
3. Return the final result

This approach works because:
- Bitwise OR operation combines bits from all even numbers
- Even numbers have the least significant bit as 0
- The operation is commutative and associative
- If no even numbers exist, result remains 0

---

## Dry Run
Example 1: `nums = [1, 2, 3, 4, 5, 6]`

Step-by-step execution:
- result = 0
- num = 1 (odd), skip
- num = 2 (even), result = 0 | 2 = 2 (binary: 0010)
- num = 3 (odd), skip
- num = 4 (even), result = 2 | 4 = 6 (binary: 0110)
- num = 5 (odd), skip
- num = 6 (even), result = 6 | 6 = 6 (binary: 0110)

Result: `6`

Example 2: `nums = [1, 3, 5]`

Step-by-step execution:
- result = 0
- num = 1 (odd), skip
- num = 3 (odd), skip
- num = 5 (odd), skip

Result: `0` (no even numbers)

Example 3: `nums = [2, 4, 6, 8]`

Step-by-step execution:
- result = 0
- num = 2 (even), result = 0 | 2 = 2
- num = 4 (even), result = 2 | 4 = 6
- num = 6 (even), result = 6 | 6 = 6
- num = 8 (even), result = 6 | 8 = 14 (binary: 1110)

Result: `14`

---

## Solution
```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                res |= num;
            }
        }
        return res;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - only using constant extra space

---

## Alternative Approaches

### Early Termination Approach
```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                result |= num;
            }
        }
        return result;
    }
}
```

**Explanation:** Same as main approach but with more descriptive variable name.

**Time Complexity:** O(n) - single pass
**Space Complexity:** O(1) - constant space

### Stream API Approach (Java 8+)
```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        return Arrays.stream(nums)
                    .filter(num -> num % 2 == 0)
                    .reduce(0, (a, b) -> a | b);
    }
}
```

**Explanation:** Use Java streams to filter even numbers and reduce with OR operation.

**Time Complexity:** O(n) - stream processing
**Space Complexity:** O(1) - excluding temporary stream objects

### Bitwise Check Approach
```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {  // Check if even using bitwise AND
                result |= num;
            }
        }
        return result;
    }
}
```

**Explanation:** Use bitwise AND with 1 to check if number is even (even numbers have LSB 0).

**Time Complexity:** O(n) - single pass
**Space Complexity:** O(1) - constant space

### Index-Based Approach
```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result = result | nums[i];
            }
        }
        return result;
    }
}
```

**Explanation:** Use traditional for loop with index instead of enhanced for loop.

**Time Complexity:** O(n) - single pass
**Space Complexity:** O(1) - constant space

---

## Bitwise OR Properties

| Property | Description | Example |
|----------|-------------|---------|
| **Identity** | `x | 0 = x` | `5 | 0 = 5` |
| **Self** | `x | x = x` | `5 | 5 = 5` |
| **Commutative** | `x | y = y | x` | `3 | 5 = 5 | 3` |
| **Associative** | `(x | y) | z = x | (y | z)` | `(1 | 2) | 4 = 1 | (2 | 4)` |

**Key Insights:**
- Bitwise OR sets a bit if either operand has that bit set
- For even numbers, we're combining their binary representations
- The result represents the "union" of all bits from even numbers
- If no even numbers exist, result is 0 (identity element)

**Binary Examples:**
- 2 in binary: `0010`
- 4 in binary: `0100`
- 6 in binary: `0110`
- 2 | 4 | 6 = `0110` (decimal 6)

**Edge Cases:**
- All odd numbers: return 0
- All even numbers: return OR of all numbers
- Mixed numbers: return OR of even numbers only
- Empty array: return 0 (though constraints say n >= 1)
