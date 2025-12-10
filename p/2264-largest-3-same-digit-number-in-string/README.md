# Largest 3-Same-Digit Number in String - <a href="https://leetcode.com/problems/largest-3-same-digit-number-in-string/" target="_blank">Link</a>

## Question Description
You are given a string `num` representing a large integer. A good integer is one that has an even number of digits and the number of 3-same-digit numbers in it is maximized.

No, wait - let me read the problem again:

Actually, you are given a string `num` representing a large integer. Return the largest good integer as a string, where a good integer is a substring of exactly 3 identical digits.

More precisely: Return the maximum 3-digit number (as a string) where all three digits are the same. If no such number exists, return an empty string.

A 3-digit number `abc` where `a == b == c` and `a`, `b`, `c` are digits.

---

## Constraints
- `3 <= num.length <= 1000`
- `num` consists only of digits

---

## Approaches

### Approach 1: Brute Force (Substring + Parse)
Iterate through the string and check every three consecutive characters to see if they form a 3-same-digit number.

**Algorithm:**
1. Iterate from index 0 to length-3
2. Check if three consecutive characters are identical
3. If they are, parse them as a number and track the maximum value
4. Handle edge case where maximum is 0 (return "000")
5. Return the maximum value as string, or empty string if none found

---

## Dry Run (Approach 1)
Example Input: `num = "6777133339"`

Step-by-step execution:
- i=0: '6','7','7' → not same, skip
- i=1: '7','7','7' → same! value = 777, max = 777
- i=2: '7','7','1' → not same, skip
- i=3: '7','1','3' → not same, skip
- i=4: '1','3','3' → not same, skip
- i=5: '3','3','3' → same! value = 333, max = 777 (777 > 333)
- i=6: '3','3','3' → same! value = 333, max = 777 (777 > 333)
- i=7: '3','3','9' → not same, skip

Maximum value = 777, return "777"

---

## Solution (Approach 1)
```java
class Solution {
    public String largestGoodInteger(String num) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < num.length() - 2; i++) {
            if (num.charAt(i) == num.charAt(i + 1) &&
                num.charAt(i + 1) == num.charAt(i + 2)) {

                long val = Long.parseLong(num.substring(i, i + 3));
                if (val > max) max = val;
            }
        }
        if (max == 0) return "000";
        return max > Long.MIN_VALUE ? String.valueOf(max) : "";
    }
}
```

---

## Time and Space Complexity (Approach 1)
- **Time Complexity:** O(n) - single pass through string, substring and parsing operations
- **Space Complexity:** O(1) - only using constant extra space

---

### Approach 2: Brute Force with Counting
Use a counter to track consecutive identical digits instead of parsing substrings.

**Algorithm:**
1. Initialize counter and previous character
2. Iterate through each character in the string
3. If current character equals previous, increment counter
4. Otherwise, reset counter to 1
5. When counter reaches 3, update maximum digit found
6. Return three instances of the maximum digit, or empty string

---

## Dry Run (Approach 2)
Example Input: `num = "6777133339"`

Step-by-step execution:
- ch='6', prev=' ', count=1, max=' '
- ch='7', prev='6', count=1, max=' '
- ch='7', prev='7', count=2, max=' '
- ch='7', prev='7', count=3, max='7' (first time count==3)
- ch='7', prev='7', count=4, max='7'
- ch='1', prev='7', count=1, max='7'
- ch='3', prev='1', count=1, max='7'
- ch='3', prev='3', count=2, max='7'
- ch='3', prev='3', count=3, max='7' (333, but 7>3)
- ch='3', prev='3', count=4, max='7'
- ch='3', prev='3', count=5, max='7'
- ch='9', prev='3', count=1, max='7'

Maximum digit = '7', return "777"

---

## Solution (Approach 2)
```java
class Solution {
    public String largestGoodInteger(String num) {
        int count = 0;
        char max = ' ';
        char prev = ' ';

        for (char ch : num.toCharArray()) {
            if (ch == prev) count++;
            else count = 1;

            if (count == 3) {
                max = (char) Math.max(max, ch);
            }
            prev = ch;
        }
        return max == ' ' ? "" : String.valueOf(max).repeat(3);
    }
}
```

---

## Time and Space Complexity (Approach 2)
- **Time Complexity:** O(n) - single pass through string
- **Space Complexity:** O(1) - only using constant extra space

---

### Approach 3: Optimal (Predefined Candidates)
Pre-store all possible 3-same-digit numbers and check which one exists in the string.

**Algorithm:**
1. Create an array of all possible 3-same-digit numbers from "999" down to "000"
2. Check if the input string contains each candidate
3. Return the first match (which will be the largest due to order)
4. If no match found, return empty string

---

## Dry Run (Approach 3)
Example Input: `num = "6777133339"`

Predefined candidates: ["999", "888", "777", "666", "555", "444", "333", "222", "111", "000"]

Step-by-step execution:
- Check "999": "6777133339".contains("999") → false
- Check "888": "6777133339".contains("888") → false
- Check "777": "6777133339".contains("777") → true ✓

Return "777" immediately

---

## Solution (Approach 3)
```java
class Solution {
    public String largestGoodInteger(String num) {
        String[] candidates = { "999", "888", "777", "666", "555",
                                 "444", "333", "222", "111", "000" };
        for (String c : candidates) {
            if (num.contains(c)) return c;
        }
        return "";
    }
}
```

---

## Time and Space Complexity (Approach 3)
- **Time Complexity:** O(n) - contains() operation is O(n) in worst case, done 10 times
- **Space Complexity:** O(1) - candidates array is constant size (10 elements)

---

## Comparison of Approaches

| Approach | Time Complexity | Space Complexity | Readability | Performance |
|----------|----------------|------------------|-------------|-------------|
| **Approach 1** | O(n) | O(1) | Medium | Good |
| **Approach 2** | O(n) | O(1) | High | Best |
| **Approach 3** | O(n) | O(1) | High | Best |

**Recommendation:** Approach 2 is the most efficient and readable solution for this problem.
  
[Back to All Problems](../README.md) 
