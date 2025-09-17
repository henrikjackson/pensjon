# Pensjon API – skjelett

Minimal Kotlin/Spring Boot 3-app (Gradle, JDK 21 via Foojay) for å estimere opptjent pensjon for
medlemmer.

## Krav
- JDK 21 (auto-provisjoneres via Foojay i `settings.gradle.kts`)
- IntelliJ IDEA anbefales

## Kjøring
```bash
# macOS/Linux
./gradlew bootRun
# Windows
.\gradlew.bat bootRun
```

Eller last inn Gradle og kjør opp `PensjonApiApplication`

Appen starter på `http://localhost:8080`.

### Test endepunktet

Eksempler på kall mot endepunkt ligger i `pensjon.http`


## Tester
Tester ligger i `PensionEstimationServiceTest`

Bygging og tester kjøres med Github Actions for hver commit / PR.