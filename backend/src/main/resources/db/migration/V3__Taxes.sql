CREATE TABLE fixed_taxes(
	id CHAR(32) NOT NULL PRIMARY KEY UNIQUE,
	regional_council BIGINT NOT NULL,
	tax_over_work BIGINT NOT NULL,   
	income_tax BIGINT NOT NULL,
	accountant BIGINT NOT NULL,   
	dental_shop BIGINT NOT NULL,   
	transport BIGINT NOT NULL,
	food BIGINT NOT NULL,
	education BIGINT NOT NULL,
	other_fixed_costs BIGINT NOT NULL,
	user_id CHAR(32) NOT NULL,
	created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);

CREATE TABLE variable_taxes(
	id CHAR(32) NOT NULL PRIMARY KEY,
	dental_shop BIGINT NOT NULL,
	prosthetist BIGINT NOT NULL,
	travel BIGINT NOT NULL,
	credit_card BIGINT NOT NULL,
	weekend BIGINT NOT NULL,
	user_id CHAR(32) NOT NULL,
	created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL
);
