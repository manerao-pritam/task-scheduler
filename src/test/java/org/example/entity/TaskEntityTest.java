package org.example.entity;

import org.example.exception.PriorityValidationException;
import org.example.exception.TaskValidationException;
import org.example.service.StandardPriority;
import org.example.util.TaskUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEntityTest {

    private Task task;
    private LocalDateTime dueDate;

    @BeforeEach
    void setUp() {
        // Initialize common objects to be used in the tests
        dueDate = LocalDateTime.now().plusDays(1);
        task = new Task("Test Task", dueDate);
    }

    @Test
    void testDefaultConstructor() {
        // Test the default constructor
        // ID will be auto-created and the rest of the fields are null by default
        Task task = new Task();
        assertNotNull(task.getId());
        assertNull(task.getTitle());
        assertNull(task.getDueDate());
        assertNull(task.getDescription());
        assertFalse(task.isCompleted());
        assertNull(task.getPriority());
    }

    @Test
    void testConstructorAndGetters() throws PriorityValidationException {
        // Test the constructor and getters
        assertNotNull(task.getId());  // The ID should never be null
        assertEquals("Test Task", task.getTitle());
        assertEquals(dueDate, task.getDueDate());
        assertFalse(task.isCompleted());  // Default value for isCompleted should be false

        // description
        assertNull(task.getDescription());
        task.setDescription("default task");
        assertEquals("default task", task.getDescription());

        // priority
        assertNull(task.getPriority());
        task.setPriority(new StandardPriority(StandardPriority.Priority.LOW));
        assertEquals("default task", task.getDescription());
    }

    @Test
    void testSetters() {
        // Test setters and verify changes in the object state
        task.setTitle("Updated Task");
        task.setDueDate(LocalDateTime.of(2025, 2, 1, 10, 0));
        task.setCompleted(true);

        assertEquals("Updated Task", task.getTitle());
        assertEquals(LocalDateTime.of(2025, 2, 1, 10, 0), task.getDueDate());
        assertTrue(task.isCompleted());
    }

    @Test
    void testToString() {
        // Test the toString method
        String expected = "Task{id=" + task.getId() + ", title='Test Task', description='" + task.getDescription()
                + "', dueDate=" + dueDate + ", isCompleted=false, priority=null}";
        assertEquals(expected, task.toString());
    }

    @Test
    void testIdUniqueness() {
        // Test that each Task has a unique ID
        Task task1 = new Task("Task 1", dueDate, "desc 1");
        Task task2 = new Task("Task 2", dueDate, "desc 2");
        assertNotEquals(task1.getId(), task2.getId());  // IDs should be unique
    }

    @Test
    void testTaskWithNullTitle() {
        Task task = new Task(null, dueDate);
        assertNull(task.getTitle());  // Should be null
        assertEquals(dueDate, task.getDueDate());
    }

    @Test
    void testTaskWithNullDueDate() {
        Task task = new Task("Test Task", null);
        assertEquals("Test Task", task.getTitle());
        assertNull(task.getDueDate());  // Should be null
    }

    @Test
    void testTaskWithPastDate() {
        // Create a task with a past due date
        Task task = new Task("Test Task", LocalDateTime.now().minusDays(1));
        task.setDescription("invalid date");

        // Validate task using TaskUtil and expect TaskValidationException
        TaskValidationException ex = assertThrows(TaskValidationException.class,
                () -> TaskUtil.validate(task),
                "Expected TaskValidationException for a task with past due date.");

        // ensure correct error message
        assertEquals("Due date can't be in the past.", ex.getMessage());
    }


    @Test
    void testSetCompleted() {
        Task task = new Task("Task", dueDate);
        task.setCompleted(true);
        assertTrue(task.isCompleted());  // Should return true

        task.setCompleted(false);
        assertFalse(task.isCompleted());  // Should return false
    }


    @Test
    public void testEqualsAndHashCode() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);
        Task task1 = new Task("Task 1", dueDate);
        Task task2 = new Task("Task 1", dueDate);

        // Since title and dueDate are the same, they should be equal
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testNotEqualTasks() {
        LocalDateTime dueDate1 = LocalDateTime.now().plusDays(7);
        LocalDateTime dueDate2 = LocalDateTime.now().plusDays(3);
        Task task1 = new Task("Task 1", dueDate1);
        Task task2 = new Task("Task 2", dueDate2);

        assertNotEquals(task1, task2);  // Different title and due date
    }
}
