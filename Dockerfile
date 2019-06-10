FROM openjdk:$JDK
WORKDIR /app
COPY . /app
RUN ./mvnw -fae verify -P$JAVA_PROFILE