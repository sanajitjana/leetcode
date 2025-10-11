# [Majority Element](https://leetcode.com/problems/majority-element/)

## Question Description
Given an array `nums` of size `n`, return the majority element. The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

---

## Constraints
- `1 <= nums.length <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

---

## Approach 1: Brute Force (Nested Loops)

Use nested loops to count occurrences of each element. For each element, scan the entire array to count how many times it appears. If any element appears more than n/2 times, return it.

**Time Complexity:** O(n²) - for each element, we scan the entire array
**Space Complexity:** O(1) - no extra data structures used

```java
class Solution {
    public int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) count++;
            }
            if (count > nums.length / 2) return nums[i];
        }
        return -1;
    }
}
```

---

## Approach 2: Sorting

Sort the array and return the middle element. Since the majority element appears more than n/2 times, it will always occupy the middle position after sorting.

**Time Complexity:** O(n log n) - due to sorting
**Space Complexity:** O(1) or O(log n) depending on sorting implementation

```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
```

---

## Approach 3: HashMap Counting

Use a HashMap to count occurrences of each element. Iterate through the array once to build the frequency map, then find the element with count greater than n/2.

**Time Complexity:** O(n) - single pass to count frequencies
**Space Complexity:** O(n) - hashmap stores up to n elements

```java
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        int level = nums.length / 2;
        for (int key : map.keySet()) {
            if (map.get(key) > level) return key;
        }
        return -1;
    }
}
```

---

## Approach 4: Boyer-Moore Voting Algorithm (Optimal)

Use a voting algorithm that maintains a candidate element and a count. This algorithm leverages the fact that the majority element appears more than n/2 times:
- If count is 0, set current element as new candidate
- If current element matches candidate, increment count
- If current element differs from candidate, decrement count

The majority element will always be the final candidate.

**Time Complexity:** O(n) - single pass through the array
**Space Complexity:** O(1) - only using constant extra space

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
```

---

## Dry Run Example
Example Input: `nums = [3,2,3]`

**Approach 1 (Brute Force):**
- i=0, nums[0]=3, inner loop finds 2 occurrences of 3, 2 > 1, return 3
- i=1, nums[1]=2, inner loop finds 1 occurrence of 2, 1 not > 1

**Approach 2 (Sorting):**
- Sorted array: [2,3,3], middle element is 3

**Approach 3 (HashMap):**
- Map: {3:2, 2:1}, 3 appears 2 times > 1, return 3

**Approach 4 (Voting):**
- i=0, num=3, count=0, set candidate=3, count=1
- i=1, num=2, count=1, 2 ≠ 3, count=0
- i=2, num=3, count=0, set candidate=3, count=1
- Final candidate = 3

---

## Time and Space Complexity Summary
- **Approach 1 (Brute Force):** O(n²) time, O(1) space
- **Approach 2 (Sorting):** O(n log n) time, O(1)/O(log n) space
- **Approach 3 (HashMap):** O(n) time, O(n) space
- **Approach 4 (Voting Algorithm):** O(n) time, O(1) space

**Recommendation:** Use Approach 4 (Boyer-Moore Voting Algorithm) as it's the most optimal solution with linear time and constant space complexity.