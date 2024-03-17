# Tennis scoreboard

# Overview
Проект представляет клиент-серверное приложене ведения счета теннисного матча с веб интерфейсом, которое реализовано в соответствии с архитектурным паттерном MVCS.
В качестве контроллеров для обработки запросов клиента используются Servlets. Которые в свою очередь пользуются сервисами для ведения счета, сохранения матчей,
получения и поиска законченных матчей. В качестве базы данных для сохранения игроков и матчей используется H2 in-memory SQL база.
Работа с которой реализована с помощью фреймворка Hibernate. Логига подсчета очков матча тестируется фрейворком JUnit 5.

Подсчет очков реализован по правилам https://www.gotennis.ru/read/world_of_tennis/pravila.html

# Technologies / tools used:
- Hibernate
- Java Servlets
- H2
- Maven
- Tomcat 10
- HTML
- CSS
- JUnit 5

# Installation
1. Собрать c помощью Maven war артефакт приложения.
2. Развернуть war артефакт в Tomcat.

# Usage
### 1. Главная страница.  

  ![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/6b1a31fb-0ff3-4f7e-8a31-f2f184f17663)

### 2. Страница создания нового матча.  
  Адрес - /new-match.

  ![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/e87a903d-0803-4ba5-91e6-588d0190ec36)

### 3. Страница матча.  
   Адрес - /match-score?uuid=$match_id.  
   GET параметр uuid содержит UUID матча.
   
  ![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/75abef11-912a-449d-ad76-0bfa2f3f5d83)

### 4. Результата законченного матча.

  ![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/4f14bd39-a3ea-42cf-90ae-108d85154cb6)

   Адрес - /matches?page=$page_number&filter_by_player_name=$player_name.  
   GET параметры:  
   + page - номер страницы. Если параметр не задан, подразумевается первая страница  
   + filter_by_player_name - имя игрока, матчи которого ищем. Если параметр не задан, отображаются все матчи.  
   
![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/29817d59-0cc5-4564-bf1e-846e9e19859c)

### 6. Поиск матчей по имени игрока.  
   
  ![image](https://github.com/Nikitavj/Scoreboard/assets/134765675/152209ca-ab29-48d4-9f35-7bec72fa71aa)

