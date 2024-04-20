CREATE TABLE parking_space (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(4) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_modified TIMESTAMP NOT NULL,
    created_by VARCHAR(255),
    modified_by VARCHAR(255)
);
