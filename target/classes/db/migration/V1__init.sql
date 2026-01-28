CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE cards (
    id BIGSERIAL PRIMARY KEY,
    card_number_encrypted TEXT NOT NULL,
    owner_id BIGINT NOT NULL,
    expiry_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    balance NUMERIC(19,2) NOT NULL,
    CONSTRAINT fk_cards_user FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE transfers (
    id BIGSERIAL PRIMARY KEY,
    from_card_id BIGINT NOT NULL,
    to_card_id BIGINT NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_from_card FOREIGN KEY (from_card_id) REFERENCES cards(id),
    CONSTRAINT fk_to_card FOREIGN KEY (to_card_id) REFERENCES cards(id)
);
