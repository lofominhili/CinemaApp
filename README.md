# CinemaApp

## Обзор

Backend приложение для загрузки и просмотра фильмов(условно).

### Использованные технологии

* Java 20

* Spring Boot

* Spring Web

* Gradle

## Запуск

Чтобы запустить, Склонируйтие репозиторий и напишите в консоли `docker-compose up`

## Endpoints

### Регистрация нового пользователя

` POST /auth/register ` на порте 8080, запрос будет выглядеть так: **localhost:8080/auth/register**

---

### Получить информацию о пользователе

` GET /api/user/{user_id}/info ` 

---

### Удалить пользователя

` DELETE /api/user/{user_id} ` 

---

### Получить "любимые" фильмы

` GET /api/user/{user_id}/favorite ` 

---

### Добавить фильм

` POST /api/film `

---

### Получение списка фильмов(по жанрам, по названию, по странице) в зависимости от того, что указать в параметрах

` GET /api/film/list `

---

### Получить фильм по его id

` GET /api/film/{film_id} `

---

### Добавление(удаление) фильма в(из) "любимые"

` POST /api/film/{film_id}/favorite/{user_id} `
` DELETE /api/film/{film_id}/favorite/{user_id} `

---

# P.S 
некоторые вещи не доработаны(некоторые endpoint'ы и комменты для фильмов) или не добавлены(spring security, чат, работа с картинками(аватар) и многое другое) в силу ограниченного времени.

