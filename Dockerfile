# Aşama 1: Uygulamayı derlemek için Maven kullanıyoruz
FROM maven:3.9.0-eclipse-temurin-21 AS build
WORKDIR /app
# pom.xml ve dependency'leri offline cache yapmak için kopyalanır
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Kaynak kodları kopyalıyoruz
COPY src ./src
# Testleri atlayarak jar paketini oluşturuyoruz
RUN mvn package -DskipTests -B

# Aşama 2: Uygulamayı çalıştırmak için hafif JRE imajı kullanıyoruz
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Build aşamasından jar dosyasını kopyalıyoruz
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
