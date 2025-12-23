// Violating SRP: Shopping cart is handling multiple responsibilities
class ShoppingCart {
    public void addProduct() {
        System.out.println("Added to cart");
    }

    public void calculateTotal() {
        System.out.println("Calculating total...");
    }

    // 1. Printing invoice is a separate responsibility
    public void printInvoice() {
        System.out.println("Printing invoice...");
    }

    // 2. Saving to DB is a separate responsibility
    public void saveToDB() {
        System.out.println("Saving to DB...");
    }
}

// Fixed: Only responsible for shopping cart functionalities
class FixedShoppingCart {
    public void addProduct() {
        System.out.println("Added to cart");
    }

    public void calculateTotal() {
        System.out.println("Calculating total...");
    }
}

// Only responsible for invoice printing
class InvoicePrinter {
    public void printInvoice() {
        System.out.println("Printing invoice...");
    }
}

// Only responsible for saving to DB
class ShoppingCartStorage {
    public void saveToDB() {
        System.out.println("Saving to DB...");
    }
}

public class SRP {
    public static void main(String[] args) {
        // ShoppingCart cart = new ShoppingCart();
        // cart.addProduct();
        // cart.calculateTotal();
        // cart.printInvoice();
        // cart.saveToDB();

        FixedShoppingCart cart = new FixedShoppingCart();
        cart.addProduct();
        cart.calculateTotal();

        InvoicePrinter printer = new InvoicePrinter();
        printer.printInvoice();

        ShoppingCartStorage storage = new ShoppingCartStorage();
        storage.saveToDB();
    }
}