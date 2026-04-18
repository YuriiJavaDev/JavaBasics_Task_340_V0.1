## Simplifying complex systems with abstractions.

### 1. Multi-Level Abstraction

When you're just starting out programming, everything seems simple: write a class, call a method, get a result. But in real projects, everything gets more complex: dozens of classes, hundreds of methods, thousands of lines of code... And if you're working on a project with a team, the task becomes even more complex! How can you avoid getting lost in this chaos?

The answer is to divide the complex into the simple, or better yet, into levels of abstraction.

#### What are levels of abstraction?

Think of it like floors in a large house: each floor has its own life, but all floors are interconnected. In programming, it's common to distinguish the following layers (levels):

- **User Interface (UI)** — what the user sees.
- **Business Logic** — the rules and processes that implement the essence of the application. - **Data Access (DAO, Repository)** — working with a database or files.

Each layer works with abstractions, unaware of the details of other layers. For example, business logic doesn't care how the user interface is implemented or how data is stored—it cares about methods like **saveOrder()** or **findUserById()**.

#### Real-Life Analogy

Imagine a restaurant. Guests (UI) place an order through a waiter (interface abstraction), a chef (business logic) prepares the dish, and a storekeeper (data access) monitors the stock. Guests don't know how the chef prepares the dish, and the chef doesn't care where the potatoes are—the main thing is that they're readily available.

### 2. Example: Multi-Tier Architecture in Practice

Let's develop our example project—for example, a task manager application. We already know how to create classes for tasks; now let's complicate the picture and divide the application into layers.

#### Abstracting Abstractions

- **Task** — an abstract description of a task: a task has a name, status, and methods for execution.
- **TaskRepository** — an abstraction for storing tasks (it doesn't matter where—in memory, a file, or a database).
- **TaskService** — business logic: adding tasks, searching, and execution.

#### Abstract Classes and Interfaces

```java
// Business Logic Layer
public abstract class Task {
    private String title;
    private boolean completed;
    
    public Task(String title) {
        this.title = title;
        this.completed = false;
    }
    
    public abstract void complete();
    
    public String getTitle() {
        return title;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    protected void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

// Data storage layer (abstraction)
public interface TaskRepository {
    void save(Task task);
    Task findByTitle(String title);
    List<Task> findAll();
}
```

#### Layer implementation

**Task implementation**

```java
public class WorkTask extends Task {
    private String deadline;
    
    public WorkTask(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }
    
    @Override
    public void complete() {
        setCompleted(true);
        System.out.println("Work task '" + getTitle() + "' completed by deadline " + deadline);
    }
}
```

**Implementation of TaskRepository**

```java
public class InMemoryTaskRepository implements TaskRepository { 
    private List<Task> tasks = new ArrayList<>(); 
    
    @Override 
    public void save(Task task) { 
        tasks.add(task); 
    } 
    
    @Override 
    public Task findByTitle(String title) { 
        for (Task task : tasks) { 
            if (task.getTitle().equals(title)) { 
                return task; 
            } 
        } 
        return null; 
    } 
    
    @Override 
    public List<Task> findAll() { 
        return new ArrayList<>(tasks); 
    }
}
```

**Implementation of TaskService**

```java
public class TaskService { 
    private TaskRepository repository; 
    
    public TaskService(TaskRepository repository) { 
        this.repository = repository; 
    } 
    
    public void addTask(Task task) { 
        repository.save(task); 
    } 
    
    public void completeTask(String title) { 
        Task task = repository.findByTitle(title); 
        if (task != null) { 
            task.complete(); 
        } else { 
            System.out.println("Task not found: " + title); 
        } 
    } 
    
    public void showAllTasks() { 
        for (Task task : repository.findAll()) { 
            System.out.println(task.getTitle() + " — " + (task.isCompleted() ? "completed" : "not completed")); 
        }
    }
}
```

**Usage in the Main Class**

```java
public class Main {
    public static void main(String[] args) {
        TaskRepository repo = new InMemoryTaskRepository();
        TaskService service = new TaskService(repo);
        
        service.addTask(new WorkTask("Generate a report", "2025-07-15"));
        service.addTask(new WorkTask("Prepare a presentation", "2025-07-16"));
        
        service.showAllTasks();
        
        service.completeTask("Generate a report");
        service.showAllTasks();
    }
}
```

#### What do we get?

- The main class (**Main**) doesn't know how the task repository works—it works with the **TaskRepository** abstraction.
- **TaskService** doesn't know what tasks there are—it works with the **Task** abstract class.
- If tomorrow we want to store tasks in the database instead of in memory, we simply implement a new **DatabaseTaskRepository** class without rewriting the business logic and UI.
- If a new task type appears, for example, **HomeTask**, we simply add a new inheritor class.

### 3. Benefits for teamwork

In large projects, it's rare for one person to write everything. Typically, the team is divided into "front-end developers," "back-end developers," "repository developers," and so on. How do abstractions help them avoid stepping on each other's toes?

#### Separation of Concerns

**Everyone works at their own level of abstraction.**

- One developer writes the **TaskRepository** implementation for working with the database.
- Another develops the business logic (**TaskService**).
- A third develops the user interface.

**The contract between layers is fixed by abstractions.**

As long as everyone agrees that **TaskRepository** has the **save, **findByTitle, **findAll** methods, the implementation details are unimportant.

#### Ease of Testing and Substituting Components

- It's easy to swap out one implementation for another (for example, use **InMemoryTaskRepository** for testing, and **InMemoryTaskRepository** for working with the database in production).
- A tester can replace the data layer with a mock to test the business logic in isolation.

#### Development Independence

- If someone decides to add a new task type, they don't break the existing code—they simply implement a new **Task** subclass.
- If a new data storage method is introduced, only the interface implementation changes, leaving the rest of the code untouched.

### 4. Best Practices: How to Avoid Overdoing Abstraction

Abstraction is like salt in a dish: without it, it's bland, but if you overdo it, you ruin everything. Here are some tips:

**Use abstractions where they truly simplify the system.**

Don't make an abstract class for the sake of an abstract class. If you only have one task type, abstraction may not be necessary.

**Document abstract classes and methods.**

Good documentation helps understand what exactly a subclass should implement and why it's needed.

**Try to make abstractions meaningful.**

An abstract class should express truly shared behavior and/or state.

**Don't mix responsibilities.**

Don't add methods to an abstract class that are needed only by one of its descendants.

### 5. Abstraction in Large Systems: A Real-World Example

Let's look at how abstraction works in a real-world project—for example, an online store.

#### System Layers

- **Controllers (UI):** Accept user requests (e.g., "place an order").
- **Services (business logic):** Check product availability, calculate discounts, and process orders.
- **Repositories (data access):** Save orders, products, and users to the database.

#### Abstraction Example

```java
// Abstraction for the order processing service
public interface OrderService {
    void createOrder(Order order);
    Order findOrderById(String id);
}

// Abstraction for the order repository
public interface OrderRepository {
    void save(Order order);
    Order findById(String id);
}
```

Each layer only knows about its own abstraction. If tomorrow they decide to store orders in the cloud, only the implementation of **OrderRepository** changes.

#### Layer Interaction — Diagram

```
[UI/Controller] <--> [OrderService (abstraction)] <--> [OrderRepository (abstraction)] <--> [Database]
```

- Each layer works with the abstraction without knowing the details of the underlying layer.
- This allows each layer to be developed, tested, and refined independently.

#### Abstraction and Code Maintenance

- **Easy to add new features** (new types of tasks, payments, transport).
- **Easy to fix bugs** (fix a bug in one place—all descendants receive the update).
- **Easy to test** (you can replace layers with stubs for unit tests).

### 6. Common Mistakes in Designing Abstractions

**Mistake №1: Overabstraction.** Sometimes it's tempting to create an abstract class for every little thing. But if you only have one type of entity, don't go for the trend of creating an abstraction—it will only complicate your code.

**Mistake №2: Overly vague abstraction.** If your abstract class describes too much and doesn't have a clear scope of responsibility, its descendants will be forced to implement unnecessary methods or store "dead" fields.

**Mistake №3: Violating the Single Responsibility Principle.** An abstract class should be responsible for only one area of ​​behavior. For example, you shouldn't mix methods for storing and methods for business logic in a single abstract class.

**Mistake №4: Tight coupling between layers.** If the business logic layer directly depends on a specific repository implementation (for example, it uses **new InMemoryTaskRepository()** internally), then replacing the repository will require rewriting the entire code. Use abstractions (interfaces, abstract classes) to loosen coupling.

**Mistake №5: Insufficient documentation.** An abstraction is a contract, and it needs to be clearly described. If you don't clearly define what the successor should do, you can easily encounter unexpected bugs or "creative" behavior from your colleagues.
