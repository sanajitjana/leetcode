/**
 * Problem: Generate an array of n unique integers such that their sum is 0.
 * 
 * Approach 1:
 * - Start from 1 and alternate adding -num and +num into the array.
 * - If n is odd, include 0 in the array.
 * - This ensures symmetry and guarantees the sum is zero.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] sumZero(int n) {
        if (n == 1) return new int[n];
        
        int[] arr = new int[n];
        int index = 0;
        if (n % 2 == 1) {
            index = 1; // Leave space for 0 if n is odd
        }
        
        boolean flag = true;
        int num = 1;
        
        while (index < n) {
            if (flag) {
                arr[index++] = -num;
                flag = false;
            } else {
                arr[index++] = num;
                flag = true;
                num++;
            }
        }
        
        return arr;
    }
}

/**
 * Approach 2 (Optimized and Cleaner):
 * - Pair numbers symmetrically: -1 & 1, -2 & 2, ..., until n/2 pairs.
 * - If n is odd, append 0 at the end.
 * - Cleaner logic without flags or while-loops.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class Solution {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        int pairs = n / 2;
        int index = 0;
        
        for (int i = 1; i <= pairs; i++) {
            arr[index++] = -i;
            arr[index++] = i;
        }
        
        if (n % 2 == 1) {
            arr[index] = 0;
        }
        
        return arr;
    }
}
