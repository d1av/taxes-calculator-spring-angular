CREATE TABLE hour_values(
	id CHAR(32) NOT NULL PRIMARY KEY UNIQUE,
	expected_salary BIGINT NOT NULL,
	hour_value BIGINT NOT NULL,
	days_of_work BIGINT NOT NULL,
	user_id CHAR(32) NOT NULL,
	created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);