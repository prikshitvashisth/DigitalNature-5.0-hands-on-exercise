import java.util.Arrays;
import java.util.Comparator;

// ─────────────────────────────────────────────────────────────
// CLASS 1: Product
// A blueprint for creating product objects.
// Every product has an id, name, and category.
// ─────────────────────────────────────────────────────────────
class Product {

    int    productId;
    String productName;
    String category;

    // Constructor — runs when we write: new Product(...)
    public Product(int productId, String productName, String category) {
        this.productId   = productId;
        this.productName = productName;
        this.category    = category;
    }

    // This is called automatically when we print a Product object
    @Override
    public String toString() {
        return "Product{ id=" + productId
             + ", name='"     + productName + "'"
             + ", category='" + category    + "' }";
    }
}


// ─────────────────────────────────────────────────────────────
// CLASS 2: LinearSearch
//
// Goes through every product ONE BY ONE until it finds the target.
//
// Think of it like searching for a name in an UNSORTED pile of papers —
// you pick up each paper and check it one at a time.
//
// Time Complexity:
//   Best case    → O(1)  : found on the very first check
//   Average case → O(n)  : found somewhere in the middle
//   Worst case   → O(n)  : found at the end, or not there at all
//
// ✅ Advantage : works on UNSORTED data
// ❌ Drawback  : slow on large catalogs
// ─────────────────────────────────────────────────────────────
class LinearSearch {

    public static Product search(Product[] products, int targetId) {

        // Check every product from index 0 to the last one
        for (int i = 0; i < products.length; i++) {

            if (products[i].productId == targetId) {
                System.out.println("  [Linear] Found at index " + i
                                 + " after checking " + (i + 1) + " item(s).");
                return products[i];  // Return as soon as we find it
            }
        }

        System.out.println("  [Linear] Not found. Checked all "
                         + products.length + " items.");
        return null;  // Nothing found
    }
}


// ─────────────────────────────────────────────────────────────
// CLASS 3: BinarySearch
//
// Cuts the search area IN HALF each time.
//
// Think of it like finding a word in a SORTED dictionary —
// you open the middle, decide left or right, and repeat.
//
// IMPORTANT: The array MUST be sorted by productId first!
//
// Time Complexity:
//   Best case    → O(1)      : found exactly at the middle
//   Average case → O(log n)  : found after a few halvings
//   Worst case   → O(log n)  : not present, search exhausted
//
// Example: 1,000,000 products → only ~20 comparisons needed!
//
// ✅ Advantage : very fast, even on huge catalogs
// ❌ Drawback  : array must be sorted first
// ─────────────────────────────────────────────────────────────
class BinarySearch {

    public static Product search(Product[] sortedProducts, int targetId) {

        int low   = 0;                           // Left boundary
        int high  = sortedProducts.length - 1;   // Right boundary
        int steps = 0;

        while (low <= high) {
            steps++;

            int mid   = low + (high - low) / 2;  // Middle index
            int midId = sortedProducts[mid].productId;

            if (midId == targetId) {
                // ✅ Found it!
                System.out.println("  [Binary] Found at index " + mid
                                 + " after " + steps + " step(s).");
                return sortedProducts[mid];

            } else if (midId < targetId) {
                low = mid + 1;   // Target is in the RIGHT half

            } else {
                high = mid - 1;  // Target is in the LEFT half
            }
        }

        System.out.println("  [Binary] Not found after " + steps + " step(s).");
        return null;
    }
}


// ─────────────────────────────────────────────────────────────
// CLASS 4: Main
// This is where the program starts — Java always runs main() first.
// ─────────────────────────────────────────────────────────────
public class Ex2 {

    public static void main(String[] args) {

        Product[] catalog = {
            new Product(312, "Python Cookbook",     "Books"),
            new Product(101, "Wireless Headphones", "Electronics"),
            new Product(520, "Denim Jacket",        "Clothing"),
            new Product(205, "Running Shoes",       "Sports"),
            new Product(418, "Garden Hose",         "Home & Garden"),
            new Product(750, "Yoga Mat",            "Sports"),
            new Product(634, "Coffee Maker",        "Electronics"),
            new Product(889, "Mystery Novel",       "Books"),
        };

        System.out.println("\nCatalog (unsorted):");
        for (Product p : catalog) {
            System.out.println("  " + p);
        }

        System.out.println("\nSearching for productId = 418 ...");
        Product result = LinearSearch.search(catalog, 418);
        System.out.println("  Result: " + result);

        System.out.println("\nSearching for productId = 999 (not in catalog) ...");
        result = LinearSearch.search(catalog, 999);
        System.out.println("  Result: " + result);

        // ── Sort the catalog for Binary Search ───────────────
        Product[] sortedCatalog = catalog.clone();
        Arrays.sort(sortedCatalog, Comparator.comparingInt(p -> p.productId));

        System.out.println("\nCatalog (sorted by productId):");
        for (Product p : sortedCatalog) {
            System.out.println("  " + p);
        }

        System.out.println("\nSearching for productId = 418 ...");
        result = BinarySearch.search(sortedCatalog, 418);
        System.out.println("  Result: " + result);

        System.out.println("\nSearching for productId = 999 (not in catalog) ...");
        result = BinarySearch.search(sortedCatalog, 999);
        System.out.println("  Result: " + result);

        System.out.printf("%n%-15s %-22s %-20s%n",
                          "Catalog Size", "Linear O(n)", "Binary O(log n)");
        System.out.println("-".repeat(58));

        int[] sizes = {10, 100, 1_000, 10_000, 100_000, 1_000_000};

        for (int n : sizes) {
            int linearSteps = n;
            int binarySteps = (int) Math.ceil(Math.log(n) / Math.log(2));
            System.out.printf("%-15s %-22s %-20s%n",
                String.format("%,d", n),
                String.format("%,d steps", linearSteps),
                binarySteps + " steps");
        }
    }
}
