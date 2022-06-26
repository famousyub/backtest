INSERT IGNORE INTO companies (id, created_at, updated_at, name) VALUES ('2', '2020-02-04 00:00:00', '2020-02-05 00:00:00','Forticas');

INSERT IGNORE INTO employees (id, created_at, updated_at, account_enabled, email, first_name, last_name, password, username, company_id) VALUES ('2', '2020-10-13 10:44:14', '2020-10-13 10:44:14', b'1', 'forticas@forticas.com', 'forticas', 'forticas', ' $2a$10$EFYJR.OxAy5na9H2EY8X7OLfqMLMTLYExc.85m5MRDXkJa670ssWC', 'forticas.forticas', '1');

INSERT IGNORE INTO roles (id, name) VALUES (5,'CUSTOMER_GUEST');
INSERT IGNORE INTO employee_roles (employee_id, role_id) VALUES ('2', '1');

INSERT IGNORE INTO networks (id, created_at, updated_at, created_by, updated_by, country, gps_lat, gps_long, reference, company_id) VALUES ('3', '2020-12-10 00:59:07', '2020-12-10 00:59:07', '1', '1', 'Tunisia', '0', '0', 'Custom Network', '1');

