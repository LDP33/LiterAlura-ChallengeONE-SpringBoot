# 📚 LiteAluraDesafio - Challenge Literalura

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/API-Gutendex-green?style=for-the-badge" alt="API">
<img src="https://img.shields.io/badge/Challenge-ONE-orange?style=for-the-badge" alt="Challenge ONE">
</div>

## 🌟 Descripción

¡Bienvenido a **LiteAlura**! Una aplicación completa desarrollada en **Java + Spring Boot** que te permite gestionar libros, consultar información desde la API **Gutendex** y almacenarlos en **PostgreSQL**. Este proyecto fue creado como parte del **ChallengeONE LiterAlura** de Alura.

### ✨ Características Principales

- 🔍 **Búsqueda de libros** por título, autor o idioma
- 💾 **Almacenamiento persistente** en base de datos PostgreSQL
- 📊 **Estadísticas por idioma** y análisis de autores
- 🖥️ **Interfaz de consola interactiva** con menú colorido
- 🌐 **API REST** para integración con otros sistemas
- ⚡ **Integración con Gutendex API** para datos actualizados

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje principal
- **Spring Boot 3.5.0** - Framework de desarrollo
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos relacional
- **Gutendex API** - Catálogo de libros en línea
- **Jackson** - Parsing de JSON
- **Maven** - Gestión de dependencias

## 📋 Requisitos Previos

- Java 21 o superior
- PostgreSQL instalado y configurado
- Conexión a internet (para consultar la API)
- IDE de tu preferencia (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Instalación y Uso

### 1. Clona el repositorio
```bash
git clone https://github.com/LDP33/LiterAlura-ChallengeONE-SpringBoot.git
cd LiterAlura-ChallengeONE-SpringBoot
```

### 2. Configura la base de datos
```sql
CREATE DATABASE LiterAlura-ChallengeONE-SpringBoot;
```

### 3. Configura las variables de entorno
En `src/main/resources/application.properties`, agrega:
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

### 4. Ejecuta el proyecto
```bash
# Desde la línea de comandos
mvn spring-boot:run

# O desde tu IDE, ejecuta la clase LiterAluraChallengeOneSpringBootApplication
```

## 🎮 Cómo Usar

### Interfaz de Consola
Al ejecutar la aplicación, verás un menú interactivo con las siguientes opciones:

| Opción | Función | Descripción |
|--------|---------|-------------|
| 1️⃣ | 🔍 Buscar Libro por Título | Busca libros en la API Gutendex |
| 2️⃣ | 📖 Listar Libros Registrados | Muestra todos los libros guardados |
| 3️⃣ | ✍️ Listar Autores Registrados | Muestra todos los autores en la BD |
| 4️⃣ | 🏛️ Listar Autores por Años | Filtra autores por período de vida |
| 5️⃣ | 🌍 Listar Libros por Idioma | Filtra libros por idioma |
| 0️⃣ | ❌ Salir | Cierra la aplicación |

### API REST
La aplicación también expone endpoints REST para integración:

- **POST** `/libros/guardar` - Guarda un nuevo libro
- **GET** `/libros/buscar?titulo={titulo}` - Busca libros por título

## 🔧 Estructura del Proyecto

```
LiteAluraDesafio/
├── src/main/java/com/aluraCursos/LiteAluraDesafio/
│   ├── LiteAluraDesafioApplication.java    # Clase principal de Spring Boot
│   ├── LibroController.java                # Controlador REST
│   ├── model/                              # Modelos de datos
│   │   ├── Datos.java                      # Modelo principal de respuesta API
│   │   ├── DatosAutor.java                 # Modelo de autor
│   │   ├── DatosLibros.java                # Modelo de libro
│   │   ├── LibroDTO.java                   # DTO para transferencia
│   │   ├── Libros.java                     # Entidad JPA
│   │   └── ListToStringConverter.java      # Convertidor personalizado
│   ├── principal/                          # Lógica de consola
│   │   └── Principal.java                  # Menú principal interactivo
│   ├── repository/                         # Capa de acceso a datos
│   │   └── LibroRepository.java            # Repositorio JPA
│   └── service/                            # Lógica de negocio
│       ├── ConsumoApi.java                 # Cliente HTTP para API
│       ├── ConvierteDatos.java             # Conversión de JSON
│       ├── IConvierteDatos.java            # Interfaz de conversión
│       └── LibroService.java               # Servicio de libros
├── src/main/resources/
│   └── application.properties              # Configuración de la aplicación
└── pom.xml                                 # Dependencias Maven
```

## 🌐 API Utilizada

Este proyecto utiliza la **Gutendex API** para obtener información de libros:

- **URL Base**: `https://gutendex.com/books/`
- **Endpoint de búsqueda**: `?search={titulo}`
- **Respuesta**: JSON con información detallada de libros

## 🎯 Funcionalidades Técnicas

### 🔄 Gestión de Datos
- **Persistencia JPA** con PostgreSQL
- **Mapeo automático** de JSON a objetos Java
- **Validación de datos** antes del almacenamiento

### 🎨 Interfaz de Usuario
- **Menú colorido** con caracteres Unicode
- **Mensajes informativos** y claros
- **Manejo de errores** robusto

### 🌐 Integración API
- **Cliente HTTP** personalizado
- **Parsing JSON** con Jackson
- **Manejo de errores** de red

## 👨‍💻 Autor

**Leandro Pollano** - https://github.com/LDP33

Proyecto creado para el **Challenge LiterAlura** de Alura Latam.