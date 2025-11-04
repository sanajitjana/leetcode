# Find Words Containing Character - [Link](https://leetcode.com/problems/find-words-containing-character/)

## Question Description
You are given a **0-indexed** array of strings `words` and a character `x`.

Return an array of **indices** representing the words that contain the character `x`.

Note that the returned array may be in **any order**.

---

## Constraints
- `1 <= words.length <= 50`
- `1 <= words[i].length <= 50`
- `x` is a lowercase English letter
- `words[i]` consists only of lowercase English letters

---

## Approach
Iterate through each word and check if it contains the target character `x`.

**Algorithm:**
1. Initialize an empty list to store indices of words containing `x`
2. For each word at index `i` in the words array:
   - Convert word to character array and check each character
   - If any character equals `x`, add `i` to result list and break
   - Break prevents duplicate indices if word contains `x` multiple times
3. Return the list of indices

This approach works because:
- We need to find all words that contain the character at least once
- Single pass through each word is sufficient
- Breaking after finding first occurrence optimizes performance
- Order doesn't matter as per problem statement

---

## Dry Run
Example 1: `words = ["leet","code"], x = 'e'`

Step-by-step execution:
- i=0, word="leet"
  - Check 'l' != 'e', continue
  - Check 'e' == 'e', add 0 to result, break
- i=1, word="code"
  - Check 'c' != 'e', continue
  - Check 'o' != 'e', continue
  - Check 'd' != 'e', continue
  - Check 'e' == 'e', add 1 to result, break

Result: `[0, 1]`

Example 2: `words = ["abc","bcd"], x = 'x'`

Step-by-step execution:
- i=0, word="abc"
  - Check 'a' != 'x', 'b' != 'x', 'c' != 'x', no match
- i=1, word="bcd"
  - Check 'b' != 'x', 'c' != 'x', 'd' != 'x', no match

Result: `[]` (empty array)

Example 3: `words = ["leet","leet"], x = 'e'`

Step-by-step execution:
- i=0, word="leet"
  - Check 'l' != 'e', 'e' == 'e', add 0 to result, break
- i=1, word="leet"
  - Check 'l' != 'e', 'e' == 'e', add 1 to result, break

Result: `[0, 1]` (duplicate indices allowed if same word appears twice)

---

## Solution
```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (char ch : words[i].toCharArray()) {
                if (ch == x) {
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(N × L) - where N is number of words, L is average word length
- **Space Complexity:** O(1) - excluding output list, only using constant extra space

---

## Alternative Approaches

### String contains() Method
```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(String.valueOf(x))) {
                result.add(i);
            }
        }
        return result;
    }
}
```

**Explanation:** Use Java's built-in `contains()` method instead of manual character checking.

**Time Complexity:** O(N × L) - contains() method still checks each character
**Space Complexity:** O(1) - excluding output list

### Stream API Approach (Java 8+)
```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].chars().anyMatch(ch -> ch == x)) {
                result.add(i);
            }
        }
        return result;
    }
}
```

**Explanation:** Use Java streams with `anyMatch()` to check if any character matches.

**Time Complexity:** O(N × L) - stream operations
**Space Complexity:** O(1) - excluding output list

### Index-Based Approach
```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) {
                result.add(i);
            }
        }
        return result;
    }
}
```

**Explanation:** Use `indexOf()` method which returns -1 if character not found.

**Time Complexity:** O(N × L) - indexOf() searches through string
**Space Complexity:** O(1) - excluding output list

### Parallel Processing (For Large Inputs)
```java
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        return IntStream.range(0, words.length)
                       .parallel()
                       .filter(i -> words[i].contains(String.valueOf(x)))
                       .boxed()
                       .collect(Collectors.toList());
    }
}
```

**Explanation:** Use parallel streams for better performance on large datasets.

**Time Complexity:** O(N × L) - with potential parallel speedup
**Space Complexity:** O(1) - excluding output list
