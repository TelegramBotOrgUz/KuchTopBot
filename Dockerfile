# 1. Ishga tushirish bosqichi: Java Runtime muhitidan foydalanish
FROM eclipse-temurin:21-jdk

# Ish papkasi /app
WORKDIR /app

# Build bosqichidan tayyor .jar faylni nusxalash
COPY target/KuchTopBot-0.0.1-SNAPSHOT.jar app.jar

# Ilovaning portini ochish
EXPOSE 8080

# Konteyner ishga tushganda bajariladigan buyruq
ENTRYPOINT ["java", "-jar", "app.jar"]