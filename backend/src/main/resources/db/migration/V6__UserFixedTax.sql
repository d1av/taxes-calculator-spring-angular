CREATE TABLE users_fixedtaxes(
	user_id CHAR(32) NOT NULL,
	fixed_tax_id CHAR(32) NOT NULL,
	CONSTRAINT idx_ftu_tax_user UNIQUE (user_id, fixed_tax_id),
	CONSTRAINT fk_ftu_fixed_tax_id FOREIGN KEY (fixed_tax_id) REFERENCES fixed_taxes (id),
	CONSTRAINT fk_ftu_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE users_hourvalues(
	user_id CHAR(32) NOT NULL,
	hour_value_id CHAR(32) NOT NULL,
	CONSTRAINT idx_hvu_hour_user UNIQUE (user_id,hour_value_id),
	CONSTRAINT fk_hvu_hour_value_id FOREIGN KEY (hour_value_id) REFERENCES hour_values (id),
	CONSTRAINT fk_hvu_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);