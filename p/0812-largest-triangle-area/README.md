# Largest Triangle Area - [Link](https://leetcode.com/problems/largest-triangle-area/)

## Question Description
You are given an array of points on the XY-plane where `points[i] = [xi, yi]`. Each point is represented as an integer coordinate `[xi, yi]`.

Return the area of the largest triangle that can be formed by any three different points. Answers within `10^-5` of the true value will be accepted.

---

## Constraints
- `3 <= points.length <= 50`
- `points[i].length == 2`
- `-50 <= points[i][0], points[i][1] <= 50`

---

## Approach
Use the shoelace formula with brute force enumeration to find the largest triangle area among all possible triplets of points.

### Shoelace Formula
The shoelace formula calculates the area of a polygon given its vertices. For three points (x1,y1), (x2,y2), (x3,y3), the area is:

```
Area = 0.5 * |x1(y2 − y3) + x2(y3 − y1) + x3(y1 − y2)|
```

**Algorithm:**
1. Iterate through all possible combinations of three points using three nested loops
2. For each triplet, calculate the area using the shoelace formula
3. Keep track of the maximum area found
4. Return the maximum area

This approach works because:
- The shoelace formula gives exact area for any three points
- We need to check all possible triangles since n ≤ 50
- The absolute value handles clockwise and counterclockwise order
- The 0.5 factor gives the actual geometric area

---

## Dry Run
Example Input: `points = [[0,0],[0,1],[1,0],[0,2],[2,0]]`

Step-by-step execution:

**Triplet (0,0), (0,1), (1,0):**
- x1=0, y1=0, x2=0, y2=1, x3=1, y3=0
- Area = 0.5 * |0*(1-0) + 0*(0-0) + 1*(0-1)| = 0.5 * |0 + 0 + 1*(-1)| = 0.5 * 1 = 0.5

**Triplet (0,0), (0,2), (2,0):**
- x1=0, y1=0, x2=0, y2=2, x3=2, y3=0
- Area = 0.5 * |0*(2-0) + 0*(0-0) + 2*(0-2)| = 0.5 * |0 + 0 + 2*(-2)| = 0.5 * 4 = 2.0

**Triplet (0,1), (0,2), (2,0):**
- x1=0, y1=1, x2=0, y2=2, x3=2, y3=0
- Area = 0.5 * |0*(2-0) + 0*(0-1) + 2*(1-2)| = 0.5 * |0 + 0 + 2*(-1)| = 0.5 * 2 = 1.0

**Other triplets give smaller areas...**

Maximum area found = 2.0

---

## Solution
```java
class Solution {
    public double largestTriangleArea(int[][] points) {
        int length = points.length;
        double maxArea = Double.MIN_VALUE;

        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {

                    int x1 = points[i][0], y1 = points[i][1];
                    int x2 = points[j][0], y2 = points[j][1];
                    int x3 = points[k][0], y3 = points[k][1];

                    double area = 0.5 * Math.abs(
                            x1 * (y2 - y3) +
                            x2 * (y3 - y1) +
                            x3 * (y1 - y2));

                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n³) - three nested loops iterate through all combinations of three points
- **Space Complexity:** O(1) - only using constant extra space for variables

---

## Alternative Approaches

### Approach 2: Convex Hull + Rotating Calipers (More Optimal)
For larger constraints, we could use:
1. Compute the convex hull of all points (O(n log n))
2. Use rotating calipers to find the maximum area triangle (O(n))

**Time Complexity:** O(n log n) - much more efficient for large n
**Space Complexity:** O(n) - for storing convex hull

### Approach 3: Random Sampling
For very large point sets:
1. Randomly sample triplets of points
2. Calculate areas and track maximum
3. Trade accuracy for performance

**Time Complexity:** O(k) - where k is number of samples
**Space Complexity:** O(1) - constant space
