# Object Oriented Programing

## Table of Contents
- [**Why OOP?**](#why-oop-)
- [**OOP vs Procedural Programming**](#oop-vs-procedural-programming)
- [**Pillars of OOP**](#pillars-of-oop)

---

### Why OOP ?
- OOP allows us to model our application as **interacting objects** mirroring real world entities.
- **Benefits:**
    - **Natural Mapping** of domain concepts (User, Car, Ride).
    - **Secure data encapsulation:** controls who can read or modify state.
    - **Code reuse** via *Inheritence* and *Interfaces*.
    - **Scalability** through loosely coupled modules.

- **Components of OOP:**
    - **Object:** A real-world "thing" or entity with attributes and behaviours.
    - **Class:** Blue print of those attributes (fields) and behaviours (methods).
    - **Instance:** Concrete object in memory, created via class.

---

### OOP vs Procedural Programming

| Aspect | OOP (Object-Oriented Programming) | Procedural Programming |
|--------|-----------------------------------|------------------------|
| **Approach** | Organizes code around objects (data + methods) | Organizes code around functions/procedures |
| **Data & Functions** | Data and functions are bundled together in classes/objects | Data and functions are separate entities |
| **Code Reusability** | High reusability through inheritance and polymorphism | Limited reusability, mainly through function calls |
| **Example** | Java, C++, Python (classes): `class Car { drive() { } }` | C, Pascal: `function drive(car) { }` |
| **Best For** | Large, complex applications with multiple interdependent components | Small, simple programs with straightforward logic |

---

### Pillars of OOP
- **Abstraction:** It hides unnecessary implementation details from the client and exposes only what is essential to use on object's functionality.
    - **Benefits:**
        - **Simplified Interfaces:** Client focuses on *what* an object does, not *how* it does it.
        - **Ease of Maintainance:** Internal changes don't affect client code.
        - **Code Reuse:** Multiple concrete classes can implement same abstract interface.
        - **Reduced Complexity:** Large systems are easier to reason about when broken into abstract modules.

- **Encapsulation:** It bundles on object's data and methods that operate on that data into a single unit, and controls access to its inner workings.
    - **Two Facets of Encapsulation:**
        - **Logical Grouping:** Data and methods that belong together live in the same *"capsule"* (class).
        - **Data Security:** Restrict direct internal access to sensitive fields to prevent invalid or unsafe operations.
    - **Benefits:**
        - **Robustness:** Prevents accidental or malicious misuse of internal state.
        - **Maintainability:** Internal changes do not ripple into client code.
        - **Clear Contracts:** Clients interact only via well-defined methods.
        - **Modularity:** Code is organized into self-contained units, easing testing and resuse.