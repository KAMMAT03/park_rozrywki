FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

COPY ./Wallet /app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-Doracle.net.wallet_location=/app/wallet", "-Doracle.net.tns_admin=/app/wallet", "-jar", "/app/app.jar"]
