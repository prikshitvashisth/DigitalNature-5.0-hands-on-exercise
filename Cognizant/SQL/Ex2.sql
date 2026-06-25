-- SCENARIO 1: ProcessMonthlyInterest
-- Applies 1% interest to all savings account balances
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    COMMIT;
END ProcessMonthlyInterest;
/


-- SCENARIO 2: UpdateEmployeeBonus
-- Updates salary of employees in a given department by adding a bonus percentage passed as a parameter
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_DepartmentID IN NUMBER,
    p_BonusPercent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_BonusPercent / 100)
    WHERE DepartmentID = p_DepartmentID;
    COMMIT;
END UpdateEmployeeBonus;
/

-- SCENARIO 3: TransferFunds
-- Transfers amount from one account to another
-- Checks source account has sufficient balance first
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_FromAccountID IN NUMBER,
    p_ToAccountID   IN NUMBER,
    p_Amount        IN NUMBER
) AS
    v_Balance NUMBER;
BEGIN
    SELECT Balance INTO v_Balance
    FROM Accounts
    WHERE AccountID = p_FromAccountID;

    IF v_Balance < p_Amount THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: Insufficient balance.');
    ELSE
        UPDATE Accounts
        SET Balance = Balance - p_Amount
        WHERE AccountID = p_FromAccountID;

        UPDATE Accounts
        SET Balance = Balance + p_Amount
        WHERE AccountID = p_ToAccountID;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer successful.');
    END IF;
END TransferFunds;
/
