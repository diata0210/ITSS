CREATE DATABASE IF NOT EXISTS sales_db;
USE sales_db;

CREATE TABLE IF NOT EXISTS Users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  upassword VARCHAR(255),
  roles INT
);

CREATE TABLE IF NOT EXISTS Sites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(30),
    saddress VARCHAR(30),
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(30),
    ptype VARCHAR(30),
    price INT
);

CREATE TABLE IF NOT EXISTS Orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  finalPrice INT,
  descriptions VARCHAR(1000),
  sendDate DATE,
  arriveDate DATE,
  ostatus INT
);

CREATE TABLE IF NOT EXISTS OrderDetails (
  productID INT,
  orderID INT,
  quantity INT,
  FOREIGN KEY (orderID) REFERENCES Orders(id),
  FOREIGN KEY (productID) REFERENCES Products(id)
);

CREATE TABLE IF NOT EXISTS ProductSites (
  siteID INT,
  productID INT,
  quantity INT,
  FOREIGN KEY (siteID) REFERENCES Sites(id),
  FOREIGN KEY (productID) REFERENCES Products(id)
);

CREATE TABLE IF NOT EXISTS SiteOrders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  orderID INT,
  siteID INT,
  FOREIGN KEY (siteID) REFERENCES Sites(id),
  FOREIGN KEY (orderID) REFERENCES Orders(id)
);

CREATE TABLE IF NOT EXISTS SiteOrderDetails (
  siteOrderID INT,
  productID INT,
  quantity INT,
  FOREIGN KEY (siteOrderID) REFERENCES SiteOrders(id),
  FOREIGN KEY (productID) REFERENCES Products(id)
);

CREATE TABLE IF NOT EXISTS Vehicles(
  id INT AUTO_INCREMENT PRIMARY KEY,
  vname VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS VehicleToSites (
  vehicleID INT,
  siteID INT,
  dateTrans INT,
  FOREIGN KEY (vehicleID) REFERENCES Vehicles(id),
  FOREIGN KEY (siteID) REFERENCES Sites(id)
);

INSERT INTO Users (username, upassword, roles) VALUES
('user1', 'password1', 1),
('user2', 'password2', 2),
('user3', 'password3', 3),
('user4', 'password4', 4),
('user5', 'password5', 4),
('user6', 'password6', 4),
('user7', 'password7', 4),
('user8', 'password8', 4);

INSERT INTO Sites (sname, saddress, userID) VALUES
('Site A', 'Address A', 4),
('Site B', 'Address B', 5),
('Site C', 'Address C', 6),
('Site D', 'Address D', 7),
('Site E', 'Address E', 8);

INSERT INTO Products (pname, ptype, price) VALUES
('Laptop', 'Electronics', 1000),
('Smartphone', 'Electronics', 800),
('Headphones', 'Electronics', 150),
('T-shirt', 'Clothing', 20),
('Jeans', 'Clothing', 40),
('Running Shoes', 'Footwear', 80),
('Backpack', 'Accessories', 50),
('Watch', 'Accessories', 200),
('Sunglasses', 'Accessories', 30),
('Coffee Maker', 'Appliances', 120),
('Keyboard', 'Electronics', 50),
('Mouse', 'Electronics', 30),
('Monitor', 'Electronics', 300),
('Desk Lamp', 'Home Accessories', 40),
('Couch', 'Furniture', 500),
('Dining Table', 'Furniture', 300),
('Office Chair', 'Furniture', 200),
('Running Shorts', 'Clothing', 15),
('Hiking Boots', 'Footwear', 100),
('Handbag', 'Accessories', 70),
('Earbuds', 'Electronics', 80),
('Smartwatch', 'Electronics', 250),
('Fitness Tracker', 'Electronics', 120),
('Blender', 'Appliances', 80);

INSERT INTO Vehicles (vname) VALUES
('Ship'),
('Air');

INSERT INTO VehicleToSites (vehicleID, siteID, dateTrans) VALUES
(1, 1, 10),
(2, 1, 4),
(1, 2, 7),
(2, 2, 2),
(1, 3, 5),
(2, 3, 3),
(1, 4, 4),
(2, 4, 2),
(1, 5, 8),
(2, 5, 5);

INSERT INTO ProductSites (siteID, productID, quantity) VALUES
(1, 1, 20), (1, 2, 30), (1, 3, 50), (1, 4, 25), (1, 5, 40),
(1, 6, 60), (1, 7, 15), (1, 8, 20), (1, 9, 35), (1, 10, 18),
(2, 13, 22), (2, 14, 30), (2, 15, 10), (2, 16, 15), (2, 17, 25),
(2, 18, 18), (2, 19, 40), (2, 20, 12), (2, 21, 35), (2, 22, 28),
(3, 23, 15), (3, 21, 20), (3, 20, 28), (3, 19, 10), (3, 18, 32),
(3, 17, 20), (3, 12, 25), (3, 15, 30), (3, 1, 15), (3, 4, 18),
(4, 5, 40), (4, 6, 60), (4, 7, 15), (4, 8, 20), (4, 9, 35),
(4, 10, 18), (4, 11, 28), (4, 12, 45), (4, 13, 22), (4, 14, 30),
(5, 15, 10), (5, 16, 15), (5, 17, 25), (5, 18, 18), (5, 19, 40),
(5, 20, 12), (5, 21, 35), (5, 22, 28), (5, 23, 15), (5, 24, 20);

INSERT INTO Orders (finalPrice, descriptions, sendDate, arriveDate,ostatus)
VALUES
    (0, NULL, '2024-05-10', '2024-05-20', 0),
    (0, NULL, '2024-05-13', NULL, 1),
    (0, NULL, '2024-05-16', NULL, 2),
    (0, NULL, '2024-05-17', NULL, 3),
    (0, NULL, '2024-05-15', NULL, 4);

INSERT INTO OrderDetails (productID, orderID, quantity)
VALUES
    (1, 1, 2),
    (2, 1, 3),
    (8, 1, 7),
    (3, 1, 8),
    (9, 1, 3),
    (15, 1, 7),
    (17, 1, 15),
    (22, 1, 10),
    (4, 2, 10),
    (5, 2, 17),
    (6, 2, 6),
    (7, 2, 3),
    (11, 2, 30),
    (12, 2, 10),
    (15, 2, 16),
    (16, 2, 7),
    (8, 3, 17),
    (3, 3, 12),
    (9, 3, 20),
    (15, 3, 10),
    (17, 3, 25),
    (22, 3, 30),
     (4, 4, 15),
    (5, 4, 25),
    (6, 4, 18),
    (7, 4, 30),
    (11, 4, 20),
    (12, 4, 10),
    (15, 4, 20),
    (16, 4, 28),
    (8, 5, 20),
    (3, 5, 30),
    (9, 5, 25),
    (15, 5, 12),
    (17, 5, 18),
    (22, 5, 15);
UPDATE Orders
SET finalPrice = (
SELECT SUM(Products.price * OrderDetails.quantity) 
FROM OrderDetails
JOIN Products ON OrderDetails.productID = Products.id
WHERE Orders.id = OrderDetails.orderID
);