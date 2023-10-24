CREATE TABLE IF NOT EXISTS customers
(
    id      BIGSERIAL,
    phone   VARCHAR(15) NOT NULL,
    email   VARCHAR(80) NOT NULL,
    address TEXT,
    CONSTRAINT customers_pk PRIMARY KEY (id),
	UNIQUE(phone, email)
);


COMMENT ON TABLE customers IS 'Список клиентов';
COMMENT ON COLUMN customers.id IS 'Номер клиента';
COMMENT ON COLUMN customers.phone IS 'Номер телефона клиента';
COMMENT ON COLUMN customers.email IS 'Электронная почта клиента';
COMMENT ON COLUMN customers.address IS 'Адрес клиента';

