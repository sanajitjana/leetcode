# Evaluate Reverse Polish Notation - <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation" target="_blank">Link</a>

## Question Description
You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

- The valid operators are '+', '-', '*', and '/'.
- Each operand may be an integer or another expression.
- The division between two integers always truncates toward zero.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in a reverse polish notation.
- The answer and all the intermediate calculations can be represented in a 32-bit integer.

---

## Constraints
- 1 <= tokens.length <= 10^4
- tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].

---

## Approach
Use a stack to evaluate the Reverse Polish Notation expression. Iterate through each token in the array. If the token is a number, push it onto the stack. If the token is an operator, pop the top two elements from the stack, apply the operation (with the first popped as the right operand and the second as the left), and push the result back onto the stack. At the end, the stack will contain the final result.

---

## Dry Run
Example Input: tokens = ["2","1","+","3","*"]

- Token "2": push 2. Stack: [2]
- Token "1": push 1. Stack: [2,1]
- Token "+": pop 1 and 2, compute 2+1=3, push 3. Stack: [3]
- Token "3": push 3. Stack: [3,3]
- Token "*": pop 3 and 3, compute 3*3=9, push 9. Stack: [9]

Output: 9

---

## Solution
```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-") ||
                    token.equals("*") || token.equals("/")) {

                int b = stack.pop();
                int a = stack.pop();

                int result;
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }

                stack.push(result);

            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - each token is processed once.
- **Space Complexity:** O(n) - stack can hold up to n elements in the worst case.  
[Back to All Problems](../README.md) 
