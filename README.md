# NEORIS
**Prueba Neoris**

Agrega las siguientes líneas al archivo Dockerfile para crear una imagen de la aplicación Java:

```
# Crea una imagen basada en Java
FROM openjdk:17-jdk-alpine

# Copia el archivo jar de la aplicación al contenedor
COPY target/nombre-del-archivo.jar app.jar

# Indica que el puerto 8080 de la aplicación estará expuesto
EXPOSE 8080

# Comando para iniciar la aplicación al arrancar el contenedor
ENTRYPOINT ["java","-jar","/app.jar"]
```

Ejecuta el siguiente comando para construir las imágenes y correr los contenedores:

```
docker-compose up --build
```

Correr la aplicación:

```
spring-boot:run
```
