-- Write a query that returns all rooms for a customer
SELECT rr.RoomID
FROM Customer AS c
INNER JOIN Reservation AS r ON c.CustomerID = r.CustomerID
INNER JOIN RoomReservation AS rr ON r.ReservationID = rr.ReservationID
WHERE r.ReservationID = 2;

-- Write a query that returns room(s) that do not contain a specific amenity
SELECT DISTINCT ar1.RoomID
FROM AmenityRoom AS ar1
WHERE ar1.RoomID NOT IN (SELECT ar2.RoomID
						FROM Amenity AS a
						INNER JOIN AmenityRoom AS ar2 ON a.AmenityID = ar2.AmenityID
						WHERE ar2.AmenityID = 3);

-- Query to return all rooms available for a date range
SELECT rr1.RoomID
FROM RoomReservation AS rr1
WHERE rr1.RoomID NOT IN (SELECT rr2.RoomID
				FROM Reservation AS r
				INNER JOIN RoomReservation AS rr2 ON r.ReservationID = rr2.ReservationID
                WHERE r.StartDate BETWEEN '2017-01-01' AND '2017-01-31');
