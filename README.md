Teams

Este proyecto es una aplicación para gestionar equipos de fútbol y proporciona un servicio de autenticación mediante JWT. 
El proyecto se desarrolló con el objetivo de responder a un challenge solicitado por una empresa de software.

Descripción

La aplicación permite gestionar equipos de fútbol mediante un sistema de alta, baja y modificación de equipos, e incluye un servicio de autenticación utilizando JWT para la seguridad.

Tecnologías Utilizadas

- Spring Boot 3
- Java 17
- Spring Data JPA
- Spring Security
- H2 Database
- Swagger
- JJWT
- Lombok
- Mockito

Requisitos Previos

- Java 17
- Maven
- Docker (opcional, para ejecutar en un contenedor)

Configuración del Proyecto

1. Clona el repositorio:

   git clone https://github.com/ArielArrillaga/teams.git

2. Navega al directorio del proyecto:

   cd teams

3. Construye el proyecto con Maven:

   mvn clean install

4. Ejecuta la aplicación:

   mvn spring-boot:run

Dockerización

1. Construye la imagen Docker:

   docker build -t teams-app .

2. Ejecuta el contenedor Docker:

   docker run -p 8080:8080 teams-app

   La aplicación estará disponible en http://localhost:8080.

Swagger

La API está documentada con Swagger. Puedes acceder a la documentación en http://localhost:8080/swagger-ui.html.

Autenticación con JWT

Swagger permite añadir un Bearer Token para autenticar las solicitudes. Después de iniciar sesión, debes copiar el JWT generado y pegarlo en el campo "Authorize" (arriba a la derecha de Swagger) para acceder a los endpoints protegidos (en este caso, todos están protegidos).

Base de Datos H2

La aplicación utiliza una base de datos en memoria H2. Puedes acceder a la consola de H2 en http://localhost:8080/h2-console. Al iniciar la aplicación, se crearán y completarán las tablas pertinentes.

Configuración de H2 para ingresar a la consola

- URL: jdbc:h2:mem:testdb
- Driver Class Name: org.h2.Driver
- Username: sa
- Password: 
