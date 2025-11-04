# Sort Vowels in a String - [Link](https://leetcode.com/problems/sort-vowels-in-a-string/)

## Question Description
Given a string s, sort only the vowels in ascending order and keep other characters in place.

Vowels are: 'a', 'e', 'i', 'o', 'u' (both lowercase and uppercase).

---

## Constraints
- `1 <= s.length <= 10^5`
- `s` consists of printable ASCII characters

---

## Approach
Two approaches are provided:

**Approach 1:**
- Extract vowels by checking "AEIOUaeiou".indexOf(c)
- Sort the extracted vowels
- Rebuild the string by replacing vowels in order

**Approach 2:**
- Use a Set<Character> of vowels for O(1) lookup
- Extract vowels into a list
- Sort the list
- Reconstruct the string by replacing vowels from the sorted list

**Why this approach works:**
- Extract all vowels first, sort them, then replace in original positions
- Maintains relative order of non-vowel characters
- Sorts vowels in ascending order as required

**Alternative approaches considered:**
- Could use in-place swapping, but extracting and sorting is clearer
- Could use counting sort for vowels since limited characters, but Collections.sort() is sufficient

---

## Dry Run
Example Input: `s = "lEetcOde"`

**Approach 2 Dry Run:**
- Step 1: Vowels set = {a,e,i,o,u,A,E,I,O,U}
- Step 2: Extract vowels: [E,e,O] (positions 1,4,6)
- Step 3: Sort vowels: [E,O,e]
- Step 4: Rebuild string:
  - Position 0: 'l' (not vowel) → 'l'
  - Position 1: 'E' (vowel) → 'E' from sorted list
  - Position 2: 't' (not vowel) → 't'
  - Position 3: 'c' (not vowel) → 'c'
  - Position 4: 'O' (vowel) → 'O' from sorted list
  - Position 5: 'd' (not vowel) → 'd'
  - Position 6: 'e' (vowel) → 'e' from sorted list

Final Answer = `"lEOtcOde"`

---

## Solution
```java
import java.util.*;

class Solution {

    // Approach 1
    public String sortVowelsApproach1(String s) {
        List<Character> vowels = new ArrayList<>();
        
        // Extract vowels
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) {
                vowels.add(c);
            }
        }

        // Sort vowels
        Collections.sort(vowels);

        // Rebuild string with sorted vowels
        StringBuilder result = new StringBuilder();
        int vIndex = 0;
        for (char c : s.toCharArray()) {
            if ("AEIOUaeiou".indexOf(c) != -1) {
                result.append(vowels.get(vIndex++));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Approach 2
    public String sortVowelsApproach2(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<Character> extractedVowels = new ArrayList<>();

        // Extract vowels
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                extractedVowels.add(c);
            }
        }

        // Sort vowels
        Collections.sort(extractedVowels);

        // Rebuild string with sorted vowels
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append(extractedVowels.get(index++));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n log n) for both approaches (O(n) to extract vowels, O(k log k) to sort, O(n) to rebuild)
- **Space Complexity:** O(n) for both approaches (storing extracted vowels and result string)
