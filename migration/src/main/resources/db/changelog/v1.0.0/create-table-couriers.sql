CREATE TABLE IF NOT EXISTS couriers
(
    id          BIGSERIAL,
    phone       VARCHAR(15) NOT NULL,
    status      VARCHAR(20) NOT NULL,
    coordinates VARCHAR(50),
    CONSTRAINT couriers_pk PRIMARY KEY (id),
	UNIQUE (phone)
);

COMMENT ON TABLE couriers IS 'Список курьеров';
COMMENT ON COLUMN couriers.id IS 'Номер курьера';
COMMENT ON COLUMN couriers.phone IS 'Номер телефона курьера';
COMMENT ON COLUMN couriers.status IS 'Статус курьера';
COMMENT ON COLUMN couriers.coordinates IS 'Координаты курьера';