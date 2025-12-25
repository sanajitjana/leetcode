# Maximize Happiness of Selected Children - <a href="https://leetcode.com/problems/maximize-happiness-of-selected-children" target="_blank">Link</a>

## Question Description
You are given an array happiness of length n, and a positive integer k.

There are n children standing in a queue, where the ith child has happiness value happiness[i]. You want to select k children from these n children in k turns.

In each turn, when you select a child, the happiness value of all the children that have not been selected till now decreases by 1. Note that the happiness value cannot become negative and gets decremented only if it is positive.

Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.

---

## Constraints
- `1 <= n == happiness.length <= 2 * 10^5`
- `1 <= happiness[i] <= 10^8`
- `1 <= k <= n`

---

## Approach
Sort the happiness array in ascending order. Then, iterate from the largest happiness value backwards. For each child selected, add the maximum of 0 and (happiness[i] - pickCount) to the sum, and increment pickCount. Stop after selecting k children.

This greedy approach works because selecting the highest remaining happiness first maximizes the sum, and the decrement affects all unselected children equally.

---

## Dry Run
Example Input: `happiness = [1,2,3], k = 2`

Sorted: [1,2,3]

Start from i=2 (3), pickCount=0, 3-0=3 >0, sum=3, pickCount=1

i=1 (2), 2-1=1 >0, sum=4, pickCount=2

Done.

Final Answer = 4

---

## Solution
```java
import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long sum = 0, pickCount = 0;
        Arrays.sort(happiness);
        for (int i = happiness.length - 1; i >= 0; i--) {
            if (happiness[i] - pickCount > 0) {
                sum += happiness[i] - pickCount;
                pickCount++;
            }
            if (pickCount == k)
                break;
        }
        return sum;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n log n) - due to sorting
- **Space Complexity:** O(1) - sorting is in-place

[Back to All Problems](../README.md)