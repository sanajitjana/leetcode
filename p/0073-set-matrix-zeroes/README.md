# Set Matrix Zeroes - <a href="https://leetcode.com/problems/set-matrix-zeroes/" target="_blank">Link</a>

## Question Description
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

---

## Constraints
- `m == matrix.length`
- `n == matrix[0].length`
- `1 <= m, n <= 200`
- `-2^31 <= matrix[i][j] <= 2^31 - 1`

---

## Approach
Use the first row and first column as markers to record which rows/columns should be zeroed. Track separately if the first row or first column need to be zeroed to avoid losing marker info. In the second pass, set entire rows/columns to zero based on these markers.

**Why this approach works:**
- Uses O(1) extra space by utilizing the matrix itself as storage
- First pass marks which rows and columns need to be zeroed
- Second pass actually sets the zeros
- Handles edge cases for first row and first column separately

**Alternative approaches considered:**
- Could use separate arrays to track rows and columns, but that would use O(m+n) space
- Could use a set to store positions, but that would also use extra space

---

## Dry Run
Example Input: `matrix = [[1,1,1],[1,0,1],[1,1,1]]`

Step-by-step execution:
- Step 1: Find 0 at position [1][1], mark row 1 and column 1
- Step 2: Set matrix[0][1] = 0 and matrix[1][0] = 0 as markers
- Step 3: Set entire column 1 to 0 (except already processed cells)
- Step 4: Set entire row 1 to 0 (except already processed cells)
- Step 5: Handle first row and first column if needed

Final Answer = `[[1,0,1],[0,0,0],[1,0,1]]`

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:** O(m * n) - each cell is visited a constant number of times
- **Space Complexity:** O(1) - in-place, only a few extra variables
