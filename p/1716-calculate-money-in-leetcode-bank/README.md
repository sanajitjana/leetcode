# Calculate Money in Leetcode Bank - <a href="https://leetcode.com/problems/calculate-money-in-leetcode-bank/" target="_blank">Link</a>

## Question Description
Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.

He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.

Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.

---

## Constraints
- 1 <= n <= 1000

---

## Approach
Simulate the process day by day. Start with monday = 1, money = 1, sum = 0, day = 1.

For each day, add money to sum, increment money, increment day.

If day == 8, reset day to 1, increment monday, set money to monday.

This way, each week starts with increasing monday amount, and within week increases by 1 each day.

---

## Dry Run
Example Input: n = 4

- Day 1: money=1, sum=1, day=2, money=2
- Day 2: money=2, sum=3, day=3, money=3
- Day 3: money=3, sum=6, day=4, money=4
- Day 4: money=4, sum=10, day=5, money=5

Total = 10

---

## Solution
```java
class Solution {
    public int totalMoney(int n) {
        int monday=1;
        int money=1;
        int sum=0;
        int day=1;

        while(n>0){
            sum+=money++;
            day++;
            if(day==8){
                money=++monday;
                day=1;
            }
            n--;
        }  
        return sum;      
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - loop runs n times
- **Space Complexity:** O(1) - constant space
   
[Back to All Problems](../README.md)