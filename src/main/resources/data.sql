/*
INSERT INTO companies (id, created_at, updated_at, name) VALUES ('1', '2020-02-04 00:00:00', '2020-02-05 00:00:00','Forticas');
INSERT INTO companies (id, created_at, updated_at, name) VALUES ('2', '2020-02-04 00:00:00', '2020-02-05 00:00:00','Company2');
INSERT INTO companies (id, created_at, updated_at, name) VALUES ('3', '2020-02-04 00:00:00', '2020-02-05 00:00:00','Company3');




INSERT INTO employees (id, created_at, updated_at, account_enabled, email, first_name, last_name, password, username, company_id) VALUES ('1', '2020-10-13 10:44:14', '2020-10-13 10:44:14', b'1', 'super_admin@forticas.com', 'super_admin', 'super_admin', '$2a$10$EFYJR.OxAy5na9H2EY8X7OLfqMLMTLYExc.85m5MRDXkJa670ssWC', 'super_admin', '1');
INSERT INTO employees (id, created_at, updated_at, account_enabled, email, first_name, last_name, password, username, company_id) VALUES ('2', '2020-10-13 10:44:14', '2020-10-13 10:44:14', b'1', 'admin_company2@forticas.com', 'admin_company2', 'admin_company2', '$2a$10$EFYJR.OxAy5na9H2EY8X7OLfqMLMTLYExc.85m5MRDXkJa670ssWC', 'admin_company2', '2');
INSERT INTO employees (id, created_at, updated_at, account_enabled, email, first_name, last_name, password, username, company_id) VALUES ('3', '2020-10-13 10:44:14', '2020-10-13 10:44:14', b'1', 'admin_company3@forticas.com', 'admin_company3', 'admin_company3', '$2a$10$EFYJR.OxAy5na9H2EY8X7OLfqMLMTLYExc.85m5MRDXkJa670ssWC', 'admin_company3', '3');

INSERT INTO roles (id, name) VALUES (1,'ROLE_SUPER_ADMIN');
INSERT INTO roles (id, name) VALUES (2,'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (3,'ROLE_USER');
INSERT INTO roles (id, name) VALUES (4,'ROLE_SCREEN_USER');

INSERT INTO roles (id, name) VALUES (5,'CUSTOMER_GUEST');

INSERT INTO employee_roles (employee_id, role_id) VALUES ('1', '1');
INSERT INTO employee_roles (employee_id, role_id) VALUES ('2', '2');
INSERT INTO employee_roles (employee_id, role_id) VALUES ('3', '2');

INSERT INTO networks (id, created_at, updated_at, created_by, updated_by, country, gps_lat, gps_long, reference, company_id) VALUES ('3', '2020-12-10 00:59:07', '2020-12-10 00:59:07', '1', '1', 'Tunisia', '0', '0', 'Custom Network', '1');

*/