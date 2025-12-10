# Alice and Bob Playing Flower Game - <a href="https://leetcode.com/problems/alice-and-bob-playing-flower-game/" target="_blank">Link</a>

## Question Description
Alice and Bob have flowers in a garden. Alice has n flowers and Bob has m flowers. They play a game where in each round, they both pick one flower each. If the sum of the number of petals of their flowers is odd, Alice wins. Otherwise, Bob wins.

Return the total number of rounds where Alice wins.

---

## Constraints
- `1 <= n, m <= 10^5`

---

## Approach
Notice that Alice wins when the sum of petals is odd. Since we don't know the actual petal counts, we need to think differently.

Consider the parity (even/odd nature) of the flowers:
- Let A be the set of Alice's flowers (1 to n)
- Let B be the set of Bob's flowers (1 to m)

For any pair (a, b) where a ∈ A and b ∈ B:
- If a and b have different parity (one even, one odd), then a + b is odd → Alice wins
- If a and b have same parity (both even or both odd), then a + b is even → Bob wins

So the number of winning rounds for Alice is: (number of odd a) × (number of even b) + (number of even a) × (number of odd b)

This simplifies to: (n/2) × (m - m/2) + (n - n/2) × (m/2) = (n*m)/2

**Why this approach works:**
- The mathematical simplification shows that exactly half the combinations result in odd sums
- This holds regardless of the actual petal counts
- Much more efficient than simulating all possible pairs

**Alternative approaches considered:**
- Could simulate all pairs, but would be O(n*m) time complexity
- Could use nested loops, but inefficient for large n, m

---

## Dry Run
Example Input: `n = 3, m = 2`

Step-by-step execution:
- Alice's flowers: 1, 2, 3 (odds: 1,3; evens: 2)
- Bob's flowers: 1, 2 (odds: 1; evens: 2)
- Winning combinations for Alice:
  - (1,2): 1+2=3 (odd) ✓
  - (2,1): 2+1=3 (odd) ✓
  - (3,2): 3+2=5 (odd) ✓
- Total winning rounds: 3

Using formula: (3*2)/2 = 3 ✓

Example Input: `n = 1, m = 1`
- Alice's flowers: 1 (odd)
- Bob's flowers: 1 (odd)
- Winning combinations: (1,1): 1+1=2 (even) → Bob wins
- Total winning rounds: 0

Using formula: (1*1)/2 = 0 ✓

---

## Solution
```java
class Solution {
    public long flowerGame(int n, int m) {
        return (long) n * m / 2;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(1) - simple arithmetic operation
- **Space Complexity:** O(1) - no extra space used
  
[Back to All Problems](../README.md) 
