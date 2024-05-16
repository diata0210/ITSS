CREATE DATABASE IF NOT EXISTS sales_db;
USE sales_db;

CREATE TABLE IF NOT EXISTS Users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  upassword VARCHAR(255),
  role INT
);

CREATE TABLE IF NOT EXISTS Sites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    address VARCHAR(30),
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    type VARCHAR(30),
    price INT
);

CREATE TABLE IF NOT EXISTS Orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  finalPrice INT,
  descriptions VARCHAR(1000),
  sendDate DATE,
  arriveDate DATE
);

CREATE TABLE IF NOT EXISTS OrderDetails (
  productID INT,
  orderID INT,
  quantity INT,
  FOREIGN KEY (orderID) REFERENCES Orders(id),
  FOREIGN KEY (productID) REFERENCES Products(id)
);

CREATE TABLE IF NOT EXISTS ProductSites (
  id INT AUTO_INCREMENT PRIMARY KEY,
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

INSERT INTO Users (username, upassword, role) VALUES
('user1', 'password1', 1),
('user2', 'password2', 2),
('user3', 'password3', 3),
('user4', 'password4', 4),
('user5', 'password5', 4),
('user6', 'password6', 4),
('user7', 'password7', 4),
('user8', 'password8', 4);

INSERT INTO Sites (name, address, userID) VALUES
('Site A', 'Address A', 4),
('Site B', 'Address B', 5),
('Site C', 'Address C', 6),
('Site D', 'Address D', 7),
('Site E', 'Address E', 8);

INSERT INTO Products (name, type, price) VALUES
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
