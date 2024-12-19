--Database Creation
CREATE DATABASE kaagapaydb;
USE kaagapaydb;

--Table Creation
CREATE TABLE Admin (
    email VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    middlename VARCHAR(50),
    age INT,
    role ENUM('donate', 'volunteer') NOT NULL
);

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
    FOREIGN KEY (donationid) REFERENCES Donation(donationid)
);

CREATE TABLE InKindDonation (
    donationid INT PRIMARY KEY,
    kindtype ENUM('food', 'clothing', 'medical supplies', 'hygiene products') NOT NULL,
    units INT NOT NULL,
    FOREIGN KEY (donationid) REFERENCES Donation(donationid)
);

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
    FOREIGN KEY (donationid) REFERENCES MonetaryDonation(donationid)
);

CREATE TABLE MissionVolunteers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    missionid INT NOT NULL,
    userid INT NOT NULL,
    assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (missionid) REFERENCES Mission(id),
    FOREIGN KEY (userid) REFERENCES User(id)
);

--Table Alterations
ALTER TABLE User
ADD COLUMN phone_number VARCHAR(15);

ALTER TABLE MonetaryDonation
ADD COLUMN donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN status ENUM('available', 'distributed') DEFAULT 'distributed';

ALTER TABLE InKindDonation
ADD COLUMN donation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN status ENUM('available', 'distributed') DEFAULT 'distributed';

ALTER TABLE Volunteer
ADD COLUMN assignment_status ENUM('unassigned', 'assigned', 'completed') DEFAULT 'unassigned';

--Trigger Creation for Mission Insertion
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

--Dummy Values Insertion
INSERT INTO Admin (email, password)
VALUES
    ('admin1@example.com', 'adminpass'),
    ('admin2@example.com', 'adminpass2');

INSERT INTO User (email, password, firstname, lastname, middlename, age, role, phone_number)
VALUES 
    ('user1@example.com', 'password1', 'John', 'Doe', 'M', 25, 'donate', '09123456789'),
    ('user2@example.com', 'password2', 'Jane', 'Smith', 'A', 30, 'volunteer', '09123456790'),
    ('user3@example.com', 'password3', 'Alice', 'Johnson', 'B', 28, 'donate', '09123456791'),
    ('user4@example.com', 'password4', 'Bob', 'White', 'C', 35, 'volunteer', '09123456792');

INSERT INTO Donation (userid, donation_date, donation_type)
VALUES 
    (1, '2024-12-01 08:00:00', 'monetary'),
    (2, '2024-12-02 10:00:00', 'inkind'),
    (3, '2024-12-03 12:00:00', 'monetary'),
    (4, '2024-12-04 14:00:00', 'inkind');

INSERT INTO MonetaryDonation (donationid, amount)
VALUES
    (1, 1000),
    (3, 500);

INSERT INTO InKindDonation (donationid, kindtype, units)
VALUES 
    (2, 'food', 10),
    (4, 'clothing', 20);

INSERT INTO Volunteer (userid, volunteered, operationtype, availability, assignment_status)
VALUES 
    (2, '2024-12-01', 'relief', 'yes', 'unassigned'),
    (4, '2024-12-02', 'rescue', 'no', 'unassigned');

INSERT INTO Mission (name, type, location, status, occurrence_date, operationtype, volunteers_needed)
VALUES ('Test Mission', 'EARTHQUAKE', 'Manila', 'ongoing', '2024-12-01', 'relief operation', 3);

INSERT INTO MissionResources (missionid, donationid, resource_type)
VALUES 
    (19, 1, 'monetary');

INSERT INTO MissionVolunteers (missionid, userid)
VALUES
    (19, 2),
    (19, 4);  

