<div align="center">

# Rezervační systém restaurací

Konzolová aplikace pro správu rezervací, hodnocení restaurací a uživatelských účtů.

[![Java](https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://java.com/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![H2 Database](https://img.shields.io/badge/H2-Database-003545?style=for-the-badge&logo=h2&logoColor=white)](https://h2database.com/)

**Rychlá a jednoduchá cesta, jak si zarezervovat stůl ve vaší oblíbené restauraci.**

</div>

***

## 📋 Obsah

- [O projektu](#-o-projektu)
- [Funkce](#-funkce)
- [Technologie](#-technologie)
- [Instalace](#-instalace)
- [Použití](#-použití)
- [Struktura projektu](#-struktura-projektu)
- [Přispívání](#-přispívání)
- [Licence](#-licence)

***

## 🚀 O projektu

Tato CLI (Command Line Interface) aplikace umožňuje uživatelům vyhledávat restaurace, rezervovat si v nich stoly a psát recenze. Aplikace je napsána v čisté Javě s důrazem na objektově orientovaný návrh a využití návrhových vzorů (Facade, Observer, Command, DAO, Builder, Factory). Data jsou ukládána do lokální H2 databáze.

***

## ✨ Funkce

- ✅ **Správa uživatelů:** Registrace a přihlášení uživatelů do systému.
- ✅ **Vyhledávání:** Možnost vyhledat konkrétní restauraci.
- ✅ **Rezervace stolů:** Vytvoření rezervace s možností ručního výběru stolu, nebo automatická rychlá rezervace.
- ✅ **Správa rezervací:** Zobrazení všech aktivních i minulých rezervací uživatele.
- ✅ **Recenze a hodnocení:** Psaní recenzí k restauracím a prohlížení vlastních hodnocení.
- ✅ **Automatické aktualizace:** Hodnocení restaurací je automaticky přepočítáváno při přidání nové recenze (implementováno pomocí vzoru Observer).

***

## 🛠 Technologie

| Kategorie | Technologie |
|---|---|
| Jazyk | Java 23 |
| Framework | Bez frameworku (Pure Java) |
| Databáze | H2 Database (lokální databáze `h2database.mv.db`) |
| Sestavení | Maven |
| Uživatelské rozhraní | Konzolové rozhraní (CLI) |

***

## ⚙️ Instalace

### Požadavky

- Java Development Kit (JDK) 23 nebo novější
- Apache Maven

### Kroky

```bash
# 1. Klonuj repozitář
git clone https://github.com/[OWNER]/Restaurant-Reservation-System.git
cd Restaurant-Reservation-System

# 2. Zkompiluj projekt a stáhni závislosti pomocí Mavenu
mvn clean compile

# 3. Spusť aplikaci
mvn exec:java -Dexec.mainClass="org.mns.Main"
```

*Při prvním spuštění se automaticky inicializuje H2 databáze (soubor `h2database.mv.db`) a naplní se testovacími daty.*

***

## 📖 Použití

Po spuštění aplikace vás uvítá hlavní menu. Pro otestování aplikace můžete využít předpřipravené testovací účty, které se vypíší do konzole při startu:

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

Zadejte požadovanou volbu a stiskněte `Enter`. Pokud se přihlásíte, dostanete se do klientského menu, kde můžete vytvářet rezervace a přidávat recenze.

***

## 📁 Struktura projektu

```text
Restaurant-Reservation-System/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/org/mns/
│   │   │   ├── Main.java               # Vstupní bod aplikace
│   │   │   ├── builder/                # Návrhový vzor Builder
│   │   │   ├── dao/                    # Data Access Objects (přístup k DB)
│   │   │   ├── db/                     # Inicializace databáze a testovací data
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── facade/                 # Návrhový vzor Facade
│   │   │   ├── factory/                # Návrhový vzor Factory
│   │   │   ├── model/                  # Datové modely (Entity)
│   │   │   ├── observer/               # Návrhový vzor Observer (aktualizace hodnocení)
│   │   │   ├── service/                # Business logika
│   │   │   └── ui/                     # CLI uživatelské rozhraní a Command pattern
│   │   └── resources/
│   │       └── schema.sql              # SQL DDL skript pro vytvoření tabulek
```

***

## 🤝 Přispívání

Příspěvky jsou vítány! Postupuj takto:

1. Forkni repozitář
2. Vytvoř větev pro svou funkci (`git checkout -b feature/nova-funkce`)
3. Commitni změny (`git commit -m 'feat: přidej novou funkci'`)
4. Pushni větev (`git push origin feature/nova-funkce`)
5. Otevři Pull Request

***

## 📄 Licence

Distribuováno pod licencí MIT. Viz soubor [`LICENSE`](LICENSE) pro více informací.

***

<div align="center">

Vytvořeno s ❤️ · 2026

</div>