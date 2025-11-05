# 3289. The Two Sneaky Numbers of Digitville - <a href="https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/description/" target="_blank">Link</a>

## Question Description
In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual.

As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.

---

## Constraints
- 2 <= n <= 100
- nums.length == n + 2
- 0 <= nums[i] < n
- The input is generated such that nums contains exactly two repeated elements.

---

## Solution 1: Sorting Approach

### Approach
Sort the array and iterate through it to find consecutive duplicates, as the duplicates will be adjacent after sorting.

### Dry Run
Example Input: nums = [0,1,1,0]

Sorted: [0,0,1,1]

Iterate:
- i=0: nums[0]=0 == nums[1]=0, add 0, i+=2 to 2
- i=2: nums[2]=1 == nums[3]=1, add 1, i+=2 to 4

Answer: [0,1]

### Code
```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[2];
        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans[index++] = nums[i];
                i++;
            }
            if (index == 2) {
                break;
            }
        }
        return ans;
    }
}
```

### Time and Space Complexity
- **Time Complexity:** O(n log n) due to sorting
- **Space Complexity:** O(1) extra space (sorting in place)

---

## Solution 2: HashMap Approach

### Approach
Use a HashMap to count the frequency of each number, then iterate through the map to find numbers with count >= 2.

### Dry Run
Example Input: nums = [0,1,1,0]

Map after counting: {0:2, 1:2}

Iterate map entries:
- 0 has count 2, add 0
- 1 has count 2, add 1

Answer: [0,1]

### Code
```java
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }
}
```

### Time and Space Complexity
- **Time Complexity:** O(n)
- **Space Complexity:** O(n) for the HashMap
