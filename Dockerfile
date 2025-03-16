FROM azul/zulu-openjdk-alpine:21

RUN apk add --no-cache curl && \
    curl -sL https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz | tar xz -C /usr/local/bin

WORKDIR /app

COPY target/*.jar app.jar

CMD ["dockerize", "-wait", "tcp://db:5432", "-timeout", "60s", "java", "-jar", "app.jar"]
