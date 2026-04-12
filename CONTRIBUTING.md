# Jak přispět do Restaurant-Reservation-System

Díky za zájem! Projekt je Java Maven aplikace s H2 embedded databází.

## Požadavky

- Java 17+
- Maven 3.8+
- IDE doporučené: IntelliJ IDEA

## Spuštění projektu

```bash
git clone https://github.com/[TVŮJ_USERNAME]/Restaurant-Reservation-System.git
cd Restaurant-Reservation-System
mvn compile
mvn exec:java -Dexec.mainClass="org.mns.Main"
```

H2 databáze se vytvoří automaticky při prvním spuštění (`h2database` soubor v kořenu projektu).

## Struktura projektu

```
src/main/java/org/mns/
├── console/      # Konzolové rozhraní (vstup/výstup)
├── model/        # Datové třídy (Zakaznik, Restaurace, Stul, Rezervace, Recenze)
├── service/      # Byznys logika a práce s databází (DatabaseManager)
└── Main.java     # Vstupní bod aplikace
```

## Konvence kódu

- Jazyk kódu: **čeština** (názvy metod, proměnných i výpisů) – drž se zavedené konvence
- Balíčky: vše pod `org.mns`
- Každá nová entita patří do `model/`, každá DB operace do `service/`
- SQL dotazy piš jako konstanty nebo `PreparedStatement`, nikdy string concatenation s uživatelským vstupem
- Pro připojení k DB vždy používej `DatabaseManager.getConnection()`, nevytvárej vlastní spojení

## Hlášení bugů

Otevři issue a uveď:
1. Kroky k reprodukci
2. Očekávané chování
3. Skutečné chování + případný výpis chyby z konzole
4. Verzi Javy (`java -version`)

## Pull requesty

1. Vytvoř větev z `main`:
   ```bash
   git checkout -b feat/nazev-funkce
   ```
2. Přidej nové tabulky do `DatabaseManager.inicializujDatabazi()` pokud je potřeba
3. Otestuj ručně přes konzolové menu
4. Commituj výstižně:
   ```
   feat: přidána správa recenzí restaurací
   fix: opraveno vyhledávání restaurace (překlep v SQL)
   ```
5. Otevři Pull Request na větev `main`

## Licence

Přispěním souhlasíš s tím, že tvůj kód bude zveřejněn pod licencí [MIT](LICENSE).