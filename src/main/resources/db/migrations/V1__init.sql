CREATE SCHEMA IF NOT EXISTS  contacts_schema;

CREATE TABLE IF NOT EXISTS contacts_schema.contacts
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL

);

-- Вставка данных в таблицу contacts
INSERT INTO contacts (first_name, last_name, email, phone) VALUES
    ('Сергей', 'Шагаев', 'sergey.a.shagaev@mail.ru', '+70123456789'),
    ('Сергей', 'Шагаев', 'sergey.a.shagaev@mail.ru', '+70123456789'),
    ('Сергей', 'Шагаев', 'sergey.a.shagaev@mail.ru', '+70123456789'),
    ('Сергей', 'Шагаев', 'sergey.a.shagaev@mail.ru', '+70123456789'),
    ('Сергей', 'Шагаев', 'sergey.a.shagaev@mail.ru', '+70123456789');
