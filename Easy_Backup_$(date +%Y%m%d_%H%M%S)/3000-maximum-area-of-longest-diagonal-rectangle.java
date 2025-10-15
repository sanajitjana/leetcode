/**
 * Problem: 2679. Sum in a Matrix (variation: Area of Max Diagonal)
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/area-of-maximum-diagonal/
 * 
 * Approach:
 * - For each rectangle (length, width), compute the squared diagonal = l² + w².
 * - Also compute its area = l * w.
 * - Track the maximum diagonal squared.
 *   - If a rectangle has a larger diagonal, update max diagonal and max area.
 *   - If diagonal is equal to current max, take the larger area.
 * - Using squared diagonal avoids floating-point issues from Math.sqrt.
 * 
 * Time Complexity: O(n)   — one pass through all rectangles
 * Space Complexity: O(1) — only a few variables stored
 */

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
