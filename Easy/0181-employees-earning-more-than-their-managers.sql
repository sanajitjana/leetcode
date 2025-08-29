-- LeetCode Problem: 181. Employees Earning More Than Their Managers
-- https://leetcode.com/problems/employees-earning-more-than-their-managers/
--
-- Description:
-- The Employee table holds all employees including their managers.
-- Every employee has an Id, and there is also a column for the manager Id.
-- Return the list of employees who earn more than their managers.
--
-- Employee table schema:
-- +----+-------+--------+-----------+
-- | Id | Name  | Salary | ManagerId |
-- +----+-------+--------+-----------+
--
-- Example:
-- Input:
-- Employee table:
-- +----+-------+--------+-----------+
-- | Id | Name  | Salary | ManagerId |
-- +----+-------+--------+-----------+
-- | 1  | Joe   | 70000  | 3         |
-- | 2  | Henry | 80000  | 4         |
-- | 3  | Sam   | 60000  | NULL      |
-- | 4  | Max   | 90000  | NULL      |
-- Output:
-- +----------+
-- | Employee |
-- +----------+
-- | Joe      |
-- +----------+

-- Solution:
SELECT e.name AS Employee
FROM Employee e
JOIN Employee m ON e.managerId = m.id
WHERE e.salary > m.salary;
