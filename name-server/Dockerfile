FROM maven:3.9.8-eclipse-temurin-22-alpine
WORKDIR /app

COPY ./mvnw .

COPY pom.xml .

COPY . .

RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests

EXPOSE 8761

CMD ["java","-jar","target/name-server-1.0.0.jar"]