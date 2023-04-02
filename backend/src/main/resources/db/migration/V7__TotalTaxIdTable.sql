CREATE TABLE total_taxes(
    tax_id CHAR(32) UNIQUE PRIMARY KEY,
    fixed_tax_id CHAR(32),
    variable_tax_id CHAR(32),
    hour_value_id CHAR(32),
    user_id CHAR(32) UNIQUE,
    CONSTRAINT total_taxes_id_unique UNIQUE (tax_id)
);

