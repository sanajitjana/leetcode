# Divide a String Into Groups of Size k - [Link](https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/)

## Question Description
Given a string s, divide the string into groups of size k (except for the last group, which may be shorter). If the last group is shorter than k, fill it with the character fill.

---

## Constraints
- 1 <= s.length <= 100
- 1 <= k <= 100
- s consists of lowercase and uppercase English letters.
- fill is a lowercase or uppercase English letter.

---

## Approach
- Calculate the number of resulting groups based on the string length and k.
- Iterate over the string in steps of k.
- For each group, build a substring of length k.
  - If characters run out, append the fill character.
- Store each group in the result array and return it.

---

## Dry Run
Example Input: s = "abcdef", k = 3, fill = 'x'

Step-by-step execution:
- sLen = 6, aLen = 2
- i=0: "abc"
- i=3: "def"
- Result: ["abc", "def"]

Another example: s = "abc", k = 3, fill = 'x'
- i=0: "abc"
- Result: ["abc"]

---

## Solution
```java
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int sLen = s.length();
        int aLen = sLen % k == 0 ? sLen / k : sLen / k + 1;
        String[] str = new String[aLen];

        int index = 0;
        for (int i = 0; i < sLen; i += k) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < k + i; j++) {
                if (j < sLen) {
                    sb.append(s.charAt(j));
                } else {
                    sb.append(fill);
                }
            }
            str[index++] = sb.toString();
        }
        return str;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) where n is the length of string s.
- **Space Complexity:** O(n) for storing the resulting array of strings.
