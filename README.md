# Crudzaso TinyTasks

**TinyTasks** es una microaplicación CRUD desarrollada con **Java 21 + Spring Boot** en el backend y **HTML + JavaScript + Bootstrap** en el frontend.  

El objetivo es practicar la estructura mínima de un proyecto completo **(Front → API → Lógica → Datos)**, trabajando con datos **en memoria** sin base de datos externa.

---

## Tecnologías utilizadas

###  Backend
- **Java 21**
- **Spring Boot (Web)**
- **Maven**
- **JUnit 5** (para pruebas)
- **CORS** habilitado para el frontend local (`http://localhost:5500`)

###  Frontend
- **HTML5**
- **CSS3 / Bootstrap 5**
- **JavaScript (fetch API)**

---

##  Estructura del proyecto

```
crudzaso-tinytasks/
├─ backend/
│  └─ src/main/java/com/crudzaso/tinytasks/
│     ├─ controller/
│     │  └─ TaskController.java
│     ├─ service/
│     │  └─ TaskService.java
│     ├─ repository/
│     │  └─ TaskRepository.java
│     ├─ model/
│     │  └─ Task.java
│     └─ config/
│        └─ CorsConfig.java
│
├─ frontend/
│  ├─ index.html
│  ├─ style.css
│  └─ app.js
│
└─ README.md
```

---

##  Instalación y ejecución

### Backend (Spring Boot)

1. Abre la carpeta `backend` en tu IDE (por ejemplo IntelliJ o VS Code).
2. Asegúrate de tener **Java 21** y **Maven** instalados.
3. Ejecuta el proyecto:

   ```bash
   mvn spring-boot:run
   ```

El backend se ejecutará en:
```
http://localhost:8080
```

### Frontend

1. Abre la carpeta `frontend` con tu editor.
2. Usa una extensión como **Live Server** o ejecuta un servidor local.
3. El frontend debe servirse en:

```
http://localhost:5500
```

>  **Nota:** El backend permite peticiones CORS solo desde `http://localhost:5500`.

---

##  Endpoints del API

| Método | Endpoint | Descripción | Body (JSON) | Respuesta |
|--------|----------|-------------|-------------|-----------|
| `GET` | `/api/tasks` | Lista todas las tareas | — | `[ { id, title, description, done } ]` |
| `POST` | `/api/tasks` | Crea una nueva tarea | `{ "title": "Task", "description": "..." }` | `{ id, title, description, done }` |
| `PUT` | `/api/tasks/{id}/toggle` | Alterna el estado (done) | — | `{ id, title, description, done }` |
| `DELETE` | `/api/tasks/{id}` | Elimina una tarea | — | — |

###  Errores comunes

| Código | Respuesta |
|--------|-----------|
| `400` | `{ "error": "Title is required" }` |
| `404` | `{ "error": "Not found" }` |

---

##  Funcionalidades

-  Crear tarea (con título y descripción opcional)  
-  Listar todas las tareas  
-  Alternar el estado (completada / pendiente)  
-  Eliminar tareas  
-  Validaciones y manejo de errores  
-  CORS configurado  
-  Tests unitarios con JUnit 5  

---

## Pruebas (JUnit 5)

Las pruebas se centran en la lógica del `TaskService` y `TaskRepository`.

| Módulo | Caso | Positivo | Negativo |
|--------|------|----------|----------|
| **Service** | Crear tarea | Título válido → tarea creada | Título vacío → excepción |
| **Service** | Alternar estado | Cambia `done` correctamente | ID inexistente → error |
| **Service** | Eliminar tarea | Elimina correctamente | ID inexistente → `false` |
| **Repository** | Generar IDs | Autoincrementales y únicos | — |
| **Repository** | Buscar por ID | Devuelve `Optional` con valor | `Optional.empty()` |

---

## Lecciones aprendidas

- Cómo estructurar un proyecto con Spring Boot.
- Cómo implementar un CRUD con datos en memoria.
- Cómo conectar el frontend con la API usando `fetch()`.
- Cómo manejar CORS en proyectos locales.
- Cómo escribir y ejecutar pruebas unitarias.

---

## Futuras mejoras

- Persistencia con base de datos MySQL o H2.
- Filtro de tareas completadas / pendientes.
- Edición de tareas.
- Manejo de usuarios y sesiones.

---

## Autor

Proyecto educativo desarrollado por **Yeferson Alejandro García Marín**  
**Actividad:** CRUDACTIVITY — TinyTasks (Spring Boot)
