# Find All Numbers Disappeared in an Array - <a href="https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/" target="_blank">Link</a>

## Question Description
Given an array `nums` of n integers where `nums[i]` is in the range `[1, n]`, return an array of all the integers in the range `[1, n]` that do not appear in `nums`.

---

## Constraints
- `n == nums.length`
- `1 <= n <= 10^5`
- `1 <= nums[i] <= n`

---

## Solution 1: Brute Force Approach (O(n²) time)

### Approach
For each number from 1 to n, iterate through the entire array to check if it exists. If not found after checking all elements, add it to the result list. This is the most straightforward but least efficient approach.

### Dry Run
Example Input: `nums = [4,3,2,7,8,2,3,1]`

Step-by-step execution:
- Check for 1: scan array [4,3,2,7,8,2,3,1] → found at index 7 ✓
- Check for 2: scan array [4,3,2,7,8,2,3,1] → found at index 2 ✓
- Check for 3: scan array [4,3,2,7,8,2,3,1] → found at index 1 ✓
- Check for 4: scan array [4,3,2,7,8,2,3,1] → found at index 0 ✓
- Check for 5: scan array [4,3,2,7,8,2,3,1] → not found → add to result
- Check for 6: scan array [4,3,2,7,8,2,3,1] → not found → add to result
- Check for 7: scan array [4,3,2,7,8,2,3,1] → found at index 3 ✓
- Check for 8: scan array [4,3,2,7,8,2,3,1] → found at index 4 ✓

Final Answer = `[5, 6]`

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            boolean found = false;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                list.add(i);
            }
        }
        return list;
    }
}
```

### Time and Space Complexity
- **Time Complexity:** O(n²) - nested loops
- **Space Complexity:** O(1) - only using result list (not counted as extra space)

---

## Solution 2: HashSet Approach (O(n) space)

### Approach
Use a HashSet to store all numbers present in the array. Then iterate from 1 to n and check which numbers are missing from the set. This provides O(1) lookup time for checking existence.

### Dry Run
Example Input: `nums = [4,3,2,7,8,2,3,1]`

Step-by-step execution:
- Create set: {4, 3, 2, 7, 8, 1}
- Check 1 to 8:
  - 1: in set ✓
  - 2: in set ✓
  - 3: in set ✓
  - 4: in set ✓
  - 5: NOT in set → add to result
  - 6: NOT in set → add to result
  - 7: in set ✓
  - 8: in set ✓

Final Answer = `[5, 6]`

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int ele : nums) {
            set.add(ele);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }
}
```

### Time and Space Complexity
- **Time Complexity:** O(n) - two passes through the array
- **Space Complexity:** O(n) - HashSet stores up to n elements

---

## Solution 3: In-place Marking (Optimal - O(1) space)

### Approach
Use the array itself as a hash table by marking visited indices. For each number, mark the index `(num - 1)` as negative. After processing, indices with positive values indicate missing numbers. This approach satisfies the follow-up requirement for O(1) extra space.

### Dry Run
Example Input: `nums = [4,3,2,7,8,2,3,1]`

Step-by-step execution:
- i=0, nums[0]=4, index=|4|-1=3, nums[3]=7 > 0, mark as -7: nums = [4,3,2,-7,8,2,3,1]
- i=1, nums[1]=3, index=|3|-1=2, nums[2]=2 > 0, mark as -2: nums = [4,3,-2,-7,8,2,3,1]
- i=2, nums[2]=-2, index=|-2|-1=1, nums[1]=3 > 0, mark as -3: nums = [4,-3,-2,-7,8,2,3,1]
- i=3, nums[3]=-7, index=|-7|-1=6, nums[6]=3 > 0, mark as -3: nums = [4,-3,-2,-7,8,2,-3,1]
- i=4, nums[4]=8, index=|8|-1=7, nums[7]=1 > 0, mark as -1: nums = [4,-3,-2,-7,8,2,-3,-1]
- i=5, nums[5]=2, index=|2|-1=1, nums[1]=-3 < 0, already marked
- i=6, nums[6]=-3, index=|-3|-1=2, nums[2]=-2 < 0, already marked
- i=7, nums[7]=-1, index=|-1|-1=0, nums[0]=4 > 0, mark as -4: nums = [-4,-3,-2,-7,8,2,-3,-1]

Check positive indices: i=4 (nums[4]=8 > 0) → missing number 5, i=5 (nums[5]=2 > 0) → missing number 6

Final Answer = `[5, 6]`

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();

        // Mark visited indices as negative
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        // Indices with positive values are missing numbers
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }

        return list;
    }
}
```

### Time and Space Complexity
- **Time Complexity:** O(n) - two passes through the array
- **Space Complexity:** O(1) - no extra space used (result list doesn't count)  
[Back to All Problems](../README.md) 
