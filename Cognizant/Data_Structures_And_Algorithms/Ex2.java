import java.util.Arrays;
import java.util.Comparator;

// ============================================================
// STEP 1: ASYMPTOTIC NOTATION — BIG O
//
// Big O notation describes how the number of steps grows
// as the input size (n) increases.
// It helps us compare algorithms without worrying about hardware.
//
// For search operations:
//   Best case    -> target is the first element checked
//   Average case -> target is somewhere in the middle
//   Worst case   -> target is last or not present at all
// ============================================================


// ============================================================
// STEP 2: SETUP — Product class with attributes for searching
// ============================================================
class Product {

    int    productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId   = productId;
        this.productName = productName;
        this.category    = category;
    }

    @Override
    public String toString() {
        return "Product{ id=" + productId
             + ", name='"     + productName + "'"
             + ", category='" + category    + "' }";
    }
}


// ============================================================
// STEP 3: IMPLEMENTATION
// ============================================================

// LINEAR SEARCH
// Checks every product one by one until target is found.
// Works on UNSORTED arrays.
//
// Best case    -> O(1)  : found at index 0
// Average case -> O(n)  : found in the middle
// Worst case   -> O(n)  : found at end or not present
class LinearSearch {

    public static Product search(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productId == targetId) {
                return products[i];
            }
        }
        return null;
    }
}


// BINARY SEARCH
// Cuts the search area in half each time.
// REQUIRES a SORTED array.
//
// Best case    -> O(1)      : found at the middle on first check
// Average case -> O(log n)  : found after a few halvings
// Worst case   -> O(log n)  : not present, search exhausted
class BinarySearch {

    public static Product search(Product[] sortedProducts, int targetId) {
        int low  = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid   = low + (high - low) / 2;
            int midId = sortedProducts[mid].productId;

            if (midId == targetId) {
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;   // target is in right half
            } else {
                high = mid - 1;  // target is in left half
            }
        }
        return null;
    }
}


public class Ex2 {

    public static void main(String[] args) {

        // Unsorted array for linear search
        Product[] products = {
            new Product(312, "Python Cookbook",     "Books"),
            new Product(101, "Wireless Headphones", "Electronics"),
            new Product(520, "Denim Jacket",        "Clothing"),
            new Product(205, "Running Shoes",       "Sports"),
            new Product(418, "Garden Hose",         "Home & Garden"),
        };

        // Sorted array for binary search (sorted by productId)
        Product[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts, Comparator.comparingInt(p -> p.productId));

        // Linear Search
        System.out.println("Linear Search:");
        System.out.println(LinearSearch.search(products, 418));
        System.out.println(LinearSearch.search(products, 999));

        // Binary Search
        System.out.println("\nBinary Search:");
        System.out.println(BinarySearch.search(sortedProducts, 418));
        System.out.println(BinarySearch.search(sortedProducts, 999));
    }

    // ============================================================
    // STEP 4: ANALYSIS
    //
    // Linear Search  : O(n) worst case
    //   Checks every element one by one.
    //   Simple but slow for large catalogs.
    //
    // Binary Search  : O(log n) worst case
    //   Halves the search space each step.
    //   At 1,000,000 products -> only ~20 comparisons needed.
    //
    // Which is more suitable?
    //   Binary Search is better for this e-commerce platform
    //   because product catalogs can be very large.
    //   O(log n) scales far better than O(n).
    //
    //   Linear Search is only preferred when:
    //   -> searching unsorted fields like productName
    //   -> the catalog is very small
    // ============================================================
}
