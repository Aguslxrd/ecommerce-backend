# Ecommerce Backend

Este es el repositorio del backend de una aplicación de comercio electrónico desarrollado en Java utilizando el framework Spring. El backend proporciona una API RESTful segura para gestionar productos, pedidos y autenticación.

## Características principales

- **Autenticación y autorización**: Implementa JSON Web Tokens (JWT) y Spring Security para la autenticación de usuarios y control de acceso basado en roles.
- **Gestión de productos**: Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para administrar productos, categorías.
- **Gestión de pedidos**: Funcionalidad para crear, actualizar y consultar pedidos de los clientes.
- *Pagos*: Actualmente el sistema cuenta con paypal para realizar los pagos (Se encuentra en modo sandbox)
- **Gestión de usuarios**: Operaciones CRUD para administrar usuarios, roles y perfiles.  - > TO-DO
- **Integración con base de datos**: Utiliza Spring Data JPA para interactuar con una base de datos relacional.

## Tecnologías utilizadas

- Java 20
- Spring Boot
- Spring Web
- Spring Security
- JSON Web Tokens (JWT)
- Spring Data JPA
- Hibernate
- Maven

## Requisitos

- Java 11 o superior
- Maven
- Una base de datos relacional (MySQL, PostgreSQL en este caso)

## Configuración

1. Clona este repositorio en tu máquina local.
2. Configura la conexión a la base de datos en el archivo `application.properties` y la conexion con las credenciales de paypal.
3. Ejecuta el proyecto utilizando Maven: `mvn spring-boot:run`
