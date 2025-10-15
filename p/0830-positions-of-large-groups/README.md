# [Positions of Large Groups](https://leetcode.com/problems/positions-of-large-groups/)

## Question Description
In a string `s` of lowercase letters, a **group** is a consecutive run of the same character. A **large group** is defined as a group that has length >= 3.

Return the intervals `[start, end]` of every large group sorted in increasing order of start index.

---

## Constraints
- `1 <= s.length <= 1000`
- `s` consists of lowercase English letters

---

## Approach
Use the two-pointer technique to identify groups of consecutive identical characters and record intervals for groups with length >= 3.

**Algorithm:**
1. Initialize `start` pointer at index 0
2. Iterate `i` from 1 to string length
3. When we reach the end OR current character differs from character at `start`:
   - Calculate group length = `i - start`
   - If length >= 3, record interval `[start, i-1]`
   - Update `start = i` for new group
4. Return list of all large group intervals

This approach works because:
- Two pointers efficiently track group boundaries
- Single pass through string gives O(n) time complexity
- Only stores intervals for groups meeting the criteria (>= 3 length)
- Handles edge cases like groups at string boundaries

---

## Dry Run
Example Input: `s = "abbxxxxzzy"`

Step-by-step execution:
- i=0: start=0, current char='a'
- i=1: s[1]='b' != s[0]='a', group length=1-0=1 (<3), skip, start=1
- i=2: s[2]='b' == s[1]='b', continue
- i=3: s[3]='x' != s[1]='b', group length=3-1=2 (<3), skip, start=3
- i=4: s[4]='x' == s[3]='x', continue
- i=5: s[5]='x' == s[3]='x', continue
- i=6: s[6]='x' == s[3]='x', continue
- i=7: s[7]='z' != s[3]='x', group length=7-3=4 (>=3), record [3,6], start=7
- i=8: s[8]='z' == s[7]='z', continue
- i=9: s[9]='y' != s[7]='z', group length=9-7=2 (<3), skip, start=9
- i=10: reached end, group length=10-9=1 (<3), skip

Result: `[[3,6]]`

---

## Solution
```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length();
        int start = 0;

        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) != s.charAt(start)) {
                if (i - start >= 3) {
                    ans.add(Arrays.asList(start, i - 1));
                }
                start = i;
            }
        }

        return ans;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the string
- **Space Complexity:** O(1) - excluding output list, only using constant extra space

---

## Alternative Approaches

### Brute Force Approach (Less Efficient)
```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < s.length(); ) {
            int j = i;
            // Find end of current group
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            // Check if group size >= 3
            if (j - i >= 3) {
                result.add(Arrays.asList(i, j - 1));
            }

            i = j;
        }

        return result;
    }
}
```

**Explanation:** Use a single index that moves to find group boundaries.

**Time Complexity:** O(n) - still single pass, but slightly less efficient due to inner while loop
**Space Complexity:** O(1) - constant extra space

### Regex Approach
```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(.)\\1{2,}");
        java.util.regex.Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            result.add(Arrays.asList(matcher.start(), matcher.end() - 1));
        }

        return result;
    }
}
```

**Explanation:** Use regex to find sequences of 3 or more identical characters.

**Time Complexity:** O(n) - regex matching is generally O(n)
**Space Complexity:** O(1) - excluding output list