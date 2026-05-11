# Jak přispět do Restaurant-Reservation-System / Contributing Guide

*[English version below](#english-version)*

Děkujeme za tvůj zájem přispět do projektu! Tento dokument obsahuje základní pravidla a postupy pro vývojáře.

## 🇨🇿 Česká verze

### Požadavky

- **Java 23** (nebo novější)
- **Maven 3.x**
- IDE doporučené: IntelliJ IDEA, Eclipse nebo VS Code

### Spuštění projektu

```bash
git clone https://github.com/[OWNER]/Restaurant-Reservation-System.git
cd Restaurant-Reservation-System
mvn clean compile
mvn exec:java -Dexec.mainClass="org.mns.Main"
```

*H2 databáze (`h2database.mv.db`) se vytvoří a naplní testovacími daty automaticky při prvním spuštění.*

### Architektura a struktura projektu

Projekt využívá objektově orientovaný návrh a běžné návrhové vzory:
- `model/` - Datové entity (v angličtině: `Customer`, `Restaurant`, `Reservation`, atd.)
- `dao/` - Data Access Objects (přístup do DB, např. `CustomerDao`)
- `service/` - Business logika
- `facade/` - Zjednodušené rozhraní pro komplexní operace
- `ui/` - Konzolové uživatelské rozhraní a Command pattern
- `db/` - Inicializace databáze a DDL skripty (`src/main/resources/schema.sql`)

### Konvence kódu

- **Jazyk kódu:** Zdrojový kód (názvy tříd, metod, proměnných) je psán v **angličtině**.
- **Uživatelské rozhraní (CLI):** Texty vypisované do konzole uživateli jsou v **češtině**.
- **Databáze:** SQL dotazy piš pomocí `PreparedStatement`, nikdy nespojuj stringy s uživatelským vstupem (prevence SQL injection).
- Udržuj oddělení vrstev: UI nekomunikuje přímo s DAO, ale využívá Service nebo Facade.

### Hlášení chyb (Bugs)

Pokud najdeš chybu, otevři Issue a uveď:
1. Kroky k reprodukci.
2. Očekávané vs. skutečné chování.
3. Výpis chyby z konzole (stack trace).
4. Verzi Javy (`java -version`).

### Pull Requesty

1. Vytvoř novou větev z `main`:
   ```bash
   git checkout -b feature/nazev-funkce
   ```
2. Pokud měníš databázové schéma, aktualizuj `src/main/resources/schema.sql`.
3. Důkladně otestuj své změny ručně v CLI rozhraní.
4. Používej konvenci pro commit zprávy (např. Conventional Commits):
   ```text
   feat: přidáno řazení restaurací podle hodnocení
   fix: opraven pád při zadání neplatného data
   ```
5. Otevři Pull Request proti větvi `main`.

---

## 🇬🇧 English Version

Thank you for your interest in contributing to the project! This document outlines the basic rules and guidelines for developers.

### Prerequisites

- **Java 23** (or newer)
- **Maven 3.x**
- Recommended IDE: IntelliJ IDEA, Eclipse, or VS Code

### Running the Project

```bash
git clone https://github.com/[OWNER]/Restaurant-Reservation-System.git
cd Restaurant-Reservation-System
mvn clean compile
mvn exec:java -Dexec.mainClass="org.mns.Main"
```

*The H2 database (`h2database.mv.db`) is created and populated with sample data automatically on the first run.*

### Architecture and Project Structure

The project utilizes Object-Oriented Design and common design patterns:
- `model/` - Data entities (`Customer`, `Restaurant`, `Reservation`, etc.)
- `dao/` - Data Access Objects (DB integration, e.g., `CustomerDao`)
- `service/` - Business logic
- `facade/` - Simplified interface for complex operations
- `ui/` - Console User Interface and Command pattern
- `db/` - Database initialization and DDL scripts (`src/main/resources/schema.sql`)

### Coding Conventions

- **Code Language:** Source code (class names, methods, variables) must be written in **English**.
- **User Interface (CLI):** The text displayed to the user in the console is in **Czech**.
- **Database:** Write SQL queries using `PreparedStatement`, never concatenate strings with user input (SQL injection prevention).
- Maintain layer separation: UI should not communicate directly with DAO; it should use Service or Facade layers.

### Bug Reports

If you find a bug, please open an Issue and include:
1. Steps to reproduce.
2. Expected vs. actual behavior.
3. Console output/stack trace of the error.
4. Java version (`java -version`).

### Pull Requests

1. Create a new branch from `main`:
   ```bash
   git checkout -b feature/feature-name
   ```
2. If you modify the database schema, update `src/main/resources/schema.sql`.
3. Test your changes thoroughly via the CLI interface.
4. Use standard commit message conventions (e.g., Conventional Commits):
   ```text
   feat: add restaurant sorting by rating
   fix: resolve crash on invalid date input
   ```
5. Open a Pull Request against the `main` branch.

---

### Licence / License
Přispěním do tohoto repozitáře souhlasíš s tím, že tvůj kód bude zveřejněn pod licencí [MIT](LICENSE). / By contributing to this repository, you agree that your code will be published under the [MIT License](LICENSE).
