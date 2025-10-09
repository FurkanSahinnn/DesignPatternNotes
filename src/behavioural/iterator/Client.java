package behavioural.iterator;

import behavioural.iterator.utils.Iterator;

public class Client {
    public static void main(String[] args) {

        BookShelf shelf = new BookShelf(5);

        shelf.addBook(new Book("Suç ve Ceza", "Dostoyevski"));
        shelf.addBook(new Book("1984", "George Orwell"));
        shelf.addBook(new Book("Simyacı", "Paulo Coelho"));

        System.out.println("=== Kütüphanedeki Kitaplar ===");
        Iterator<Book> iterator = shelf.createIterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book);
        }
    }
}
