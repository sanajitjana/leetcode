/*
 * Title: 3002. Result Array After Splitting
 * Link: https://leetcode.com/problems/result-array-after-splitting/
 *
 * Problem:
 * You are given an integer array nums. 
 * You must split it into two arrays list1 and list2 such that:
 *  - list1 starts with nums[0]
 *  - list2 starts with nums[1]
 *  - For every i from 2 to n-1, compare the last elements of list1 and list2:
 *      -> If last element of list1 > last element of list2, put nums[i] in list1
 *      -> Otherwise, put nums[i] in list2
 * Finally, return the concatenation of list1 followed by list2.
 *
 * Approach:
 * - Initialize list1 with nums[0] and list2 with nums[1].
 * - Traverse nums from index 2.
 * - At each step, compare the last element of list1 and list2.
 * - Place nums[i] into the list whose last element is greater (list1) or smaller (list2).
 * - After traversal, build the final result array by appending list1 followed by list2.
 *
 * Dry Run:
 * Example: nums = [2, 1, 3, 3]
 *   list1 = [2], list2 = [1]
 *   i=2 → compare lastList1=2, lastList2=1 → list1 wins → list1=[2,3]
 *   i=3 → compare lastList1=3, lastList2=1 → list1 wins → list1=[2,3,3]
 * Final result = [2,3,3,1]
 *
 * Time Complexity: O(n) → single pass over nums, building result in linear time
 * Space Complexity: O(n) → extra space used for list1 and list2
 */

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
