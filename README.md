# NotificationService

Для запуска сервиса необходимо выполнить в терминале из корневой папки проекта:  
mvn clean package

После завершения работы maven при запущенном Docker ввести команду:   
docker-compose up

Будут развернуты 3 контейнера Docker. 
Доступ к сервису: localhost:9090
Доступ к Eureka: localhost:8761
