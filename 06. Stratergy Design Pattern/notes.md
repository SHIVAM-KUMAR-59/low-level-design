# Stratergy Design Pattern

## Table of Contents
- [**Problem Statement**](#problem-statement)
- [**Why Inheritence is bad?**](#why-inheritence-is-bad)
- [**What is Stratergy Design Pattern?**](#what-is-stratergy-design-pattern)


### Problem Statement
- Create an application which shows the simulation of the functions of a robot.
- Assuming a robot has 3 methods:
    - `walk()`
    - `talk()`
    - `projection()`

---

### Why Inheritence is bad?
- Assume we have a class `Robot` having 3 methods: `walk()`, `talk()` and `projection()`.
- Now, we have 2 types of robot classes `CompanionRobot` and `WorkerRobot` which inherit the parent `Robot` class and having their own declaration for the `projection()` method.
- Now, If we introduce a new class `SparrowRobot`, inherting from the same parent class which has the 3 methods and can `fly()`.
- Furthermore, if many new classes come up having the same `fly()` method, then we would need to copy-paste the code from one class to another as there is no `fly()` method present in the parent `Robot` class, thus violating **DRY (Don't Repeat Yourself) Principle**
- Also, we cannot declare the `fly()` method in the parent class as other robots who cannot fly will also hae the ability to fly.
- To tackle this, we can have another class `FlyableRobot` which will inherit the parent class and have the `fly()` method, and all the robots that can fly will inherit the `FlyableRobot` class.
- But, this can go on forever, like if some new kind of robot comes who can `swim()` as well, then we would need to create a new `Swimmable` class, or a robot who flies at a different speed or a different way, thus we would need to make new classes.
- This results in a very **complex inheritence** hierarchy, as every new type will require a new class.

---

### What is Stratergy Design Pattern?
- It defines a family of algorithms, put them into separate classes so that they can be changed at run time.
- **Redesigning the `Robot` class:**
    - As the `projection()` method is not changing (being abstract), keep it the in the main `Robot` class and extract all the other methods.
    - Create interfaces for the rest like `Talkable`, `Walkable`, `Flyable` having `talk()`, `walk()` and `fly()` methods respectively.
    - Now, we can create different concrete classes implementing these interfaces which have the different kinds of implementations of the methods.
    - We can have the references to these interfaces in our main `Robot` class. This class is also called **client** and the different types of heirarchies are called **stratergies**.
---