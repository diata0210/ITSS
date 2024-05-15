-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS sale_db;
USE sale_db;

-- Creating table for `Site`
CREATE TABLE IF NOT EXISTS Sites (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    sname VARCHAR(30),
    saddress VARCHAR(30)
);

-- Creating table for `Product`
CREATE TABLE IF NOT EXISTS Products (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    names VARCHAR(30),
    types VARCHAR(30)
);

-- Tạo bảng Đơn hàng
CREATE TABLE IF NOT EXISTS Orders (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  finalPrice INT,
  descriptions VARCHAR(1000),
  sendDate DATE,
  arriveDate DATE
);

CREATE TABLE IF NOT EXISTS OrderDetail (
  productID INT,
  orderID INT,
  quantity INT,
  FOREIGN KEY (orderID) REFERENCES Orders(ID),
  FOREIGN KEY (productID) REFERENCES Products(ID)
);

CREATE TABLE IF NOT EXISTS ProductSite (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  siteID INT,
  productID INT,
  quantity INT,
  FOREIGN KEY (siteID) REFERENCES Sites(ID),
  FOREIGN KEY (productID) REFERENCES Products(ID)
);

CREATE TABLE IF NOT EXISTS SiteOrder (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  orderID INT,
  siteID INT,
  FOREIGN KEY (siteID) REFERENCES Sites(ID),
  FOREIGN KEY (orderID) REFERENCES Orders(ID)
);

CREATE TABLE IF NOT EXISTS SiteOrderDetail (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  
  productID INT,
  quantity INT,
  FOREIGN KEY (productID) REFERENCES Products(ID)
);



CREATE TABLE IF NOT EXISTS Users (
  username VARCHAR(255),
  upassword VARCHAR(255),
  ID INT,
  roles INT
);

CREATE TABLE IF NOT EXISTS Vehicles(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  vname VARCHAR(30)
);
CREATE TABLE IF NOT EXISTS VehicleToSite (
  ID INT,
  vehicleID INT,
  siteID INT,
  FOREIGN KEY (vehicleID) REFERENCES Vehicles(ID),
  FOREIGN KEY (siteID) REFERENCES Sites(ID)
);
