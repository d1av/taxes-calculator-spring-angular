CREATE TABLE total_taxes(
    tax_id INT AUTO_INCREMENT PRIMARY KEY,
    fixed_tax_id CHAR(32),
    variable_tax_id CHAR(32),
    hour_value_id CHAR(32),
    user_id CHAR(32),
    CONSTRAINT total_taxes_id_unique UNIQUE (tax_id)
);

INSERT INTO users (id, name, password, active, created_at, updated_at, deleted_at)
VALUES ('998675f558eb45d1a455c8e835350d2a', 'admindavi', 
'$2a$10$jEG2.f0ZfPuIvDN2QIil0.dRdT4X4v2hA1cDF8Gum1EbO4ydbAky2', 1, NOW(), NOW(), null);

INSERT INTO roles(id,authority,created_at,updated_at)
VALUES ('0f10cc17a85748d1836dc4665567bc83', 'ROLE_ADMIN', NOW(), NOW()),
('dfb6fb0d85cc4cb0a46abb711d6bb7e0', 'ROLE_MEMBER', NOW(), NOW());

INSERT INTO users_roles(user_id,role_id)
VALUES ('998675f558eb45d1a455c8e835350d2a','0f10cc17a85748d1836dc4665567bc83'),
 ('998675f558eb45d1a455c8e835350d2a','dfb6fb0d85cc4cb0a46abb711d6bb7e0');