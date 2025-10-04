# [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)

## Question Description
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to these rules:
1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.

Note: A Sudoku board (partially filled) could be valid but is not necessarily solvable.

---

## Constraints
- `board.length == 9`
- `board[i].length == 9`
- `board[i][j]` is a digit `'1'-'9'` or `'.'`
- The board may be partially filled

---

## Approach
Use HashSet to track digits in each row, column, and 3x3 sub-box.

**Why this approach works:**
- HashSet provides O(1) average time complexity for lookup and insertion operations
- We need to ensure no duplicate digits appear in rows, columns, or 3x3 boxes
- By using separate HashSets for each validation, we can efficiently track seen digits

**Alternative approaches considered:**
- Could use arrays instead of HashSets for digit tracking, but HashSets are more readable and handle the logic more elegantly
- Could use bitwise operations for memory optimization, but HashSet approach is clearer and sufficient for this problem

---

## Dry Run
Example Input:
```
board = [
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
```

Step-by-step execution:
- Step 1: Validate all rows - check each row has unique digits (skip '.')
- Step 2: Validate all columns - check each column has unique digits (skip '.')
- Step 3: Validate all 3x3 sub-boxes - check each 3x3 block has unique digits (skip '.')

Final Answer = `true` (this is a valid Sudoku board)

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:** O(1) - We scan each cell at most 3 times (row, column, box validation), board size is fixed at 9x9
- **Space Complexity:** O(1) - Extra space comes from HashSets used in validation, but board size is fixed so memory usage is constant