# Vowels Game in a String - [Leetcode Link](https://leetcode.com/problems/vowels-game-in-a-string/)

## Question Description
Alice and Bob are playing a game on a string s.

- Alice removes any non-empty substring containing an odd number of vowels
- Bob removes any non-empty substring containing an even number of vowels
- The game starts with Alice and they take turns optimally
- The player who cannot make a move loses

Return true if Alice wins the game, false otherwise.

---

## Constraints
- `1 <= s.length <= 10^5`
- `s` consists of printable ASCII characters

---

## Approach
The key insight is simple:
- If there is at least one vowel in the string, Alice can remove a substring containing exactly one vowel
- This leaves Bob in a position where the vowel count is now reduced by one
- Since both play optimally, Alice can always force a win by repeating this process

So, the solution simply checks whether there is at least one vowel in the string.

**Why this approach works:**
- Alice can always find a single vowel to remove if vowels exist
- Each move reduces the vowel count by 1 (for odd count substrings)
- Game theory shows Alice can force a win when vowels are present

**Alternative approaches considered:**
- Could analyze all possible moves, but would be exponential time
- Could use dynamic programming, but simple check is sufficient

---

## Dry Run
Example Input: `s = "leetcode"`

Step-by-step execution:
- Step 1: Check each character for vowels
- Step 2: 'l' - not vowel
- Step 3: 'e' - is vowel! Return true

Final Answer = `true`

Example Input: `s = "bbcd"`

Step-by-step execution:
- Step 1: Check each character for vowels
- Step 2: 'b' - not vowel
- Step 3: 'b' - not vowel
- Step 4: 'c' - not vowel
- Step 5: 'd' - not vowel
- Step 6: No vowels found, return false

Final Answer = `false`

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) where n is the length of the string
- **Space Complexity:** O(1) - constant space used
