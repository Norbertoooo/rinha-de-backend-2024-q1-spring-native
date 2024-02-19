create table transacao (
    id SERIAL primary key,
    valor integer not null,
    descricao varchar(10) not null,
    realizada_em timestamp not null,
    tipo char not null,
    cliente_id SERIAL  references cliente(id) not null
);

CREATE INDEX idx_transacoes_cliente_id ON transacao (cliente_id);
