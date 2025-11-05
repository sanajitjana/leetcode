# Design Task Manager - <a href="https://leetcode.com/problems/design-task-manager/" target="_blank">Link</a>

## Question Description
You are asked to design a task manager that supports:

- `add(userId, taskId, priority)` - Add a new task
- `edit(taskId, newPriority)` - Update the priority of a task
- `rmv(taskId)` - Remove a task
- `execTop()` - Execute and remove the task with highest priority (if tie, highest taskId)

Rules:
- `execTop()` always executes the task with the highest priority
- If multiple tasks have the same priority, the one with the highest taskId is chosen
- `execTop()` should return the userId of the executed task

---

## Constraints
- `1 <= userId, taskId, priority <= 10^5`
- At most 10^4 calls will be made to all methods
- taskId values are unique

---

## Approach
Use a combination of:
1. A HashMap<Integer, Task> to keep track of taskId → Task info
2. A TreeSet<Task> with a custom comparator to order tasks by:
   - Higher priority first
   - If tie, higher taskId first
   - Tie breaker: smaller userId (to keep comparator stable)

**Operations:**
- `add`: Insert into both HashMap and TreeSet
- `edit`: Remove old task from TreeSet, update priority, reinsert
- `rmv`: Remove task from both structures
- `execTop`: Poll the first task from TreeSet (highest priority), return its userId

**Why this approach works:**
- TreeSet maintains sorted order automatically with O(log n) operations
- HashMap provides O(1) lookup for task information
- Custom comparator ensures correct priority ordering
- Handles all edge cases including non-existent tasks

**Alternative approaches considered:**
- Could use priority queue, but TreeSet allows efficient removal of specific tasks
- Could use multiple data structures, but HashMap + TreeSet is optimal

---

## Dry Run
Example Operations:
```
TaskManager tm = new TaskManager([]);
tm.add(1, 101, 5);      // Add task 101 with priority 5 for user 1
tm.add(2, 102, 3);      // Add task 102 with priority 3 for user 2
tm.add(1, 103, 5);      // Add task 103 with priority 5 for user 1
tm.execTop();           // Should return 1 (task 103 has higher taskId than 101)
tm.edit(102, 7);        // Update task 102 priority to 7
tm.execTop();           // Should return 2 (task 102 now has highest priority)
```

Step-by-step execution:
- Step 1: Initialize empty HashMap and TreeSet
- Step 2: Add task(1,101,5) → HashMap: {101: task}, TreeSet: [task(1,101,5)]
- Step 3: Add task(2,102,3) → HashMap: {101: task, 102: task}, TreeSet: [task(1,101,5), task(2,102,3)]
- Step 4: Add task(1,103,5) → HashMap: {101: task, 102: task, 103: task}, TreeSet: [task(1,103,5), task(1,101,5), task(2,102,3)]
- Step 5: execTop() → Remove and return task(1,103,5).userId = 1
- Step 6: Edit task 102 priority to 7 → Remove old, add new with priority 7
- Step 7: TreeSet now: [task(2,102,7), task(1,101,5)]
- Step 8: execTop() → Remove and return task(2,102,7).userId = 2

---

## Solution
```java
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
```

---

## Time and Space Complexity
- **Time Complexity:**
  - add: O(log n) due to TreeSet
  - edit: O(log n) due to remove + add in TreeSet
  - rmv: O(log n)
  - execTop: O(log n)
- **Space Complexity:** O(n) for storing all tasks
