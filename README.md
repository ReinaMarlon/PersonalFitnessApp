# Personal Fitness Tracker

Aplicación desarrollada en **Java Spring Boot** para gestionar rutinas de ejercicio, registrar entrenamientos y llevar un historial de progreso.  
Pensada tanto para **usuarios regulares** que desean registrar su actividad, como para **administradores** que gestionan la librería de ejercicios.

---

## Características principales

**Usuarios**
- Registro e inicio de sesión.
- Distinción de roles: **Admin** y **Regular**.

**Ejercicios**
- Listar todos los ejercicios disponibles.
- Añadir nuevos ejercicios *(solo Admin)*.
- Eliminar ejercicios *(solo Admin)*.
- Búsqueda de ejercicios por nombre.

**Workouts**
- Listar rutinas disponibles.
- Registrar un workout realizado.

**Workout Logs**
- Registrar entrenamientos con detalle de ejercicios, tiempo y calorías quemadas.
- Ver historial de entrenamientos ordenados por fecha.
- Consultar detalles de un entrenamiento específico.

---

## Arquitectura

El proyecto sigue principios de **Clean Architecture**, dividiendo responsabilidades en capas:

- **domain** → Modelos de negocio y entidades principales.
- **application** → Casos de uso y servicios de aplicación.
- **infrastructure** → Adaptadores, controladores y capa de entrada (consola).
- **dto** → Objetos de transferencia de datos para requests/responses.

---


---

## Tecnologías usadas

- **Spring Boot**
- **Spring Data JPA**
- **TiDB** (base de datos relacional distribuida)
- **Lombok**
- **Hibernate**
- **Maven**

---

## 🚀 Base de datos

La configuración de acceso a la base de datos en aplication.properties sera enviada al correo.



