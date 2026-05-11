<div align="center">

# Restaurant Reservation System

A command-line application for managing restaurant table reservations, reviews, and user accounts.

[![Java](https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://java.com/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![H2 Database](https://img.shields.io/badge/H2-Database-003545?style=for-the-badge&logo=h2&logoColor=white)](https://h2database.com/)

**A fast and straightforward way to book a table at your favorite restaurant.**

</div>

***

## 📋 Table of Contents

- [About](#-about)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [License](#-license)

***

## 🚀 About

This CLI (Command Line Interface) application allows users to search for restaurants, book tables, and write reviews. The application is built in pure Java with a strong emphasis on Object-Oriented Design (OOD) and design patterns (Facade, Observer, Command, DAO, Builder, Factory). Data is stored in a local H2 relational database.

***

## ✨ Features

- ✅ **User Management:** Secure registration and user authentication.
- ✅ **Restaurant Search:** Quickly find specific restaurants in the system.
- ✅ **Table Reservation:** Create a reservation by manually selecting a table, or use the quick automatic table assignment.
- ✅ **Reservation History:** View all your active and past reservations.
- ✅ **Reviews & Ratings:** Submit reviews for visited restaurants and browse your past reviews.
- ✅ **Automatic Rating Updates:** Restaurant overall ratings are automatically recalculated using the Observer pattern whenever a new review is submitted.

***

## 🛠 Tech Stack

| Category | Technology |
|---|---|
| Language | Java 23 |
| Framework | No framework (Pure Java) |
| Database | H2 Database (local file `h2database.mv.db`) |
| Build Tool | Maven |
| User Interface| Console (CLI) |

***

## ⚡ Getting Started

### Prerequisites

- Java Development Kit (JDK) 23 or newer
- Apache Maven

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/[OWNER]/Restaurant-Reservation-System.git
cd Restaurant-Reservation-System

# 2. Compile the project and download dependencies
mvn clean compile

# 3. Run the application
mvn exec:java -Dexec.mainClass="org.mns.Main"
```

*Note: On the first run, the H2 database (`h2database.mv.db`) will be automatically initialized and populated with sample data.*

***

## 📖 Usage

Upon launching the application, you will be greeted by the main menu. You can use the preconfigured test accounts displayed in the console to try it out immediately:

```text
Testovací účty: jan@test.cz / 1234 | eva@test.cz / 1234

╔════════════════════════════════════════╗
║   Rezervační systém restaurací  v1.0   ║
╚════════════════════════════════════════╝

Hlavní menu
--------------------------------------------------
  1) Přihlásit se
  2) Registrovat se
  0) Ukončit aplikaci

Volba: 1
```

Type your chosen option number and press `Enter`. After logging in, you will access the customer menu where you can start making reservations and leaving reviews.

***

## 📁 Project Structure

```text
Restaurant-Reservation-System/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/org/mns/
│   │   │   ├── Main.java               # Application entry point
│   │   │   ├── builder/                # Builder design pattern
│   │   │   ├── dao/                    # Data Access Objects (DB integration)
│   │   │   ├── db/                     # Database initialization & mock data
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── facade/                 # Facade design pattern
│   │   │   ├── factory/                # Factory design pattern
│   │   │   ├── model/                  # Data models (Entities)
│   │   │   ├── observer/               # Observer design pattern (ratings update)
│   │   │   ├── service/                # Business logic
│   │   │   └── ui/                     # CLI user interface & Command pattern
│   │   └── resources/
│   │       └── schema.sql              # SQL DDL script for table creation
```

***

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

***

## 📄 License

Distributed under the MIT License. See [`LICENSE`](LICENSE) for more information.

***

<div align="center">

Made with ❤️ · 2026

</div>