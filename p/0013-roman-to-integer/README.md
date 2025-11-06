# Roman to Integer - <a href="https://leetcode.com/problems/roman-to-integer/" target="_blank">Link</a>

## Question Description
Roman numerals are represented by seven different symbols: I, V, X, L, C, D, and M.

```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

For example, `2` is written as `II` in Roman numeral, just two ones added together. `12` is written as `XII`, which is simply `X + II`. The number `27` is written as `XXVII`, which is `XX + V + II`.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not `IIII`. Instead, the number four is written as `IV`. Because the one is placed before the five, it is subtracted. The same principle applies to the number nine, which is written as `IX`. There are six instances where subtraction is used:

- `I` can be placed before `V` (5) and `X` (10) to make 4 and 9.
- `X` can be placed before `L` (50) and `C` (100) to make 40 and 90.
- `C` can be placed before `D` (500) and `M` (1000) to make 400 and 900.

Given a Roman numeral, convert it to an integer.

---

## Constraints
- `1 <= s.length <= 15`
- `s` contains only the characters `('I', 'V', 'X', 'L', 'C', 'D', 'M')`.
- It is guaranteed that `s` is a valid Roman numeral in the range `[1, 3999]`.

---

## Approach
Use a HashMap to store the values of Roman numerals. Iterate through the string from left to right, and for each character, check if the current value is less than the next character's value. If it is, subtract the current value; otherwise, add it.

This approach works because Roman numerals follow a specific pattern where smaller values before larger values indicate subtraction. For example, in "IV", I (1) is less than V (5), so we subtract 1. In "VI", V (5) is greater than I (1), so we add both values.

---

## Dry Run
Example Input: `s = "MCMXCIV"`

Step-by-step execution:
- i=0, char='M' (1000), next='C' (100), 1000 > 100 → res = 1000
- i=1, char='C' (100), next='M' (1000), 100 < 1000 → res = 1000 - 100 = 900
- i=2, char='M' (1000), next='X' (10), 1000 > 10 → res = 900 + 1000 = 1900
- i=3, char='X' (10), next='C' (100), 10 < 100 → res = 1900 - 10 = 1890
- i=4, char='C' (100), next='I' (1), 100 > 1 → res = 1890 + 100 = 1990
- i=5, char='I' (1), next='V' (5), 1 < 5 → res = 1990 - 1 = 1989
- i=6, char='V' (5), no next character → res = 1989 + 5 = 1994

Final Answer = `1994`

---

## Solution
```java
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res=0;
        for(int i=0; i<s.length(); i++){
            if(i<s.length()-1 && map.get(s.charAt(i))<map.get(s.charAt(i+1))){
                res-=map.get(s.charAt(i));
            }else{
                res+=map.get(s.charAt(i));
            }
        }
        return res;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the string where n is the length of s
- **Space Complexity:** O(1) - constant space for the hashmap with fixed size (7 entries)