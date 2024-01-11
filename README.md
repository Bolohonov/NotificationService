# NotificationService

Для запуска сервиса необходимо выполнить в терминале из корневой папки проекта:  
mvn clean package

После завершения работы maven при запущенном Docker ввести команду:   
docker-compose up

Будут развернуты 5 контейнеров Docker.  
По умолчанию созданы учетные записи администратора  
логин: admin  
пароль: admin  

пользователя:  
логин: user  
пароль: user  

Доступ к сервису: авторизация пользователя localhost:9090/auth/login  
Основные эндпоинты:  
localhost:9090/api/notifications/sms - отправка sms  
localhost:9090/api/notifications/push - отправка push  
localhost:9090/api/notifications/email - отправка email  

Доступ к Eureka: localhost:8761  

Postman-коллекция для тестирования контроллера в корневой папке: NotificationService.postman_collection.json  
содержит pre-request script для автоматической авторизации админа и отправка запросов от его имени.
