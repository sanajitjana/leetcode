# Maximum Number of Words You Can Type - [Leetcode Link](https://leetcode.com/problems/maximum-number-of-words-you-can-type/)

## Question Description
There is a string `text` consisting of words separated by a single space, and a string `brokenLetters` containing unique broken keyboard letters. A word can be typed if none of its letters are in `brokenLetters`. Return the number of words in `text` that can be typed.

---

## Constraints
- 1 <= text.length <= 10^4
- 0 <= brokenLetters.length <= 26
- text consists of words separated by a single space without any leading or trailing spaces.
- Each word only consists of lowercase English letters.
- brokenLetters consists of distinct lowercase English letters.

---

## Approach
1. Create a boolean array of size 26 to mark broken letters.
2. For each broken letter, mark its corresponding index as true.
3. Split the `text` into words by spaces.
4. For each word, check every character:
   - If any character is broken, skip counting this word.
   - Otherwise, count it as a valid word.

---

## Dry Run
Example Input: text = "hello world", brokenLetters = "ad"

Step-by-step execution:
- Mark broken letters: 'a' and 'd' are broken.
- Split text: ["hello", "world"]
- Check "hello": 'h','e','l','l','o' - none broken, count = 1
- Check "world": 'w','o','r','l','d' - 'd' is broken, skip
- Final count = 1

---

## Solution
```java
public class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] charExists = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            charExists[c - 'a'] = true;
        }

        String[] textArr = text.split(" ");
        int count = 0;

        for (String word : textArr) {
            boolean canType = true;
            for (int i = 0; i < word.length(); i++) {
                if (charExists[word.charAt(i) - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) {
                count++;
            }
        }

        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n + m) — n = length of `text`, m = length of `brokenLetters`
- **Space Complexity:** O(1) — Fixed array of size 26 is used for tracking broken letters.
