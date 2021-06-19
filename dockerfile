#stackoverflow answer reference , thanks Sebastian :D
#https://stackoverflow.com/questions/51679363/multi-module-maven-project-on-dockers

# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as DEPS

WORKDIR /app
COPY api/pom.xml api/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY common/pom.xml common/pom.xml

COPY pom.xml .

RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.2.0:go-offline -DexcludeArtifactIds=com.example

FROM maven:3.8.1-jdk-11 as BUILDER
WORKDIR /app

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /app/ /app
COPY api/src /app/api/src
COPY domain/src /app/domain/src
COPY infrastructure/src /app/infrastructure/src
COPY common/src /app/common/src

RUN mvn -B -e package -DskipTests=true

# At this point, BUILDER stage should have your .jar or whatever in some path
FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/api/target/api-0.0.1-SNAPSHOT.jar .
EXPOSE 8090
CMD [ "java", "-jar", "/app/api-0.0.1-SNAPSHOT.jar" ]