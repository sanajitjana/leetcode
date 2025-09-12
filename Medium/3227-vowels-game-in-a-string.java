/**
 * Problem: 3227. Vowels Game in a String
 * 
 * Alice and Bob are playing a game on a string s.  
 * - Alice removes any non-empty substring containing an odd number of vowels.
 * - Bob removes any non-empty substring containing an even number of vowels.  
 * The game starts with Alice and they take turns optimally.  
 * The player who cannot make a move loses.  
 * 
 * Return true if Alice wins the game, false otherwise.
 * 
 * Approach:
 * The key insight is simple:
 * - If there is at least one vowel in the string, Alice can remove a substring containing exactly one vowel.
 * - This leaves Bob in a position where the vowel count is now reduced by one.
 * - Since both play optimally, Alice can always force a win by repeating this process.
 * 
 * So, the solution simply checks whether there is at least one vowel in the string.
 * 
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), constant space used.
 */
public class Solution {
    public boolean doesAliceWin(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                return true;
            }
        }
        return false;
    }
}
