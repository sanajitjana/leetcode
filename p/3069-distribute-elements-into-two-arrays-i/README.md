# Distribute Elements Into Two Arrays I - <a href="https://leetcode.com/problems/distribute-elements-into-two-arrays-i/" target="_blank">Link</a>

## Question Description
You are given an integer array `nums`.

You must split it into **two arrays** `list1` and `list2` such that:
- `list1` starts with `nums[0]`
- `list2` starts with `nums[1]`
- For every `i` from 2 to n-1, compare the **last elements** of `list1` and `list2`:
  - If last element of `list1` > last element of `list2`, put `nums[i]` in `list1`
  - Otherwise, put `nums[i]` in `list2`

Finally, return the **concatenation** of `list1` followed by `list2`.

---

## Constraints
- `2 <= nums.length <= 100`
- `1 <= nums[i] <= 100`

---

## Approach
Use two ArrayLists to simulate the distribution process with comparison-based placement.

**Algorithm:**
1. Initialize `list1` with `nums[0]` and `list2` with `nums[1]`
2. For each element from index 2 to n-1:
   - Compare last element of `list1` with last element of `list2`
   - If `list1.last > list2.last`, add to `list1`
   - Otherwise, add to `list2`
3. Create result array and copy elements from `list1` followed by `list2`
4. Return the concatenated result

This approach works because:
- We follow the exact rules specified in the problem
- ArrayLists allow efficient addition to end (O(1) amortized)
- The comparison is always between current last elements
- Final concatenation maintains the required order

---

## Dry Run
Example 1: `nums = [2, 1, 3, 3]`

Step-by-step execution:
- Initialize: list1 = [2], list2 = [1]
- i=2, nums[2]=3
  - Compare last of list1 (2) vs last of list2 (1)
  - 2 > 1, so add 3 to list1
  - list1 = [2, 3], list2 = [1]
- i=3, nums[3]=3
  - Compare last of list1 (3) vs last of list2 (1)
  - 3 > 1, so add 3 to list1
  - list1 = [2, 3, 3], list2 = [1]

- Concatenate: [2, 3, 3, 1]

Result: `[2, 3, 3, 1]`

Example 2: `nums = [5, 4, 3, 8]`

Step-by-step execution:
- Initialize: list1 = [5], list2 = [4]
- i=2, nums[2]=3
  - Compare last of list1 (5) vs last of list2 (4)
  - 5 > 4, so add 3 to list1
  - list1 = [5, 3], list2 = [4]
- i=3, nums[3]=8
  - Compare last of list1 (3) vs last of list2 (4)
  - 3 < 4, so add 8 to list2
  - list1 = [5, 3], list2 = [4, 8]

- Concatenate: [5, 3, 4, 8]

Result: `[5, 3, 4, 8]`

Example 3: `nums = [1, 3]`

Step-by-step execution:
- Only 2 elements, no more to process
- Concatenate: [1, 3]

Result: `[1, 3]`

---

## Solution
```java
class Solution {
    public int[] resultArray(int[] nums) {
        int length = nums.length;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(nums[0]);
        list2.add(nums[1]);

        for (int i = 2; i < length; i++) {
            int lastList1 = list1.get(list1.size() - 1);
            int lastList2 = list2.get(list2.size() - 1);

            if (lastList1 > lastList2) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }

        int[] ans = new int[length];
        int index = 0;
        for (int ele : list1) {
            ans[index++] = ele;
        }
        for (int ele : list2) {
            ans[index++] = ele;
        }
        return ans;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through nums, linear concatenation
- **Space Complexity:** O(n) - for two ArrayLists (excluding output array)

---

## Alternative Approaches

### Array-Based Approach (More Memory Efficient)
```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(nums[0]);
        list2.add(nums[1]);

        for (int i = 2; i < n; i++) {
            if (list1.get(list1.size() - 1) > list2.get(list2.size() - 1)) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }

        // Build result array
        int[] result = new int[n];
        int idx = 0;
        for (int num : list1) result[idx++] = num;
        for (int num : list2) result[idx++] = num;

        return result;
    }
}
```

**Explanation:** Same logic but with more explicit variable naming.

**Time Complexity:** O(n) - same as main approach
**Space Complexity:** O(n) - same as main approach

### Pre-sized ArrayList Approach
```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> list1 = new ArrayList<>(n);
        List<Integer> list2 = new ArrayList<>(n);

        list1.add(nums[0]);
        list2.add(nums[1]);

        for (int i = 2; i < n; i++) {
            int last1 = list1.get(list1.size() - 1);
            int last2 = list2.get(list2.size() - 1);

            if (last1 > last2) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < list1.size(); i++) {
            result[i] = list1.get(i);
        }
        for (int i = 0; i < list2.size(); i++) {
            result[list1.size() + i] = list2.get(i);
        }

        return result;
    }
}
```

**Explanation:** Pre-size ArrayLists and use indexed assignment for result.

**Time Complexity:** O(n) - same performance
**Space Complexity:** O(n) - same as main approach

### Stream API Approach (Java 8+)
```java
class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(nums[0]);
        list2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int last1 = list1.get(list1.size() - 1);
            int last2 = list2.get(list2.size() - 1);

            if (last1 > last2) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }

        return Stream.concat(list1.stream(), list2.stream())
                    .mapToInt(Integer::intValue)
                    .toArray();
    }
}
```

**Explanation:** Use Stream API for concatenation instead of manual copying.

**Time Complexity:** O(n) - stream operations
**Space Complexity:** O(n) - same as main approach

---

## Key Insights

**Distribution Strategy:**
- Elements tend to go to the array with the larger last element
- This creates a natural separation based on relative magnitudes
- The algorithm is greedy and makes locally optimal choices

**Edge Cases:**
- `n = 2`: Return `[nums[0], nums[1]]` (no distribution needed)
- All increasing: Elements go to list1 if each > previous maximum
- All decreasing: Elements go to list2 after first comparison

**Performance Characteristics:**
- Simple and intuitive algorithm
- No complex data structures needed
- Works well for small to medium arrays (n ≤ 100)
- The distribution creates natural ordering within each list
  
[Back to All Problems](../README.md) 
