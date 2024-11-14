# Etapa 1: Compilar o projeto
FROM maven:3.8.6-openjdk-17 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /dslist

# Copiar os arquivos do projeto para o container
COPY . .

# Compilar o projeto e criar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Executar o JAR gerado
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho no container
WORKDIR /dslist

# Copiar o JAR da etapa de build
COPY --from=build /dslist/target/*.jar dslist.jar

# Expor a porta que a aplicação utiliza (8080 por padrão)
EXPOSE 8080

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "dslist.jar"]