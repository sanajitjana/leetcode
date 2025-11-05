# Power of Four - <a href="https://leetcode.com/problems/power-of-four/" target="_blank">Link</a>

## Question Description
Given an integer `n`, return `true` if it is a power of four. Otherwise, return `false`.

An integer `n` is a power of four, if there exists an integer `x` such that `n == 4^x`.

---

## Constraints
- `-2^31 <= n <= 2^31 - 1`

---

## Approach
Use iterative division to check if a number is a power of four. A number `n` is a power of four if it can be written as 4^x where x ≥ 0.

**Algorithm:**
1. If `n` is less than or equal to 0, return false (powers of four must be positive)
2. While `n` is divisible by 4, divide `n` by 4
3. After the loop, if `n` equals 1, then it was a power of four
4. Otherwise, it is not a power of four

This approach works because:
- Powers of four in binary have exactly one '1' bit and even number of trailing zeros
- Repeatedly dividing by 4 eliminates all factors of 4
- If the final result is 1, then the original number was a power of four
- If the final result is not 1, then there were other prime factors

Alternative approaches include:
- Bit manipulation: Check if n > 0, n is power of 2, and binary representation has even number of zeros
- Using logarithms with base 4
- Checking if n is power of 2 and n % 3 == 1 (since 4^x = (2^2)^x = 2^(2x))

---

## Dry Run
Example 1: `n = 16` (4^2)

Step-by-step execution:
- 16 > 0, continue
- 16 % 4 == 0, so 16 / 4 = 4
- 4 % 4 == 0, so 4 / 4 = 1
- 1 == 1, return true

Example 2: `n = 64` (4^3)

Step-by-step execution:
- 64 > 0, continue
- 64 % 4 == 0, so 64 / 4 = 16
- 16 % 4 == 0, so 16 / 4 = 4
- 4 % 4 == 0, so 4 / 4 = 1
- 1 == 1, return true

Example 3: `n = 8` (2^3, not power of 4)

Step-by-step execution:
- 8 > 0, continue
- 8 % 4 == 0, so 8 / 4 = 2
- 2 % 4 != 0, exit loop
- 2 != 1, return false

Example 4: `n = 5` (not a power of 4)

Step-by-step execution:
- 5 > 0, continue
- 5 % 4 != 0, exit loop
- 5 != 1, return false

Example 5: `n = 1` (4^0)

Step-by-step execution:
- 1 > 0, continue
- 1 % 4 != 0, exit loop
- 1 == 1, return true

Example 6: `n = 0`

Step-by-step execution:
- 0 <= 0, return false

---

## Solution
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0)
            return false;
        while (n % 4 == 0)
            n = n / 4;
        return n == 1;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(log₄ n) - in each step we divide by 4, so we perform about log₄(n) operations
- **Space Complexity:** O(1) - only using a few variables, no extra space proportional to input size

---

## Alternative Approaches

### Bit Manipulation Approach (More Optimal)
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        // n must be positive power of 2, and when written in binary,
        // should have even number of zeros after the single 1
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }
}
```

**Explanation:**
- `(n & (n - 1)) == 0` checks if n is power of 2
- `0x55555555` is binary 01010101010101010101010101010101
- `(n & 0x55555555) != 0` ensures the single '1' bit is at odd position (1-based)

**Time Complexity:** O(1) - single bitwise operations
**Space Complexity:** O(1) - constant space

### Mathematical Approach
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        double log4 = Math.log(n) / Math.log(4);
        return Math.abs(log4 - Math.round(log4)) < 1e-10;
    }
}
```

**Explanation:** Use logarithms to check if n is perfect power of 4.

**Time Complexity:** O(1) - mathematical operations
**Space Complexity:** O(1) - constant space
