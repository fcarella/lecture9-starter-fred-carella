This `README.md` is designed to be placed in the root of your `lecture9-starter-firstname-lastname` repository. It provides a professional overview of the project, setup instructions, and the key concepts covered in the lecture.

***

# Lecture 9: Spring Boot MVC and Full CRUD

This project serves as the foundation for **Web-based Inventory Management** in the CSD214 course. It transitions our backend data layer (JPA/Hibernate) into a full-stack Web MVC application using **Spring Boot** and **Thymeleaf**.

## 🚀 Key Learning Objectives
* **Spring MVC Architecture:** Understanding the flow between `@Controller`, Model, and View.
* **Server-Side Rendering (SSR):** Using Thymeleaf to dynamically generate HTML on the server before it reaches the browser.
* **Web Request Handling:** Implementing `@GetMapping`, `@PostMapping`, and `@PathVariable` to route browser traffic.
* **Data Binding:** Using `@ModelAttribute` to automatically map HTML form submissions to Java Entities.
* **Fragments:** Building reusable UI components (like navigation bars) to maintain DRY (Don't Repeat Yourself) code.
* **Full CRUD:** Implementing Create, Read, Update, and Delete operations for our domain models.

## 🛠 Prerequisites
* **Java 17+** (JDK 25 recommended based on local environment)
* **Docker Desktop** (to run the MySQL 8.0 database)
* **IntelliJ IDEA** (with the Spring Boot plugin)

## ⚙️ Setup & Installation

### 1. Database Setup
This project uses a MySQL database running in a Docker container.
1. Ensure Docker Desktop is running.
2. Run the following command in the project root:
   ```bash
   docker compose up -d
   ```
3. This starts a MySQL container on port `3333` (mapped to `3306` inside the container).

### 2. Running the Application
You can run the application directly through IntelliJ:
1. Open the project in IntelliJ.
2. Locate `src/main/java/csd214/app/Lecture9StarterFirstnameLastnameApplication.java`.
3. Right-click the class and select **Run**.
4. Once the console logs indicate `Started Application...`, open your browser to:
   👉 `http://localhost:8080/inventory`

## 📂 Project Structure
* `docker-compose.yaml`: MySQL container configuration.
* `src/main/java/csd214/app/controllers/`: Web controllers handling HTTP requests.
* `src/main/java/csd214/app/entities/`: JPA Entities (Parent `ProductEntity`, Child `BookEntity`).
* `src/main/java/csd214/app/repositories/`: Spring Data JPA interfaces for database access.
* `src/main/resources/templates/`: Thymeleaf HTML files for the UI.
* `src/main/resources/templates/fragments/`: Reusable UI components (like `navbar.html`).

## 💡 Key Concepts Explained
* **PRG Pattern (Post/Redirect/Get):** We use `return "redirect:/..."` after any `POST` request to prevent accidental form resubmission if a user refreshes their browser.
* **Data Binding:** By using `@ModelAttribute` in our POST methods, Spring automatically performs the tedious task of calling setters on our Entity objects using the data submitted from HTML forms.
* **Thymeleaf Fragments:** Using `th:replace="~{fragments/navbar :: header}"` allows us to define the site navigation in one place and inject it into every page automatically.

## 🎓 Next Steps
This project is configured as a **GitHub Template**. At the conclusion of this lecture, use this repository to generate your **Lab 7** workspace, where you will implement additional CRUD functionality for `MagazineEntity` and other inventory items.
