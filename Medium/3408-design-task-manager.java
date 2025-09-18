/**
 * 3408. Design Task Manager
 * 
 * LeetCode Link: https://leetcode.com/problems/design-task-manager/
 * 
 * Description:
 * You are asked to design a task manager that supports:
 * - add(userId, taskId, priority)
 * - edit(taskId, newPriority)
 * - rmv(taskId)
 * - execTop()
 * 
 * Rules:
 * - execTop() always executes the task with the highest priority.
 * - If multiple tasks have the same priority, the one with the highest taskId is chosen.
 * - execTop() should return the userId of the executed task.
 * 
 * -----------------------------------------------------------
 * Approach:
 * We use a combination of:
 * 1. A HashMap<Integer, Task> to keep track of taskId -> Task info.
 * 2. A TreeSet<Task> with a custom comparator to order tasks by:
 *      - Higher priority first
 *      - If tie, higher taskId first
 *      - Tie breaker: smaller userId (to keep comparator stable)
 * 
 * Operations:
 * - add: Insert into both HashMap and TreeSet.
 * - edit: Remove old task from TreeSet, update priority, reinsert.
 * - rmv: Remove task from both structures.
 * - execTop: Poll the first task from TreeSet (highest priority), return its userId.
 * 
 * -----------------------------------------------------------
 * Complexity:
 * - add: O(log n) due to TreeSet
 * - edit: O(log n) due to remove + add in TreeSet
 * - rmv: O(log n)
 * - execTop: O(log n)
 * Space: O(n) for storing all tasks.
 */

import java.util.*;

class Task {
    int userId;
    int taskId;
    int priority;
    Task(int u, int t, int p) {
        userId = u;
        taskId = t;
        priority = p;
    }
}

class TaskManager {

    private Map<Integer, Task> taskInfo = new HashMap<>();
    private TreeSet<Task> taskSet;

    public TaskManager(List<List<Integer>> tasks) {
        taskSet = new TreeSet<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority); // higher priority first
            }
            if (a.taskId != b.taskId) {
                return Integer.compare(b.taskId, a.taskId);     // higher taskId first
            }
            return Integer.compare(a.userId, b.userId); // tie breaker for stability
        });

        for (List<Integer> t : tasks) {
            add(t.get(0), t.get(1), t.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskInfo.put(taskId, task);
        taskSet.add(task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskInfo.get(taskId);
        if (old == null) return;
        taskSet.remove(old);
        Task updated = new Task(old.userId, taskId, newPriority);
        taskInfo.put(taskId, updated);
        taskSet.add(updated);
    }

    public void rmv(int taskId) {
        Task old = taskInfo.get(taskId);
        if (old == null) return;
        taskSet.remove(old);
        taskInfo.remove(taskId);
    }

    public int execTop() {
        if (taskSet.isEmpty()) return -1;
        Task top = taskSet.first(); // highest priority, highest taskId
        taskSet.remove(top);
        taskInfo.remove(top.taskId);
        return top.userId;
    }
}
