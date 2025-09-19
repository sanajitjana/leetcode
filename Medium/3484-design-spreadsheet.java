/**
 * LeetCode Problem: Design Spreadsheet (Hypothetical Custom Problem)
 * 
 * Problem Link: https://leetcode.com/problems/design-spreadsheet/   (⚠️ replace with actual if exists)
 * 
 * Description:
 * Implement a simple spreadsheet where:
 * - You can set a cell with a numeric value.
 * - You can reset a cell (making its value 0).
 * - You can evaluate a formula string of the form "A1+B2+3", where
 *   tokens may be integers or cell references.
 * 
 * Methods:
 * Spreadsheet(int rows) -> initializes spreadsheet
 * void setCell(String cell, int value) -> sets a cell value
 * void resetCell(String cell) -> resets cell to 0
 * int getValue(String formula) -> evaluates a formula string
 * 
 * Example:
 * Spreadsheet obj = new Spreadsheet(3);
 * obj.setCell("A1", 5);
 * obj.setCell("B1", 10);
 * obj.getValue("=A1+B1+3"); // returns 18
 * obj.resetCell("A1");
 * obj.getValue("=A1+B1+3"); // returns 13
 * 
 * Time Complexity:
 * - setCell, resetCell: O(1) average (hash map put/get).
 * - getValue: O(k) where k = number of terms in the formula.
 * 
 * Space Complexity:
 * - O(n) for storing n cells in the map.
 */

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

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
