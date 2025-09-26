/**
 * Title: Valid Triangle Number
 * Link: https://leetcode.com/problems/valid-triangle-number/
 *
 * Problem:
 * Given an integer array nums, return the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 *
 * Conditions for a valid triangle:
 *  - The sum of any two sides must be greater than the third side.
 *  - So, for three numbers a, b, c:
 *       a + b > c
 *       a + c > b
 *       b + c > a
 *
 * ---------------------------------------------------------------------------
 * Approach 1: Brute Force (3 nested loops)
 * ---------------------------------------------------------------------------
 * Idea:
 * - Check all possible triplets (i, j, k).
 * - For each triplet, test the triangle inequality conditions.
 *
 * Dry Run:
 * nums = [2, 2, 3, 4]
 * Triplets:
 *   (2,2,3) -> valid
 *   (2,3,4) -> valid
 *   (2,2,4) -> not valid
 *   (2,3,4) -> valid
 * Total = 3
 *
 * Time Complexity: O(n^3)
 * Space Complexity: O(1)
 */
class SolutionBruteForce {
    public int triangleNumber(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int a = nums[i];
                    int b = nums[j];
                    int c = nums[k];
                    if ((a + b > c) && (a + c > b) && (b + c > a)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

/**
 * ---------------------------------------------------------------------------
 * Approach 2: Sort + Triple Nested Loop
 * ---------------------------------------------------------------------------
 * Idea:
 * - Sort the array first.
 * - Because sorted array ensures nums[i] <= nums[j] <= nums[k],
 *   only need to check: nums[i] + nums[j] > nums[k].
 *
 * Dry Run:
 * nums = [2, 2, 3, 4] -> sorted
 *   i=0, j=1, k=2 -> 2+2 > 3 ✅
 *   i=0, j=1, k=3 -> 2+2 > 4 ❌
 *   i=0, j=2, k=3 -> 2+3 > 4 ✅
 *   i=1, j=2, k=3 -> 2+3 > 4 ✅
 * Total = 3
 *
 * Time Complexity: O(n^3) (sorting O(n log n) negligible)
 * Space Complexity: O(1)
 */
class SolutionSortedTriple {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

/**
 * ---------------------------------------------------------------------------
 * Approach 3: Sort + Two Pointers (Optimized)
 * ---------------------------------------------------------------------------
 * Idea:
 * - Sort the array.
 * - Fix the largest side (nums[i]).
 * - Use two pointers (j, k) to count valid pairs.
 *   If nums[j] + nums[k] > nums[i], then all elements between j..k-1 with nums[k]
 *   also form valid triangles. So add (k-j) to count.
 *
 * Dry Run:
 * nums = [2, 2, 3, 4] -> sorted
 *   i=3 (nums[i]=4), j=0, k=2
 *     nums[0]+nums[2]=5 > 4 -> count += 2 (k-j=2), k--
 *     nums[0]+nums[1]=4 !> 4 -> j++
 * Done. Count=3
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
class SolutionOptimized {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int j = 0, k = i - 1;
            while (j < k) {
                if (nums[j] + nums[k] > nums[i]) {
                    count += k - j;
                    k--;
                } else {
                    j++;
                }
            }
        }
        return count;
    }
}
