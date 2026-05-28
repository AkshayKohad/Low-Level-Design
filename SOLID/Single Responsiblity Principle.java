This follows Single Responsibility Principle:

Book stores book data
Invoice calculates invoice total
InvoicePrinter handles printing
InvoiceStorage handles database saving

  
Below is code for above understanding
  

class Book {
    private String name;
    private String authorName;
    private int year;
    private double price;
    private String isbn;

    public Book(String name, String authorName, int year, double price, String isbn) {
        this.name = name;
        this.authorName = authorName;
        this.year = year;
        this.price = price;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }
}

class Invoice {
    private Book book;
    private int quantity;
    private double discount;
    private double tax;
    private double total;

    public Invoice(Book book, int quantity, double discount, double tax) {
        this.book = book;
        this.quantity = quantity;
        this.discount = discount;
        this.tax = tax;
        this.total = calculateTotal();
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public double calculateTotal() {
        double amount = book.getPrice() * quantity;
        amount = amount - discount;
        amount = amount + (amount * tax / 100);
        return amount;
    }
}

class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void print() {
        System.out.println("----- Invoice -----");
        System.out.println("Book Name: " + invoice.getBook().getName());
        System.out.println("Author: " + invoice.getBook().getAuthorName());
        System.out.println("Year: " + invoice.getBook().getYear());
        System.out.println("ISBN: " + invoice.getBook().getIsbn());
        System.out.println("Price: " + invoice.getBook().getPrice());
        System.out.println("Quantity: " + invoice.getQuantity());
        System.out.println("Discount: " + invoice.getDiscount());
        System.out.println("Tax: " + invoice.getTax());
        System.out.println("Total: " + invoice.getTotal());
    }
}

class InvoiceStorage {
    private Invoice invoice;

    public InvoiceStorage(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDatabase() {
        System.out.println("Saving invoice to database...");
        System.out.println("Invoice for book: " + invoice.getBook().getName() + " saved successfully.");
    }
}

public class Main {
    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", 2008, 500.0, "9780132350884");

        Invoice invoice = new Invoice(book, 2, 50.0, 18.0);

        InvoicePrinter printer = new InvoicePrinter(invoice);
        printer.print();

        InvoiceStorage storage = new InvoiceStorage(invoice);
        storage.saveToDatabase();
    }
}




