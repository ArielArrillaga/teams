# imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copia el archivo WAR al contenedor
COPY target/teams-0.0.1-SNAPSHOT.war /app/teams.war

# Puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar el archivo WAR
ENTRYPOINT ["java", "-jar", "/app/teams.war"]