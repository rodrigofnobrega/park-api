CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  role VARCHAR(30) NOT NULL,
  date_created TIMESTAMP,
  date_modified TIMESTAMP,
  created_by VARCHAR(255),
  modified_by VARCHAR(255)
);
