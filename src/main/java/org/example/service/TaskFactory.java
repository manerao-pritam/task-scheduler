package org.example.service;

import org.example.entity.Task;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class TaskFactory {
    public static Task createTask(final TaskType type,
                                  final String title,
                                  final String description,
                                  final LocalDateTime dueDate,
                                  Map<String, Object> params) {
        return switch (type) {
            case RECURRING -> {
                System.out.println("Recurring task will be created");
                RecurringTask.Frequency frequency = (RecurringTask.Frequency) params.get("frequency");
                int interval = (Integer) params.get("interval");
                RecurringTask.EndType endType = (RecurringTask.EndType) params.get("endType");
                LocalDateTime startDate = (LocalDateTime) params.get("startDate");
                LocalDateTime endDate = (LocalDateTime) params.get("endDate");
                int occurrences = params.get("occurrences") != null ? (Integer) params.get("occurrences") : 0;
                Set<DayOfWeek> daysOfWeek = (Set<DayOfWeek>) params.get("dayOfWeek");


                yield new RecurringTask(title, dueDate, description, frequency, interval, startDate, endType, endDate,
                        occurrences, daysOfWeek);

            }
            case DEADLINE -> {
                System.out.println("Deadline driver task will be created");
                // @TODO:: Create and return a DeadlineTask
                yield new Task(title, dueDate);
            }
            case SIMPLE, default -> new Task(title, dueDate);
        };
    }
}
