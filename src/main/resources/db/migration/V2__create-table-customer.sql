CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    id_user BIGINT NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_modified TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    modified_by VARCHAR(255),
    FOREIGN KEY (id_user) REFERENCES users(id)
);
