## Приложение для заказа и доставки еды из ресторана
#### В данный проект входят 4 сервиса необходимые для выполнения заказа

##### Сервис регистрации заказов (order-service)
- Прием заказа и передача его на приготовление в ресторан
- Получение push-уведовления о ключевых изменениях статуса заказа

##### Кухонный сервис (kitchen-service)
- Сервис умеет добавлять, изменять  и отображать список имеющихся блюд
- Прием заказов с последующим уведомлением клиента о статусе заказа

##### Курьерский сервис (delivery-service)
- Обрабатывает заказы подбирая курьеров в легкой доступности
- Дальнейшее подтверждение о доставке заказа

##### Сервис уведомлений (notification-service)
- Данный сервис позволяет своевременно отследить статус заказа
- Каждый заказ обязательно дойдет до кухни, так как используется брокер сообщений RABBIT MQ
(Данная технология находится на этапе внедрения, некоторые функции могут не работать)
Взаимодействие между сервисами происходит посредствам FEIGN

##### FAQ по настройке и запуску данного микросервиса
1. В данном проекте реализован модуль migration работающий по технологии LiquiBase
Необходимо запустить его либо командой в строке mvn liquibase:update либо командой в плагинах MAVEN.
Убедитесь что пароли и логин совпадают в application.yml.
2. Запустите каждый из модулей Order-service, Kitchen-service, Delivery-service.
3. В папке postman имеются файлы для HTTP-запросов для тестирования и отработки приложении.
4. В файле Food-service ---RoadOrder имеются запросы с последовательным изменением статусов заказа.
5. Обращаю особое внимание на правильность заполнения логина и пароля в application.yml.
