package behavioural.iterator;

import behavioural.iterator.utils.Aggregate;
import behavioural.iterator.utils.Iterator;

public class BookShelf implements Aggregate<Book> {
    private Book[] books;
    private int lastIndex = 0;

    public BookShelf(int maxSize) {
        books = new Book[maxSize];
    }

    public void addBook(Book book) {
        if (lastIndex < books.length) {
            books[lastIndex] = book;
            lastIndex++;
        }
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookShelfIterator();
    }

    private class BookShelfIterator implements Iterator<Book> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {
            return currentIndex < lastIndex;
        }

        @Override
        public Book next() {
            Book book = books[currentIndex];
            currentIndex++;
            return book;
        }
    }
}
