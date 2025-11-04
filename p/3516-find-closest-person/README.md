# Find Closest Person - [Link](https://leetcode.com/problems/find-closest-person/)

## Question Description
Given three integers x, y, and z, return:
- 1 if x is closer to z,
- 2 if y is closer to z,
- 0 if both are equally close.

---

## Constraints
- 1 <= x, y, z <= 100

---

## Approach
- Use absolute difference to measure distance from z.
- Compare xStepNeed and yStepNeed.
- Return accordingly.

---

## Dry Run
Example Input: x = 3, y = 7, z = 5

Step-by-step execution:
- xStepNeed = |3-5| = 2
- yStepNeed = |7-5| = 2
- Equal, return 0

---

## Solution
```java
class Solution {
    public int findClosest(int x, int y, int z) {
        int xStepNeed = Math.abs(x - z);
        int yStepNeed = Math.abs(y - z);

        if (xStepNeed < yStepNeed) return 1;
        else if (xStepNeed > yStepNeed) return 2;
        else return 0;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)
