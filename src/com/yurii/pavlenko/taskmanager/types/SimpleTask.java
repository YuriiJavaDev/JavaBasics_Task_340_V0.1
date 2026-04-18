package com.yurii.pavlenko.taskmanager.types;

import com.yurii.pavlenko.taskmanager.Task;

/**
 * Basic task implementation with simple feedback logic.
 */
public class SimpleTask extends Task {

    public SimpleTask(String title) {
        super(title);
    }

    @Override
    public void complete() {
        System.out.println("Task '" + title + "' completed");
    }
}