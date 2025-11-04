# Compare Version Numbers - [Link](https://leetcode.com/problems/compare-version-numbers/)

## Question Description
Given two version strings, version1 and version2, compare them. A version string consists of revisions separated by dots '.'. Each revision is an integer ignoring leading zeros.

Return:
- If version1 < version2, return -1
- If version1 > version2, return 1
- Otherwise, return 0

---

## Constraints
- `1 <= version1.length, version2.length <= 500`
- `version1` and `version2` consist of English letters, digits, and '.' characters
- `version1` and `version2` are valid version numbers
- All revisions in version1 and version2 are integers that do not start with zero (except for the number 0 itself)

---

## Approach
1. Split both version strings into integer lists by '.'
2. Normalize lengths by padding the shorter list with zeros
3. Compare the lists index by index:
   - If any revision differs, return -1 or 1 accordingly
   - If all are equal, return 0

**Why this approach works:**
- Handles different version string lengths by padding with zeros
- Compares versions revision by revision from left to right
- Correctly handles leading zeros by parsing as integers

**Alternative approaches considered:**
- Could compare as strings directly, but need to handle different lengths and numeric comparison
- Could use more complex parsing, but simple split and integer conversion is sufficient

---

## Dry Run
Example Input: `version1 = "1.01", version2 = "1.001"`

Step-by-step execution:
- Step 1: Split version1 by '.' -> [1, 1]
- Step 2: Split version2 by '.' -> [1, 1]
- Step 3: Compare index by index:
  - index 0: 1 == 1
  - index 1: 1 == 1
- Step 4: All revisions equal, return 0

Final Answer = `0`

Example Input: `version1 = "1.0.1", version2 = "1"`

Step-by-step execution:
- Step 1: Split version1 by '.' -> [1, 0, 1]
- Step 2: Split version2 by '.' -> [1]
- Step 3: Pad shorter list with zeros -> [1, 0, 0]
- Step 4: Compare index by index:
  - index 0: 1 == 1
  - index 1: 0 == 0
  - index 2: 1 > 0, return 1

Final Answer = `1`

---

## Solution
```java
import java.util.*;

class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> list1 = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < version1.length(); i++) {
            char ch = version1.charAt(i);
            if (ch == '.') {
                list1.add(Integer.parseInt(st.toString()));
                st = new StringBuilder();
            } else {
                st.append(ch);
            }
        }
        list1.add(Integer.parseInt(st.toString()));

        List<Integer> list2 = new ArrayList<>();
        StringBuilder st2 = new StringBuilder();
        for (int i = 0; i < version2.length(); i++) {
            char ch = version2.charAt(i);
            if (ch == '.') {
                list2.add(Integer.parseInt(st2.toString()));
                st2 = new StringBuilder();
            } else {
                st2.append(ch);
            }
        }
        list2.add(Integer.parseInt(st2.toString()));

        if (list1.size() > list2.size()) {
            for (int i = 0; i < list1.size() - list2.size()+1; i++) {
                list2.add(0);
            }
        } else if (list1.size() < list2.size()) {
            for (int i = 0; i < list2.size() - list1.size()+1; i++) {
                list1.add(0);
            }
        }

        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            int num1 = list1.get(i);
            int num2 = list2.get(j);
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
            i++;
            j++;
        }
        return 0;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n + m) where n = length of version1, m = length of version2 (we parse both strings fully once)
- **Space Complexity:** O(n + m) for storing split parts in two lists
