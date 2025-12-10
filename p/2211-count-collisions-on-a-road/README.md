# Count Collisions on a Road - <a href="https://leetcode.com/problems/count-collisions-on-a-road/" target="_blank">Link</a>

## Question Description
There are n cars on an infinitely long road. The cars are numbered from 0 to n - 1 from left to right and each car is present at a unique point.

You are given a 0-indexed string directions of length n. directions[i] can be either 'L', 'R', or 'S' denoting whether the ith car is moving towards the left, towards the right, or staying at its current point respectively. Each moving car has the same speed.

The number of collisions can be calculated as follows:

- When two cars moving in opposite directions collide with each other, the number of collisions increases by 2.
- When a moving car collides with a stationary car, the number of collisions increases by 1.

After a collision, the cars involved can no longer move and will stay at the point where they collided. Other than that, cars cannot change their state or direction of motion.

Return the total number of collisions that will happen on the road.

---

## Constraints
- 1 <= directions.length <= 10^5
- directions[i] is either 'L', 'R', or 'S'.

---

## Approach
The key insight is that collisions only occur in the middle section where cars are moving towards each other. Cars moving left on the far left and cars moving right on the far right won't collide with anything outside the string.

We skip all leading 'L's (cars moving left that won't collide) and trailing 'R's (cars moving right that won't collide). Then, in the remaining segment from i to j, every car that is not stationary ('S') will cause a collision, because they are either moving left into the segment or moving right into the segment.

Each non-'S' in [i,j] will collide at least once.

The code counts the number of non-'S' in [i,j].

---

## Dry Run
Example Input: directions = "RLRSLL"

- n=6
- i=0, dir[0]='R' not 'L', stop, i=0
- j=5, dir[5]='L' not 'R', stop, j=5
- From 0 to 5: positions 0:'R',1:'L',2:'R',3:'S',4:'L',5:'L'
- Non-'S': 0,1,2,4,5 -> 5, matches output 5

Another example: "LLRR"

- i=0, 'L', i=1, 'L', i=2, 'R' not 'L', i=2
- j=3, 'R', j=2, 'R', j=1, 'L' not 'R', j=1
- i=2, j=1, i>j, no loop, coli=0, correct.

---

## Solution
```java
class Solution {
    public int countCollisions(String dir) {
        int n=dir.length();
        int i=0, j=n-1;
        while(i<n){
            if(dir.charAt(i)=='L') i++;
            else break;
        }
        while(j>=0){
            if(dir.charAt(j)=='R') j--;
            else break;
        }
        int coli=0;
        while(i<=j){
            if(dir.charAt(i)!='S') coli++;
            i++;
        }
        return coli;
    }
}
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - three passes through the string
- **Space Complexity:** O(1) - no extra space used  
[Back to All Problems](../README.md) 
