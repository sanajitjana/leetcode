# Strong Password Checker II - <a href="https://leetcode.com/problems/strong-password-checker-ii/" target="_blank">Link</a>

## Question Description
A password is said to be strong if:
- It has at least 8 characters.
- It contains at least one lowercase letter.
- It contains at least one uppercase letter.
- It contains at least one digit.
- It contains at least one special character from the string "!@#$%^&*()-+".
- It does not contain two identical characters in adjacent positions.

Given a string password, return true if it is a strong password. Otherwise, return false.

---

## Constraints
- 1 <= password.length <= 100
- password consists of ASCII characters.

---

## Approach
- Check if length is at least 8.
- Use boolean flags for lowercase, uppercase, digit, special.
- Iterate through the string, set flags and check for adjacent identical characters.
- Return true if all conditions are met.

---

## Dry Run
Example Input: password = "IloveLe3tcode!"

Step-by-step execution:
- Length: 13 >= 8
- Lower: 'l','o','v','e','e','t','c','o','d','e' - yes
- Upper: 'I','L','t','c' - yes
- Digit: '3' - yes
- Special: '!' - yes
- Adjacent: no two identical in a row
- Result: true

---

## Solution
```java
class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) return false;

        boolean lower = false, upper = false, digit = false, special = false;
        String specials = "!@#$%^&*()-+";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else if (specials.indexOf(c) >= 0) special = true;

            // check adjacent characters
            if (i > 0 && c == password.charAt(i - 1)) return false;
        }

        return lower && upper && digit && special;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) — iterate through the string once
- **Space Complexity:** O(1) — only a few boolean flags used
  
[Back to All Problems](../README.md) 
