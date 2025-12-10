# Build an Array With Stack Operations - <a href="https://leetcode.com/problems/build-an-array-with-stack-operations/" target="_blank">Link</a>

## Question Description
You are given an integer array target and an integer n.

You have an empty stack with the two following operations:

    "Push": pushes an integer to the top of the stack.
    "Pop": removes the integer on the top of the stack.

You also have a stream of the integers in the range [1, n].

Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to target. You should follow the following rules:

    If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.
    If the stack is not empty, pop the integer at the top of the stack.
    If, at any moment, the elements in the stack (from the bottom to the top) are equal to target, do not read new integers from the stream and do not do more operations on the stack.

Return the stack operations needed to build target following the mentioned rules. If there are multiple valid answers, return any of them.

 

Example 1:

Input: target = [1,3], n = 3
Output: ["Push","Push","Pop","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Pop the integer on the top of the stack. s = [1].
Read 3 from the stream and push it to the stack. s = [1,3].

Example 2:

Input: target = [1,2,3], n = 3
Output: ["Push","Push","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Read 3 from the stream and push it to the stack. s = [1,2,3].

Example 3:

Input: target = [1,2], n = 4
Output: ["Push","Push"]
Explanation: Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
The answers that read integer 3 from the stream are not accepted.

 

Constraints:

    1 <= target.length <= 100
    1 <= n <= 100
    1 <= target[i] <= n
    target is strictly increasing.

---

## Approaches

### Approach 1: Simulation
Simulate the process by iterating through numbers from 1 to n. For each number, push it onto the stack. If it matches the current target element, increment the target index; otherwise, pop it immediately.

**Algorithm:**
1. Initialize an empty list to store the operations.
2. Initialize an index for the target array.
3. For each number i from 1 to n (while index < target.length):
   - Push "Push" to the list.
   - If i == target[index], increment index.
   - Else, push "Pop" to the list.
4. Return the list of operations.

---

## Dry Run
Example Input: target = [1,3], n = 3

Step-by-step execution:
- i=1: Push, 1 == target[0]=1, index=1, operations=["Push"]
- i=2: Push, 2 != target[1]=3, Pop, operations=["Push","Push","Pop"]
- i=3: Push, 3 == target[1]=3, index=2, operations=["Push","Push","Pop","Push"]

Final Answer = ["Push","Push","Pop","Push"]

---

## Solution
```java
import java.util.*;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();

        int index = 0;
        for (int i = 1; i <= n && index < target.length; i++) {
            if (target[index] == i) {
                ans.add("Push");
                index++;
            } else {
                ans.add("Push");
                ans.add("Pop");
            }
        }
        return ans;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - iterate through numbers up to n
- **Space Complexity:** O(1) - excluding the output list, only using constant extra space  
[Back to All Problems](../README.md) 
