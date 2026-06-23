package Design_Principles_And_Patterns;

class Logger {

    private static Logger instance = null;

    private Logger() {
        System.out.println("Logger object created for the first time!");
        System.out.println("(This line must appear only ONCE)\n");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]   " + message);
    }

    public void info(String message) {
        System.out.println("[INFO]  " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN]  " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}

class Shop {
    public static Logger getLogger() {
        Logger logger = Logger.getInstance();
        logger.info("Shop is using the logger");
        return logger;
    }
}

class Cart {
    public static Logger getLogger() {
        Logger logger = Logger.getInstance();
        logger.info("Cart is using the logger");
        return logger;
    }
}

public class Ex1 {

    public static void main(String[] args) {


        test1_objectCreatedOnlyOnce();
        test2_sameObjectEveryTime();
        test3_logMessagesWork();
        test4_sharedAcrossClasses();

    }

    static void test1_objectCreatedOnlyOnce() {
        System.out.println("--- TEST 1: Object created only once ---\n");
        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();
        Logger c = Logger.getInstance();
        System.out.println("TEST 1 DONE: Creation message appeared only once.\n");
    }

    static void test2_sameObjectEveryTime() {
        System.out.println("--- TEST 2: Always the same object ---\n");
        Logger first  = Logger.getInstance();
        Logger second = Logger.getInstance();
        int idFirst  = System.identityHashCode(first);
        int idSecond = System.identityHashCode(second);
        System.out.println("Memory ID of first  = " + idFirst);
        System.out.println("Memory ID of second = " + idSecond);
        if (idFirst == idSecond) {
            System.out.println("PASS: Same ID! It is the same object.\n");
        } else {
            System.out.println("FAIL: Different IDs! Something is wrong.\n");
        }
    }

    static void test3_logMessagesWork() {
        System.out.println("--- TEST 3: Log messages ---\n");
        Logger logger = Logger.getInstance();
        logger.log("This is a general message");
        logger.info("App started successfully");
        logger.warn("Low battery warning");
        logger.error("File not found!");
        System.out.println("\nTEST 3 DONE: All 4 message types printed above.\n");
    }

    static void test4_sharedAcrossClasses() {
        System.out.println("--- TEST 4: Shared across Shop and Cart ---\n");
        Logger fromShop = Shop.getLogger();
        Logger fromCart = Cart.getLogger();
        int shopId = System.identityHashCode(fromShop);
        int cartId = System.identityHashCode(fromCart);
        System.out.println("\nLogger ID in Shop = " + shopId);
        System.out.println("Logger ID in Cart = " + cartId);
        if (shopId == cartId) {
            System.out.println("PASS: Shop and Cart share the same Logger!\n");
        } else {
            System.out.println("FAIL: They have different Loggers!\n");
        }
    }
}