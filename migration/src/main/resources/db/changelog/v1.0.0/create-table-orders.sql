CREATE TABLE IF NOT EXISTS orders
(
    id            BIGSERIAL,
    status        VARCHAR(20),
    timestamp     TIMESTAMP NOT NULL,
    customer_id   BIGINT,
    courier_id    BIGINT,
    restaurant_id BIGINT,
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT customer_fk FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT courier_fk FOREIGN KEY (courier_id) REFERENCES couriers (id),
    CONSTRAINT restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);

COMMENT ON TABLE orders IS 'Список заказов';
COMMENT ON COLUMN orders.id IS 'Номер заказа';
COMMENT ON COLUMN orders.status IS 'Статус заказа';
COMMENT ON COLUMN orders.timestamp IS 'Дата и время совершения заказа';
COMMENT ON COLUMN orders.customer_id IS 'Номер клиента';
COMMENT ON COLUMN orders.courier_id IS 'Номер курьера';
COMMENT ON COLUMN orders.restaurant_id IS 'Номер ресторана';

