# AstralXMedia

AstralXMedia is a social media platform for space enthusiasts.

## Features

- User registration and authentication
- Create, read, update, and delete posts
- Comment on posts

## Technologies Used

- Java 21
- Spring Boot 3.2.0
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- Lombok
- JWT

## Getting Started

### Prerequisites

- JDK 21
- Maven

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/your_username_/astralxmedia.git
   ```
2. Build the project
   ```sh
   mvn clean install
   ```
3. Run the application
    ```sh
    java -jar target/astralxmedia-0.0.1-SNAPSHOT.jar
    ```

## Configuration

The application uses an in-memory H2 database. The H2 console is enabled and can be accessed at `http://localhost:8080/h2-console`.

- **JDBC URL:** `jdbc:h2:mem:astralxmedia`
- **Username:** `sa`
- **Password:** (leave blank)

### JWT Configuration

The JWT secret and expiration time can be configured in `src/main/resources/application.properties`:

- `jwt.secret`: A strong, long, and unique secret key for signing JWTs.
- `jwt.expiration`: The JWT expiration time in milliseconds.

## API Endpoints

### Authentication

- `POST /api/v1/auth/login`: Authenticate a user and receive a JWT.

### Users

- `POST /api/v1/users/register`: Register a new user.

### Posts

- `GET /api/v1/posts`: Get all posts.
- `GET /api/v1/posts/{id}`: Get a post by ID.
- `POST /api/v1/posts`: Create a new post.
- `PUT /api/v1/posts/{id}`: Update a post.
- `DELETE /api/v1/posts/{id}`: Delete a post.

### Comments

- `GET /api/v1/posts/{postId}/comments`: Get all comments for a post.
- `POST /api/v1/posts/{postId}/comments`: Create a new comment on a post.
