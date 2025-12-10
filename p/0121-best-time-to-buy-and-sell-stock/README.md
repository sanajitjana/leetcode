# Best Time to Buy and Sell Stock - <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/" target="_blank">Link</a>

## Question Description
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

---

## Constraints
- 1 <= prices.length <= 10^5
- 0 <= prices[i] <= 10^4

---

## Brute Force

### Approach
The brute force approach involves checking all possible pairs of buy and sell days. For each pair (i, j) where i < j, calculate the profit as prices[j] - prices[i] and keep track of the maximum profit.

This approach has a time complexity of O(n²) due to the nested loops, which is inefficient for large inputs.

### Dry Run
Example Input: prices = [7,1,5,3,6,4]

- Check all pairs: (0,1): 1-7=-6, (0,2):5-7=-2, (0,3):3-7=-4, (0,4):6-7=-1, (0,5):4-7=-3, (1,2):5-1=4, (1,3):3-1=2, (1,4):6-1=5, (1,5):4-1=3, (2,3):3-5=-2, (2,4):6-5=1, (2,5):4-5=-1, (3,4):6-3=3, (3,5):4-3=1, (4,5):4-6=-2
- Max profit: 6-1=5

Final Answer = 5

### Solution
```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int diff = prices[j] - prices[i];
                max = Math.max(diff, max);
            }
        }
        return max;
    }
}
```

### Time Complexity
O(n²)

---

## Optimized Solution

### Approach
The optimized solution uses a single pass through the array. We keep track of the minimum price seen so far (buy) and the maximum profit achievable. For each day, if the current price is less than the buy price, update buy. Otherwise, calculate the profit and update max if it's greater.

### Dry Run
Example Input: prices = [7,1,5,3,6,4]

- buy=7, max=0
- i=1, prices[1]=1 < buy, buy=1
- i=2, prices[2]=5 > buy, profit=5-1=4, max=4
- i=3, prices[3]=3 > buy, profit=3-1=2, max=4
- i=4, prices[4]=6 > buy, profit=6-1=5, max=5
- i=5, prices[5]=4 > buy, profit=4-1=3, max=5

Final Answer = 5

### Solution
```java
class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < buy) buy = prices[i];
            else if (prices[i] - buy > max) max = prices[i] - buy;
        }
        return max;
    }
}
```

### Time and Space Complexity
- Time Complexity: O(n)
- Space Complexity: O(1)
  
[Back to All Problems](../README.md) 
