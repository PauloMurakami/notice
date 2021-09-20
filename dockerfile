FROM adoptopenjdk/openjdk11

WORKDIR /app/

COPY . ./

RUN chmod +x ./mvnw && \
    ./mvnw clean install && \
    rm -rf .mvn src mvnw mvnw.cmd pom.xml

RUN mv target/*.jar app.jar

RUN rm -rf target

EXPOSE 8080

CMD java -jar app.jar -DskipTests
