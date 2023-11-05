CREATE TABLE IF NOT EXISTS order_items
(
    id                   BIGSERIAL,
    quantity             INT NOT NULL ,
    price                BIGINT NOT NULL ,
    order_id             BIGINT,
    restaurant_menu_item BIGINT NOT NULL ,
    CONSTRAINT order_items_pk PRIMARY KEY (id),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id) references orders (id),
    CONSTRAINT restaurant_menu_item_fk FOREIGN KEY (restaurant_menu_item) references restaurant_menu_items (id)
);

COMMENT ON TABLE order_items IS 'Список блюд в заказе';
COMMENT ON COLUMN order_items.id IS 'Номер блюда';
COMMENT ON COLUMN order_items.quantity IS 'Количество данных блюд в заказе';
COMMENT ON COLUMN order_items.price IS 'Общая стоймость данных блюд';
COMMENT ON COLUMN order_items.order_id IS 'Номер заказа';
COMMENT ON COLUMN order_items.restaurant_menu_item IS 'Номер блюда в меню';