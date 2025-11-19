# Keep Multiplying Found Values by Two - <a href="https://leetcode.com/problems/keep-multiplying-found-values-by-two/" target="_blank">Link</a>

## Question Description
You are given an array of integers nums. You are also given an integer original which is the first number that needs to be searched for in nums.

You then do the following steps:
- If original is found in nums, multiply it by two (i.e., set original = 2 * original).
- Otherwise, stop the process.
- Repeat this process with the new number as long as you keep finding the number.
- Return the final value of original.

---

## Constraints
- `1 <= nums.length <= 1000`
- `1 <= nums[i], original <= 1000`

---

## Approach
Use a HashSet to store all numbers from nums for O(1) lookups. Then repeatedly check if the current original value exists in the set. If it does, multiply by 2 and continue. If not, break the loop and return the current original value.

This approach works because:
- HashSet provides O(1) average time complexity for lookups
- The problem requires repeatedly checking for the same numbers, so storing them in a set is more efficient than searching the array each time
- The constraints are small (max 1000), but this approach scales well

Alternative approaches like linear search through the array for each iteration would work but would be less efficient (O(n²) in worst case vs O(n) with HashSet).

---

## Dry Run
Example Input: `nums = [5,3,6,1,12], original = 3`

Step-by-step execution:
- Initial: original = 3, set = {5,3,6,1,12}
- Step 1: 3 is in set, original = 3 * 2 = 6
- Step 2: 6 is in set, original = 6 * 2 = 12
- Step 3: 12 is in set, original = 12 * 2 = 24
- Step 4: 24 is not in set, break loop

Final Answer = `24`

---

## Solution
```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        while(true){
            if(set.contains(original)){
                original *= 2;
            }else{
                break;
            }
        }      
        return original;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - one pass to build the set, plus at most log₂(1000) ≈ 10 iterations of the loop
- **Space Complexity:** O(n) - hashset stores up to n elements from the input array
