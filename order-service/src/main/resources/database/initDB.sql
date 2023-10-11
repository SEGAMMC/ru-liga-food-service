CREATE TABLE IF NOT EXISTS couriers
(
    id          Bigserial PRIMARY KEY,
    phone       varchar(15) not null,
    status      varchar(20) not null,
    coordinates varchar(50) not null
);

CREATE TABLE IF NOT EXISTS customers
(
    id      Bigserial PRIMARY KEY,
    phone   varchar(15)  not null,
    email   varchar(80)  not null,
    address varchar(100) not null
);

CREATE TABLE IF NOT EXISTS orders
(
    id          Bigserial PRIMARY KEY,
    customer_id Bigserial   not null,
    status      varchar(20) not null,
    courier_id  Bigserial,
    timestamp   timestamp   NOT NULL,
    FOREIGN KEY (customer_id) references food_service.public.customers (id),
    FOREIGN KEY (courier_id) references food_service.public.couriers (id)
);

CREATE TABLE IF NOT EXISTS order_items
(
    id                   Bigserial PRIMARY KEY,
    order_id             Bigserial not null,
    restaurant_menu_item varchar(100),
    price                double precision,
    quantity             int,
    FOREIGN KEY (order_id) references food_service.public.orders (id)

);

CREATE TABLE IF NOT EXISTS restaurants
(
    id      Bigserial PRIMARY KEY,
    address varchar(100) not null,
    status  varchar(20)  not null
);

CREATE TABLE IF NOT EXISTS restaurant_menu_items
(
    id            Bigserial PRIMARY KEY,
    order_id      Bigserial    not null,
    restaurant_id Bigserial    not null,
    name          varchar(100) not null,
    price         double precision,
    image_url     varchar(100) not null,
    description   varchar(200) not null,
    FOREIGN KEY (order_id) references food_service.public.orders (id),
    FOREIGN KEY (restaurant_id) references food_service.public.restaurants (id)
);
