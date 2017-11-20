USE Hotel;

-- Test Data for Customer table
-- insert into Customer (CustomerID, FirstName, LastName, Phone, Email, BirthDate) values (1, 'Preston', 'Lethbrig', '(571)350-0330', 'plethbrig0@washingtonpost.com', '1991-10-12');
-- insert into Customer (CustomerID, FirstName, LastName, Phone, Email, BirthDate) values (2, 'Patin', 'Muspratt', '(303)070-8215', 'pmuspratt1@vk.com', '1993-05-01');
-- insert into Customer (CustomerID, FirstName, LastName, Phone, Email, BirthDate) values (3, 'Reider', 'Pote', '(361)018-1833', 'rpote2@wsj.com', '1994-02-13');
-- insert into Customer (CustomerID, FirstName, LastName, Phone, Email, BirthDate) values (4, 'Yurik', 'Mephan', '(225)068-3934', 'ymephan3@photobucket.com', '1991-01-26');
-- insert into Customer (CustomerID, FirstName, LastName, Phone, Email, BirthDate) values (5, 'Waring', 'Franckton', '(673)364-2107', 'wfranckton4@nsw.gov.au', '1992-05-07');
-- 
-- insert into DiscountType (DiscountTypeID, DiscountName) values (1, 'discount_percent');
-- insert into DiscountType (DiscountTypeID, DiscountName) values (2, 'discount_flatAmount');
-- 
-- insert into Promotion (Code, DiscountValue, StartDate, EndDate, DiscountTypeID) values ('F0001', 0.25, '2017-01-01', '2017-01-31', 1);
-- insert into Promotion (Code, DiscountValue, StartDate, EndDate, DiscountTypeID) values ('F0002', 100.00, '2017-02-01', '2017-02-28', 2);
-- 
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-01-05', '2017-01-12', 5);
-- insert into Reservation (StartDate, EndDate, CustomerID, PromotionID) values ('2017-01-05', '2017-02-05', 2, 1);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-01-10', '2017-01-17', 3);
-- insert into Reservation (StartDate, EndDate, CustomerID, PromotionID) values ('2017-01-13', '2017-02-13', 1, 1);
-- insert into Reservation (StartDate, EndDate, CustomerID, PromotionID) values ('2017-01-14', '2017-02-08', 4, 1);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-01-17', '2017-01-24', 5);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-01-24', '2017-01-31', 3);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-01-25', '2017-02-12', 5);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-02-02', '2017-02-10', 3);
-- insert into Reservation (StartDate, EndDate, CustomerID) values ('2017-02-12', '2017-02-20', 2);
-- 
-- insert into Amenity (AmenityName) values ('Pool');
-- insert into Amenity (AmenityName) values ('Fitness Center');
-- insert into Amenity (AmenityName) values ('Spa');

-- insert into RoomType (TypeName) values ('Single');
-- insert into RoomType (TypeName) values ('Double');
-- insert into RoomType (TypeName) values ('Triple');
-- insert into RoomType (TypeName) values ('Quad');

-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (1, 1, 1, 1);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (2, 1, 1, 1);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (3, 2, 1, 2);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (4, 3, 1, 3);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (5, 4, 1, 4);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (6, 1, 2, 1);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (7, 1, 2, 1);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (8, 2, 2, 2);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (9, 3, 2, 3);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (10, 4, 2, 4);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (11, 1, 3, 1);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (12, 2, 3, 2);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (13, 3, 3, 3);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (14, 4, 3, 4);
-- insert into Room (RoomNumber, OccupanceLimit, Floor, RoomTypeID) values (15, 4, 3, 4);

-- insert into AmenityRoom (AmenityID, RoomID) values (1, 1);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 1);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 2);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 2);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 3);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 3);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 4);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 4);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 5);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 5);
-- insert into AmenityRoom (AmenityID, RoomID) values (3, 5);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 6);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 6);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 7);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 7);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 8);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 8);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 9);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 9);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 10);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 10);
-- insert into AmenityRoom (AmenityID, RoomID) values (3, 10);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 11);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 11);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 12);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 12);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 13);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 13);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 14);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 14);
-- insert into AmenityRoom (AmenityID, RoomID) values (3, 14);
-- insert into AmenityRoom (AmenityID, RoomID) values (1, 15);
-- insert into AmenityRoom (AmenityID, RoomID) values (2, 15);
-- insert into AmenityRoom (AmenityID, RoomID) values (3, 15);

insert into RoomReservation (RoomID, ReservationID) values (1, 1);
insert into RoomReservation (RoomID, ReservationID) values (6, 2);
insert into RoomReservation (RoomID, ReservationID) values (7, 2);
insert into RoomReservation (RoomID, ReservationID) values (2, 3);
insert into RoomReservation (RoomID, ReservationID) values (15, 4);
insert into RoomReservation (RoomID, ReservationID) values (14, 5);
insert into RoomReservation (RoomID, ReservationID) values (3, 6);
insert into RoomReservation (RoomID, ReservationID) values (5, 7);
insert into RoomReservation (RoomID, ReservationID) values (8, 8);
insert into RoomReservation (RoomID, ReservationID) values (12, 9);
insert into RoomReservation (RoomID, ReservationID) values (9, 10);
insert into RoomReservation (RoomID, ReservationID) values (10, 10);