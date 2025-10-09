## Iterator Pattern:
Bir collection'ın iç yapısını açığa çıkartmadan sadece tek tip bir interface ile iterate etmeyi sağlayan pattern'dır.

Bildiğimiz for ve foreach'ten daha farklı bir şekilde collection üzerinde iterate etmeyi sağlar. 

Şu şekilde gözükmektedir:
```java
IntBag bag = new IntBag(10);
bag.add(3); bag.add(5); bag.add(7);

Iterator<Integer> it = bag.iterator();
while (it.hasNext()) {
    if (it.next() == 5) it.remove();
}
```
### Nasıl Oluşturulur?
2 tane interface oluşturulur:
1. **Iterator Interface:**
```java
interface Iterator<T> {
    boolean hasNext();
    T next();
}
```
Bunun görevi, hasNext() ve next() method'larının implement edilebilmesini sağlamaktır.

2. **Aggregate Interface:** 
```java
interface Aggregate<T> {
    Iterator<T> createIterator();
} 
```
Aggregate interface'in görevi Iterator üretmekdir. Bir nevi iterator oluşturmayı sağlayan factory method gibi düşünülebilir.

Bu aşamadan sonra iterator pattern'nın uygulanmak istendiği concrete class belirlenir. Genellikle data class'lar kullanılır.
```java
class Book {
    private String title;
    private String author;
    
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    @Override
    public String toString() {
        return "'" + title + "' - " + author;
    }
}
```
Sonraki aşamada Book class'ının instance'larını depolayacak bir BookShelf class'ını oluşturulmalı ve bu class'a
iterator özelliği verilmelidir. Bu özelliği verebilmek için ise Aggregate interface'i kullanılır. Bu interface'in 
asıl amacı kendisini implement edecek olan class'lara iterator nesnesini oluşturma özelliğini vermektir.

```java
class BookShelf implements Aggregate<Book> {
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
    
    public Book getBookAt(int index) {
        return books[index];
    }
    
    public int getLength() {
        return lastIndex;
    }
    
    // Kendi iterator'ımızı oluşturuyoruz
    @Override
    public Iterator<Book> createIterator() {
        return null;
    }
}
```

BookShelf'in kendi iterator'ünü oluşturmak için ise 2 farklı ama sonucu aynı olacak yöntem vardır:
1. Ayrı bir BookShelfIterator class'ı oluşturup Iterator interface'ini implement etmek.
2. BookShelf'in içerisinde BookShelfIterator isimli inner class oluşturup Iterator interface'ini implement etmek.

Her iki yöntem de aynı sonuca ulaşmayı sağlayacaktır fakat clean code açısından 2. yöntem daha clean'dir çünkü ayrı bir class 
açıldığı zaman, BookShelf class'ının method'larına erişebilmek için BookShelfIterator class'ına BookShelf'i field olarak
geçmek gerekir. Birbirine bu kadar depend olan iki class'ın coupling'ini daha sıkı yapmak varken bu bağı daha az tutmak 
çok mantıklı bir yöntem olmayabilir.

1. Separate Concrete BookShelfIterator Class Yöntemi:
```java
class BookShelf implements Aggregate<Book> {
    private Book[] books;
    private int lastIndex = 0;

    public BookShelf(int maxSize) {
        books = new Book[maxSize];
    }
    
    // Diğer methodlar
    // ...

    @Override
    public Iterator<Book> createIterator() {
        return new BookShelfIterator(this);
    }
}

class BookShelfIterator implements Iterator<Book> {
    private BookShelf bookShelf;
    private int currentIndex;
    
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.currentIndex = 0;
    }
    
    @Override
    public boolean hasNext() {
        return currentIndex < bookShelf.getLength();
    }
    
    @Override
    public Book next() {
        Book book = bookShelf.getBookAt(currentIndex);
        currentIndex++;
        return book;
    }
}
```

2. Inner BookShelfIterator Class Yöntemi:
```java
class BookShelf implements Aggregate<Book> {
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
```

BookShelfIterator, BookShelf'in önemli bir parçası olduğu ve amacının BookShelf üzerinde iterate edilebilmesini sağlamak
olduğu için BookShelfIterator'ın BookShelf'in inner class'ı olması daha anlaşılır bir yöntemdir. Inner class'lar outer class'ların
method'larına ve field'larına direkt erişim sağlayabilmesinden ötürü BookShelf'in BookShelfIterator içerisine field olarak geçilmesine
gerek yoktur.

Kullanım Örneği:
```java
BookShelf shelf = new BookShelf(5);

// Kitapları ekle
shelf.addBook(new Book("Suç ve Ceza", "Dostoyevski"));
shelf.addBook(new Book("1984", "George Orwell"));
shelf.addBook(new Book("Simyacı", "Paulo Coelho"));
shelf.addBook(new Book("Tutunamayanlar", "Oğuz Atay"));

// Iterator kullanarak kitapları gez
System.out.println("=== Kütüphanedeki Kitaplar ===");
Iterator<Book> iterator = shelf.createIterator();

while (iterator.hasNext()) {
    Book book = iterator.next();
    System.out.println(book);
}
```


