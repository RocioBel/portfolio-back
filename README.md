# Portfolio web

Aplicación construída en Spring Boot para el proyecto final de Argentina Programa.

## Tecnologías utilizadas
- Java 17
- Spring Boot
- MySQL


## Uso

### Swagger
Para acceder al swagger, hay que dirigirse a '/swagger-ui.html'

### Url base de datos creada en Clever Cloud:

jdbc:mysql://bflbosener34hc2cfvzs-mysql.services.clever-cloud.com:3306/bflbosener34hc2cfvzs

### Seguridad
A tener en cuenta:

- Para loguearse es necesario utilizar el endpoint POST: /signin con las credenciales proporcionadas.
- Para acceder a los métodos GET no es necesario enviar un token
- Para los demás métodos (POST, PUT, DELETE), es necesario hacer el signin para obtener el token y enviarlo cada vez que se requiera acceder a uno de esos métodos.

### Postman
En el repositorio se encuentra el Portfolio.postman_collection.json que contiene todos los requests prearmados para probar la aplicación.


## Funcionalidad
El proyecto consiste en un microservicio destinado a servirle al front de un portfolio web. Se puede crear una persona nueva, editarla, borrarla y obtenerla.
Cada persona contiene varios listados: 
- Educación
- Experiencias laborales
- Idiomas
- Skills
- Proyectos

Cada una de estas entidades también se puede crear, obtener, modificar y eliminar.


### Autora:

Rocío Belén Vargas