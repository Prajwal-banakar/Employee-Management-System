# Employee Management System

This is a simple employee management system built with Spring Boot, Spring Data MongoDB, and Thymeleaf.

## Features

*   Create new employees
*   Update existing employee information
*   Remove individual employees
*   Remove all employees

## Technologies Used

*   Java
*   Spring Boot
*   Spring Data MongoDB
*   Thymeleaf
*   Maven

## How to Run

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 8 or later
    *   Apache Maven
    *   MongoDB

2.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```

3.  **Configure the database:**
    Open `src/main/resources/application.properties` and configure your MongoDB connection details:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/ems
    ```

4.  **Build and run the application:**
    ```bash
    mvn spring-boot:run
    ```

5.  **Access the application:**
    Open your web browser and go to `http://localhost:8080`.

## Endpoints

| Method | Endpoint      | Description                  |
|--------|---------------|------------------------------|
| GET    | `/`           | Displays the home page.      |
| GET    | `/create`     | Displays the form to create a new employee. |
| POST   | `/create`     | Creates a new employee.      |
| GET    | `/update`     | Displays the form to update an employee. |
| POST   | `/update`     | Updates an employee's information. |
| GET    | `/remove`     | Displays the form to remove an employee. |
| POST   | `/remove`     | Removes an employee.         |
| GET    | `/removeall`  | Displays the form to remove all employees. |
| POST   | `/removeall`  | Removes all employees.       |
