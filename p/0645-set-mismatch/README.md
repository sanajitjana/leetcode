# [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/)

## Description

**Difficulty:** Easy

You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

You are given an integer array nums representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return them in the form of an array.

**Example 1:**

```
Input: nums = [1,2,2,4]
Output: [2,3]
```

**Example 2:**

```
Input: nums = [1,1]
Output: [1,2]
```

**Constraints:**

- `2 <= nums.length <= 10^4`
- `1 <= nums[i] <= 10^4`

---

## Approach 1: HashSet

Use a HashSet to track numbers we've seen. When we encounter a number already in the set, that's our duplicate. Then create another set of all numbers in the array and iterate from 1 to n to find which number is missing.

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                ans[0] = nums[i];
            } else {
                set.add(nums[i]);
            }
        }
        HashSet<Integer> numbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numbers.add(nums[i]);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!numbers.contains(i)) {
                ans[1] = i;
                break;
            }
        }
        return ans;
    }
}
```

**Time Complexity:** O(n) - We iterate through the array multiple times, but each iteration is O(n).

**Space Complexity:** O(n) - We use HashSets to store the numbers.

---

## Approach 2: Mathematical (Sum Difference)

Use mathematical properties to find the duplicate and missing numbers:
- Calculate the sum of all elements in the array (`arrSum`)
- Calculate the natural sum from 1 to n using formula n*(n+1)/2 (`naturalSum`)
- Calculate the sum of unique elements (`uSum`)

The duplicate number is `arrSum - uSum` (since the duplicate adds extra to the sum).
The missing number is `naturalSum - uSum` (since the missing number is not in the unique set).

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int arrSum = sum(nums);
        int naturalSum = n * (n + 1) / 2;
        int uSum = uniqueSum(nums);
        return new int[] {arrSum - uSum, naturalSum - uSum };
    }

    public int sum(int[] nums) {
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        return sum;
    }

    public int uniqueSum(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        for (int a : nums) {
            set.add(a);
        }
        for (int a : set) {
            sum += a;
        }
        return sum;
    }
}
```

**Time Complexity:** O(n) - We iterate through the array to calculate sums.

**Space Complexity:** O(n) - We use a HashSet to store unique numbers.

---

## Approach 3: Frequency Array (Optimized)

Use a frequency array to count occurrences of each number. The number with frequency 2 is the duplicate, and the number with frequency 0 is the missing one. This approach avoids the overhead of HashSet operations.

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+1];

        for (int i = 0; i < n; i++) {
            arr[nums[i]]++;
        }

        int dup = 0, missing = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == 2) {
                dup = i;
            }
            if (arr[i] == 0) {
                missing = i;
            }
        }
        return new int[]{dup, missing};
    }
}
```

**Time Complexity:** O(n) - We iterate through the array twice.

**Space Complexity:** O(n) - We use an array of size n+1 to store frequencies.