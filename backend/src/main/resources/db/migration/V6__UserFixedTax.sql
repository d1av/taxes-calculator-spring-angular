CREATE TABLE users_fixedtaxes(
	user_id CHAR(32) NOT NULL,
	fixed_tax_id CHAR(32) NOT NULL,
	CONSTRAINT idx_ftu_tax_user UNIQUE (user_id, fixed_tax_id),
	CONSTRAINT fk_ftu_fixed_tax_id FOREIGN KEY (fixed_tax_id) REFERENCES fixed_tax (id),
	CONSTRAINT fk_ftu_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);