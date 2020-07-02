CREATE TABLE PAYMENTS
(
  paymentId int NOT NULL PRIMARY KEY,
  paymentType varchar(20),
  paymentStatus varchar(20),
  description varchar(255),
  createdDate DATE
);

INSERT INTO PAYMENTS (paymentId, description, paymentType, paymentStatus) VALUES (1, 'type :BTOB', 'INTERNATIONAL', 'COMPLETED');
INSERT INTO PAYMENTS (paymentId, description, paymentType, paymentStatus) VALUES (2, 'SEPA', 'COMPLETED', 'USA');
INSERT INTO PAYMENTS (paymentId, description, paymentType, paymentStatus) VALUES (3, 'SEPA', 'COMPLETED', 'Finland');
INSERT INTO PAYMENTS (paymentId, description, paymentType, paymentStatus) VALUES (4, 'BANK_TO_BANK', 'COMPLETED', 'USA');