-- Forget Me Not
-- CMSC 495 6380
-- SQL Scripts
-- Group 8
-- Robert Hunter Robinson
-- Jered Russell
-- Keith Tulloch

-- Base Database Table to track dates

CREATE TABLE FORGETNOT (
	date_ID int NOT NULL AUTO_INCREMENT,
	name VARCHAR2(30) NOT NULL,
	occasion VARCHAR2(30) NOT NULL,
	event_date DATE NOT NULL,	
	reminder_date DATE NOT NULL,
    item1 VARCHAR2(20),
	item2 VARCHAR2(20),
	item3 VARCHAR2(20),
	item4 VARCHAR2(20),
	CONSTRAINT forgetnot_pk PRIMARY KEY (date_ID)
);

-- Query to insert new data in, single quoted items are to be replaced by variables from JAVA end

INSERT INTO FORGETNOT (name, occasion, event_date, reminder_date, item1, item2, item3, item4)
	VALUES (‘name’, ‘occasion’, 
TO_DATE(‘event_date’, ‘MM/DD/YYYY’), TO_DATE(‘reminder_date’,’MM/DD/YYYY’), 
‘item1’, ‘item2’, ‘item3’, ‘item4’);

-- Statement to search for occasions, occasion can be replaced with other fields to search 

SELECT reminder_date FROM FORGETNOT f WHERE f.occasion=x.occasion;

-- Statement to retrieve 

SELECT * FROM FORGETNOT WHERE DATEDIFF(day, reminder_date,
	GETDATE()) = 0;
