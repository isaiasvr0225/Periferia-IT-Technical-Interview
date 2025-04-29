# Arquitectura de Microservicios ToDo App - Periferia IT

Este proyecto está compuesto por múltiples microservicios que se comunican entre sí utilizando una arquitectura basada en Spring Boot. A continuación se describe el propósito de cada microservicio, su rol en el sistema y cómo levantar todo el ecosistema con el script `start.sh`.

## 📦 Microservicios

### 1. `microservice-api-gateway`
**Descripción:**  
Este microservicio actúa como punto de entrada único para el sistema. Redirige las solicitudes entrantes hacia los microservicios correspondientes utilizando rutas definidas. También puede encargarse de la autenticación, autorización, rate limiting, entre otros.

**Responsabilidades:**
- Encaminamiento de solicitudes (routing)
- Seguridad (con JWT, en este caso no se implementó por falta de tiempo)
- Manejo de CORS
- Centralización de logs y errores

---

### 2. `microservice-eureka`
**Descripción:**  
Servidor de descubrimiento de servicios (Service Registry) usando **Netflix Eureka**. Todos los microservicios se registran aquí y el Gateway lo utiliza para redirigir solicitudes dinámicamente.

**Responsabilidades:**
- Registro y descubrimiento de microservicios
- Balanceo de carga entre instancias
- Alta disponibilidad y resiliencia

---

### 3. `microservice-config-server`
**Descripción:**  
Servidor centralizado de configuración. Proporciona los archivos de configuración (application.yml/properties) para todos los microservicios desde un repositorio Git u otra fuente externa.

**Responsabilidades:**
- Centralización de la configuración de todos los microservicios
- Soporte para múltiples entornos (`dev`, `prod`, etc.)
- Permite actualizaciones en caliente si se implementa con Spring Cloud Bus

---

### 4. `microservice-tasks-manager`
**Descripción:**  
Gestiona un recurso llamado tasks con los campos: id, title, description y status.

**Responsabilidades:**
- Crear una nueva tarea.
- Obtener una tarea específica por ID.
- Actualizar el estado de una tarea por ID.
- Eliminar una tarea por ID.
- Listar todas las tareas con paginación simple.


## ⚙️ Arquitectura General

```plaintext
             +---------------------+
             |   Cliente / Front   |
             +---------+-----------+
                       |
                       ▼
             +---------------------+
             |   API Gateway       |
             +---------+-----------+
                       |
            +--------------------------+
           | microservice-tasks-manager |
            +--------------------------+

Todos los servicios se registran en:
       +--------------------+
       |    Eureka Server   |
       +--------------------+

Las configuraciones provienen de:
       +-----------------+
       |  Config Server   |
       +-----------------+
```
# 🚀 Cómo levantar el proyecto?

Este proyecto está compuesto por varios microservicios desarrollados con Spring Boot. Para facilitar su ejecución local, se incluye un script llamado `start.sh` que inicia todos los servicios en el orden correcto.

## 📁 Microservicios incluidos

- `microservice-config-server`
- `microservice-eureka`
- `microservice-api-gateway`
- `microservice-tasks-manager`

## ✅ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java 17 o superior**
- **Maven**
- **Git**
- **Docker**

## 🔄 Orden de arranque de los microservicios

El orden es importante para garantizar el correcto registro y descubrimiento de servicios:

1. `microservice-config-server` → Provee la configuración a los demás microservicios
2. `microservice-eureka` → Registra los microservicios que se van levantando
3. `microservice-api-gateway` → Puerta de entrada a todo el sistema
4. `microservice-task-manager` → Microservicio de gestión de tareas

## 🧪 ¿Cómo ejecutar todos los servicios?

### Usando el script `start.sh`

Este script automatiza la ejecución de todos los microservicios.

```bash
./start.sh
