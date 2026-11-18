# Spatial KNN Search with Generic Quadtree (Java OOP)

A spatial data structure project implementing a **Generic Quadtree** and **K-Nearest Neighbors (KNN)** search engine in Java, combining generics, polymorphism, lambda expressions, and geometric spatial pruning.

---

## 🧱 Architecture & Packages

1. **`lpoo.geom` (Geometry & Spatial Structures):**
   - **`Quadtree<P extends Point2>`:** Refactored with Java Generics for compile-time type safety.
   - **`KNN`:** Encapsulates K-nearest neighbor ordering and capacity limits (`findNeighbors` and `forEachNeighbor`).
   - **`Particle2`:** Extends `Point2` with RGB color attributes to demonstrate polymorphism.
   - **`PointFunc<P>`:** Functional interface used to filter search results.

2. **`lpoo.math` (Mathematical Utilities):**
   - **`Real`:** Encapsulates floating-point epsilon tolerance for accurate comparisons along Axis-Aligned Bounding Box (AABB) boundaries.

3. **`app` (Application & Visualization):**
   - **`Main`:** Reads coordinate data (`points.txt`), instantiates `Particle2` objects with randomized colors, builds the generic Quadtree, and provides interactive querying via `Console`.
   - **`QuadtreeViewer`:** Graphical tree visualization adapted with generic wildcards (`<?>`).

---

## ⚡ Technical Highlights

- **Lambda Filtering:** Uses Java lambda expressions with `PointFunc<P>` to filter radius and KNN queries by specific particle colors (e.g., Red).
- **Geometric Spatial Pruning:** The recursive search discards entire Quadtree quadrants whenever the minimum distance from a quadrant's bounding box to the query point exceeds the current worst KNN distance or search radius.

---

## 🛠️ Tech Stack

- **Language:** Java (Object-Oriented Programming)
- **Concepts:** Quadtree, K-Nearest Neighbors (KNN), Java Generics & Wildcards, Polymorphism, Functional Interfaces & Lambdas, Spatial Pruning
