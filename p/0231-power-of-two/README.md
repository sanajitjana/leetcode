# [Power of Two](https://leetcode.com/problems/power-of-two/)

## Question Description
Given an integer `n`, return `true` if it is a power of two. Otherwise, return `false`.

An integer `n` is a power of two, if there exists an integer `x` such that `n == 2^x`.

---

## Constraints
- `-2^31 <= n <= 2^31 - 1`

---

## Approach
Use iterative division to check if a number is a power of two. A number `n` is a power of two if it can be written as 2^k where k ≥ 0.

**Algorithm:**
1. If `n` is less than or equal to 0, return false (powers of two must be positive)
2. While `n` is even (divisible by 2), divide `n` by 2
3. After the loop, if `n` equals 1, then it was a power of two
4. Otherwise, it is not a power of two

This approach works because:
- Powers of two in binary have exactly one '1' bit
- Repeatedly dividing by 2 eliminates all factors of 2
- If the final result is 1, then the original number was a power of two
- If the final result is not 1, then there were other prime factors

Alternative approaches include:
- Bit manipulation: `n > 0 && (n & (n - 1)) == 0`
- Recursive division
- Using logarithms

---

## Dry Run
Example 1: `n = 16`

Step-by-step execution:
- 16 > 0, continue
- 16 % 2 == 0, so 16 / 2 = 8
- 8 % 2 == 0, so 8 / 2 = 4
- 4 % 2 == 0, so 4 / 2 = 2
- 2 % 2 == 0, so 2 / 2 = 1
- 1 == 1, return true

Example 2: `n = 12`

Step-by-step execution:
- 12 > 0, continue
- 12 % 2 == 0, so 12 / 2 = 6
- 6 % 2 == 0, so 6 / 2 = 3
- 3 % 2 != 0, exit loop
- 3 != 1, return false

Example 3: `n = 1`

Step-by-step execution:
- 1 > 0, continue
- 1 % 2 != 0, exit loop
- 1 == 1, return true

Example 4: `n = 0`

Step-by-step execution:
- 0 <= 0, return false

---

## Solution
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        while (n % 2 == 0) {
            n = n / 2;
        }
        return n == 1;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(log n) - in each step we divide by 2, so we perform about log₂(n) operations
- **Space Complexity:** O(1) - only using a few variables, no extra space proportional to input size

---

## Alternative Approaches

### Bit Manipulation Approach
```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

**Explanation:** Powers of two have exactly one '1' bit in binary. Using `n & (n - 1)` clears the least significant '1' bit. If result is 0 and n > 0, then n is a power of two.

**Time Complexity:** O(1) - single bitwise operation
**Space Complexity:** O(1) - constant space