# SOLID Principles

## Table of Contents
- [**Issues that occur from not following SOLID Principles**](#issues-that-occur-from-not-following-solid-principles)
- [**Introduction**](#introduction)
- [**Single Responsibility Principle (SRP)**](#single-responsibility-principle-srp)

---

### Issues that occur from not following SOLID Principles
- **Maintainability:** The application becomes difficult to locate bugs and do changes without affecting existing code.
- **Readability:** It becomes difficult to read and understand the purpose/behaviour of a class or method.
- **Bugs:** A large number of bugs can come up while building big applications which are hard to debug.

---

### Introduction
- SOLID Principles are a set of guidelines or standards which must be followed to make the development process smoother and build a maintainable, scalable and readable software/application.
- SOLID stands for:
    - *Single Responsibility Principle (SRP)*
    - *Open-Closed Principle (OCP)*
    - *Liskov's Substitution Principle (LSP)*
    - *Interface Segregation Principle (ISP)*
    - *Dependency Inversion Principle (DIP)*
We will study about each of them individually in detail.

---

### Single Responsibility Principle (SRP)
- A class should have only one reason to change.
- A class should do only one thing, i.e. it must have only one responsibility.
```java
    // SRP - Single Responsibility Principle: One class, one job
    class Invoice {
        double amount;
    }

    class InvoicePrinter {
        void print(Invoice invoice) {
            System.out.println(invoice.amount);
        }
    }
```

---

### Open-Closed Principle (OCP)
- A class should be open for extention but closed for modification.
- Extention means adding new feature and modification means changing old code/methods.
```java
// OCP - Open/Closed Principle: Extend, don’t modify
interface Shape {
    double area();
}

class Rectangle implements Shape {
    public double area() {
        return 10 * 5;
    }
}

class AreaCalculator {
    double calculate(Shape shape) {
        return shape.area();
    }
}
```