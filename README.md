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

Appen starter på `http://localhost:8080`.

### Test endepunktet

Eksempler på kall mot endepunkt ligger i `pensjon.http`

```http
GET http://localhost:8080/api/v1/pensjon/opptjening?fnr=12345678901
```

## Tester
```bash
# macOS/Linux
./gradlew test
# Windows
.\gradlew.bat test
```