1. Запустить docker-desktop
2. Зайти в папку init, запустить docker-compose.yml
3. Задать в Spring Configuration для UserSubscriptionApplication переменные окружения Edit Configuration -> Modify Options -> Environment variables 
Вставить DB_HOST=localhost;DB_NAME=user_balance;DB_PASSWORD=postgres;DB_PORT=5432;DB_USER=postgres
4. Запустить приложение
5. http://localhost:8080/swagger-ui/index.html ссылка на swagger
6. Добавил класс userInitializer, который генерит напрямую в обход liquebase пользователей для наглядной работы.
