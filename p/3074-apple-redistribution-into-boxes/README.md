# Apple Redistribution into Boxes - <a href="https://leetcode.com/problems/apple-redistribution-into-boxes" target="_blank">Link</a>

## Question Description
You are given an array apple of size n and an array capacity of size m.

There are n packs where the ith pack contains apple[i] apples. There are m boxes as well, and the ith box has a capacity of capacity[i] apples.

Return the minimum number of boxes you need to select to redistribute these n packs of apples into boxes.

Note that, apples from the same pack can be distributed into different boxes.

---

## Constraints
- 1 <= n == apple.length <= 50
- 1 <= m == capacity.length <= 50
- 1 <= apple[i], capacity[i] <= 50
- The input is generated such that it's possible to redistribute packs of apples into boxes.

---

## Approach
To minimize the number of boxes, we should use the boxes with the largest capacities first. Sort the capacity array in descending order. Calculate the total number of apples. Then, iterate from the largest capacity, subtract it from the total sum, increment the count, and stop when the sum becomes less than or equal to zero.

This greedy approach works because using larger boxes first minimizes the number needed.

---

## Dry Run
Example Input: apple = [1,3,2], capacity = [4,3,1,5,2]

Total apples: 1+3+2=6

Sorted capacity descending: [5,4,3,2,1]

- Use box 5: sum = 6-5=1, count=1
- Use box 4: sum =1-4=-3 <=0, count=2

Final Answer = 2

---

## Solution
```java
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        for (int e : apple) {
            sum += e;
        }
        Arrays.sort(capacity);
        int count = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            sum -= capacity[i];
            count++;
            if (sum <= 0)
                break;
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(m log m) due to sorting the capacity array
- **Space Complexity:** O(1) extra space, as sorting is in-place

[Back to All Problems](../README.md)