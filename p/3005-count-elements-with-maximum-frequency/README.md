# Count Elements With Maximum Frequency - <a href="https://leetcode.com/problems/count-elements-with-maximum-frequency/" target="_blank">Link</a>

## Question Description
You are given an array `nums` consisting of positive integers.

Return the **total frequencies** of elements in `nums` such that those elements all have the **maximum frequency**.

In other words, return the sum of frequencies of all elements that appear with the highest frequency.

---

## Constraints
- `1 <= nums.length <= 100`
- `1 <= nums[i] <= 100`

---

## Approaches

### Approach 1: HashMap-Based Approach
Use a HashMap to count frequencies, then find maximum frequency and sum all elements with that frequency.

**Algorithm:**
1. Create a HashMap to store frequency of each number
2. Iterate through array and count frequencies using `getOrDefault`
3. Find the maximum frequency by iterating through map values
4. Sum all frequencies that equal the maximum frequency
5. Return the total count

---

## Dry Run (HashMap)
Example Input: `nums = [1, 2, 2, 3, 1, 4]`

Step-by-step execution:
- Create HashMap<Integer, Integer>
- Process nums[0] = 1: map = {1: 1}
- Process nums[1] = 2: map = {1: 1, 2: 1}
- Process nums[2] = 2: map = {1: 1, 2: 2}
- Process nums[3] = 3: map = {1: 1, 2: 2, 3: 1}
- Process nums[4] = 1: map = {1: 2, 2: 2, 3: 1}
- Process nums[5] = 4: map = {1: 2, 2: 2, 3: 1, 4: 1}

- Find max frequency: max(2, 2, 1, 1) = 2
- Sum frequencies with value 2: 2 + 2 = 4

Result: `4`

---

## Solution (HashMap)
```java
class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        // Step 1: count frequencies
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: find maximum frequency
        int max = 0;
        for (int val : freq.values()) {
            max = Math.max(max, val);
        }

        // Step 3: sum up frequencies of elements with maximum frequency
        int ans = 0;
        for (int val : freq.values()) {
            if (val == max) ans += val;
        }

        return ans;
    }
}
```

---

## Time and Space Complexity (HashMap)
- **Time Complexity:** O(n) - single pass to count + pass to find max + pass to sum
- **Space Complexity:** O(k) - where k is number of distinct elements (≤ 100)

---

### Approach 2: Frequency Array (More Optimal)
Use a fixed-size frequency array since nums[i] ≤ 100, then track maximum frequency while counting.

**Algorithm:**
1. Create frequency array of size 101 (since nums[i] ≤ 100)
2. Count frequencies and track maximum frequency simultaneously
3. Sum all frequencies that equal the maximum frequency
4. Return the total count

---

## Dry Run (Frequency Array)
Example Input: `nums = [1, 2, 2, 3, 1, 4]`

Step-by-step execution:
- Create freqArr[101] = [0, 0, 0, ...]
- Process nums[0] = 1: freqArr[1] = 1, maxFreq = 1
- Process nums[1] = 2: freqArr[2] = 1, maxFreq = 1
- Process nums[2] = 2: freqArr[2] = 2, maxFreq = 2
- Process nums[3] = 3: freqArr[3] = 1, maxFreq = 2
- Process nums[4] = 1: freqArr[1] = 2, maxFreq = 2
- Process nums[5] = 4: freqArr[4] = 1, maxFreq = 2

- Sum frequencies with value 2: freqArr[1] + freqArr[2] = 2 + 2 = 4

Result: `4`

---

## Solution (Frequency Array)
```java
class SolutionArray {
    public int maxFrequencyElements(int[] nums) {
        int[] freqArr = new int[101];
        int maxFreq = 0;

        // Step 1: count frequencies and track max
        for (int num : nums) {
            freqArr[num]++;
            maxFreq = Math.max(maxFreq, freqArr[num]);
        }

        // Step 2: sum up contributions of max frequency
        int total = 0;
        for (int freq : freqArr) {
            if (freq == maxFreq) {
                total += freq;
            }
        }

        return total;
    }
}
```

---

## Time and Space Complexity (Frequency Array)
- **Time Complexity:** O(n + k) - where k = 100 (constant)
- **Space Complexity:** O(1) - fixed array of size 101

---

## Comparison of Approaches

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **HashMap** | O(n) | O(k) | General case, unknown range |
| **Frequency Array** | O(n) | O(1) | Small known range (1-100) |

**Key Insight:** Since the range of numbers is small (1-100), the frequency array approach is more efficient and uses constant space.

**Why Sum Frequencies?**
- The problem asks for "total frequencies" not "count of elements"
- If elements 1 and 2 both appear twice (max frequency), we return 4, not 2
- This represents the total number of occurrences of maximum frequency elements

**Edge Cases:**
- All elements unique: return n (each appears once)
- All elements same: return n (one element appears n times)
- Multiple elements with same max frequency: sum all their frequencies

**Alternative Mathematical Approaches:**
1. **Two-pass algorithm:** First pass to find max frequency, second pass to sum
2. **Single-pass with tracking:** Track max frequency and sum simultaneously
3. **Sorting approach:** Sort and count consecutive elements
  
[Back to All Problems](../README.md) 
