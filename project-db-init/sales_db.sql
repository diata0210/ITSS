CREATE DATABASE IF NOT EXISTS sales_db;
USE sales_db;

CREATE TABLE IF NOT EXISTS Users (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  upassword VARCHAR(255),
  roles INT
);

CREATE TABLE IF NOT EXISTS Sites (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(30),
    saddress VARCHAR(30),
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users(ID)
);

CREATE TABLE IF NOT EXISTS Products (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(30),
    punit VARCHAR(30),
    price INT
);

CREATE TABLE IF NOT EXISTS Orders (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  finalPrice INT,
  descriptions VARCHAR(1000),
  -- Ngày gửi Order
  sendDate DATE,
  -- Ngày nhận hàng
  arriveDate DATE,
  -- Ngày nhận hàng mong muốn
  deliveryDate DATE 
);

CREATE TABLE IF NOT EXISTS OrderDetails (
  productID INT,
  orderID INT,
  quantity INT,
  PRIMARY KEY (productID, orderID),
  FOREIGN KEY (orderID) REFERENCES Orders(ID),
  FOREIGN KEY (productID) REFERENCES Products(ID)
);

CREATE TABLE IF NOT EXISTS ProductSites (
  siteID INT,
  productID INT,
  quantity INT,
  PRIMARY KEY (siteID, productID),
  FOREIGN KEY (siteID) REFERENCES Sites(ID),
  FOREIGN KEY (productID) REFERENCES Products(ID)
);

CREATE TABLE IF NOT EXISTS SiteOrders (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  orderID INT,
  siteID INT,
  finalPrice DECIMAL(10, 2),
  oStatus INT,
  FOREIGN KEY (siteID) REFERENCES Sites(ID),
  FOREIGN KEY (orderID) REFERENCES Orders(ID)
);

CREATE TABLE IF NOT EXISTS SiteOrderDetails (
  siteOrderID INT,
  productID INT,
  quantity INT,
  PRIMARY KEY (siteOrderID, productID),
  FOREIGN KEY (siteOrderID) REFERENCES SiteOrders(ID),
  FOREIGN KEY (productID) REFERENCES Products(ID)
);

CREATE TABLE IF NOT EXISTS Vehicles(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  vname VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS VehicleSites (
  vehicleID INT,
  siteID INT,
  dateTrans INT,
  FOREIGN KEY (vehicleID) REFERENCES Vehicles(ID),
  FOREIGN KEY (siteID) REFERENCES Sites(ID)
);

INSERT INTO Users (username, upassword, roles) VALUES
('bpbh', '123456', 1),
('bpdh', '123456', 2),
('Admmyan', '123456', 3),
('htqlk', '123456', 4),
('AdmBankok', '123456', 3),
('AdmQuanCh', '123456', 3),
('AdmAnGiang', '123456', 3),
('AdmHaNoi', '123456', 3),


-- 1: Bộ phận bán hàng
-- 2: Bộ phận đặt hàng
-- 3: Site
-- 4: Hệ thống quản lý kho

INSERT INTO Sites (sname, saddress, userID) VALUES
('QuanChau', 'China', 6),
('PhuTan', 'VietNam', 7),
('SmfiYm', 'MyanMa', 3),
('Colosseum', 'Bangkok', 5),
('Hanoi', 'Hanoi', 8);

INSERT INTO Products (pname, ptype, price, punit) VALUES
('Laptop', 'Electronics', 1000, 'piece'),
('Smartphone', 'Electronics', 800, 'piece'),
('Headphones', 'Electronics', 150, 'piece'),
('T-shirt', 'Clothing', 20, 'piece'),
('Jeans', 'Clothing', 40, 'piece'),
('Running Shoes', 'Footwear', 80, 'pair'),
('Backpack', 'Accessories', 50, 'piece'),
('Watch', 'Accessories', 200, 'piece'),
('Sunglasses', 'Accessories', 30, 'piece'),
('Coffee Maker', 'Appliances', 120, 'piece'),
('Keyboard', 'Electronics', 50, 'piece'),
('Mouse', 'Electronics', 30, 'piece'),
('Monitor', 'Electronics', 300, 'piece'),
('Desk Lamp', 'Home Accessories', 40, 'piece'),
('Couch', 'Furniture', 500, 'piece'),
('Dining Table', 'Furniture', 300, 'piece'),
('Office Chair', 'Furniture', 200, 'piece'),
('Running Shorts', 'Clothing', 15, 'piece'),
('Hiking Boots', 'Footwear', 100, 'pair'),
('Handbag', 'Accessories', 70, 'piece'),
('Earbuds', 'Electronics', 80, 'piece'),
('Smartwatch', 'Electronics', 250, 'piece'),
('Fitness Tracker', 'Electronics', 120, 'piece'),
('Blender', 'Appliances', 80, 'piece');

INSERT INTO Vehicles (vname) VALUES
('Ship'),
('Air');

INSERT INTO VehicleSites (vehicleID, siteID, dateTrans) VALUES
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

INSERT INTO Orders (finalPrice, descriptions, sendDate, arriveDate, deliveryDate)
VALUES
    (0, NULL, '2024-05-10', '2024-05-20', '2024-05-15'),
    (0, NULL, '2024-05-13', NULL, '2024-05-20'),
    (0, NULL, '2024-05-16', NULL, '2024-05-25'),
    (0, NULL, '2024-05-17', NULL, '2024-05-25'),
    (0, NULL, '2024-05-15', NULL, '2024-05-20');

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

INSERT INTO SiteOrders (orderID, siteID, finalPrice, oStatus)
VALUES
    (1, 1, 0, 4),  -- Order 1 tại Site 1
    (1, 3, 0, 4),  -- Order 1 tại Site 3
    (1, 5, 0, 4),  -- Order 1 tại Site 5
    (2, 2, 0, 2),  -- Order 2 tại Site 2
    (2, 4, 0, 1),  -- Order 2 tại Site 4
    (2, 5, 0, 3),  -- Order 2 tại Site 5
    (3, 1, 0, 5),  -- Order 3 tại Site 1
    (3, 2, 0, 3),  -- Order 3 tại Site 2
    (3, 4, 0, 2),  -- Order 3 tại Site 4
    (4, 3, 0, 5),  -- Order 4 tại Site 3
    (4, 5, 0, 4),  -- Order 4 tại Site 5
    (5, 1, 0, 2),  -- Order 5 tại Site 1
    (5, 3, 0, 5);  -- Order 5 tại Site 3

INSERT INTO SiteOrderDetails (siteOrderID, productID, quantity)
VALUES
    (1, 1, 2),   -- Order 1 tại Site 1, sản phẩm 1, số lượng 2
    (1, 2, 3),   -- Order 1 tại Site 1, sản phẩm 2, số lượng 3
    (1, 3, 1),   -- Order 1 tại Site 1, sản phẩm 3, số lượng 1
    (2, 4, 5),   -- Order 2 tại Site 2, sản phẩm 4, số lượng 5
    (2, 5, 2),   -- Order 2 tại Site 2, sản phẩm 5, số lượng 2
    (2, 6, 7),   -- Order 2 tại Site 2, sản phẩm 6, số lượng 7
    (3, 7, 4),   -- Order 3 tại Site 3, sản phẩm 7, số lượng 4
    (3, 8, 6),   -- Order 3 tại Site 3, sản phẩm 8, số lượng 6
    (4, 9, 8),   -- Order 4 tại Site 4, sản phẩm 9, số lượng 8
    (4, 10, 3),  -- Order 4 tại Site 4, sản phẩm 10, số lượng 3
    (5, 11, 5),  -- Order 5 tại Site 5, sản phẩm 11, số lượng 5
    (5, 12, 4);  -- Order 5 tại Site 5, sản phẩm 12, số lượng 4

-- 1: Chờ xác nhận
-- 2: Đang lấy hàng
-- 3: Đang giao hàng
-- 4: Đã nhận
-- 5: Đã hủy

INSERT 
UPDATE Orders
SET finalPrice = (
  SELECT SUM(Products.price * OrderDetails.quantity) 
  FROM OrderDetails
  JOIN Products ON OrderDetails.productID = Products.ID
  WHERE Orders.ID = OrderDetails.orderID
);

UPDATE SiteOrders
  SET finalPrice = (
  SELECT SUM(Products.price * SiteOrderDetails.quantity)
  FROM SiteOrderDetails
  JOIN Products ON SiteOrderDetails.productID = Products.ID
  WHERE SiteOrders.ID = SiteOrderDetails.siteOrderID
)