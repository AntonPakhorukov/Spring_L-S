1. Создаем Dockerfile.
2. В него пишем:
FROM postgres:13.2-alpine
ENV POSTGRES_DB seminar_05
ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD password
3. Запускаем команду из папки, где находится Dockerfile
docker build -t seminar_05 .
Команда создаст новый образ Docker с именем "seminar_05"
4. Запускаем контейнер с помощью команды:
docker run --name seminar_05 -p 5432:5432 -d seminar_05
5. База данных развернута.
=============================================================
6. Создаем application.yaml в папке resources и прописываем:
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/seminar_05
    username: user
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
7. Зайти в БД через консоль:
docker exec -it seminar_05 psql -U user seminar_05
где seminar_05 -> название контейнера, название БД
8. Запросить список всех таблиц: \dt