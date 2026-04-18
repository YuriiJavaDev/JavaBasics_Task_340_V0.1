package com.yurii.pavlenko.app;

import com.yurii.pavlenko.taskmanager.Task;
import com.yurii.pavlenko.taskmanager.types.SimpleTask;

/**
 * Entry point for testing the task manager core.
 */
public class TaskLauncherApp {

    public static void main(String[] args) {
        // Polymorphism: base type reference to a concrete task instance
        Task task = new SimpleTask("Buy coffee");

        // Executing the completion logic
        task.complete();
    }
}