# 1518. Water Bottles - <a href="https://leetcode.com/problems" target="_blank">Link</a>

## Question Description
There are numBottles water bottles that are initially full of water.  
You can exchange numExchange empty water bottles from the market with one full water bottle.  

The operation of drinking a full water bottle turns it into an empty bottle.  

Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.

---

## Constraints
- 1 <= numBottles <= 100  
- 2 <= numExchange <= 100  

---

## Approach
We simulate the process step by step:  
1. Drink all current bottles and increase the total count.  
2. Track how many empty bottles are collected.  
3. If empty bottles are enough for an exchange, convert them into new full bottles.  
4. Continue until you cannot exchange anymore.  

This works because each exchange is independent and only depends on the count of empty bottles at each step.

---

## Dry Run
Example Input: numBottles = 9, numExchange = 3  

Step-by-step execution:  
- Start: count = 0, empty = 0, numBottles = 9  
- Drink 9 bottles → count = 9, empty = 9  
- Exchange 9/3 = 3 → numBottles = 3, empty = 0  
- Drink 3 bottles → count = 12, empty = 3  
- Exchange 3/3 = 1 → numBottles = 1, empty = 0  
- Drink 1 bottle → count = 13, empty = 1  
- Not enough bottles to exchange → Stop  

Final Answer = 13  

---

## Solution
```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int empty = 0;
        while (numBottles > 0) {
            count += numBottles;
            empty += numBottles;

            numBottles = empty / numExchange;
            empty = empty % numExchange;
        }
        return count;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(numBottles) in the worst case, as we simulate exchanges until bottles run out.  
- **Space Complexity:** O(1), using only a few variables.  

  
[Back to All Problems](../README.md) 
