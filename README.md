<h1> Contact list </h1> 

Список контактов - приложение созданное с использованием Spring MVC и Spring Data JDBC имеющее простой веб-интерфейс, 
реализованный с помощью Thymeleaf. Запуск и настройка базы данных производиться через Docker-файлы в папке docker.

Посредством Api-контроллера приложение выполняет следующие задачи:

1. Выводит все контакты имеющиеся в таблице.
2. Добавляет новый контакт через форму.
3. Редактирует существующие контакты через форму.
4. Удаляет конкретный контакт через кнопку в списке контактов.

- В окне терминала Alt-F12 введите сd docker
- Запустите PostgresSQL в docker-контейнере командой docker compose up
- Запустите приложение, по умолчанию TomcatWebServer запустится на http://localhost:8081
- При необходимости загрузить исходные данные в БД выполните Liquibase миграцию.
  - Для этого измените application.yml
    liquibase:
      drop-first: true
      contexts: init
- Приложение создает, изменяет и редактирует список контактов в БД
    
