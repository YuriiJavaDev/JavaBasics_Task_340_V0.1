# Task Management Core: Abstraction (JavaBasics_Task_340_V0.1)

## 📖 Description
Efficient team productivity relies on a structured way to manage various activities. This project implements a **Task Management Core** using an abstract `Task` class. It manages a common `title` field for all task types and establishes a mandatory `complete()` contract. The `SimpleTask` implementation provides a basic realization of this contract, demonstrating how subclasses can utilize parent state (title) to provide meaningful feedback upon task execution.

## 📋 Requirements Compliance
- **Stateful Abstraction**: Implemented an abstract `Task` class to hold the task's identity.
- **Constructor Management**: Provided a dedicated constructor in the abstract class to ensure all tasks have a title.
- **Contract Fulfillment**: Realized the `complete()` method in the `SimpleTask` subclass.
- **Polymorphic Execution**: Verified that a `Task` reference can successfully trigger subclass behavior.

## 🚀 Architectural Stack
- Java 8+ (Abstract Classes, Inheritance, Encapsulation)

## 🏗️ Implementation Details
- **Task**: The abstract base managing the title and defining the completion contract.
- **SimpleTask**: A concrete task type providing basic completion feedback.
- **TaskLauncherApp**: The entry point for verifying the task manager's core logic.

## 📋 Expected result
```text
Task 'Buy coffee' completed
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_340/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── TaskLauncherApp.java
    │                 └── taskmanager/
    │                     ├── types/
    │                     │   └── SimpleTask.java
    │                     └── Task.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.taskmanager.Task;
import com.yurii.pavlenko.taskmanager.types.SimpleTask;

public class TaskLauncherApp {

    public static void main(String[] args) {
        Task task = new SimpleTask("Buy coffee");
        task.complete();
    }
}
```
```java
package com.yurii.pavlenko.taskmanager;

public abstract class Task {
    protected String title;

    public Task(String title) {
        this.title = title;
    }

    public abstract void complete();
}
```
```java
package com.yurii.pavlenko.taskmanager.types;

import com.yurii.pavlenko.taskmanager.Task;

public class SimpleTask extends Task {

    public SimpleTask(String title) {
        super(title);
    }

    @Override
    public void complete() {
        System.out.println("Task '" + title + "' completed");
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
