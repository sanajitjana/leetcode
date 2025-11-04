# Find N Unique Integers Sum up to Zero - [Link](https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/)

## Question Description
Given an integer `n`, return **any array** containing `n` **unique** integers such that they add up to `0`.

---

## Constraints
- `1 <= n <= 1000`

---

## Approaches

### Approach 1: Flag-Based Alternating Approach
Use a flag to alternate between negative and positive numbers while building the array.

**Algorithm:**
1. Handle edge case: if n == 1, return empty array (no unique integers sum to 0)
2. Create array of size n
3. If n is odd, start from index 1 (reserve index 0 for 0)
4. Use a flag to alternate between negative and positive numbers
5. Start with num = 1 and increment after placing positive numbers
6. Place 0 at the beginning if n is odd

---

## Dry Run (Approach 1)
Example Input: `n = 5`

Step-by-step execution:
- n = 5 (odd), so index = 1, arr = [0, 0, 0, 0, 0]
- flag = true, num = 1
- index = 1: flag=true, place -1, index=2, flag=false
- index = 2: flag=false, place 1, index=3, flag=true, num=2
- index = 3: flag=true, place -2, index=4, flag=false
- index = 4: flag=false, place 2, index=5, flag=true, num=3
- index = 5: reached end

Result: `[0, -1, 1, -2, 2]` (sum = 0)

---

## Solution (Approach 1)
```java
class Solution {
    public int[] sumZero(int n) {
        if (n == 1) return new int[n];

        int[] arr = new int[n];
        int index = 0;
        if (n % 2 == 1) {
            index = 1; // Leave space for 0 if n is odd
        }

        boolean flag = true;
        int num = 1;

        while (index < n) {
            if (flag) {
                arr[index++] = -num;
                flag = false;
            } else {
                arr[index++] = num;
                flag = true;
                num++;
            }
        }

        return arr;
    }
}
```

---

## Time and Space Complexity (Approach 1)
- **Time Complexity:** O(n) - single pass to fill the array
- **Space Complexity:** O(n) - for the output array

---

### Approach 2: Pair-Based Approach (Cleaner)
Create pairs of numbers that sum to zero: (-1, 1), (-2, 2), etc.

**Algorithm:**
1. Calculate number of pairs: pairs = n / 2
2. Create array of size n
3. Use a loop to place pairs: (-i, i) for i from 1 to pairs
4. If n is odd, place 0 at the end
5. This ensures sum is always 0

---

## Dry Run (Approach 2)
Example Input: `n = 5`

Step-by-step execution:
- pairs = 5 / 2 = 2
- index = 0
- i=1: arr[0] = -1, arr[1] = 1, index = 2
- i=2: arr[2] = -2, arr[3] = 2, index = 4
- n is odd, so arr[4] = 0

Result: `[-1, 1, -2, 2, 0]` (sum = 0)

Example Input: `n = 4`

Step-by-step execution:
- pairs = 4 / 2 = 2
- index = 0
- i=1: arr[0] = -1, arr[1] = 1, index = 2
- i=2: arr[2] = -2, arr[3] = 2, index = 4
- n is even, no 0 added

Result: `[-1, 1, -2, 2]` (sum = 0)

---

## Solution (Approach 2)
```java
class Solution {
    public int[] sumZero(int n) {
        int[] arr = new int[n];
        int pairs = n / 2;
        int index = 0;

        for (int i = 1; i <= pairs; i++) {
            arr[index++] = -i;
            arr[index++] = i;
        }

        if (n % 2 == 1) {
            arr[index] = 0;
        }

        return arr;
    }
}
```

---

## Time and Space Complexity (Approach 2)
- **Time Complexity:** O(n) - single loop for n/2 iterations
- **Space Complexity:** O(n) - for the output array

---

## Comparison of Approaches

| Approach | Time Complexity | Space Complexity | Readability | Edge Case Handling |
|----------|----------------|------------------|-------------|-------------------|
| **Approach 1** | O(n) | O(n) | Medium | Good |
| **Approach 2** | O(n) | O(n) | High | Excellent |

**Key Insight:** Both approaches generate the same mathematical pattern: pairs of numbers that cancel each other out, with 0 added for odd lengths.

**Why This Works:**
- For even n: n/2 pairs of (i, -i) sum to 0
- For odd n: n/2 pairs of (i, -i) + 0 = 0
- All numbers are unique by construction

**Alternative Mathematical Approaches:**
1. **Arithmetic Sequence:** `[0, 1, 2, ..., n-1]` transformed to sum to 0
2. **Random Selection:** Any n-1 numbers and their negative sum
