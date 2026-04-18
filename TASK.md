### Imagine you're developing a smart task manager that will help your team stay productive. Each task in this manager has a name, and while it can be completed, the actual process can vary.

#### - First, create an abstract Task class. Define a String title field to store the task's title and add an abstract void complete() method to symbolize the completion of the task.

#### - Don't forget to also implement a constructor for Task that accepts and stores the task's title.

#### - Next, create the first, base task type—SimpleTask. This class should extend Task and implement the complete() method so that it displays the phrase "Task '<task_title>' completed," substituting the actual task title.

```java
// A quick demo
public class TaskLauncherApp {
    public static void main(String[] args) {
        Task task = new SimpleTask("Buy coffee");
        task.complete(); // Expected output: The 'Buy coffee' task is complete
    }
}
```
