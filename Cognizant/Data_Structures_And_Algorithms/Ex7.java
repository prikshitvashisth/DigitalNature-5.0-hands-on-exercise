// ============================================================
// STEP 1: WHAT IS RECURSION?
//
// Recursion is when a method calls ITSELF to solve a problem.
// It simplifies problems that can be broken into smaller
// versions of the same problem.
//
// Every recursive method needs TWO things:
//   1. BASE CASE      -> the stopping condition
//   2. RECURSIVE CASE -> method calls itself with smaller input
//
// Example: futureValue(year 3) needs futureValue(year 2)
//          futureValue(year 2) needs futureValue(year 1)
//          futureValue(year 1) needs futureValue(year 0)
//          futureValue(year 0) = principal  <- stops here!
// ============================================================

public class Ex7 {

    // ============================================================
    // STEP 2: SETUP — recursive method to calculate future value
    //
    // Formula:
    //   futureValue(year 0) = principal          <- base case
    //   futureValue(year N) = futureValue(year N-1) x (1 + growthRate)
    // ============================================================
    public static double futureValue(double principal, double growthRate, int years) {

        // BASE CASE — no years left, return the original amount
        if (years == 0) {
            return principal;
        }

        // RECURSIVE CASE — find last year's value, then grow it by one year
        return futureValue(principal, growthRate, years - 1) * (1 + growthRate);
    }

    // ============================================================
    // STEP 3: IMPLEMENTATION — predict future values based on
    //         past growth rates
    // ============================================================
    public static void main(String[] args) {

        // Past growth rates data
        double[] pastGrowthRates = { 0.06, 0.08, 0.09, 0.07, 0.08 };

        // Calculate average growth rate from past data
        double total = 0;
        for (double rate : pastGrowthRates) {
            total += rate;
        }
        double avgGrowthRate = total / pastGrowthRates.length;

        double principal = 10000.00;
        int    years     = 10;

        double result = futureValue(principal, avgGrowthRate, years);

        System.out.printf("Principal        : Rs. %.2f%n", principal);
        System.out.printf("Avg Growth Rate  : %.2f%%%n", avgGrowthRate * 100);
        System.out.printf("Years            : %d%n", years);
        System.out.printf("Future Value     : Rs. %.2f%n", result);
    }

    // ============================================================
    // STEP 4: ANALYSIS
    //
    // Time Complexity: O(n)
    //   The method calls itself once per year, so for 10 years
    //   it makes exactly 10 recursive calls.
    //
    // Space Complexity: O(n)
    //   Each call waits on the call stack for the previous one.
    //   10 years = 10 stack frames open at the same time.
    //
    // OPTIMIZATION — Memoization:
    //   Store already calculated years in a HashMap.
    //   If the same year is asked again, return the saved value
    //   instead of recalculating from scratch.
    //   This reduces repeated calls from O(n) to O(1).
    //
    //   For very large years (e.g. 100,000), recursion will
    //   crash with StackOverflowError. In that case, replace
    //   recursion with a simple for loop (iterative approach).
    // ============================================================
}
