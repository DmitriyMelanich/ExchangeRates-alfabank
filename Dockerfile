FROM openjdk:14
EXPOSE 8080
ADD build/libs/ExchangeRates-0.0.1.jar ExchangeRates-0.0.1.jar
ENTRYPOINT ["java", "-jar", "ExchangeRates-0.0.1.jar"]