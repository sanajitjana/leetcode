# Design Spreadsheet - [Link](https://leetcode.com/problems/design-spreadsheet/)

## Question Description
Implement a simple spreadsheet where:

- You can set a cell with a numeric value
- You can reset a cell (making its value 0)
- You can evaluate a formula string of the form "A1+B2+3", where tokens may be integers or cell references

**Methods:**
- `Spreadsheet(int rows)` → initializes spreadsheet
- `void setCell(String cell, int value)` → sets a cell value
- `void resetCell(String cell)` → resets cell to 0
- `int getValue(String formula)` → evaluates a formula string

---

## Constraints
- `1 <= rows <= 1000`
- Cell references are in format "A1", "B2", etc. (A-Z for columns, 1-1000 for rows)
- Formula format: "=A1+B2+3" (starts with =, terms separated by +)
- `1 <= values <= 10^6`

---

## Approach
Use a HashMap to store cell values with cell references as keys.

**Operations:**
- `setCell`: Store value in HashMap with cell reference as key
- `resetCell`: Set cell value to 0 in HashMap
- `getValue`: Parse formula string, evaluate each term, sum results

**Formula parsing:**
- Remove '=' prefix
- Split by '+' to get individual terms
- For each term, check if it starts with letter (cell reference) or digit (number)
- Cell references: get value from HashMap (default 0 if not set)
- Numbers: parse as integer

**Why this approach works:**
- HashMap provides O(1) average lookup for cell values
- Simple string parsing handles the formula format efficiently
- Default value of 0 for unset cells matches spreadsheet behavior

**Alternative approaches considered:**
- Could use 2D array for fixed rows, but HashMap is more flexible
- Could use more complex expression parser, but simple split is sufficient

---

## Dry Run
Example Operations:
```
Spreadsheet obj = new Spreadsheet(3);
obj.setCell("A1", 5);
obj.setCell("B1", 10);
obj.getValue("=A1+B1+3"); // returns 18
obj.resetCell("A1");
obj.getValue("=A1+B1+3"); // returns 13
```

Step-by-step execution:
- Step 1: Initialize empty HashMap
- Step 2: setCell("A1", 5) → HashMap: {"A1": 5}
- Step 3: setCell("B1", 10) → HashMap: {"A1": 5, "B1": 10}
- Step 4: getValue("=A1+B1+3"):
  - Parse: ["A1", "B1", "3"]
  - A1 = 5, B1 = 10, 3 = 3
  - Sum = 18
- Step 5: resetCell("A1") → HashMap: {"A1": 0, "B1": 10}
- Step 6: getValue("=A1+B1+3"):
  - Parse: ["A1", "B1", "3"]
  - A1 = 0, B1 = 10, 3 = 3
  - Sum = 13

---

## Solution
```java
import java.util.*;

class Spreadsheet {

    private Map<String, Integer> cellMap = new HashMap<>();
    
    public Spreadsheet(int rows) {
        // rows parameter can be used for validation if needed
    }
    
    public void setCell(String cell, int value) {
        cellMap.put(cell, value);
    }
    
    public void resetCell(String cell) {
        cellMap.put(cell, 0);
    }
    
    public int getValue(String formula) {
        int sum = 0;
        // skip '=' at start
        for (String s : formula.substring(1).split("\\+")) {
            sum += mapToValue(s.trim());
        }
        return sum;
    }

    private int mapToValue(String s) {
        return Character.isLetter(s.charAt(0)) ? cellMap.getOrDefault(s, 0) : Integer.parseInt(s);
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:**
  - setCell, resetCell: O(1) average (hash map put/get)
  - getValue: O(k) where k = number of terms in the formula
- **Space Complexity:** O(n) for storing n cells in the map
