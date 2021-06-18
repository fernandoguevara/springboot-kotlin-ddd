# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as DEPS

WORKDIR /app
COPY api/pom.xml api/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY shared/pom.xml shared/pom.xml

COPY pom.xml .
#RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

FROM maven:3.8.1-jdk-11 as BUILDER
WORKDIR /app
#COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /app/ /app
COPY api/src /app/api/src
COPY domain/src /app/domain/src
COPY infrastructure/src /app/infrastructure/src
COPY shared/src /app/shared/src

# use -o (--offline) if you didn't need to exclude artifacts.
# if you have excluded artifacts, then remove -o flag
RUN mvn package -DskipTests=true

# At this point, BUILDER stage should have your .jar or whatever in some path
FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/api/target/api-0.0.1-SNAPSHOT.jar .
EXPOSE 8090
CMD [ "java", "-jar", "/app/api-0.0.1-SNAPSHOT.jar" ]