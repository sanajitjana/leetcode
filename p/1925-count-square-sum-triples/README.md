# Count Square Sum Triples - <a href="https://leetcode.com/problems/count-square-sum-triples" target="_blank">Link</a>

## Question Description
A square triple (a,b,c) is a triple where a, b, and c are integers and a² + b² = c².

Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

---

## Constraints
- 1 <= n <= 250

---

## Approach
Use three nested loops to iterate over all possible values of a, b, and c from 1 to n. For each combination, check if a² + b² equals c². If so, increment the count.

This brute force approach is feasible since n is small (up to 250), resulting in at most 250³ = 15,625,000 operations, which is acceptable for the constraints.

---

## Dry Run
Example Input: n = 5

Possible triples where a² + b² = c²:
- (3,4,5): 9 + 16 = 25, yes
- (4,3,5): 16 + 9 = 25, yes
- Others like (1,1,2): 1+1=2≠4, no

Count = 2

---

## Solution
```java
class Solution {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (i * i + j * j == k * k) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n³) - three nested loops each up to n
- **Space Complexity:** O(1) - constant extra space  
[Back to All Problems](../README.md) 
