# Count Partitions with Even Sum Difference - <a href="https://leetcode.com/problems/count-partitions-with-even-sum-difference" target="_blank">Link</a>

## Question Description
You are given an integer array nums of length n.

A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty subarrays such that:

- Left subarray contains indices [0, i].
- Right subarray contains indices [i + 1, n - 1].

Return the number of partitions where the difference between the sum of the left and right subarrays is even.

---

## Constraints
- 2 <= n == nums.length <= 100
- 1 <= nums[i] <= 100

---

## Approach 1
For each possible partition index i from 0 to n-2, compute the sum of the left subarray (indices 0 to i) and the right subarray (indices i+1 to n-1). Check if the difference (left_sum - right_sum) is even. Since the difference is even if both sums have the same parity, we check if (left_sum % 2 == right_sum % 2).

To compute, maintain a running sum for the left part, and for each i, compute the right sum by summing from i+1 to end.

---

## Dry Run 1
Example Input: nums = [10,10,3,7,6]

Partitions:

- i=0: left=[10], sum=10; right=[10,3,7,6], sum=26; diff=10-26=-16, even. Count=1
- i=1: left=[10,10], sum=20; right=[3,7,6], sum=16; diff=4, even. Count=2
- i=2: left=[10,10,3], sum=23; right=[7,6], sum=13; diff=10, even. Count=3
- i=3: left=[10,10,3,7], sum=30; right=[6], sum=6; diff=24, even. Count=4

Output: 4

---

## Solution 1
```java
class Solution {
    public int countPartitions(int[] nums) {

        int fSum=0, count=0;
        for(int i=0; i<nums.length-1; i++){
            fSum+=nums[i];
            int sSum=0;
            for(int j=i+1; j<nums.length; j++){
                sSum+=nums[j];
            }
            if((fSum-sSum)%2==0){
                count++;
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity 1
- **Time Complexity:** O(n^2) - for each of n-1 partitions, summing the right subarray takes O(n) time.
- **Space Complexity:** O(1) - only a few variables used.

---

## Approach 2
Precompute the total sum of the array. Then, for each partition index i, maintain a cumulative left sum, compute right sum as total - left sum, and check if (left - right) % 2 == 0. This avoids recomputing the right sum each time.

---

## Dry Run 2
Example Input: nums = [10,10,3,7,6]

Total sum = 10+10+3+7+6 = 36

Partitions:

- i=0: left sum=10; right sum=36-10=26; diff=10-26=-16, even. Count=1
- i=1: left sum=10+10=20; right sum=36-20=16; diff=4, even. Count=2
- i=2: left sum=20+3=23; right sum=36-23=13; diff=10, even. Count=3
- i=3: left sum=23+7=30; right sum=36-30=6; diff=24, even. Count=4

Output: 4

---

## Solution 2
```java
class Solution {
    public int countPartitions(int[] nums) {
        int tSum=0;
        for(int ele:nums){
            tSum+=ele;
        }
        int fSum=0, count=0;
        for(int i=0; i<nums.length-1; i++){
            fSum+=nums[i];
            int sSum=tSum-fSum;
            if((fSum-sSum)%2==0){
                count++;
            }
        }
        return count;
    }
}
```

---

## Time and Space Complexity 2
- **Time Complexity:** O(n) - single pass to compute total sum, then single pass for partitions.
- **Space Complexity:** O(1) - only a few variables used.