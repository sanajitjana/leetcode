# Delete Columns to Make Sorted - <a href="https://leetcode.com/problems/delete-columns-to-make-sorted/" target="_blank">Link</a>

## Question Description
You are given an array of n strings strs, all of the same length.

The strings can be arranged such that there is one on each line, making a grid.

For example, strs = ["abc", "bce", "cae"] can be arranged as follows:

abc
bce
cae

You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted, while column 1 ('b', 'c', 'a') is not, so you would delete column 1.

Return the number of columns that you will delete.

---

## Constraints
- n == strs.length
- 1 <= n <= 100
- 1 <= strs[i].length <= 1000
- strs[i] consists of lowercase English letters.

---

## Approach
Iterate through each column (index i from 0 to strs[0].length() - 1). For each column, check if the characters in that column are sorted in non-decreasing order by comparing adjacent rows (j from 0 to strs.length - 2). If any pair strs[j].charAt(i) > strs[j+1].charAt(i), the column is not sorted, so increment the count and break to the next column.

This approach ensures we count each unsorted column exactly once.

---

## Dry Run
Example Input: strs = ["cba","daf","ghi"]

Grid:
c d a
b a f
a g h
Wait, no: strs[0]="cba", strs[1]="daf", strs[2]="ghi"

So columns:
Col 0: c, d, g
Col 1: b, a, h
Col 2: a, f, i

Check col 0: c <= d <= g? c<d? c='c', d='d' yes, d<g? 'd'<'g' yes.
Col 1: b, a, h -> b > a? 'b'>'a' yes, so unsorted.
Col 2: a, f, i -> a<f<i yes.

So count = 1, matches example.

---

## Solution
```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n * m) where n is the number of strings and m is the length of each string, as we iterate through each column and each row pair.
- **Space Complexity:** O(1) - no extra space used besides a few variables.

[Back to All Problems](../README.md)