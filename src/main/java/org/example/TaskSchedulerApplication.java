package org.example;

import org.example.entity.Task;
import org.example.exception.PriorityValidationException;
import org.example.service.IPriorityStrategy;
import org.example.service.StandardPriority;
import org.example.service.TaskManager;
import org.example.service.TaskPriorityDecorator;

import java.time.LocalDateTime;

public class TaskSchedulerApplication {
    public static void main(String[] args) throws PriorityValidationException {
        TaskManager manager = new TaskManager();

        // create and read
        System.out.println("Creating tasks:");
        Task task = new Task("Learn OOP", LocalDateTime.now().plusDays(1));
        Task task2 = new Task("Learnt OOP", LocalDateTime.now().plusDays(2));
        manager.addTask(task);
        manager.addTask(task2);
        System.out.println("\nYour Tasks: " + manager.getTasks());

        // create and read
        Task taskToUpdate = new Task("Learn DP", LocalDateTime.now().plusDays(2));
        manager.addTask(taskToUpdate);
        System.out.println("\nCreated Task: " + manager.getTaskById(taskToUpdate.getId()));

        // update and read
        System.out.println("\nUpdating tasks:");
        taskToUpdate.setTitle("learn Design Principles");
        taskToUpdate.setDueDate(LocalDateTime.now().plusDays(3));
        Task updatedTask = manager.updateTask(taskToUpdate.getId(), taskToUpdate);
        System.out.println("Updated Task: " + updatedTask);

        taskToUpdate.setCompleted(true);
        updatedTask = manager.updateTask(taskToUpdate.getId(), taskToUpdate);
        System.out.println("Updated Task: " + updatedTask);

        // all tasks
        System.out.println("\nYour Tasks: " + manager.getTasks());

        System.out.println("\nDeleting tasks:");
        Task taskToDelete = new Task("Learn Java", LocalDateTime.now().plusDays(3));
        manager.addTask(taskToDelete);
        System.out.println("\nYour Tasks: " + manager.getTasks());

        // deleted task
        manager.deleteTask(taskToDelete.getId());
        System.out.println("\nYour Tasks: " + manager.getTasks());

        // testing priority tasks
        // Step 1: Create a normal task
        Task normalTask = new Task("Finish Report", LocalDateTime.now().plusDays(2));

        // Step 2: Create a priority strategy (e.g., High Priority)
        IPriorityStrategy highPriority = new StandardPriority(StandardPriority.Priority.HIGH);

        // Step 3: Decorate the task with priority
        Task priorityTask = new TaskPriorityDecorator(normalTask, highPriority).getTask();

        // Step 4: Print out the task
        System.out.println(priorityTask);
    }
}