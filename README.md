# 🎟️ Sistema de Venta de Entradas

Sistema desarrollado con arquitectura de microservicios utilizando Spring Boot para la gestión de eventos y ventas de entradas.

---

# 🚀 Tecnologías Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- Maven
- Postman

---

# 📦 Microservicios

- Evento
- Venta
- Ticket
- Preventa
- Recinto
- Artistas
- Devoluciones
- Pomotores
- Streaming
- Validación 

---

# 🔌 Puertos

| Microservicio | Puerto |
|---|---|
| Evento | 8081 |
| Recinto | 8082 |
| Ticket | 8085 |
| Preventa | 8086 |
| Venta | 8091 |
| Artistas | 8088 |
| Devoluciones | 8094 |
| Promotores | 8090 |
| Streaming | 8095 |
| Validación | 8084 |


---

# 📬 Endpoints Principales

## Evento
- GET /evento
- POST /evento
- DELETE /evento/{id}

## Venta
- GET /ventas
- POST /ventas
- PUT /ventas/{id}
- DELETE /ventas/{id}

## Ticket
- GET /api/v1/tickets/listar
- POST /api/v1/tickets/crear-ticket

---

# ✅ Funcionalidades

- CRUD completo
- Validaciones con DTO
- Comunicación entre microservicios
- Persistencia con MySQL

---

# 👨‍💻 Autor

Valentina Santana - Nicol Gonzalez