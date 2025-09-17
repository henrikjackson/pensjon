# Pensjon API – skjelett

Minimal Kotlin/Spring Boot 3-app (Gradle, JDK 21 via Foojay) med ett tomt endepunkt:
- `GET /api/v1/pensjon/opptjening?fnr=...` → **501 Not Implemented** (placeholder)

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

## Neste steg (forslag)
- Definer DTO-er for opptjening (årsposter, sum, etc.)
- Legg inn service-lag og en mock-klient
- Returner 200 OK + placeholder-JSON mens logikk implementeres
- Dokumentér API med springdoc-openapi
