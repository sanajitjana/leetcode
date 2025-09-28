/**
 * Link: https://leetcode.com/problems/compute-decimal-representation/
 * Title: Compute Decimal Representation
 *
 * Question:
 * You are given an integer n. Your task is to break it down into its decimal components.
 * For example:
 *   n = 4321 → [4000, 300, 20, 1]
 *   n = 5030 → [5000, 30]
 *   n = 7    → [7]
 *
 * Return the decimal representation as an array of integers, excluding zeros.
 *
 * --------------------------------------------------------------------
 * Approach:
 * - Start with place value = 1 (units place).
 * - While n > 0:
 *      - Extract the last digit (digit = n % 10).
 *      - If digit != 0, compute digit * place and store it.
 *      - Update n by dividing it by 10.
 *      - Update place by multiplying it by 10.
 * - Reverse the list since we collect from lowest place to highest.
 * - Convert the list into an integer array and return.
 *
 * --------------------------------------------------------------------
 * Dry Run:
 * n = 5030
 * place = 1
 * digit = 0 → skip
 * n = 503, place = 10
 * digit = 3 → add 30
 * n = 50, place = 100
 * digit = 0 → skip
 * n = 5, place = 1000
 * digit = 5 → add 5000
 * Result list = [30, 5000]
 * Reverse → [5000, 30]
 * Output = [5000, 30]
 *
 * --------------------------------------------------------------------
 * Time Complexity: O(log10(n)) 
 *   - We process each digit once.
 * Space Complexity: O(log10(n))
 *   - We store at most one value per digit.
 */

class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> base = new ArrayList<>();
        int place = 1;
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                base.add(digit * place);
            }
            n /= 10;
            place *= 10;
        }
        Collections.reverse(base);
        int[] list = new int[base.size()];
        for (int i = 0; i < base.size(); i++) {
            list[i] = base.get(i);
        }
        return list;
    }
}
