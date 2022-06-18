FROM openjdk:14
EXPOSE 8080
COPY . /app
WORKDIR /app
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "build/libs/ExchangeRates-0.0.1.jar"]
