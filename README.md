# 🎬 CineHub - Film Management System

A comprehensive film catalog management system built with Spring Framework, featuring complete CRUD operations for films, directors, and categories.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring](https://img.shields.io/badge/Spring-6.x-green)
![JPA](https://img.shields.io/badge/JPA-Hibernate-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)

---

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Business Rules](#business-rules)
- [User Stories](#user-stories)
- [Testing](#testing)
- [Docker Setup](#docker-setup)

---

## ✨ Features

### 🎥 Film Management
- Add films to the catalog with complete information
- Update existing film details
- Delete films from the catalog
- View complete film list
- Search films by title, year, or category
- View detailed film information (including director and category)

### 🎬 Director Management
- Add new directors
- Update director information
- Delete directors (only if they have no associated films)
- View list of all directors
- View complete filmography of a director
- Search directors by name

### 📂 Category Management
- Add new film categories
- Update existing categories
- Delete categories (only if they contain no films)
- View list of all categories
- View all films in a specific category

---

## 🛠️ Technologies

### Backend
- **Java 17**
- **Spring Core** - Dependency Injection (IoC)
- **Spring Data JPA** - Data persistence
- **Spring MVC** - REST Controllers
- **Hibernate** - ORM
- **MySQL** - Relational database
- **Maven** - Build tool
- **Docker** - Containerization

### Additional Libraries
- **Java Time API** - Date management
- **JUnit 5** - Unit testing
- **Mockito** - Mocking framework
- **MapStruct** - DTO mapping (optional)

---

## 🏗️ Architecture

The project follows a **multi-layered MVC architecture**:

```
┌─────────────────────────────────────────┐
│          Controller Layer               │
│   (REST APIs & Request Handling)        │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Service Layer                  │
│   (Business Logic & Validation)         │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Repository Layer               │
│   (Data Access & JPA Queries)           │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Database (MySQL)               │
└─────────────────────────────────────────┘
```

### Design Patterns Used
- **Dependency Injection** - Spring IoC Container
- **Repository Pattern** - Data access abstraction
- **DTO Pattern** - Data transfer objects
- **Mapper Pattern** - Entity-DTO conversion

---

## 📦 Prerequisites

- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+ or PostgreSQL
- Docker & Docker Compose (optional)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/cinehub-spring.git
cd cinehub-spring
```

### 2. Configure Database

Update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/cinehub_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3. Build the Project

```bash
./mvnw clean install
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8082`

---

## 📁 Project Structure

```
mustapha-moutaki-cinehub-spring/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── src/
│   └── main/
│       ├── java/org/mustapha/
│       │   ├── Main.java
│       │   ├── config/
│       │   │   ├── AppConfig.java           # Spring configuration
│       │   │   ├── AppInitializer.java      # Web application initializer
│       │   │   └── WebMvcConfig.java        # MVC configuration
│       │   ├── controller/
│       │   │   ├── CategoryController.java
│       │   │   ├── DirectorController.java
│       │   │   └── MovieController.java
│       │   ├── dto/
│       │   │   ├── CategoryDTO.java
│       │   │   ├── DirectorDTO.java
│       │   │   └── MovieDTO.java
│       │   ├── mapper/
│       │   │   ├── CategoryMapper.java
│       │   │   ├── DirectorMapper.java
│       │   │   └── MovieMapper.java
│       │   ├── model/
│       │   │   ├── Category.java
│       │   │   ├── Director.java
│       │   │   └── Movie.java
│       │   ├── repository/
│       │   │   ├── CategoryRepository.java
│       │   │   ├── DirectorRepository.java
│       │   │   └── MovieRepository.java
│       │   └── service/
│       │       ├── CategoryService.java
│       │       ├── DirectorService.java
│       │       ├── MovieService.java
│       │       └── Impl/
│       │           ├── CategoryServiceImpl.java
│       │           ├── DirectorServiceImpl.java
│       │           └── MovieServiceImpl.java
│       └── resources/
│           └── application.properties
└── .mvn/wrapper/
```

---

## 🗄️ Database Schema

### Entity: Movie (Film)

| Field | Type | Description |
|-------|------|-------------|
| idFilm | Long | Primary key |
| title | String | Film title |
| releaseYear | Integer | Release year |
| duration | Integer | Duration in minutes |
| synopsis | String | Film synopsis |
| rating | Double | Rating (0-10) |
| director | ManyToOne | Associated director |
| category | ManyToOne | Associated category |

### Entity: Director (Réalisateur)

| Field | Type | Description |
|-------|------|-------------|
| idDirector | Long | Primary key |
| firstName | String | First name |
| lastName | String | Last name |
| nationality | String | Nationality |
| birthDate | LocalDate | Birth date |
| biography | String | Biography |
| films | OneToMany | List of films |

### Entity: Category (Catégorie)

| Field | Type | Description |
|-------|------|-------------|
| idCategory | Long | Primary key |
| name | String | Category name |
| description | String | Category description |
| films | OneToMany | List of films |

---

## 🔌 API Endpoints

### Film Endpoints

```http
GET    /api/movies              # Get all movies
GET    /api/movies/{id}         # Get movie by ID
POST   /api/movies              # Create new movie
PUT    /api/movies/{id}         # Update movie
DELETE /api/movies/{id}         # Delete movie
GET    /api/movies/search?title={title}  # Search by title
GET    /api/movies/year/{year}  # Filter by year
GET    /api/movies/rating/{min} # Filter by minimum rating
```

### Director Endpoints

```http
GET    /api/directors           # Get all directors
GET    /api/directors/{id}      # Get director by ID
POST   /api/directors           # Create new director
PUT    /api/directors/{id}      # Update director
DELETE /api/directors/{id}      # Delete director
GET    /api/directors/{id}/films # Get director's filmography
GET    /api/directors/search?name={name} # Search by name
```

### Category Endpoints

```http
GET    /api/categories          # Get all categories
GET    /api/categories/{id}     # Get category by ID
POST   /api/categories          # Create new category
PUT    /api/categories/{id}     # Update category
DELETE /api/categories/{id}     # Delete category
GET    /api/categories/{id}/films # Get films by category
```

---

## 📏 Business Rules

1. **Film-Director Relationship**
    - One film belongs to one director
    - One director can have multiple films

2. **Film-Category Relationship**
    - One film belongs to one category
    - One category can contain multiple films

3. **Deletion Constraints**
    - Directors can only be deleted if they have no associated films
    - Categories can only be deleted if they contain no films

4. **Validation Rules**
    - Film rating must be between 0 and 10
    - Release year cannot be in the future
    - Duration must be greater than 0

---

## 📖 User Stories

### Films (US1-US8)
- **US1**: Add a film to the catalog with all information
- **US2**: Update existing film information
- **US3**: Delete a film from the catalog
- **US4**: View complete list of films
- **US5**: Search for a film by title
- **US6**: View detailed film information (with director and category)
- **US7**: Filter films by release year
- **US8**: Filter films by minimum rating

### Directors (US9-US14)
- **US9**: Add a new director
- **US10**: Update director information
- **US11**: Delete a director (if no films associated)
- **US12**: View list of all directors
- **US13**: View complete filmography of a director
- **US14**: Search for a director by name

### Categories (US15-US19)
- **US15**: Add a new film category
- **US16**: Update an existing category
- **US17**: Delete a category (if it contains no films)
- **US18**: View list of all categories
- **US19**: View all films in a specific category

---

## 🧪 Testing

### Run Unit Tests

```bash
./mvnw test
```

### Test Coverage

Tests are implemented for:
- Service layer (FilmServiceTest, DirectorServiceTest, CategoryServiceTest)
- Entity creation and validation
- CRUD operations (success and failure cases)
- Business rule enforcement
- Custom search methods

### Example Test Structure

```java
@Test
void testCreateMovie_Success() {
    // Given
    MovieDTO movieDTO = new MovieDTO();
    // When
    MovieDTO result = movieService.create(movieDTO);
    // Then
    assertNotNull(result.getId());
}
```

---

## 🐳 Docker Setup

### Using Docker Compose

1. **Start all services** (MySQL + Application):

```bash
docker-compose up -d
```

2. **View logs**:

```bash
docker-compose logs -f
```

3. **Stop services**:

```bash
docker-compose down
```

### Docker Compose Configuration

The `docker-compose.yml` includes:
- MySQL 8.0 database
- Spring application
- Persistent volume for database
- Network configuration

### Build Docker Image Manually

```bash
docker build -t cinehub-spring .
docker run -p 8080:8080 cinehub-spring
```

---

## 🔧 Configuration

### Spring Configuration Types

The project supports multiple configuration approaches:

1. **XML Configuration** (applicationContext.xml)
2. **Annotation-based** (@Component, @Service, @Repository)
3. **Java Configuration** (@Configuration, @Bean)

### Bean Scopes
- **Singleton** (default) - One instance per container
- **Prototype** - New instance per request

### Component Scanning
Enabled in configuration to automatically detect beans.

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---


## 👨‍💻 Author

**Mustapha Moutaki**

- GitHub: [@mustapha-moutaki](https://github.com/mustapha-moutaki)
- LinkedIn: [Mustapha Moutaki](https://www.linkedin.com/in/mustapha-moutaki-6528a2242/)
- Email: mustaphaamoutaki@gmail.com

---

## 🙏 Acknowledgments

- Spring Framework Documentation
- Hibernate ORM Documentation
- JPA Specifications
- RESTful API Best Practices

---

