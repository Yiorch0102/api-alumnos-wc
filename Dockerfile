# Etapa 1: Construir el .jar con Maven
FROM maven:3.9-eclipse-temurin-17 AS build

# Copiar el código fuente
WORKDIR /app
COPY . .

# Limpiar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final ligera
FROM eclipse-temurin:17-jre-jammy

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar solo el .jar construido de la etapa anterior
# Asegúrate que el nombre del .jar coincida con tu pom.xml
COPY --from=build /app/target/alumnos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 (aunque Render lo maneja)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]