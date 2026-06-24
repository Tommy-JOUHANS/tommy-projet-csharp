# ── Étape 1 : Compilation ────────────────────────────────────────────────────
FROM eclipse-temurin:21-jdk AS build
WORKDIR /src
COPY LookAndSay.java ./
RUN javac LookAndSay.java && java LookAndSay

# ── Étape 2 : Runtime (JRE uniquement, image plus légère) ────────────────────
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
COPY --from=build /src/LookAndSay.class ./

ENTRYPOINT ["java", "LookAndSay"]
