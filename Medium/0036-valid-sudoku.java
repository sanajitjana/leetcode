// LeetCode: https://leetcode.com/problems/valid-sudoku/
// ---------------------------------------------------------
// Problem Statement:
// Determine if a 9 x 9 Sudoku board is valid. 
// Only the filled cells need to be validated according to these rules:
//   1. Each row must contain the digits 1-9 without repetition.
//   2. Each column must contain the digits 1-9 without repetition.
//   3. Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.
// Note: A Sudoku board (partially filled) could be valid but is not necessarily solvable.
//
// ---------------------------------------------------------
// Approach:
// - Use a HashSet to track digits in each row, column, and 3x3 sub-box.
// - For rows: Iterate row-wise and check duplicates.
// - For columns: Iterate col-wise and check duplicates.
// - For 3x3 sub-boxes: Divide board into 9 blocks using (blockRow, blockCol)
//   and iterate inside each 3x3 block.
// - If at any point a duplicate is found, return false.
// - If all checks pass, return true.
//
// ---------------------------------------------------------
// Complexity Analysis:
// Time Complexity: O(9^2) = O(81) ≈ O(1)
//   - We scan each cell at most 3 times (row, col, box).
//   - Constant-time operations in sets.
//   - Overall complexity is effectively constant since board size is fixed.
//
// Space Complexity: O(9) per row/col/box = O(1)
//   - Extra space comes from the HashSet used in each validation step.
//   - Board size is fixed, so extra memory usage is constant.
//
// ---------------------------------------------------------
// Notes for Future:
// - This solution validates *only* the current board state. It does not attempt to solve the Sudoku.
// - If extending this to a Sudoku solver, backtracking would be required.
// ---------------------------------------------------------

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        // Check rows
        for (int i = 0; i < m; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    if (set.contains(ch))
                        return false;
                    else
                        set.add(ch);
                }
            }
        }

        // Check cols
        for (int i = 0; i < m; i++) {
            Set<Character> set = new HashSet<>();
            for (int k = 0; k < m; k++) {
                char ch = board[k][i];
                if (ch != '.') {
                    if (set.contains(ch))
                        return false;
                    else
                        set.add(ch);
                }
            }
        }

        // Check 3x3 sub-boxes
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                Set<Character> set = new HashSet<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char ch = board[blockRow * 3 + i][blockCol * 3 + j];
                        if (ch != '.') {
                            if (set.contains(ch))
                                return false;
                            set.add(ch);
                        }
                    }
                }
            }
        }

        return true;
    }
}
