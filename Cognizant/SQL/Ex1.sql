-- SCENARIO 1: Apply 1% discount to loan interest rates for customers above 60 years old
BEGIN
    FOR customer IN (SELECT CustomerID, Age FROM Customers) LOOP
        IF customer.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = customer.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/


-- SCENARIO 2: Set IsVIP to TRUE for customers with a balance over $10,000
BEGIN
    FOR customer IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF customer.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = TRUE
            WHERE CustomerID = customer.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/


-- SCENARIO 3: Print reminder messages for customers whose loans are due within the next 30 days
BEGIN
    FOR loan IN (
        SELECT l.LoanID, l.CustomerID, l.DueDate, c.CustomerName
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || loan.CustomerName
            || ', your loan (ID: ' || loan.LoanID
            || ') is due on ' || TO_CHAR(loan.DueDate, 'DD-MON-YYYY') || '.');
    END LOOP;
END;
/
