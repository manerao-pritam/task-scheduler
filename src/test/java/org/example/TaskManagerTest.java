package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskManagerTest {

    private Task task;
    private LocalDateTime dueDate;
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        // Initialize a new task manager before each test
        taskManager = new TaskManager();

        // Create a new task with a future due date
        dueDate = LocalDateTime.now().plusDays(1);
        task = new Task("Test Task", dueDate);
    }

    @Test
    void addTask() {
        taskManager.addTask(task);

        // Verify task was added successfully
        Task fetchedTask = taskManager.getTaskById(task.getId());
        assertNotNull(fetchedTask);
        assertEquals(task, fetchedTask);
    }

    @Test
    void getTasks() {
        // Create new tasks
        Task task1 = new Task("Task 1", dueDate);
        Task task2 = new Task("Task 2", dueDate);

        // Add tasks to the manager
        taskManager.addTask(task);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        // Fetch all tasks
        List<Task> tasks = taskManager.getTasks();
        assertNotNull(tasks);
        assertEquals(3, tasks.size());

        // Verify the task list contains expected task titles
        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Test Task")
                && t.getDueDate().equals(dueDate)), "Test Task should exist");

        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Task 1")
                && t.getDueDate().equals(dueDate)), "Task 1 should exist");

        assertTrue(tasks.stream().anyMatch(t -> t.getTitle().equals("Task 2")
                && t.getDueDate().equals(dueDate)), "Task 2 should exist");

    }

    @Test
    void getTaskById() {
        taskManager.addTask(task);

        // Retrieve task by ID and validate
        Task fetchedTask = taskManager.getTaskById(task.getId());
        assertNotNull(fetchedTask);
        assertEquals(task, fetchedTask);

        // Test fetching a non-existing task
        UUID randomId = UUID.randomUUID();
        assertNull(taskManager.getTaskById(randomId));
    }

    @Test
    void updateTask() {
        taskManager.addTask(task);

        // Update task properties
        task.setTitle("Updated Task");
        task.setDueDate(LocalDateTime.now().plusDays(5));
        task.setCompleted(true);

        // Apply update
        taskManager.updateTask(task.getId(), task);

        // Fetch the updated task
        Task updatedTask = taskManager.getTaskById(task.getId());
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals(task.getDueDate(), updatedTask.getDueDate());
        assertTrue(updatedTask.isCompleted());
    }

    @Test
    void deleteTask() {
        taskManager.addTask(task);
        assertNotNull(taskManager.getTaskById(task.getId())); // Ensure task exists

        // Delete task
        taskManager.deleteTask(task.getId());

        // Verify task was deleted
        assertNull(taskManager.getTaskById(task.getId()));

        // Attempt to delete a non-existing task (should not throw an error)
        UUID randomId = UUID.randomUUID();
        taskManager.deleteTask(randomId);  // Should not fail
    }
}
