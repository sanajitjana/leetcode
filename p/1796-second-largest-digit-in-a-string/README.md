# Second Largest Digit in a String - [Link](https://leetcode.com/problems/second-largest-digit-in-a-string/)

## Question Description
Given an alphanumeric string `s`, return the **second largest** numerical digit that appears in `s`, or **-1** if it does not exist.

A **numerical digit** is a character in the range `'0'` to `'9'`.

---

## Constraints
- `1 <= s.length <= 500`
- `s` consists of only lowercase English letters and/or digits

---

## Approach
Use a single pass through the string to track the maximum and second maximum digits found.

**Algorithm:**
1. Initialize `max = -1` and `secondMax = -1`
2. Iterate through each character in the string
3. If the character is a digit, convert it to integer value
4. Update tracking variables:
   - If current digit > max: update secondMax = max, then max = current digit
   - If current digit < max but > secondMax: update secondMax = current digit
5. Return secondMax

This approach works because:
- Single pass ensures O(n) time complexity
- Only two variables needed for constant space
- Handles duplicates correctly (ignores them after first occurrence)
- Returns -1 if no second largest digit exists

---

## Dry Run
Example 1: `s = "dfa12321afd"`

Step-by-step execution:
- ch='d': not digit, skip
- ch='f': not digit, skip
- ch='a': not digit, skip
- ch='1': digit, num=1, 1 > -1, so secondMax=-1, max=1
- ch='2': digit, num=2, 2 > 1, so secondMax=1, max=2
- ch='3': digit, num=3, 3 > 2, so secondMax=2, max=3
- ch='2': digit, num=2, 2 < 3 && 2 == 2, no update (duplicate)
- ch='1': digit, num=1, 1 < 3 && 1 < 2, no update
- ch='a': not digit, skip
- ch='f': not digit, skip
- ch='d': not digit, skip

Result: `secondMax = 2`

Example 2: `s = "abc"`

Step-by-step execution:
- No digits found
- max = -1, secondMax = -1

Result: `-1`

Example 3: `s = "a1b"`

Step-by-step execution:
- ch='a': not digit, skip
- ch='1': digit, num=1, 1 > -1, so secondMax=-1, max=1
- ch='b': not digit, skip

Result: `-1` (only one unique digit)

---

## Solution
```java
class Solution {
    public int secondHighest(String s) {
        int max = -1;
        int sMax = -1;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                int num = ch - '0';
                if (num > max) {
                    sMax = max;
                    max = num;
                } else if (num < max && num > sMax) {
                    sMax = num;
                }
            }
        }
        return sMax;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the string
- **Space Complexity:** O(1) - only using constant extra space for variables

---

## Alternative Approaches

### Using Set Approach (Handles Duplicates Explicitly)
```java
class Solution {
    public int secondHighest(String s) {
        Set<Integer> digits = new HashSet<>();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.add(ch - '0');
            }
        }

        if (digits.size() < 2) {
            return -1;
        }

        // Find the largest digit
        int max = Collections.max(digits);

        // Remove the largest and find the new maximum
        digits.remove(max);

        return Collections.max(digits);
    }
}
```

**Explanation:** Use a Set to collect unique digits, then find the maximum twice.

**Time Complexity:** O(n) - single pass plus set operations
**Space Complexity:** O(k) - where k is number of unique digits (≤ 10)

### Stream API Approach (Java 8+)
```java
class Solution {
    public int secondHighest(String s) {
        int max = s.chars()
                  .filter(Character::isDigit)
                  .map(c -> c - '0')
                  .distinct()
                  .max()
                  .orElse(-1);

        int secondMax = s.chars()
                        .filter(Character::isDigit)
                        .map(c -> c - '0')
                        .distinct()
                        .filter(d -> d < max)
                        .max()
                        .orElse(-1);

        return secondMax;
    }
}
```

**Explanation:** Use Java streams to process digits functionally.

**Time Complexity:** O(n) - stream processing
**Space Complexity:** O(k) - for distinct elements

### ASCII-based Approach
```java
class Solution {
    public int secondHighest(String s) {
        int max = -1;
        int secondMax = -1;

        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                if (num > max) {
                    secondMax = max;
                    max = num;
                } else if (num > secondMax && num < max) {
                    secondMax = num;
                }
            }
        }
        return secondMax;
    }
}
```

**Explanation:** Same logic but using ASCII range check instead of `Character.isDigit()`.

**Time Complexity:** O(n) - single pass
**Space Complexity:** O(1) - constant space
