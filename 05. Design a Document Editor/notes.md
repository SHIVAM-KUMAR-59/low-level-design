# Design a Document Editor

## Table of Contents
- [**Introduction**](#introduction)
- [**Ways to approach a System design Question**](#ways-to-approach-a-system-design-question)
- [**Bad Design Approach**](#bad-design-approach)
- [**Better Design Approach**](#better-design-approach)

---

### Introduction
- Design a document editer where the users be able to write and store their texts and images. should support text and images for now but should be scalable for documents, tables, fonts, etc.

---

### Ways to approach a System design Question:
- **Top Down:** We start by creating the top most object then we create the smaller sub objects and then decide their association/dependency.
- **Bottom Up:** We start by creating the lowest objects and from them we build upwards creating upper level objects.

---

### Bad Design Approach
- Create a `DocumentEditor` class and add all variables and methods into it
![Diagram Preview](BadDesign.png)
- **Problems with this bad design:**
    - Breaks SCP as the DocumentEditor class has multiple responsibilities.
    - Breaks OCP as we would need to add new methods to incorporate new fonts or new types of 
    
---

### Better Design Approach
- Create separate classes and abstract classes based upon requirements and responsibilities.
![Diagram Preview](GoodDesign.png)
- **How this design follows SOLID Principles:**
    - *SCP:* Each class handle only one responsibility thus it follows SCP.
    - *OCP:* We can add new elements without changing anything in existing classes, thus it follows OCP.
    - *LSP:* The subclasses are replacable to parent classes, thus following LSP.
    - *ISP:* No class is implementing any method they don't need, thus it follows ISP.
    - *DIP:* No high level module is directly interacting to low level module, they interact via interface, thus following DIP.

- **Flaws in the current design:**
    - The `DocumentEditor` class has knowledge of the methods present in other classes, so if we remove a method from some other class, we might also have to remove that method form the `DocumentEditor` class, thus it may violate *LSP*.
![Diagram Preview](Best.png)

- **Principle of Least Knowledge**
    - A class should have only those objects or call only those methods which are directly connected to it.
