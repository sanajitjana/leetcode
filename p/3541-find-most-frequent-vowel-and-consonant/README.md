# Find Most Frequent Vowel and Consonant - <a href="https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/" target="_blank">Link</a>

## Question Description
Given a string, count the frequencies of vowels and consonants. Find the maximum frequency among vowels and the maximum frequency among consonants. Return the sum of these two maximum frequencies.

---

## Constraints
- 1 <= s.length <= 100
- s consists of lowercase English letters.

---

## Approach 1: Using Arrays
- Pre-mark vowels in a boolean array of size 26 for O(1) lookup.
- Use two frequency arrays (size 26) to count vowels and consonants separately.
- Iterate over each character in the string:
  - If it's a vowel, increment its count in the vowel frequency array.
  - Otherwise, increment its count in the consonant frequency array.
- Scan both frequency arrays to find max frequency among vowels and consonants.
- Return their sum.

---

## Dry Run (Approach 1)
Example Input: s = "hello"

Step-by-step execution:
- Vowels: e=1, o=1, maxV=1
- Consonants: h=1, l=2, maxC=2
- Sum = 3

---

## Solution (Approach 1)
```java
class Solution {
    public int maxFreqSum(String s) {

        boolean[] vowels = new boolean[26];
        for (char ch : new char[] { 'a', 'e', 'i', 'o', 'u' }) {
            vowels[ch - 'a'] = true;
        }

        int[] vowFreq = new int[26];
        int[] conFreq = new int[26];

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';
            if (vowels[index]) {
                vowFreq[index]++;
            } else {
                conFreq[index]++;
            }
        }

        int maxVow = 0;
        int maxCon = 0;
        for (int i = 0; i < 26; i++) {
            maxVow = Math.max(maxVow, vowFreq[i]);
            maxCon = Math.max(maxCon, conFreq[i]);
        }

        return maxVow + maxCon;
    }
}
```

---

## Time and Space Complexity (Approach 1)
- **Time Complexity:** O(n + 26) ≈ O(n), where n = length of the string
- **Space Complexity:** O(26) ≈ O(1), constant extra space

---

## Approach 2: Using HashMaps (Optimized)
- Use two HashMaps: one for vowels, one for consonants.
- Iterate over each character in the string.
  - If it's a vowel, update the vowel map.
  - Otherwise, update the consonant map.
- Find the maximum frequency from vowels and from consonants.
- Return their sum.

---

## Dry Run (Approach 2)
Example Input: s = "hello"

Step-by-step execution:
- Vowels: e=1, o=1, maxV=1
- Consonants: h=1, l=2, maxC=2
- Sum = 3

---

## Solution (Approach 2)
```java
class Solution {
    HashMap<Character, Integer> vMap = new HashMap<>();
    HashMap<Character, Integer> cMap = new HashMap<>();

    public int maxFreqSum(String s) {
        for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vMap.put(ch, vMap.getOrDefault(ch, 0) + 1);
            } else {
                cMap.put(ch, cMap.getOrDefault(ch, 0) + 1);
            }
        }

        int maxV = 0;
        // find max vowel frequency
        for (int val : vMap.values()) {
            if (val > maxV) {
                maxV = val;
            }
        }

        int maxC = 0;
        // find max consonant frequency
        for (int val : cMap.values()) {
            if (val > maxC) {
                maxC = val;
            }
        }

        return maxV + maxC;
    }
}
```

---

## Time and Space Complexity (Approach 2)
- **Time Complexity:** O(n) where n = length of string
- **Space Complexity:** O(k) where k = number of distinct characters (at most 26 for lowercase English letters)
