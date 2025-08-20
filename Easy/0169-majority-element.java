/**
 * 169. Majority Element
 * Difficulty: Easy
 * URL: https://leetcode.com/problems/majority-element/description/
 *
 * ---------------------
 * Approach 1: Brute Force (Nested Loops)
 *
 * Intuition:
 * - For each element, count how many times it appears in the array.
 * - If it appears more than n/2 times, return it.
 *
 * Time Complexity: O(n^2)   — for each element, we scan the entire array
 * Space Complexity: O(1)    — no extra data structures used
 */
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

/**
 * ---------------------
 * Approach 2: Sorting
 *
 * Intuition:
 * - After sorting, the majority element will always occupy the middle position.
 *
 * Time Complexity: O(n log n)   — due to sorting
 * Space Complexity: O(1) or O(log n) depending on sorting implementation
 */
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}

/**
 * ---------------------
 * Approach 3: HashMap Counting
 *
 * Intuition:
 * - Count occurrences of each element using a HashMap.
 * - The element with count > n/2 is the majority element.
 *
 * Time Complexity: O(n)   — iterate and count in one pass
 * Space Complexity: O(n)  — to store counts in HashMap
 */
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

/**
 * ---------------------
 * Approach 4: Boyer-Moore Voting Algorithm (Optimized)
 *
 * Intuition:
 * - The majority element appears more than n/2 times in the array.
 * - Keep a candidate (ans) and a count.
 * - If count becomes 0, select a new candidate.
 * - If the current element equals the candidate, increment count, otherwise decrement.
 * - The majority element will remain as the final candidate.
 *
 * Time Complexity: O(n)   — single scan
 * Space Complexity: O(1)  — constant space
 */
class Solution {
    public int majorityElement(int[] nums) {
        int ans = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0)
                ans = nums[i];
            if (nums[i] == ans) {
                count++;
            } else {
                count--;
            }
        }
        return ans;
    }
}
