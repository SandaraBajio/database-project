# 🤝 KAAGAPAY: A Disaster Response Management System

# Object-oriented Programming
<table>
  <tr>
    <td style="padding-right: 20px;">
      <img src="https://drive.google.com/uc?id=1doBylTYvOLnCkRZN3VWnWbgsiXuRjWLQ" alt="Sandara Bajio" width="150" style="border-radius: 50%;">
    </td>
    <td>
      <h2> 🧑‍💻 Sandara B. Bajio</h2>
      <h2> 📘 CS-2102</h2>
      <h2> ☕ Java Programming Language</h2>
    </td>
  </tr>
</table>

Hi everyone! I am Sandara Bajio, from CS-2102. This is my project in OOP. It is a disaster response management system. Come and see its functionalities and features. Thank you for visiting this repo! ❤️

## 📖 I. Project Overview
### 🌟 What is KAAGAPAY?
  KAAGAPAY: A Disaster Response Management System is a Java-based application designed to improve disaster response and recovery efforts. It features two primary user roles, Users and Admins, with functionalities such as donating, volunteering, managing resources, and tracking disaster relief missions. The system simplifies coordination and enhances efficiency during critical times, directly contributing to community resilience and recovery.
- Purpose: To streamline disaster response efforts using a robust management system.
- Objective: Provide real-time support and coordination during emergencies.
- Target Users: Emergency responders, NGOs, and local government units.


## List of Packages: 

- **`.vscode`**  
  Contains editor-specific configurations such as formatting rules or extensions.

- **`controllers`**  
  Manages interactions between the user interface and the backend logic.

- **`dao`**  
  Handles database operations specific to users, such as CRUD operations.

- **`db`**  
  Provides functionality for connecting to and managing the database.

- **`main`**  
  Serves as the entry point of the application.

- **`models`**  
  Represents the structure and attributes of a user entity in the application.

- **`services`**  
  Contains the core business logic related to user management.

- **`utils`**  
  Includes utility methods that can be used across the project.

---

## 🧑‍💻 II. Application of Object-Oriented Programming (OOP) Principles
### 🔹 How OOP Enhances KAAGAPAY
-II. Explanation of How OOP Principles Were Applied

Encapsulation: 
Encapsulation is central to the system’s architecture, ensuring that data is safeguarded and tightly integrated with its corresponding functionality. Each module is logically grouped into packages such as Db, Entities, Manager, UI, and Main. Sensitive attributes like username and donationAmount are declared private to prevent unauthorized access. Access to these attributes is strictly controlled using getter and setter methods, enabling validation and ensuring proper handling of data. Encapsulation also enhances modularity, making the system easier to maintain, debug, and extend.
Inheritance:The base class User contains common attributes such as username and password.
Derived classes like Volunteer extend User, adding specific attributes like assignedMission.

Polymorphism:
Method Overriding: Subclasses redefine inherited methods to implement specific behaviors. For instance, the toString method in the Volunteer and Donation classes provides tailored string representations for these objects.
Enums: Enumerations, such as mission statuses (ONGOING, COMPLETED), encapsulate predefined constants, ensuring type safety, readability, and extensibility. These enums enable the system to adapt dynamically to various use cases while maintaining clarity and consistency in code.

Inheritance
The project uses inheritance to streamline shared functionality and promote code reuse. The User class acts as a base class, encapsulating common attributes such as username and password. Subclasses like Volunteer extend User and introduce specialized attributes and behaviors, such as assignedMission and isApprovedForMission. This design not only eliminates redundancy but also ensures that shared logic can be updated in one place and inherited across related entities, improving the overall maintainability and scalability of the system.

Abstract/Interface
I utilized the ConsoleDesign interface to define a structured and consistent framework for managing console aesthetics. This interface specifies methods for tasks such as clearing the screen, changing text colors, blinking text, and displaying logos. By employing the ConsoleDesignImpl class to implement the interface, I ensure that all console-related operations are centralized and reusable. The interface abstraction allows me to maintain platform independence, as implementation details like screen clearing commands or color codes are encapsulated within the implementing class. This design fosters scalability, as additional methods or effects can be introduced without altering the interface users. By leveraging interfaces, I effectively decouple the console's design logic from the application's core functionality, promoting modularity, easier maintenance, and enhanced flexibility in adapting to new design requirements.

---

## 🌍 III. Integration with Sustainable Development Goals (SDGs)

### 🎯 Focused SDG: **Goal 11 - Sustainable Cities and Communities**

**Goal 11** aims to make cities inclusive, safe, resilient, and sustainable. KAAGAPAY contributes directly to this objective by leveraging technology to address urban challenges and disaster preparedness.

### ✨ **Target 11.5**  
**Significantly reduce the number of deaths and the number of people affected by disasters, with a focus on protecting vulnerable populations.**  

### **How KAAGAPAY Aligns with Target 11.5**

#### 🗺️ **Mapping & Analytics**
- **Objective**: Empower decision-makers with actionable insights for effective urban planning and disaster management.
- **Features**:
  - Advanced **geospatial mapping** to identify high-risk zones.
  - Real-time **disaster analytics** to assess risks and monitor ongoing situations.
  - Tools to prioritize vulnerable areas during disaster response planning.

#### 📦 **Resource Distribution**
- **Objective**: Ensure equitable and efficient delivery of resources during crises, minimizing delays and wastage.
- **Features**:
  - Smart **inventory management** for tracking supplies like food, water, and medical aid.
  - Optimized **logistics planning** to reduce transit times and enhance coverage.
  - Data-driven allocation to ensure resources reach the most affected and vulnerable populations first.

#### 🤝 **Community Resilience Building**
- **Objective**: Foster long-term resilience through inclusive participation and education.
- **Features**:
  - Engagement with local communities to co-develop disaster response strategies.
  - Workshops and campaigns to raise awareness about disaster preparedness.
  - Tools for simulation and training exercises to strengthen local capacity.

### **KAAGAPAY’s Broader Impact**
By integrating these features, KAAGAPAY not only aligns with **Target 11.5** but also contributes to the overarching mission of creating sustainable cities and communities. Its focus on data-driven solutions ensures a proactive approach to urban challenges, enhancing resilience for future generations.

---

## 💾 MySQL Database Integration

This project includes integration with a MySQL database using **JDBC**. The database schema and initialization logic are defined in `init.sql`. Below is an overview of the database setup and how it integrates with the application.

### 📂 **Database Schema Overview**

The database, `kaagapaydb`, includes the following tables:

1. **Admin**: Stores administrator credentials.
2. **User**: Contains user details such as name, email, role, and contact information.
3. **Donation**: Records donations, categorized as `monetary` or `inkind`.
4. **MonetaryDonation**: Tracks monetary contributions linked to `Donation`.
5. **InKindDonation**: Tracks in-kind contributions with types and quantities.
6. **Mission**: Captures disaster missions, their details, and requirements.
7. **MissionResources**: Maps donations to missions.
8. **MissionVolunteers**: Assigns volunteers to missions.

### 📜 **Schema Definition**

```sql
-- Database Creation
CREATE DATABASE kaagapaydb;
USE kaagapaydb;

-- Admin Table
CREATE TABLE Admin (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

-- User Table
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    middlename VARCHAR(50),
    age INT,
    phone_number VARCHAR(15),
    role ENUM('donate', 'volunteer') NOT NULL
);

-- Donation Tables
CREATE TABLE Donation (
    donationid INT AUTO_INCREMENT PRIMARY KEY,
    userid INT NOT NULL,
    donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    donation_type ENUM('monetary', 'inkind') NOT NULL,
    FOREIGN KEY (userid) REFERENCES User(id)
);

CREATE TABLE MonetaryDonation (
    donationid INT PRIMARY KEY,
    amount DECIMAL(10,2) NOT NULL,
    donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('available', 'distributed') DEFAULT 'distributed',
    FOREIGN KEY (donationid) REFERENCES Donation(donationid)
);

CREATE TABLE InKindDonation (
    donationid INT PRIMARY KEY,
    kindtype ENUM('food', 'clothing', 'medical supplies', 'hygiene products') NOT NULL,
    units INT NOT NULL,
    donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('available', 'distributed') DEFAULT 'distributed',
    FOREIGN KEY (donationid) REFERENCES Donation(donationid)
);

-- Mission and Related Tables
CREATE TABLE Mission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type ENUM('EARTHQUAKE', 'TYPHOON', 'FIRE', 'FLOOD', 'VOLCANIC_ERUPTION', 'LANDSLIDE', 'TSUNAMI') NOT NULL,
    location VARCHAR(100) NOT NULL,
    status ENUM('ongoing', 'completed') NOT NULL,
    occurrence_date DATE NOT NULL,
    operationtype ENUM('rescue operation', 'relief operation') NOT NULL,
    volunteers_needed INT NOT NULL
);

CREATE TABLE MissionResources (
    resourceid INT AUTO_INCREMENT PRIMARY KEY,
    missionid INT NOT NULL,
    donationid INT NOT NULL,
    resource_type ENUM('monetary', 'inkind') NOT NULL,
    FOREIGN KEY (missionid) REFERENCES Mission(id),
    FOREIGN KEY (donationid) REFERENCES Donation(donationid)
);

CREATE TABLE MissionVolunteers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    missionid INT NOT NULL,
    userid INT NOT NULL,
    assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (missionid) REFERENCES Mission(id),
    FOREIGN KEY (userid) REFERENCES User(id)
);
```

### 🔗 **JDBC Integration**

- The application uses **JDBC (Java Database Connectivity)** to establish connections and perform operations on `kaagapaydb`.
- Connection details (e.g., database URL, username, and password) are configured in the application’s settings file, typically `DbConnection.java`.

### 🛠️ **Triggers** 
  The database includes automated operations using triggers.  
  - Example: The `after_mission_insert` trigger automatically assigns volunteers to new missions based on the required number of volunteers.

```sql
DELIMITER //

CREATE TRIGGER after_mission_insert
AFTER INSERT ON Mission
FOR EACH ROW
BEGIN
    DECLARE counter INT DEFAULT 0;
    DECLARE volunteers_needed INT;

    SET volunteers_needed = NEW.volunteers_needed;

    WHILE counter < volunteers_needed DO
        INSERT INTO MissionVolunteers (missionid, userid)
        VALUES (NEW.id, NULL); 
        SET counter = counter + 1;
    END WHILE;
END;
//

DELIMITER ;
```

---
## 🚀 Instructions to Run the Program

Follow these steps to set up and run the application:

### 1️⃣ **Prerequisites**
Ensure the following are installed on your system:
- **Java Development Kit (JDK)** (version 8 or above)
- **MySQL Server** 
- A suitable IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor with terminal access.

### 2️⃣ **Database Setup**
1. **Start MySQL Server**:
   - Ensure your MySQL server is running. You can start it via terminal or MySQL Workbench.

2. **Create the Database**:
   - Open your MySQL client or terminal and run the SQL commands in `init.sql` to initialize the database schema and insert dummy data.
   ```bash
   mysql -u [username] -p < path/to/init.sql

### 3️⃣ **Building the Project**
1. **Set up your IDE**:
   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

2. **Configure the Project**:
   - Ensure the MySQL JDBC driver is added to the project’s classpath. You can download the MySQL JDBC driver (Connector/J) from the [official site](https://dev.mysql.com/downloads/connector/j/) or add it to the build path if your IDE supports it.
   - In your IDE, add the MySQL JDBC JAR file to your project:
     - **In IntelliJ IDEA**: Go to **Project Structure > Libraries > Add JARs**.
     - **In Eclipse**: Right-click the project > **Build Path > Add External Archives**.

### 4️⃣ **Run the Program**
1. **Locate the Main Application**:
   - In your IDE, find the entry point of the program, typically `Main.java`.

2. **Run the Program**:
   - **Using IDE**: Right-click `Main.java` and select **Run** to start the program.
   - **Using Command Line**:
     - If you need to compile the program manually, use the following command:
       ```bash
       javac -cp .:path/to/mysql-connector-java-X.X.X.jar Main.java
       ```
     - To run the program:
       ```bash
       java -cp .:path/to/mysql-connector-java-X.X.X.jar Main
       ```

### 5️⃣ **Access the Application**
- **Command-line Interface**: After running the program, follow the on-screen prompts to interact with the system, such as creating users, making donations, assigning volunteers, and managing missions.
  - For example, you'll be prompted to log in as an Admin or User and perform tasks such as:
    - Registering a new user
    - Making a monetary or in-kind donation
    - Assigning volunteers to missions

### 6️⃣ **Verify Database Operations**
- **Check MySQL Database**:
   - Open MySQL Workbench or any MySQL client.
   - Verify that the data is being inserted into the `kaagapaydb` database, including the `Admin`, `User`, `Donation`, `Mission`, and other tables.
   - Run the following query to check the inserted data:
     ```sql
     SELECT * FROM User;
     SELECT * FROM Donation;
     ```

### 🔧 **Troubleshooting**

1. **Database Connection Issues**:
   - Ensure that your MySQL service is running and accessible.
   - Double-check the `DbConnection.java` file for correct database credentials (username, password, and database URL).
   - Test the connection directly via MySQL command line or MySQL Workbench using the credentials provided in `DbConnection.java` to ensure the database is accessible.

2. **JDBC Driver Issues**:
   - Ensure the MySQL JDBC driver (`mysql-connector-java-X.X.X.jar`) is correctly added to the classpath.
   - If you're running the program via the command line, ensure you’re specifying the correct path to the JDBC driver JAR file.

3. **Build Errors**:
   - If compilation fails, check for any missing files or misconfigured paths in your IDE or terminal.
   - Ensure that you have the correct Java version installed and configured for your IDE or terminal.

4. **MySQL Errors**:
   - If you encounter errors like "Unknown database", ensure you’ve created the database using the SQL script provided in `init.sql`.
   - If there are any SQL errors, check the syntax in the `init.sql` file for accuracy.

### ✅ **Ready to Use!**
Once you’ve completed these steps, the program should be up and running. You can now start testing the features like user registration, donation management, and mission assignments!

If you encounter any issues, please check the troubleshooting section or refer to the application logs for further details.

