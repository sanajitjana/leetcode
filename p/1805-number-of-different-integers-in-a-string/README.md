# Number of Different Integers in a String - <a href="https://leetcode.com/problems/number-of-different-integers-in-a-string/" target="_blank">Link</a>

## Question Description
You are given a string `word` that consists of digits and lowercase English letters.

You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".

Return the number of different integers after performing the replacement operations on `word`.

Two integers are considered different if their decimal representations without any leading zeros are different.

---

## Constraints
- `1 <= word.length <= 1000`
- `word` consists of digits and lowercase English letters.

---

## Approach
Iterate through each character in the string. When a digit is encountered, append it to a StringBuilder to build the current number. When a non-digit is encountered, if the StringBuilder has content, normalize the number (remove leading zeros) and add it to a HashSet to ensure uniqueness. After the loop, handle the last number if present. The size of the set gives the count of distinct integers.

The normalize function removes leading zeros from the number string. If the number consists entirely of zeros, it returns "0".

This approach efficiently handles the problem by using a set for uniqueness and normalizing numbers to ignore leading zeros as required.

---

## Dry Run
Example Input: `word = "a123bc34d8ef34"`

Step-by-step execution:
- Start with empty StringBuilder and empty HashSet.
- i=0, 'a' (non-digit), sb is empty, no action.
- i=1, '1' (digit), sb = "1"
- i=2, '2' (digit), sb = "12"
- i=3, '3' (digit), sb = "123"
- i=4, 'b' (non-digit), sb = "123", normalize("123") = "123", add to set, sb reset.
- i=5, 'c' (non-digit), sb empty, no action.
- i=6, '3' (digit), sb = "3"
- i=7, '4' (digit), sb = "34"
- i=8, 'd' (non-digit), sb = "34", normalize("34") = "34", add to set, sb reset.
- i=9, '8' (digit), sb = "8"
- i=10, 'e' (non-digit), sb = "8", normalize("8") = "8", add to set, sb reset.
- i=11, 'f' (non-digit), sb empty, no action.
- i=12, '3' (digit), sb = "3"
- i=13, '4' (digit), sb = "34"
- End of string, sb = "34", normalize("34") = "34", but "34" already in set, no add.

Set contains: "123", "34", "8"

Final Answer = 3

---

## Solution
```java
class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> nums = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : word.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else {
                if (sb.length() > 0) {
                    nums.add(normalize(sb.toString()));
                    sb.setLength(0);
                }
            }
        }

        if (sb.length() > 0) {
            nums.add(normalize(sb.toString()));
        }

        return nums.size();
    }

    private String normalize(String num) {
        int i = 0;
        while (i < num.length() && num.charAt(i) == '0') i++;
        String trimmed = num.substring(i);
        return trimmed.length() == 0 ? "0" : trimmed;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the string, with normalization being O(k) where k is number length, but overall linear.
- **Space Complexity:** O(n) - HashSet stores up to n characters in worst case.

---

[Back to All Problems](../README.md)