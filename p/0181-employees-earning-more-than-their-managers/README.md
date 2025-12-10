# Employees Earning More Than Their Managers - <a href="https://leetcode.com/problems/employees-earning-more-than-their-managers/" target="_blank">Link</a>

## Question Description
The Employee table holds all employees including their managers. Every employee has an Id, and there is also a column for the manager Id. Return the list of employees who earn more than their managers.

---

## Constraints
- Each employee has a unique Id
- ManagerId references Id in the same table (self-referencing foreign key)
- Employees without managers have ManagerId as NULL
- Salary values are positive integers

---

## Approach
Use a self-join to compare each employee's salary with their manager's salary. Join the Employee table with itself on the condition that employee's ManagerId equals manager's Id, then filter for cases where employee's salary is greater than manager's salary.

This approach works because we need to compare each employee with their specific manager, and a self-join allows us to create the employee-manager relationship for comparison. Alternative approaches like subqueries would work but self-join is generally more efficient for this type of comparison.

---

## Dry Run
Example Input:
Employee table:
| Id | Name | Salary | ManagerId |
|----|------|--------|-----------|
| 1  | Joe  | 70000  | 3         |
| 2  | Henry| 80000  | 4         |
| 3  | Sam  | 60000  | NULL      |
| 4  | Max  | 90000  | NULL      |

Step-by-step execution:
- Join Employee e with Employee m where e.ManagerId = m.Id
- This creates pairs: (Joe, Sam) and (Henry, Max)
- Check where e.Salary > m.Salary: 70000 > 60000 is true for Joe
- 80000 > 90000 is false for Henry, so only Joe is included

Final Answer = `Joe`

---

## Solution
```sql
SELECT e.name AS Employee
FROM Employee e
JOIN Employee m ON e.managerId = m.id
WHERE e.salary > m.salary;
```

---

## Time and Space Complexity
- **Time Complexity:** O(n) - single pass through the table to perform the join and filtering
- **Space Complexity:** O(n) - temporary space for the join result before filtering
  
[Back to All Problems](../README.md) 
