INSERT INTO
    customers (name, email)
VALUES
    ('customer 1', 'customer1@test.com'),
    ('customer 2', 'customer2@test.com'),
    ('customer 3', 'customer3@test.com'),
    ('customer 4', 'customer4@test.com'),
    ('customer 5', 'customer5@test.com'),
    ('customer 6', 'customer6@test.com'),
    ('customer 7', 'customer7@test.com'),
    ('customer 8', 'customer8@test.com'),
    ('customer 9', 'customer9@test.com'),
    ('customer 10', 'customer10@test.com');

INSERT INTO
    applications (name, monthly_cost)
VALUES
    ('app 1', 10.0),
    ('app 2', 20.0),
    ('app 3', 30.0),
    ('app 4', 40.0),
    ('app 5', 50.0),
    ('app 6', 60.0),
    ('app 7', 70.0),
    ('app 8', 80.0),
    ('app 9', 90.0),
    ('app 10', 100.0);

INSERT INTO
    signatures (application_id, customer_id, beginning_term, end_term)
VALUES
    (1, 1, '2024-05-01', '2024-06-01'),
    (1, 6, '2024-05-10', '2024-08-17'),
    (2, 7, '2024-04-01', '2024-05-01'),
    (3, 8, '2024-04-15', '2024-05-14'),
    (4, 9, '2024-05-01', '2024-09-30'),
    (5, 10, '2024-03-08', '2024-05-08'),
    (1, 10, '2024-05-01', '2024-05-08'),
    (2, 2, '2024-05-10', '2024-06-17'),
    (3, 3, '2024-05-01', '2024-06-30'),
    (4, 4, '2024-05-15', '2024-07-14'),
    (5, 5, '2024-03-01', '2024-04-30'),
    (10, 6, '2024-01-15', '2024-03-14'),
    (9, 7, '2024-03-01', '2024-04-30'),
    (8, 8, '2024-02-15', '2024-03-14'),
    (7, 9, '2024-05-02', '2024-05-30'),
    (6, 10, '2024-04-28', '2024-10-14');

INSERT INTO
    users (username, password)
SELECT
    'admin', 'password'
WHERE NOT EXISTS (SELECT 1 FROM users);
