## Приложение для заказа и доставки еды из ресторана
#### В данный проект входят 4 сервиса необходимые для выполнения заказа

##### Сервис регистрации заказов (order-service)
- Прием заказа и передача его на приготовление в ресторан
- Получение push-уведовления о ключевых статусах изменения статуса заказа




Взаимодействие между сервисами происходит посредствам FEIGN 
PUSH уведомления используют технологию обмена сообщениями RABBITMQ

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
##### Клиентский сервис
- Передача заявки в ресторан через сайт или мобильно приложение
- Обработка заявки и передача ее соответствующему ресторану
- Получение подтверждения о приеме заказа от ресторана

##### Сервис ресторана
- Отображение списка доступных блюд на сайте или в приложении
- Отправка запроса на заказ курьерам через систему push-уведомлений
- Прием заказа от клиента и его обработка
- Подготовка к доставке и упаковка заказа

##### Курьерский сервис
- Прием заявки от ресторана на доставку заявки клиенту
- Обработка заявки и отправка ее курьеру с указанием адреса доставки
- Подтверждение передачи заказа клиенту

Последовательность запуска приложений
Order-service
Kitchen-service
Delivery-service