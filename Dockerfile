FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

RUN mkdir -p /data && chmod 777 /data

RUN useradd -m appuser

USER appuser

CMD ["java", "-jar", "target/clyvo-predict-0.0.1-SNAPSHOT.jar"]
