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

List of Packages: 
- Package: db
  -This package is likely responsible for handling data storage and retrieval operations, acting as a simulation of a database for the project.
  -Classes:
  -DonationDatabase.java: Manages the storage, retrieval, and updating of donation records.
  -MissionDatabase.java: Handles mission-related data, such as mission details or progress.
  -UserDatabase.java: Stores and retrieves user-related data, such as user profiles or login details.

2. Package: entities
  -This package contains the core data models or entities of the project, representing the main objects the system works with.
  -Classes:
  -Donation.java: Represents a donation entity, likely containing fields like amount, donor details, and donation date.
  -Mission.java: Represents a mission or campaign, possibly with attributes like mission name, description, and status.
  -User.java: Represents a user entity, including details like username, password, and role.
  -Volunteer.java: Extends or specializes the User class, likely to include volunteer-specific attributes such as assigned missions or hours worked.

3. Package: managers
  -This package provides the logic for managing and interacting with the entities stored in the databases.
   -Classes:
   -DonationManager.java: Contains business logic for handling donation operations, such as adding, updating, or deleting donations.
   -MissionManager.java: Manages the lifecycle of missions, including creation, updates, and assigning volunteers.
   -UserManager.java: Handles user-related operations like registration, authentication, and role management.

4. Package: ui
   -This package handles the user interface, likely focused on console-based interaction.
   -Classes:
   -ConsoleDesign.java: Provides basic methods or templates for designing console outputs, such as headers or formatting.
   -ConsoleDesignImpl.java: Implements the methods defined in ConsoleDesign.java, providing specific console layouts and designs.
   -Menu.java: Displays and manages the main menu or navigation flow of the program.
   -KaagapayMain.java: Serves as the main entry point of the application, where execution begins. It integrates all the other components.

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
### 🎯 Focused SDG: Goal 11 - Sustainable Cities and Communities
- **Target 11.5**: Reducing the impact of disasters, particularly for vulnerable populations.
- **How KAAGAPAY aligns**:
  - 🗺️ **Mapping & Analytics**: Enhancing resilience through better planning and response.
  - 📦 **Resource Distribution**: Efficient management of supplies and relief efforts.

---

## 🛠️ IV. Instructions for Running the Program

To run your program in Visual Studio Code (VS Code) using the repository KAAGAPAY, follow these updated steps:

Step 1: Install Prerequisites
Before running the program, ensure you have the necessary tools installed on your system:

Install Java Development Kit (JDK): Download and install the JDK from the official Oracle website or use OpenJDK. Ensure Java is installed by running the following command in your terminal: java -version

Install Visual Studio Code (VS Code):
Download and install VS Code from the official website.

After installation, open VS Code.
Install VS Code Extensions:

Open VS Code, go to Extensions (Ctrl+Shift+X), and install the following extensions:
Java Extension Pack (includes Language Support for Java(TM) by Red Hat, Debugger for Java, Java Test Runner, and Maven for Java).

Step 2: Clone the Repository
To clone the repository KAAGAPAY to your local machine:
Open VS Code and open the integrated terminal (Ctrl+ orView > Terminal`).
Clone the repository using Git: git clone https://github.com/SandaraBajio/KAAGAPAY.git
Replace your-username with the actual GitHub username where the repository is hosted.
Once cloned, navigate to the project directory:cd KAAGAPAY

Step 3: Open the Project in VS Code
Open the KAAGAPAY directory in VS Code: code .
This will open the project folder in VS Code.

Step 4: Set Up the Java Project
Once inside the VS Code workspace, the Java extension should automatically detect the project.
If necessary, click on the "Configure Java Runtime" notification that appears and select the appropriate JDK version.

Step 5: Run the Program
To run the program:

