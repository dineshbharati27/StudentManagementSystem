# Student Management System

A Spring Boot application for managing students, courses, and enrollments with role-based authentication (Admin/Student).

## Features

### Admin Role:
- Add/View students and courses
- Assign courses to students
- Search students by name or course

### Student Role:
- Validate identity using student code and DOB
- View/update profile
- Enroll in or leave courses

### Security:
- Basic authentication with in-memory admin user (username: admin, password: admin123)
- Role-based access control (RBAC)

### Database:
- MySQL 8.x for persistent storage
- JPA/Hibernate for ORM with automatic schema updates
- Hibernate dialect: MySQL8Dialect

## Tech Stack

### Backend:
- Java 17
- Spring Boot 3.x
- Spring Security
- JPA/Hibernate
- MySQL 8.x
- Maven

### Development Tools:
- Spring Boot DevTools
- Spring Boot Actuator
- Lombok (for reducing boilerplate code)

## Project Structure
```
src/  
├── main/  
│   ├── java/  
│   │   └── com/StudentManagementSystem/  
│   │       ├── config/                # Security and app configs  
│   │       │   └── SecurityConfig.java  
│   │       ├── controller/            # REST endpoints  
│   │       │   ├── AdminController.java  
│   │       │   └── StudentController.java  
│   │       ├── exception/             # Custom exception handling  
│   │       │   ├── ApiError.java  
│   │       │   ├── GlobalExceptionHandler.java  
│   │       │   ├── ApiException.java  
│   │       │   └── ResourceNotFoundException.java  
│   │       ├── models/                # JPA entities  
│   │       │   ├── Address.java  
│   │       │   ├── Course.java  
│   │       │   └── Student.java  
│   │       ├── repository/            # JPA repositories  
│   │       │   ├── AddressRepository.java  
│   │       │   ├── CourseRepository.java  
│   │       │   └── StudentRepository.java  
│   │       └── service/               # Business logic  
│   │           ├── AdminService.java  
│   │           ├── StudentService.java  
│   │           └── implementation/    # Service implementations  
│   │               ├── AdminServiceImpl.java  
│   │               └── StudentServiceImpl.java  
│   └── resources/  
│       ├── application.properties     # DB and security configs
│       ├── static/                    # Static resources
│       └── templates/                 # Template files
```

## API Endpoints

### Admin Endpoints (Requires ADMIN role)

| Endpoint | Method | Description |
|:---------|:-------|:------------|
| `/admin/students` | GET | Get all students |
| `/admin/add-student` | POST | Add a new student |
| `/admin/add-course` | POST | Add a new course |
| `/admin/assign-course` | POST | Assign course to student (query params: studentId, courseId) |
| `/admin/student/search` | GET | Search students by name (query param: name) |
| `/admin/students/by-course` | GET | Get students by course (query param: courseId) |

### Student Endpoints

| Endpoint | Method | Description |
|:---------|:-------|:------------|
| `/student/validate` | POST | Validate student (params: code, dob) |
| `/student/{id}` | PUT | Update student profile |
| `/student/courses/{id}` | GET | Get courses enrolled by student |
| `/student/course/{studentId}/{courseId}` | DELETE | Leave a course |

## Setup & Run

### Prerequisites
- JDK 21
- MySQL 8.x
- Maven 3.8+

### Steps
1. Clone the repository:
```bash
git clone https://github.com/your-repo/StudentManagementSystem.git
```

2. Configure MySQL:
Create a database named `newdb` and update application.properties with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/newdb
spring.datasource.username=root
spring.datasource.password=root
```

3. Build and run:
```bash
mvn spring-boot:run
```

4. Access APIs:
- Use http://localhost:8080/admin/** for admin operations
- Default admin credentials: admin/admin123

## Configuration Details

### Database Configuration
- MySQL 8.x with automatic schema updates
- Hibernate dialect: MySQL8Dialect
- DDL auto: update (automatically updates schema)

### Security Configuration
- In-memory admin user
- Basic authentication
- Role-based access control

### Logging Configuration
- Spring Security: DEBUG
- Spring Web: DEBUG
- Spring Web Filter: DEBUG
- Spring Security Web: DEBUG
- Spring MVC: DEBUG

## Future Improvements
- Add JWT/OAuth2 for enhanced security
- Implement frontend (React/Angular)
- Add unit/integration tests (JUnit, Mockito)
- Dockerize the application
- Add API documentation using Swagger/OpenAPI
- Implement caching for better performance
- Add request validation and sanitization
- Implement rate limiting


