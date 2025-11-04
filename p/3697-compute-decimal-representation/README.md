# Compute Decimal Representation - [Link](https://leetcode.com/problems/compute-decimal-representation/)

## Question Description
You are given an integer `n`. Your task is to break it down into its decimal components and return them as an array.

For example:
- `n = 4321` → `[4000, 300, 20, 1]`
- `n = 5030` → `[5000, 30]`
- `n = 7` → `[7]`

Return the decimal representation as an array of integers, **excluding zeros**.

---

## Constraints
- `1 <= n <= 10^9`

---

## Approach
Extract each non-zero digit and multiply it by its place value, starting from the units place.

**Algorithm:**
1. Initialize an empty list to store decimal components
2. Initialize `place = 1` (units place)
3. While `n > 0`:
   - Extract the last digit: `digit = n % 10`
   - If digit is not 0, compute `digit * place` and add to list
   - Remove the last digit: `n = n / 10`
   - Move to next place value: `place = place * 10`
4. Reverse the list (since we processed from least significant to most significant)
5. Convert list to array and return

This approach works because:
- We process digits from right to left (least to most significant)
- Each non-zero digit contributes to the final result
- Place value correctly represents the digit's position
- Reversing gives us the correct order (most significant first)

---

## Dry Run
Example 1: `n = 5030`

Step-by-step execution:
- place = 1, n = 5030
- digit = 5030 % 10 = 0 (skip, don't add)
- n = 5030 / 10 = 503, place = 1 * 10 = 10
- digit = 503 % 10 = 3, 3 * 10 = 30 (add 30)
- n = 503 / 10 = 50, place = 10 * 10 = 100
- digit = 50 % 10 = 0 (skip, don't add)
- n = 50 / 10 = 5, place = 100 * 10 = 1000
- digit = 5 % 10 = 5, 5 * 1000 = 5000 (add 5000)
- n = 5 / 10 = 0, place = 1000 * 10 = 10000

List before reverse: `[30, 5000]`  
List after reverse: `[5000, 30]`

Result: `[5000, 30]`

Example 2: `n = 4321`

Step-by-step execution:
- place = 1, n = 4321
- digit = 1, 1 * 1 = 1 (add 1)
- n = 432, place = 10
- digit = 2, 2 * 10 = 20 (add 20)
- n = 43, place = 100
- digit = 3, 3 * 100 = 300 (add 300)
- n = 4, place = 1000
- digit = 4, 4 * 1000 = 4000 (add 4000)
- n = 0, place = 10000

List before reverse: `[1, 20, 300, 4000]`  
List after reverse: `[4000, 300, 20, 1]`

Result: `[4000, 300, 20, 1]`

Example 3: `n = 7`

Step-by-step execution:
- place = 1, n = 7
- digit = 7, 7 * 1 = 7 (add 7)
- n = 0, place = 10

List before reverse: `[7]`  
List after reverse: `[7]`

Result: `[7]`

---

## Solution
```java
class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> base = new ArrayList<>();
        int place = 1;
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                base.add(digit * place);
            }
            n /= 10;
            place *= 10;
        }
        Collections.reverse(base);
        int[] list = new int[base.size()];
        for (int i = 0; i < base.size(); i++) {
            list[i] = base.get(i);
        }
        return list;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(log₁₀ n) - we process each digit once
- **Space Complexity:** O(log₁₀ n) - we store at most one value per digit

---

## Alternative Approaches

### String-Based Approach
```java
class Solution {
    public int[] decimalRepresentation(int n) {
        String s = String.valueOf(n);
        List<Integer> result = new ArrayList<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                int placeValue = digit * (int) Math.pow(10, s.length() - 1 - i);
                result.add(placeValue);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

**Explanation:** Convert to string and calculate place values using powers of 10.

**Time Complexity:** O(log₁₀ n) - string conversion and processing
**Space Complexity:** O(log₁₀ n) - for string and result list

### Mathematical Approach with Place Tracking
```java
class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> result = new ArrayList<>();
        int multiplier = 1;

        while (n > 0) {
            int remainder = n % 10;
            if (remainder != 0) {
                result.add(remainder * multiplier);
            }
            n = n / 10;
            multiplier = multiplier * 10;
        }

        // Reverse the list
        Collections.reverse(result);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

**Explanation:** Same logic but with different variable names for clarity.

**Time Complexity:** O(log₁₀ n) - digit processing
**Space Complexity:** O(log₁₀ n) - result storage

### Recursive Approach
```java
class Solution {
    private List<Integer> result = new ArrayList<>();

    public int[] decimalRepresentation(int n) {
        helper(n, 1);
        Collections.reverse(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void helper(int n, int place) {
        if (n == 0) return;

        int digit = n % 10;
        if (digit != 0) {
            result.add(digit * place);
        }

        helper(n / 10, place * 10);
    }
}
```

**Explanation:** Use recursion to process digits from least significant to most significant.

**Time Complexity:** O(log₁₀ n) - recursive calls for each digit
**Space Complexity:** O(log₁₀ n) - recursion stack + result list

---

## Key Insights

**Place Value System:**
- Units place: 10⁰ = 1
- Tens place: 10¹ = 10
- Hundreds place: 10² = 100
- Thousands place: 10³ = 1000

**Why Skip Zeros:**
- Zeros don't contribute to the place value representation
- Including them would give `[5000, 0, 30]` instead of `[5000, 30]`
- The problem specifically asks to exclude zeros

**Edge Cases:**
- `n = 1` → `[1]`
- `n = 10` → `[10]` (not `[10, 0]`)
- `n = 100` → `[100]` (not `[100, 0, 0]`)
- `n = 101` → `[100, 1]` (not `[100, 0, 1]`)

**Applications:**
- Understanding place value systems
- Number decomposition
- Educational programming problems
- Digit manipulation exercises
