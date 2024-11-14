# Etapa 1: Build usando Maven
FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos do projeto
COPY ./src src/
COPY ./pom.xml pom.xml

# Construir o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Executar a aplicação com uma imagem leve
FROM eclipse-temurin:21-jre-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar dslist.jar

# Expor a porta
EXPOSE 8080

# Comando para executar o JAR
CMD ["java", "-jar", "dslist.jar"]