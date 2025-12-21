# Intro to Low Level Design

## Table of Contents
- [**What is Low Level Design ?**](#what-is-low-level-design-)
- [**Core LLD Principles & Focus Areas**](#core-lld-principles--focus-areas)
- [**HLD vs LLD**](#hld-vs-lld)

### What is Low Level Design ?
- LLD is basically designing the internal structure("skeleton") of an application by identifying classes/objects, their relationships, data flows and DSA solutions plug into this solution.
    - **DSA:** Solves isolated problem using algorithms
    - **LLD:** Determines which objects exist in the system and how they interact, then applies DSA inside that structure.
    - **HLD:** Architecture: System-wide infrastructure, tech-stack, databases, servers etc.
- **If DSA is the brain, LLD is the skeleton of the application.**

---

### Core LLD Principles & Focus Areas
- **Scalability:**
    - Handle large user volumes easily.
    - Code structure should allow rapid, low-effor expansion.

- **Maintainability:**
    - New features shouldn't break existing ones.
    - Code should be easy to debug and locate bugs.

- **Reusability:**
    - Write loosely coupled, "plug-and-play" modules, i.e, it should be generic and not specifi to one use case.

---

### HLD vs LLD

| Aspect | HLD (High-Level Design) | LLD (Low-Level Design) |
|--------|-------------------------|------------------------|
| **What** | Overall system architecture & components | Detailed logic & implementation of each component |
| **Who** | System Architects, Senior Developers | Developers, Programmers |
| **Focus** | WHAT the system does & major components | HOW each component works internally |
| **Contains** | Architecture diagrams, tech stack, module breakdown, data flow, external integrations | Class diagrams, algorithms, pseudocode, database schema (tables/columns), API details, function logic |
| **Example** | "User service communicates with Payment gateway via REST API" | "UserController.createUser() validates email using regex, hashes password with bcrypt, saves to users table with fields: id, name, email, password_hash" |