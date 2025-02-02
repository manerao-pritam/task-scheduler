package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.example.TaskUtil.validate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testAddTask() {
        // No exception should be thrown
        assertDoesNotThrow(() -> validate(task),
                "Valid task should not throw an exception.");

        taskManager.addTask(task);

        // Verify task was added successfully
        Task fetchedTask = taskManager.getTaskById(task.getId());
        assertNotNull(fetchedTask);
        assertEquals(task, fetchedTask);
    }

    @Test
    void testAddInvalidTask() {
        // Create a task with a past due date
        Task invalidTask = new Task("Invalid Task", null);

        // Attempt to add the invalid task and verify that it throws an exception
        TaskValidationException exception = assertThrows(TaskValidationException.class,
                () -> taskManager.addTask(invalidTask));

        // Assert that the exception message is as expected
        assertEquals("Due date can't be null.", exception.getMessage());

        // Verify that the task manager does not contain the invalid task
        assertFalse(taskManager.getTasks().values().stream()
                .anyMatch(task -> task.getTitle().equals("Invalid Task")));
    }


    @Test
    void testGetTasks() {
        // Create new tasks
        Task task1 = new Task("Task 1", dueDate);
        Task task2 = new Task("Task 2", dueDate);
        Task taskWithDesc = new Task("Task with description", dueDate, "This is a test task");

        // Add tasks to the manager
        taskManager.addTask(task);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(taskWithDesc);

        // Fetch all tasks
        Map<UUID, Task> tasks = taskManager.getTasks();
        assertNotNull(tasks);
        assertEquals(4, tasks.size());

        // Verify the task list contains expected task titles
        assertTrue(tasks.values().stream().anyMatch(t -> t.getTitle().equals("Test Task")
                && t.getDueDate().equals(dueDate)), "Test Task should exist");

        assertTrue(tasks.values().stream().anyMatch(t -> t.getTitle().equals("Task 1")
                && t.getDueDate().equals(dueDate)), "Task 1 should exist");

        assertTrue(tasks.values().stream().anyMatch(t -> t.getTitle().equals("Task 2")
                && t.getDueDate().equals(dueDate)), "Task 2 should exist");

        assertTrue(tasks.values().stream().anyMatch(t -> t.getTitle().equals("Task with description")
                && t.getDueDate().equals(dueDate)), "Task with description should exist");

    }

    @Test
    void testGetTaskById() {
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
    void testUpdateTask() {
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
    void testUpdateInvalidTask() {
        // Create a valid task and add it to the manager
        Task validTask = new Task("Valid Task", LocalDateTime.now().plusDays(1));
        taskManager.addTask(validTask);

        // Attempt to update the valid task with an empty title
        Task invalidUpdatedTask = new Task("", LocalDateTime.now().minusDays(1));

        // Attempt to update the task and verify that it throws an exception
        TaskValidationException exception = assertThrows(TaskValidationException.class, () -> {
            taskManager.updateTask(validTask.getId(), invalidUpdatedTask); // Assuming update by ID
        });

        // Assert that the exception message is as expected
        assertEquals("Task title can't be empty.", exception.getMessage());

        // Verify that the task's title and due date have not been updated
        Task fetchedTask = taskManager.getTaskById(validTask.getId());
        assertEquals(validTask.getTitle(), fetchedTask.getTitle());
        assertEquals(validTask.getDueDate(), fetchedTask.getDueDate());
    }

    @Test
    void testDeleteTask() {
        taskManager.addTask(task);
        assertNotNull(taskManager.getTaskById(task.getId())); // Ensure task exists

        // Delete task
        taskManager.deleteTask(task.getId());

        // Verify task was deleted
        assertNull(taskManager.getTaskById(task.getId()));
    }

    @Test
    void testDeleteNonExistingTask() {
        // Attempt to delete a non-existing task (should not throw an error)
        UUID randomId = UUID.randomUUID();
        taskManager.deleteTask(randomId);  // Should not fail
    }
}
