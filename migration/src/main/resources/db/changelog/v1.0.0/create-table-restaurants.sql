CREATE TABLE IF NOT EXISTS restaurants
(
    id      BIGSERIAL,
    address TEXT NOT NULL ,
    name    VARCHAR(100) NOT NULL ,
    status  VARCHAR(30),
    CONSTRAINT restaurants_pk PRIMARY KEY (id)
);


COMMENT ON TABLE restaurants IS 'Список ресторанов';
COMMENT ON COLUMN restaurants.id IS 'Номер клиента';
COMMENT ON COLUMN restaurants.address IS 'Адрес ресторана';
COMMENT ON COLUMN restaurants.name IS 'Название ресторана';
COMMENT ON COLUMN restaurants.status IS 'Статус ресторана';

