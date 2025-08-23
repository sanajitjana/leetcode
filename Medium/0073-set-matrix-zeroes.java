/**
 * Problem: 73. Set Matrix Zeroes
 * Difficulty: Medium
 * URL: https://leetcode.com/problems/set-matrix-zeroes/
 * 
 * Approach:
 * - Use the first row and first column as markers to record which rows/columns should be zeroed.
 * - Track separately if the first row or first column need to be zeroed to avoid losing marker info.
 * - In the second pass, set entire rows/columns to zero based on these markers.
 * 
 * Time Complexity: O(m * n)  // each cell is visited a constant number of times
 * Space Complexity: O(1)    // in-place, only a few extra variables
 */

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean r0F = false;
        boolean c0F = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && matrix[i][j] == 0) r0F = true;
                if (j == 0 && matrix[i][j] == 0) c0F = true;
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (r0F) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (c0F) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
