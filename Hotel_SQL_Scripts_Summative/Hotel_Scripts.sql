CREATE DATABASE Hotel;

USE Hotel;

CREATE TABLE Customer
(
	CustomerID INT NOT NULL auto_increment,
    FirstName VARCHAR(10) NOT NULL,
    LastName VARCHAR(10) NOT NULL,
    Phone VARCHAR(14) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    BirthDate DATE NOT NULL,
    PRIMARY KEY (CustomerID)
);

CREATE TABLE DiscountType
(
	DiscountTypeID INT NOT NULL auto_increment,
    DiscountName VARCHAR(30) NOT NULL,
    PRIMARY KEY(DiscountTypeID)
);

CREATE TABLE Promotion
(
	PromotionID INT NOT NULL auto_increment,
    Code VARCHAR(10) NOT NULL,
    DiscountValue DECIMAL(5,2) NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    DiscountTypeID INT NOT NULL,
    PRIMARY KEY(PromotionID),
    FOREIGN KEY(DiscountTypeID) REFERENCES DiscountType(DiscountTypeID)
);

CREATE TABLE Reservation
(
	ReservationID INT NOT NULL auto_increment,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    CustomerID INT NOT NULL,
    PromotionID INT NULL,
    PRIMARY KEY(ReservationID),
    FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY(PromotionID) REFERENCES Promotion(PromotionID)
);

CREATE TABLE Guest
(
	GuestID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    Age INT NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY(GuestID),
    FOREIGN KEY(ReservationID) REFERENCES Reservation(ReservationID)
);


CREATE TABLE AddOn
(
	AddOnID INT NOT NULL auto_increment,
    AddOnName VARCHAR(30) NOT NULL,
    PRIMARY KEY(AddOnID)
);

CREATE TABLE AddOnRate
(
	AddOnRateID INT NOT NULL auto_increment,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Price DECIMAL(5,2),
    AddOnID INT NOT NULL,
    PRIMARY KEY(AddOnRateID),
    FOREIGN KEY(AddOnID) REFERENCES AddOn(AddOnID)
);

CREATE TABLE ReservationAddOn
(
	ReservationID INT NOT NULL,
    AddOnID INT NOT NULL,
    PRIMARY KEY(ReservationID, AddOnID)
);

ALTER TABLE ReservationAddOn
ADD CONSTRAINT fk_ReservationAddOn_Reservation
FOREIGN KEY(ReservationID) REFERENCES Reservation(ReservationID);

ALTER TABLE ReservationAddOn
ADD CONSTRAINT fk_ReservationAddOn_AddOn
FOREIGN KEY(AddOnID) REFERENCES AddOn(AddOnID);

CREATE TABLE RoomType
(
	RoomTypeID INT NOT NULL auto_increment,
    TypeName VARCHAR(30) NOT NULL,
    PRIMARY KEY(RoomTypeID)
);

CREATE TABLE TypeRate
(
	TypeRateID INT NOT NULL auto_increment,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    Price DECIMAL(5,2) NOT NULL,
    RoomTypeID INT NOT NULL,
    PRIMARY KEY(TypeRateID),
    FOREIGN KEY(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE Room
(
	RoomID INT NOT NULL auto_increment,
    RoomNumber INT NOT NULL,
    OccupanceLimit INT NOT NULL,
    Floor INT NOT NULL,
    RoomTypeID INT NOT NULL,
    PRIMARY KEY(RoomID),
    FOREIGN KEY(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE RoomReservation
(
	RoomID INT NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY(RoomID, ReservationID)
);

ALTER TABLE RoomReservation
ADD CONSTRAINT fk_RoomReservation_Room
FOREIGN KEY(RoomID) REFERENCES Room(RoomID);


ALTER TABLE RoomReservation
ADD CONSTRAINT fk_RoomReservation_Reservation
FOREIGN KEY(ReservationID) REFERENCES Reservation(ReservationID);

CREATE TABLE Amenity
(
	AmenityID INT NOT NULL auto_increment,
    AmenityName VARCHAR(30) NOT NULL,
    PRIMARY KEY(AmenityID)
);

CREATE TABLE AmenityRoom
(
	AmenityID INT NOT NULL,
    RoomID INT NOT NULL,
    PRIMARY KEY(AmenityID, RoomID)
);

ALTER TABLE AmenityRoom
ADD CONSTRAINT fk_AmenityRoom_Amenity
FOREIGN KEY(AmenityID) REFERENCES Amenity(AmenityID);

ALTER TABLE AmenityRoom
ADD CONSTRAINT fk_AmenityRoom_Room
FOREIGN KEY(RoomID) REFERENCES Room(RoomID);

CREATE TABLE Bill
(
	BillID INT NOT NULL auto_increment,
    PromotionCode VARCHAR(10) NULL,
    Tax DECIMAL(2,2) NOT NULL,
    Total DECIMAL(15,2) NOT NULL,
    Date DATE NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY(BillID),
    FOREIGN KEY(ReservationID) REFERENCES Reservation(ReservationID)
);

CREATE TABLE BillingDetails
(
	DetailsID INT NOT NULL auto_increment,
    RoomCost DECIMAL(10,2) NOT NULL,
    AddOnCost DECIMAL(5,2) NULL,
    BillID INT NOT NULL,
    PRIMARY KEY(DetailsID),
    FOREIGN KEY(BillID) REFERENCES Bill(BillID)
);