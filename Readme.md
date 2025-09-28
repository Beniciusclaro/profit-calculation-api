# Profit Calculator API

API for calculating and persisting shipment profits per customer.  
This application exposes REST endpoints to calculate shipments, list customer profits, and store the results.

---

## 🧱 Project Structure

| Layer / Package   | Responsibility |
|-------------------|----------------|
| `controller`      | REST endpoints, exposes HTTP operations |
| `service`         | Business logic (calculation, transformation, orchestration) |
| `repository`      | Data access via JPA / repositories |
| `mapper`          | Converts between entities and request/response objects |
| `model` / `entity`| Domain entities and request/response objects |

---

## 🔧 Requirements

- Java 17+
- Maven 3.8+
- Database (e.g., MySQL, PostgreSQL) or test profile with H2
- Database connection settings in `application.properties` or `application.yml`, including URL, username, password, and `hibernate.dialect`

---

## 🚀 How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/Beniciusclaro/profit-calculation-api.git
   cd profit-calculation-api


2. Configure the database properties:

- in src/main/resources/application.yml or .properties


- or, if you want to run via Docker, simply use the command:: `docker-compose up -d --build` This will start a database inside a container.


- Then create a simple script to add a user to the database, for example:
```sql
    INSERT INTO users (id, name) VALUES (1, 'Edwardo');
```

3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. The application will start on `http://localhost:8080`, and you can access the Swagger UI at `http://localhost:8080/swagger-ui.html` for API documentation and testing.