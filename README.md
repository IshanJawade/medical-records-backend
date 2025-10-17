# Medical Records Backend

Spring Boot REST API for managing medical records with JWT-based authentication and role-based access control (RBAC). CORS is configured for a Vite frontend at http://localhost:5173.

## Tech Stack
- Java 21+
- Spring Boot 3 (Web, Security, Data JPA, Validation)
- PostgreSQL
- Hibernate
- JJWT (HS256)
- Lombok

## Roles and Permissions (RBAC)
- ADMIN: Full CRUD on all resources.
- RECEPTION: Can create users with role PATIENT only.
- DOCTOR: Can create and edit PATIENT users; can view patient profiles.
- PATIENT: Can view own profile.

RBAC is enforced via Spring Security and service-level checks.

## Project Structure (key parts)
- `src/main/java/com/ishanjawade/backend/security`:
    - JWT filter, utilities/service, and `AuthController` (`/api/auth/*`)
- `src/main/java/com/ishanjawade/backend/user`:
    - `User`, `UserService`, `UserController`, `UserRepository`
- `src/main/java/com/ishanjawade/backend/config`:
    - `CorsConfig` (allows http://localhost:5173)

## Prerequisites
- Java 21+ (e.g., Temurin)
- Maven 3.9+ (or use Maven Wrapper)
- PostgreSQL running locally

## Configuration
Create/update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/med_db
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# JWT secret (use a strong base64-encoded or long random string)
app.jwt.secret=change_me_super_secret_value
app.jwt.expiration-ms=86400000

# Optional debug
# logging.level.org.springframework.security=DEBUG
```

CORS is configured in:
- `src/main/java/com/ishanjawade/backend/config/CorsConfig.java` (allowed origin: http://localhost:5173)

## Database
Create a database (macOS Homebrew example):
```bash
createdb med_db
```

## Build and Run
Using Maven:
```bash
./mvnw spring-boot:run
# or
./mvnw -DskipTests package && java -jar target/medical-records-backend-*.jar
```

Server runs at: http://localhost:8080

## Authentication Flow (JWT)
- Register: creates a user.
- Login: returns a JWT.
- Use JWT: include in `Authorization: Bearer <token>` header for protected endpoints.

### Endpoints
- POST `/api/auth/register`
    - Body:
      ```json
      {
        "email": "admin@example.com",
        "password": "admin123",
        "role": "ADMIN",
        "entityId": null
      }
      ```
- POST `/api/auth/login`
    - Body:
      ```json
      {
        "email": "admin@example.com",
        "password": "admin123"
      }
      ```
- Protected resources (example)
    - Users:
        - GET `/api/users` (ADMIN)
        - GET `/api/users/{id}` (ADMIN or owner for PATIENT; DOCTOR/RECEPTION can view patients)
        - POST `/api/users` (ADMIN; DOCTOR/RECEPTION only create PATIENT)
        - PATCH `/api/users/{id}` (ADMIN; DOCTOR can edit PATIENT)
        - DELETE `/api/users/{id}` (ADMIN)

Add header for protected calls:
```
Authorization: Bearer <your_jwt_token>
```

## Postman Quick Start
1. Create environment:
    - `baseUrl = http://localhost:8080`
    - `token = (empty)`
2. Requests:
    - Register: `POST {{baseUrl}}/api/auth/register`
    - Login: `POST {{baseUrl}}/api/auth/login`
        - Tests tab:
          ```javascript
          if (pm.response.code === 200) {
            pm.environment.set('token', pm.response.json().token);
          }
          ```
    - Authenticated example:
        - GET `{{baseUrl}}/api/users`
        - Header: `Authorization: Bearer {{token}}`

## Common Issues
- 403 Forbidden:
    - Missing/invalid `Authorization` header (Bearer token).
    - Role not permitted for the endpoint.
- BCrypt warning:
    - Ensure passwords are encoded on create/update using `BCryptPasswordEncoder`.
- JWT signature error:
    - Same secret must be used for signing and verifying (`app.jwt.secret`).
    - Generate a new token after changing the secret.
- PostgreSQL keyword conflicts:
    - User table is named `users` (not `user`).

## Notes
- The `User` entity stores: `email`, `password` (BCrypt), `role`, `entityId`.
- Consider evolving to strong domain links: `Patient.user`, `Doctor.user`, `Reception.user` (1–1) instead of `entityId` for referential integrity.
- Update `CorsConfig` if your frontend origin changes.

## License
NOT YET DECIDED 