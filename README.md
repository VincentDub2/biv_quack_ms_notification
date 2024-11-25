# Notification Service

## Description
The **Notification Service** is a microservice responsible for managing notifications in the application. It provides RESTful endpoints for creating, retrieving, and deleting notifications. Notifications can be filtered by recipient, and all data is persisted in a database.

This microservice is built using **Spring Boot** and follows RESTful API principles to ensure scalability and ease of integration with other components.

---

## Features
- Retrieve all notifications.
- Fetch a specific notification by its unique ID.
- Retrieve notifications for a specific recipient.
- Create new notifications.
- Delete notifications by ID.

---

## API Endpoints

### Base URL
/


### Endpoints
1. **GET `/notifications`**
    - Retrieves a list of all notifications.
    - **Response:** `200 OK` with a JSON array of notifications.

2. **GET `/notifications/{id}`**
    - Fetches a specific notification by its unique ID.
    - **Path Parameter:**
        - `id` (Long): ID of the notification.
    - **Response:** `200 OK` with a JSON object representing the notification.

3. **GET `/notifications/recipient/{recipientId}`**
    - Retrieves a notification based on the recipient's ID.
    - **Path Parameter:**
        - `recipientId` (Long): ID of the recipient.
    - **Response:** `200 OK` with a JSON object representing the notification.

4. **POST `/notifications`**
    - Creates a new notification.
    - **Request Body:** JSON object representing the notification details.
    - **Response:** `201 Created` with the newly created notification.

5. **DELETE `/notifications/{id}`**
    - Deletes a notification by its unique ID.
    - **Path Parameter:**
        - `id` (Long): ID of the notification to delete.
    - **Response:** `204 No Content` if successful.

---

## Models

### Notification
The `Notification` entity represents a single notification and includes:
- `id` (Long): Unique identifier for the notification.
- `recipientId` (Long): ID of the recipient.
- `content` (String): The content of the notification.
- `timestamp` (DateTime): Timestamp when the notification was created.

---

## Technologies
- **Java 21**
- **Spring Boot**
- **Spring Web** (REST APIs)
- **Spring Data JPA** (Database access)
- **PostgreSQL** (Database)
- **Maven/Gradle** (Dependency Management)

---

## Getting Started

### Prerequisites
- **Java 21** or later
- **PostgreSQL** installed and running
- **Maven** or **Gradle**

### Running the Application
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd service-notification
    ```

## Set up the database

1. Create a PostgreSQL database named `notificationsdb`.
2. Update the `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/notificationsdb
   spring.datasource.username=<username>
   spring.datasource.password=<password>
    ```

3. Run the application using Maven or Gradle:
4. Using Maven:
   ```bash
   mvn spring-boot:run
    ```
5. Using Gradle:
    ```bash
   ./gradlew bootRun
    ```

### Contributors
- [Dubuc Vincent](https://github.com/VincentDub2)
