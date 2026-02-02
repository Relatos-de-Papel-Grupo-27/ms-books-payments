INSERT INTO payments (order_id, amount, status, payment_date)
VALUES (1, 150.00, 'COMPLETED', CURRENT_TIMESTAMP);

INSERT INTO payments (order_id, amount, status, payment_date)
VALUES (2, 89.99, 'PENDING', CURRENT_TIMESTAMP);

INSERT INTO payments (order_id, amount, status, payment_date)
VALUES (3, 45.50, 'FAILED', CURRENT_TIMESTAMP);