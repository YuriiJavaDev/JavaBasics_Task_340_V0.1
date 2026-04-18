package com.yurii.pavlenko.taskmanager;

/**
 * Abstract blueprint for any task within the management system.
 */
public abstract class Task {
    protected String title;

    public Task(String title) {
        this.title = title;
    }

    /**
     * Abstract method representing the completion process.
     */
    public abstract void complete();
}