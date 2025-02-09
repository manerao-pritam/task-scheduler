package org.example.service;

import org.example.entity.Task;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

public class RecurringTask extends Task {
    public enum Frequency {
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    public enum EndType {
        END_DATE,
        REPEAT_COUNT
    }

    private Frequency frequency;    // eg, DAILY, WEEKLY, etc.
    private int interval;           // every n days
    private LocalDateTime startDate;
    private EndType endType;        // recurrence ends or end date
    private LocalDateTime endDate;
    private int occurrences;        // no. of times a task should be repeated

    // optional: for Weekly freq
    private Set<DayOfWeek> daysOfWeek;      // eg, Monday, Friday, etc.

    public RecurringTask(final Frequency frequency, final int interval, final LocalDateTime startDate,
                         final EndType endType, final LocalDateTime endDate, final int occurrences,
                         final Set<DayOfWeek> daysOfWeek) {
        super();
        this.frequency = frequency;
        this.interval = interval;
        this.startDate = startDate;
        this.endType = endType;
        this.endDate = endDate;
        this.occurrences = occurrences;
        this.daysOfWeek = daysOfWeek;
    }

    public RecurringTask(final String title, final LocalDateTime dueDate, final Frequency frequency,
                         final int interval, final LocalDateTime startDate, final EndType endType,
                         final LocalDateTime endDate, final int occurrences, final Set<DayOfWeek> daysOfWeek) {
        super(title, dueDate);
        this.frequency = frequency;
        this.interval = interval;
        this.startDate = startDate;
        this.endType = endType;
        this.endDate = endDate;
        this.occurrences = occurrences;
        this.daysOfWeek = daysOfWeek;
    }

    public RecurringTask(final String title, final LocalDateTime dueDate, final String description,
                         final Frequency frequency, final int interval, final LocalDateTime startDate,
                         final EndType endType, final LocalDateTime endDate, final int occurrences,
                         final Set<DayOfWeek> daysOfWeek) {
        super(title, dueDate, description);
        this.frequency = frequency;
        this.interval = interval;
        this.startDate = startDate;
        this.endType = endType;
        this.endDate = endDate;
        this.occurrences = occurrences;
        this.daysOfWeek = daysOfWeek;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public EndType getEndType() {
        return endType;
    }

    public void setEndType(EndType endType) {
        this.endType = endType;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
