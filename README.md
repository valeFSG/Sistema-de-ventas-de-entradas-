#  Sistema de Venta de Entradas

Sistema basado en arquitectura de microservicios desarrollado con Spring Boot para la gestión de eventos, ventas, tickets, preventas y recintos.

---

#  Tecnologías Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- Maven
- WebClient
- Postman
- Lombok

---

#  Microservicios

El sistema está compuesto por los siguientes microservicios:

- Microservicio Evento
- Microservicio Venta
- Microservicio Ticket
- Microservicio Preventa
- Microservicio Recinto

---

#  Puertos

| Microservicio | Puerto |
|---|---|
| Evento | 8081 |
| Recinto | 8084 |
| Ticket | 8085 |
| Preventa | 8086 |
| Venta | 8091 |

---

#  Arquitectura

El proyecto utiliza arquitectura basada en microservicios y comunicación REST.

Cada microservicio posee:

- Controller
- Service
- Repository
- DTO
- Model
- Configuración propia
- Base de datos MySQL

---

#  Funcionalidades

## Evento
- Crear eventos
- Listar eventos
- Buscar evento por ID
- Eliminar eventos

## Venta
- Crear ventas
- Listar ventas
- Actualizar ventas
- Eliminar ventas

## Ticket
- Crear tickets
- Buscar tickets
- Actualizar tickets
- Eliminar tickets

## Preventa
- Crear preventas
- Actualizar preventas
- Eliminar preventas

## Recinto
- Crear recintos
- Actualizar recintos
- Eliminar recintos

---

#  Comunicación entre Microservicios

El sistema implementa comunicación entre microservicios mediante WebClient.

Ejemplos:

- Ticket → Venta
- Evento → Recinto

---

#  Validaciones

Se implementaron validaciones utilizando:

- Jakarta Validation
- DTOs
- @Valid

Ejemplos de validaciones:

- Campos obligatorios
- Valores positivos
- Datos nulos

---

#  Endpoints Principales

## Evento

GET /evento

POST /evento

GET /evento/{id}

DELETE /evento/{id}

---

## Venta

GET /ventas

POST /ventas

PUT /ventas/{id}

DELETE /ventas/{id}

---

## Ticket

GET /api/v1/tickets/listar

POST /api/v1/tickets/crear-ticket

PUT /api/v1/tickets/actualizar/{id}

DELETE /api/v1/tickets/eliminar/{id}

---

## Preventa

GET /preventa

POST /preventa

PUT /preventa/{id}

DELETE /preventa/{id}

---

## Recinto

GET /recinto

POST /recinto

PUT /recinto/{id}

DELETE /recinto/{id}

---

#  Base de Datos

El proyecto utiliza MySQL como sistema de gestión de base de datos.

Flyway se utiliza para la administración y migración de tablas.

---

#  Autor

Valentina Santana - Nicol Gonzalez 

Ingenieria en Informatica 

Proyecto académico desarrollado para la asignatura de desarrollo Fullstack 