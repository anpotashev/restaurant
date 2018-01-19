Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

**The task is:**

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users

Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

Menu changes each day (admins do the updates)

Users can vote on which restaurant they want to have lunch at

Only one vote counted per user

If user votes again the same day:
* If it is before 11:00 we asume that he changed his mind.
* If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

__________________________________
Tables:
* Dish
    - id (pk)
    - name
    - deleted
* Restaurant
    - id (pk)
    - name
    - deleted
* Price 
    - id
    - restaurant_id (*)
    - dish_id (*)
    - date (*)
    - price
* Users
    - id (pk)
    - name
    - password
* Vote
    - date (pk)
    - user_id (pk)
    - restaurant_id
* User_roles
    - user_id (pk)
    - role (pk)
* - уникальный индекс в таблице

REST:

| Object | uri | get |  post | put | delete 
|:---|:---|:--- |:---  |:--- | :---  
| Restaurant | /rest/restaurants| list all | new | - | -
| Restaurant | /rest/restaurants/{r_id} | list | - | edit | delete
| Dish | /rest/dishes| list all | new | - | -
| Dish | /rest/dishes/{r_id} | list | - | edit | delete
| MenuItem | /rest/restaurants/{r_id}/prices/ | list all | - | - |
| MenuItem | /rest/restaurants/{r_id}/prices/{date} | list all | new | - |
| MenuItem | /rest/restaurants/{r_id}/prices/{date}/{id} | list | - | edit | delete
| Vote | /rest/restaurants/{r_id}/votes| get count | (re)vote | - | -
| Vote | /rest/restaurants/{r_id}/votes/{date}| get count | - | - | -


****API****

***DISHES***

**Получить список блюд:**

| - | - |
|---:|:---|
| URI | /rest/dishes |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"name" :name1},...,{"id":idN,"name":nameN}]` |

**Получить список блюд, отфильтрованный по началу поля _name like {NAME}%_**

| - | - |
|---:|:---|
| URI | /rest/dishes/filter |
| метод | GET |
| формат принимаемых данных | URL-encoded |
| принимаемые данные | `name=NAME` | 
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"name" :name1},...,{"id":idN,"name":nameN}]` |

 
**Получить блюдо с _id={id}_**

| - | - | 
|---:|:---|
| URI | /rest/dishes/{id} |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `{"id":id,"name" :name}` |


**Создать блюдо**

| - | - |
|---:|:---|
| URI | /rest/dishes/ |
| метод | POST |
| формат принимаемых данных | JSON |
| Принимаемые данные | `{"name" :name}` |
| формат возвращаемых данных | - |
| возвращаемые данные | `{"id":id,"name" :name}` |

Возвращает Location созданного ресурса.

**Изменить блюдо с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/dishes/{id} |
| метод | PUT |
| формат принимаемых данных | JSON |
| Принимаемые данные | `{"name" :name}` |
| формат возвращаемых данных | - |

**Удалить блюдо с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/dishes/{id} |
| метод | DELETE |
| формат принимаемых данных | - |
| формат возвращаемых данных | - |


***RESTAURANTS***

**Получить список ресторанов:**

| - | - |
|---:|:---|
| URI | /rest/restaurants |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"name" :name1},...,{"id":idN,"name":nameN}]` |

**Получить список ресторанов, отфильтрованный по началу поля _name like {NAME}%_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/filter |
| метод | GET |
| формат принимаемых данных | URL-encoded |
| принимаемые данные | `name=NAME` | 
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"name" :name1},...,{"id":idN,"name":nameN}]` |

 
**Получить рестораново с _id={id}_**

| - | - | 
|---:|:---|
| URI | /rest/restaurants/{id} |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `{"id":id,"name" :name}` |


**Создать рестораново**

| - | - |
|---:|:---|
| URI | /rest/restaurants/ |
| метод | POST |
| формат принимаемых данных | JSON |
| Принимаемые данные | `{"name" :name}` |
| формат возвращаемых данных | - |
| возвращаемые данные | `{"id":id,"name" :name}` |

Возвращает Location созданного ресурса.

**Изменить рестораново с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{id} |
| метод | PUT |
| формат принимаемых данных | JSON |
| Принимаемые данные | `{"name" :name}` |
| формат возвращаемых данных | - |

**Удалить рестораново с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{id} |
| метод | DELETE |
| формат принимаемых данных | - |
| формат возвращаемых данных | - |

***PRICES***

**Получить меню ресторана с _id={r_id}_ на текущую дату**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/ |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"price":price1,"dishId":dishId1,"dishName":dishName1},...,{"id":idN,"price":priceN,"dishId":dishIdN,"dishName":dishNameN}]` |

*здесь и далее price1..priceN имеют числовой формат с 2 знаками после запятой 

**Получить меню ресторана с _id={r_id}_ на дату _{date}_**

Здесь и далее {date} имеет формат yyyy-MM-dd

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/{date} |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `[{"id":id1,"price":price1,"dishId":dishId1,"dishName":dishName1},...,{"id":idN,"price":priceN,"dishId":dishIdN,"dishName":dishNameN}]` |

**Получить запись меню _id={id}_ для ресторана с _id={r_id}_ на дату _{date}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/{date}/{id} |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | JSON |
| возвращаемые данные | `{"id":id,"price":price,"dishId":dishId,"dishName":dishName}` |

**Создать запись меню для ресторана с _id={r_id}_ на дату _{date}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/{date}/{id} |
| метод | POST |
| формат принимаемых данных | JSON |
| Принимаемые данные | `{"price":price,"dishId":dishId}` |
| формат возвращаемых данных | - |

**Изменить запись меню _id={id}_ для ресторана с _id={r_id}_ на дату _{date}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/{date}/{id} |
| метод | PUT |
| Принимаемые данные | `{"price":price,"dishId":dishId}` |
| формат принимаемых данных | JSON |
| формат возвращаемых данных | - |


**Удалить запись меню _id={id}_ для ресторана с _id={r_id}_ на дату _{date}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/prices/{date}/{id} |
| метод | DELETE |
| формат принимаемых данных | - |
| формат возвращаемых данных | - |

***VOTES***

**Проголосовать/переголосовать за ресторана с _id={r_id}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/votes |
| метод | POST |
| формат принимаемых данных | - |
| формат возвращаемых данных | - |

**Получить статистику (кол-во голосов) на текущую дату для ресторана с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/votes |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | TEXT |

**Получить статистику (кол-во голосов) на дату {date} для ресторана с _id={id}_**

| - | - |
|---:|:---|
| URI | /rest/restaurants/{r_id}/votes/{date} |
| метод | GET |
| формат принимаемых данных | - |
| формат возвращаемых данных | TEXT |

    

***Примеры curl***

Для удобства можно определить следующую переменную, указывающую на корень приложения:
export RESTAURANT="http://172.16.1.11:8081"

Далее в примерах будут использованы следующие пользователи с имеющимися ролями:
Роли пользователей:

|Username | Role  
|:---|:---   
| user | ROLE_USER
| admin | ROLE_ADMIN


**Рестораны**

*Список всех:*
````
curl "$RESTAURANT/rest/restaurants" | json_pp
````

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   167    0   167    0     0  22690      0 --:--:-- --:--:-- --:--:-- 23857
[
   {
      "id" : 100004,
      "name" : "Ресторан1"
   },
   {
      "name" : "Ресторан2",
      "id" : 100005
   },
   {
      "name" : "Ресторан3",
      "id" : 100006
   },
   {
      "id" : 100007,
      "name" : "Рресторан4"
   }
]

*Конкретный ресторан:*
````
curl "$RESTAURANT/rest/restaurants/100004" | json_pp
````

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    40    0    40    0     0    452      0 --:--:-- --:--:-- --:--:--   449
{
   "id" : 100004,
   "name" : "Ресторан1"
}

*Создать новый:*
````
curl -i --user admin:password -H "Content-Type: application/json" -X POST -d '{"name":"ресторан Боброфф"}' "$RESTAURANT/rest/restaurants"
````

HTTP/1.1 201
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=EF5E746D5343B7FE644F9E9DEFA96AD6; Path=/; HttpOnly
Location: http://172.16.1.11:8081/rest/restaurants/100024
Content-Length: 0
Date: Thu, 18 Jan 2018 20:56:38 GMT

*Изменить существующий:*
````
curl -i --user admin:password -H "Content-Type: application/json" -X PUT -d '{"name":"ресторан Баролло"}' "$RESTAURANT/rest/restaurants/100024"
````

HTTP/1.1 204
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=4F72E70D5380A19BEC614516BFA520BB; Path=/; HttpOnly
Date: Thu, 18 Jan 2018 20:59:16 GMT

*Удалить существующий:*
````
curl -i --user admin:password -X DELETE "$RESTAURANT/rest/restaurants/100024"                                                                     HTTP/1.1 204
````

X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=F3C0E0790131702ACEA52FE133C85946; Path=/; HttpOnly
Date: Thu, 18 Jan 2018 21:01:50 GMT

*Найти рестораны по началу названия:*
````
curl -G -X GET --data-urlencode "name=Рест" "$RESTAURANT/rest/restaurants/filter"
````

[{"id":100004,"name":"Ресторан1"},{"id":100005,"name":"Ресторан2"},{"id":100006,"name":"Ресторан3"}]

**Справочиник наминований блюд**

Работа со справочником еды полностью аналогична работе со справочником ресторанов; меняется только адрес:

$RESTAURANT/rest/dishes

**Меню на день**

При редактировании элементов меню (создание, удаление, изменение) дата д.б. не раньше текущей.

*Справочник на дату для конкретного ресторана:*

Текущая дата:
```
curl "$RESTAURANT/rest/restaurants/100004/prices/" |json_pp
```

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   210    0   210    0     0  20272      0 --:--:-- --:--:-- --:--:-- 21000
[
   {
      "id" : 100013,
      "price" : 10.11,
      "dishId" : 100001,
      "dishName" : "блюдо2"
   },
   {
      "dishName" : "блюдо3",
      "id" : 100014,
      "price" : 10.12,
      "dishId" : 100002
   },
   {
      "price" : 10.13,
      "dishId" : 100003,
      "id" : 100015,
      "dishName" : "ББлюдо4"
   }
]

Произвольная дата:
````
curl "$RESTAURANT/rest/restaurants/100004/prices/2018-01-18" |json_pp
````

  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   205    0   205    0     0   4861      0 --:--:-- --:--:-- --:--:--  5000
[
   {
      "dishName" : "блюдо1",
      "price" : 10,
      "dishId" : 100000,
      "id" : 100019
   },
   {
      "dishName" : "блюдо2",
      "price" : 11,
      "id" : 100020,
      "dishId" : 100001
   },
   {
      "price" : 12,
      "dishName" : "блюдо3",
      "dishId" : 100002,
      "id" : 100021
   }
]

*Конкретный пункт меню:*
````
curl "$RESTAURANT/rest/restaurants/100004/prices/2018-01-18/100019" |json_pp
````
  
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    67    0    67    0     0   2739      0 --:--:-- --:--:-- --:--:--  2791
{
   "dishId" : 100000,
   "id" : 100019,
   "dishName" : "блюдо1",
   "price" : 10
}


*Создать:*

Дата должна быть не раньше текущей.
````
curl -i --user admin:password -H "Content-Type: application/json" -X POST -d '{"dishId":100003,"price":"100.02"}' "$RESTAURANT/rest/restaurants/100006/prices/2018-01-19"
````

HTTP/1.1 201
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=0DF35078B4F8FD2AC7BBBEBFDCB48C8E; Path=/; HttpOnly
Location: http://172.16.1.11:8081/rest/restaurants/100006/prices/2018-01-19/100025
Content-Length: 0
Date: Thu, 18 Jan 2018 21:22:39 GMT

*Изменить:*
````
curl -i --user admin:password -H "Content-Type: application/json" -X PUT -d '{"dishId":100002,"price":"111.02"}' "$RESTAURANT/rest/restaurants/100006/prices/2018-01-19/100025"
````

HTTP/1.1 204
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=954907A1B6C0E77386A7B744413742FE; Path=/; HttpOnly
Date: Thu, 18 Jan 2018 21:24:32 GMT

*Удалить:*
````
curl -i --user admin:password -X DELETE "$RESTAURANT/rest/restaurants/100006/prices/2018-01-19/100025"
````

HTTP/1.1 204
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=8C45EE9CB784D17503E4F67E1A4AB41F; Path=/; HttpOnly
Date: Thu, 18 Jan 2018 21:41:43 GMT

**Голосование**

*Проголосовать/переголосовать:*
````
curl -i --user user:password -X POST "$RESTAURANT/rest/restaurants/100006/votes"
````

HTTP/1.1 204
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=D8546736352D80DA96304BFD6649D01A; Path=/; HttpOnly
Date: Thu, 18 Jan 2018 21:59:06 GMT

*Получить кол-во голосов в ресторане на дату*:

Текущую:
````
curl "$RESTAURANT/rest/restaurants/100006/votes"
````
              
1

Произвольную:
````
curl "$RESTAURANT/rest/restaurants/100006/votes/2018-01-18"
````

0


***Замечания:***
* По умолчанию используется база данных HSQLDB с хранением в памяти. При старте подгружаются тестовые данные.
* Используется soft-delete в таблицах restaurant и dishes. Сделано, для сохранения истории. 
  Но это увеличило количество запросов: методы getReference и getOne выполняют запрос к б.д., для проверки не "удалена" ли-запись.  
* Используется справочник содержащий наименования блюд.
* Использование АОП для валидации результатов операций в репозиториях и некоторых входных данных (запрет измения меню в прошлом).
* Принудительно использовал BCrypt в таблице паролей.
* Имеется rest для регистрации нового пользователя. Это сделано для удобства тестирования через curl в процессе разработки. Удалять не стал.
* Вывод кол-ва голосов отстуствует в ТЗ. Так же сделано для удобства тестирования через curl.

***TODO:***
* Разделить сущность пользователь на две:
    * user без числового id-поля
    * таблица с двумя полями - id и username. Первое поле связано с таблицей голосов, второе - username
Это позволит безболезненно подменять одну систему аутентификации на другую, т.к. у интерфейса UserDetails нет поля id, а username - есть. 
* Изменить шифрование паролей на произвольный алгоритм.
* Добавить возможность массовых операций "загрузки" меню на день.