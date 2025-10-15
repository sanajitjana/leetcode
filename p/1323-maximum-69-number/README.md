# [Maximum 69 Number](https://leetcode.com/problems/maximum-69-number/)

## Question Description
You are given a positive integer `num` consisting only of digits 6 and 9.

Return the maximum number you can get by changing **at most one digit** (6 becomes 9, and 9 becomes 6).

---

## Constraints
- `1 <= num <= 10^4`
- `num` consists only of digits 6 and 9

---

## Approach
Use a greedy strategy to maximize the number by changing the first '6' to '9' from the left (most significant digit).

**Algorithm:**
1. Convert the number to a character array to easily modify digits
2. Iterate through the digits from left to right
3. Find the first occurrence of '6' and change it to '9'
4. Break immediately since changing the leftmost '6' gives the maximum value
5. Convert back to integer and return

This approach works because:
- To maximize the number, we want to maximize the leftmost digits first
- Changing any '6' to '9' increases the number by 3 × 10^(position from right)
- The leftmost '6' has the highest place value, so changing it gives the maximum increase
- We can only change at most one digit, so we choose the one with maximum impact

Alternative approaches include:
- String manipulation (same as current approach)
- Mathematical approach using digit manipulation

---

## Dry Run
Example 1: `num = 9669`

Step-by-step execution:
- Convert to char array: ['9', '6', '6', '9']
- i=0: '9' != '6', continue
- i=1: '6' == '6', change to '9' → ['9', '9', '6', '9']
- Break (found first '6')
- Convert back: 9969

Example 2: `num = 9996`

Step-by-step execution:
- Convert to char array: ['9', '9', '9', '6']
- i=0: '9' != '6', continue
- i=1: '9' != '6', continue
- i=2: '9' != '6', continue
- i=3: '6' == '6', change to '9' → ['9', '9', '9', '9']
- Break
- Convert back: 9999

Example 3: `num = 9999`

Step-by-step execution:
- Convert to char array: ['9', '9', '9', '9']
- No '6' found, no changes needed
- Return: 9999

Example 4: `num = 6`

Step-by-step execution:
- Convert to char array: ['6']
- i=0: '6' == '6', change to '9' → ['9']
- Break
- Convert back: 9

---

## Solution
```java
class Solution {
    public int maximum69Number(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(digits));
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - where n is the number of digits (n ≤ 4 for given constraints)
- **Space Complexity:** O(n) - for storing the character array (can be considered O(1) since n ≤ 4)

---

## Alternative Approaches

### Mathematical Approach (More Optimal)
```java
class Solution {
    public int maximum69Number(int num) {
        // Find the largest power of 10 that divides num when it's all 9s
        int temp = num;
        int pos = 1;

        // Find the position from right where we should change 6 to 9
        while (temp >= 10) {
            pos *= 10;
            temp /= 10;
        }

        // Start from leftmost digit
        temp = num;
        while (pos > 0) {
            int digit = temp / pos;
            if (digit == 6) {
                return num + 3 * pos; // 6 -> 9 increases by 3 * 10^pos
            }
            temp %= pos;
            pos /= 10;
        }

        return num;
    }
}
```

**Explanation:** Use mathematical operations to find and modify the leftmost '6' without string conversion.

**Time Complexity:** O(n) - where n is number of digits
**Space Complexity:** O(1) - constant space

### One-Liner Approach
```java
class Solution {
    public int maximum69Number(int num) {
        return Integer.parseInt(String.valueOf(num).replaceFirst("6", "9"));
    }
}
```

**Explanation:** Use String's replaceFirst method to replace the first occurrence of '6' with '9'.

**Time Complexity:** O(n) - string operations
**Space Complexity:** O(n) - for string creation