# Singleton Design Pattern

## Table of Contents
- [**What is Singleton?**](#what-is-singleton)
- [**When to Use?**](#when-to-use)
- [**Java Implementation**](#java-implementation)
- [**Key Points**](#key-points)
- [**Pros and Cons**](#pros--cons)

### What is Singleton?

The **Singleton Pattern** ensures a class has only **one instance** throughout the application and provides a global point of access to it.

**Simple Rule:** One class = One object, always.

---

### Real-Life Example

**Office Printer Scenario:**

Imagine an office with 10 computers but only 1 printer. 

- Everyone needs to send print jobs to the **same printer queue**
- You don't want 10 different printer managers - that would cause chaos!
- One **Printer Manager** (Singleton) handles all print requests

Without Singleton → Multiple printer managers → Print conflicts  
With Singleton → One printer manager → Organized printing

---

### When to Use?
- Database connection pools  
- Configuration managers  
- Logging systems  
- Cache managers  
- Thread pools  

- Don't use when you need multiple instances  
- Avoid if it makes testing difficult

---

### Java Implementation

- **Basic Singleton**
```java
public class PrinterManager {
    // Single instance stored here
    private static PrinterManager instance;
    
    // Private constructor - no one can create new objects
    private PrinterManager() {
        System.out.println("Printer Manager initialized");
    }
    
    // Global access point
    public static PrinterManager getInstance() {
        if (instance == null) {
            instance = new PrinterManager();
        }
        return instance;
    }
    
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        // Both get the SAME instance
        PrinterManager pm1 = PrinterManager.getInstance();
        PrinterManager pm2 = PrinterManager.getInstance();
        
        pm1.print("Document1.pdf");
        pm2.print("Document2.pdf");
        
        // Proof they're the same object
        System.out.println(pm1 == pm2);  // true
    }
}
```

---

- **Thread-Safe Singleton (Recommended)**
```java
public class DatabaseConnection {
    // volatile ensures thread safety
    private static volatile DatabaseConnection instance;
    private String connectionUrl;
    
    private DatabaseConnection() {
        this.connectionUrl = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Database connection created");
    }
    
    // Double-checked locking for thread safety
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
}
```

**Usage:**
```java
public class Main {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.query("SELECT * FROM users");
    }
}
```

---

### Key Points

| Feature | Description |
|---------|-------------|
| **Private Constructor** | Prevents `new` keyword usage |
| **Static Instance** | Stores the single object |
| **Static Method** | `getInstance()` provides access |
| **Lazy Initialization** | Created only when needed |

---

### Pros & Cons

- **Advantages**
    - Controlled access to single instance
    - Saves memory (only one object)
    - Global access point
    - Lazy initialization possible

- **Disadvantages**
    - Difficult to unit test
    - Violates Single Responsibility Principle
    - Can hide bad design
    - Global state issues