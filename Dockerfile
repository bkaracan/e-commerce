# Java 21 JDK ve Maven içeren bir temel imaj kullanıyoruz
FROM eclipse-temurin:21-jdk-alpine AS build

# Maven'i yükle
RUN apk add --no-cache maven

# Çalışma dizinini ayarla
WORKDIR /app

# Maven ve proje dosyalarını kopyala
COPY pom.xml .
COPY src ./src

# Maven komutunu kullanarak bağımlılıkları indir ve uygulamayı derle
RUN mvn clean package -DskipTests

# Uygulamayı çalıştırmak için daha hafif bir Java 21 JRE imajı kullan
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Derlenmiş JAR dosyasını kopyala
COPY --from=build /app/target/e-commerce-0.0.1-SNAPSHOT.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
