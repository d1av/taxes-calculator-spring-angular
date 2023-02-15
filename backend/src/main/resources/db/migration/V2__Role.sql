CREATE TABLE roles(
	id CHAR(32) NOT NULL PRIMARY KEY,
	authority VARCHAR(60) NOT NULL,
    user_id CHAR(32) NULL,
	created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES users (id)  
);