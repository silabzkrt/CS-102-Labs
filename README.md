# CS102 – Algorithms and Programming II  
### Spring 2025 – Lab & Assignment Portfolio  

This repository contains my solutions for the **CS102 course assignments**, covering concepts from object-oriented design to recursion, GUI development, and hybrid sorting.  
Each lab helped me strengthen my programming skills in Java and prepared me for larger projects.  

---

##  Assignment 1 – Simple Monopoly Game :contentReference[oaicite:0]{index=0}  
- Implemented a **console-based Monopoly game** where the player competes against computer-controlled opponents.  
- Built a turn-based system with dice rolls, property management, rent collection, special event cells, and win/lose conditions.  
- Practiced:
  - **Class design** for players, properties, and game map.  
  - **Randomized events** (dice rolls, AI decisions).  
  - **Game loop logic** with user input vs. computer moves.  
  - Printing structured game maps in the console.  

**Key Learning:** I strengthened my understanding of **OOP fundamentals**, encapsulation, and **simulation logic** for games.  

---

##  Assignment 2 – Chain of Words :contentReference[oaicite:1]{index=1}  
- Developed a console app that generates **word chains** where each consecutive word differs by exactly one character (insert, delete, or replace).  
- Added:
  - `Word` class with chain detection (`canChain`) and storage of possible links.  
  - File I/O with `Scanner` to read a large word list.  
  - Option to generate random word chains up to 10 words without duplicates.  
  - A **guessing game** where the middle word in a chain must be guessed.  
- Practiced:
  - **File I/O and exception handling** (try–catch, `FileNotFoundException`).  
  - **Data structures** (`ArrayList`) for storing and filtering valid words.  
  - **Algorithmic thinking** to detect valid transformations.  

**Key Learning:** Improved skills in **string manipulation, file handling, and algorithm design** for problem constraints.  

---

##  Assignment 3 – Calculating Shapes :contentReference[oaicite:2]{index=2}  
- Built a **hierarchy of 2D and 3D geometric shape classes** using abstract classes and inheritance.  
- Implemented:
  - `GeometricShape2D` (with `calculateArea`, `printInfo`).  
  - `GeometricShape3D` (adds `calculateVolume`).  
  - Shapes: `Rectangle`, `Circle`, `Square`, `Ellipse`, `EquilateralTriangle`, `Cuboid`, `Sphere`, `Cylinder`, `Cube`, `Pyramid`.  
  - `MultiShape2D` that stores multiple shapes, supports merging into a single square.  
- Practiced:
  - **Inheritance, abstraction, polymorphism**.  
  - Working without Java Collections – managing dynamic **object arrays** manually.  
  - Menu-driven design with **shape creation, listing, editing, and merging**.  

**Key Learning:** Learned how to design and use **class hierarchies** and **polymorphic structures** for extensible applications.  

---

##  Assignment 4 – Monopoly with GUI :contentReference[oaicite:3]{index=3}  
- Extended Assignment 1 into a **GUI-based Monopoly game**.  
- Features:  
  - Start screen with player name input (`JFrame`, `GridLayout`).  
  - Game board drawn using `JPanel` and custom `paintComponent`.  
  - Game log with `JTextArea` for player actions.  
  - Action buttons (`Roll`, `Buy`, `Sell`, `Build`, `End Turn`) with `ActionListener`.  
  - Pop-up messages for selling, buying, losing, and game results.  
- Practiced:
  - **Event-driven programming** in Java.  
  - **Swing components and layouts**.  
  - **Graphics API** (`Graphics`, `fillRect`, `drawString`, repaint lifecycle).  
  - Transitioning from **console loops to GUI states**.  

**Key Learning:** Gained experience in **GUI development, MVC separation, and event handling**.  

---

##  Assignment 5 – Mouse Maze with Recursion :contentReference[oaicite:4]{index=4}  
- Implemented a **maze-solving GUI application** where a mouse finds the shortest path to cheese.  
- Features:
  - 5x5 grid with customizable **start, end, and walls**.  
  - Maze interaction via mouse clicks (`MouseListener`).  
  - `Find Path` button uses **recursive backtracking** to compute a shortest path.  
  - Visualization with images (`BufferedImage`, `Graphics.drawImage`).  
  - Options for clearing, resetting, and editing walls dynamically.  
- Practiced:
  - **Recursion in pathfinding**.  
  - **Shortest path exploration** using DFS-like recursion and distance tracking.  
  - **Image rendering & scaling** in Java Swing.  

**Key Learning:** Learned how to apply **recursion to real-world problems** and combine it with **GUI interactivity**.  

---

##  Assignment 6 – Sorting Accounts with Hybrid Sort :contentReference[oaicite:5]{index=5}  
- Built a **banking system simulation** with user accounts in multiple currencies.  
- Features:
  - Users have IDs, names, surnames, and multiple accounts.  
  - Conversion rates for currencies (A, B, C, D) dynamically adjustable.  
  - Implemented **hybrid sorting** (Quick Sort + Insertion Sort) for users by ID or total balance.  
  - Random user/account generation with realistic datasets.  
  - Exception handling for invalid user queries.  
- Practiced:
  - **Sorting algorithms** and runtime measurement with `System.currentTimeMillis()`.  
  - **Hybrid algorithm design** – switching strategies for small partitions.  
  - **Exception handling** (`try-catch`) for invalid IDs.  
  - **Large dataset performance testing**.  

**Key Learning:** Improved knowledge of **sorting algorithms, performance optimization, and exception-safe programming**.  

---

## Overall Reflection  
Throughout CS102, I practiced:  
- **OOP design principles** (abstraction, inheritance, polymorphism).  
- **Data structures & algorithms** (sorting, recursion, graph-like word chains).  
- **File I/O & exception handling**.  
- **GUI development in Swing**.  
- **Performance measurement & hybrid algorithms**.  

This progression built a strong foundation in **software engineering practices** and prepared me for advanced projects in later courses.  
