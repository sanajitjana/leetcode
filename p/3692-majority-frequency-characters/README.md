# Majority Frequency Characters - <a href="https://leetcode.com/problems/majority-frequency-characters/" target="_blank">Link</a>

## Question Description
You are given a string s consisting of lowercase English letters. The frequency group for a value k is the set of characters that appear exactly k times in s. The majority frequency group is the group that contains the largest number of distinct characters. If two or more groups tie for the largest size, choose the group with the larger frequency k. Return a string containing all characters in the majority frequency group, in any order.

---

## Constraints
- 1 <= s.length <= 100
- s consists of lowercase English letters.

---

## Approach
- Step 1: Count frequencies of each character using a HashMap.
- Step 2: Build frequency groups: for each frequency k, store all characters that appear k times.
- Step 3: Iterate over all groups:
     - Track the group with the maximum size (# of distinct characters).
     - If there is a tie in size, pick the group with the larger frequency value.
- Step 4: Return the characters of the chosen group.

---

## Dry Run
s = "aabbccc"

Frequencies:
  a -> 2
  b -> 2
  c -> 3

Groups:
  freq=2 → "ab"
  freq=3 → "c"

Group sizes:
  "ab" → size=2
  "c"  → size=1

Best = "ab" (since size=2 > size=1)
Output = "ab"

---

## Solution
```java
class Solution {
    public String majorityFrequencyGroup(String s) {
        // Step 1: frequency map
        HashMap<Character, Integer> fre = new HashMap<>();
        for (char ch : s.toCharArray()) {
            fre.put(ch, fre.getOrDefault(ch, 0) + 1);
        }

        // Step 2: group by frequency
        HashMap<Integer, String> group = new HashMap<>();
        for (Map.Entry<Character, Integer> pair : fre.entrySet()) {
            int val = pair.getValue();
            char ch = pair.getKey();
            group.put(val, group.getOrDefault(val, "") + ch);
        }

        // Step 3: choose best group
        int bestSize = 0;
        int bestFreq = 0;
        String ans = "";

        for (Map.Entry<Integer, String> pair : group.entrySet()) {
            String chars = pair.getValue();
            int freq = pair.getKey();
            int size = chars.length();

            if (size > bestSize || (size == bestSize && freq > bestFreq)) {
                bestSize = size;
                bestFreq = freq;
                ans = chars;
            }
        }

        return ans;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) — One pass to count frequencies, one pass to build groups, one pass to find the best group.
- **Space Complexity:** O(n) — Storing frequency map and groups.
