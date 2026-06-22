# Sistema de Venta de Entradas

Sistema desarrollado bajo arquitectura de microservicios utilizando Spring Boot para la gestión de eventos, ventas de entradas, validaciones y accesos streaming.

---

# Tecnologías Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Web
* Spring WebFlux
* MySQL
* Flyway
* Maven
* Postman
* JUnit 5
* Mockito

---

# Arquitectura

El sistema está compuesto por 10 microservicios independientes y un API Gateway para la gestión centralizada de las solicitudes.

---

# Microservicios

* Evento
* Recinto
* Validación
* Ticket
* Preventa
* Artistas
* Promotores
* Venta
* Devoluciones
* Streaming

---

# Puertos

| Microservicio | Puerto |
| ------------- | ------ |
| Evento        | 8081   |
| Recinto       | 8082   |
| Validación    | 8084   |
| Ticket        | 8085   |
| Preventa      | 8086   |
| Artistas      | 8088   |
| Promotores    | 8090   |
| Venta         | 8091   |
| Devoluciones  | 8094   |
| Streaming     | 8095   |

---

# Funcionalidades

* CRUD completo en todos los microservicios.
* Comunicación entre microservicios mediante WebClient.
* Persistencia de datos con MySQL.
* Migraciones automáticas con Flyway.
* Validaciones mediante DTO y Jakarta Validation.
* Testing con JUnit y Mockito.
* Documentación de endpoints mediante Postman.
* API Gateway para centralización de rutas.

---

# Testing

El proyecto incluye pruebas unitarias utilizando:

* JUnit 5
* Mockito
* MockMvc

Se realizaron pruebas para los distintos endpoints de los microservicios.

---

# Autor

* Valentina Santana
* Nicol González
