# [Maximum Area of Longest Diagonal Rectangle](https://leetcode.com/problems/area-of-maximum-diagonal/)

## Question Description
Given a 2D array dimensions where dimensions[i] = [length, width] represents the dimensions of the i-th rectangle. Return the area of the rectangle having the longest diagonal. If there are multiple rectangles with the longest diagonal, return the one with the largest area.

---

## Constraints
- 1 <= dimensions.length <= 100
- 1 <= dimensions[i][0], dimensions[i][1] <= 100

---

## Approach
- For each rectangle (length, width), compute the squared diagonal = l² + w².
- Also compute its area = l * w.
- Track the maximum diagonal squared.
  - If a rectangle has a larger diagonal, update max diagonal and max area.
  - If diagonal is equal to current max, take the larger area.
- Using squared diagonal avoids floating-point issues from Math.sqrt.

---

## Dry Run
Example Input: dimensions = [[9,3],[8,6]]

Step-by-step execution:
- First: l=9, w=3, diaSq=81+9=90, area=27
- Second: l=8, w=6, diaSq=64+36=100, area=48
- Max diaSq=100, area=48

Final Answer = 48

---

## Solution
```java
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiaSq = 0;  // store squared diagonal
        int maxArea = 0;

        for (int[] arr : dimensions) {
            int diaSq = arr[0] * arr[0] + arr[1] * arr[1];
            int area = arr[0] * arr[1];

            if (diaSq > maxDiaSq) {
                maxDiaSq = diaSq;
                maxArea = area;
            } else if (diaSq == maxDiaSq && area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) — one pass through all rectangles
- **Space Complexity:** O(1) — only a few variables stored