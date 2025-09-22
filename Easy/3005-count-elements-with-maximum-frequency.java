/*
https://leetcode.com/problems/count-elements-with-maximum-frequency/
3005. Count Elements With Maximum Frequency

You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.

Example 1:
Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.

Example 2:
Input: nums = [1,2,3,4,5]
Output: 5
Explanation: All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100

Approach:
1. Count the frequency of each element using a HashMap.
2. Find the maximum frequency among all elements.
3. Sum the frequencies of elements that match this maximum.
4. Return the result.

Time Complexity: O(n)   where n = length of nums
Space Complexity: O(k)  where k = number of distinct elements (at most 100)
*/

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
