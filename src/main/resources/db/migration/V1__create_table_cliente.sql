create table cliente
(
    id     SERIAL primary key,
    limite integer not null,
    saldo  integer not null
);

CREATE INDEX idx_cliente_id ON cliente (id);