CREATE TABLE users_roles(
	user_id CHAR(32) NOT NULL,
	role_id CHAR(32) NOT NULL,
	CONSTRAINT idx_rou_role_user UNIQUE (user_id, role_id),
	CONSTRAINT fk_rou_role_id FOREIGN KEY (role_id) REFERENCES roles (id),
	CONSTRAINT fk_rou_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
