/*
    Question: Largest Triangle Area
    Link: https://leetcode.com/problems/largest-triangle-area/

    Question Info:
    You are given an array of points on a 2D plane. Each point is represented as an integer coordinate [x, y].
    Your task is to return the largest area of a triangle formed by any three points in the given array.

    Approach:
    1. Use the shoelace formula (determinant method) to compute the area of a triangle formed by three points (x1,y1), (x2,y2), (x3,y3).
       Formula:
         Area = 0.5 * |x1(y2 − y3) + x2(y3 − y1) + x3(y1 − y2)|
    2. Iterate over all possible triplets of points using three nested loops.
    3. For each triplet, calculate the area using the formula and update the maximum area found.
    4. Return the maximum area.

    Dry Run:
    Example: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]

    - Pick (0,0), (0,1), (1,0) → Area = 0.5
    - Pick (0,0), (0,2), (2,0) → Area = 2
    - Pick (0,1), (0,2), (2,0) → Area = 1
    - After checking all triplets, maximum = 2

    Time Complexity: O(n^3), since we check all triplets of points.
    Space Complexity: O(1), only a few variables used.
*/

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
