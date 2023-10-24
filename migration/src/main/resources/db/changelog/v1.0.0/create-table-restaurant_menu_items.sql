CREATE TABLE IF NOT EXISTS restaurant_menu_items
(
    id            BIGSERIAL,
    name          VARCHAR(100) NOT NULL ,
    price         BIGINT NOT NULL ,
    image         VARCHAR(256),
    description   TEXT,
    restaurant_id BIGINT NOT NULL ,
    CONSTRAINT restaurant_menu_items_pk PRIMARY KEY (id),
    CONSTRAINT restaurant_fk FOREIGN KEY (restaurant_id) references restaurants (id)
);

COMMENT ON TABLE restaurant_menu_items IS 'Меню в ресторане';
COMMENT ON COLUMN restaurant_menu_items.id IS 'Номер блюда в меню';
COMMENT ON COLUMN restaurant_menu_items.name IS 'Название блюда';
COMMENT ON COLUMN restaurant_menu_items.price IS 'Цена блюда';
COMMENT ON COLUMN restaurant_menu_items.image IS 'Изображение блюда';
COMMENT ON COLUMN restaurant_menu_items.description IS 'Описание блюда';
COMMENT ON COLUMN restaurant_menu_items.restaurant_id IS 'Номер ресторана данного блюда';