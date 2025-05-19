# 1. Build bosqichi: Maven yordamida loyihani build qilish
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Ish papkasi /app
WORKDIR /app

# Loyihani konteyner ichiga nusxalash
COPY . .

# Loyihani paketlash, testlarni o‘tkazmaydi
RUN mvn clean package -DskipTests

# 2. Ishga tushirish bosqichi: Java Runtime muhitidan foydalanish
FROM eclipse-temurin:21-jdk

# Ish papkasi /app
WORKDIR /app

# Build bosqichidan tayyor .jar faylni nusxalash
COPY --from=build /app/target/KuchTopBot-0.0.1-SNAPSHOT.jar app.jar

# Ilovaning portini ochish
EXPOSE 8080

# Konteyner ishga tushganda bajariladigan buyruq
ENTRYPOINT ["java", "-jar", "app.jar"]