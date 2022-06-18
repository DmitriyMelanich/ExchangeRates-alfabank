FROM openjdk:14
EXPOSE 8080
COPY . /app
WORKDIR /app
RUN chmod 777 gradlew
RUN ./gradlew build
ENTRYPOINT ["java", "-jar", "build/libs/ExchangeRates-0.0.1.jar"]
