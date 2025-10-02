#### Static Method ve Field'lar:
Static method'lar ve field'lar class seviyesinde (class-level) çalışan yapılardır yani class'a aittirler ve tek kopyaları
vardır. Kısacası, bir static method veya field yazdığınız zaman JVM arkaplanda ondan ne kadar oluşturmaya çalışırsanız
çalışın aynı class'ta bulundukları için aynı referans'a point eder.

##### Örnek:
```java
class Counter {
    private static int count;
    
    public static int incrementCount() {
        return  ++count;
    }
}

public class Run {
    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        counter1.incrementCount();
        System.out.println(counter1.getCount()); // 1


        counter2.incrementCount();
        System.out.println(counter2.getCount()); // 2
    }
}
```
Buradaki örnekte birden fazla Counter object'imiz olsa da ikisinin referans'ı aynı yerdir. Static yapılar isminden de
anlaşılacağı üzere static'tir yani bir class'ın static field'ı birden fazla referans'a point edemez. Bu yüzden, counter1
ve counter2 object'lerinin static olan count field'ı aynıdır. Counter1, count'u 1 arttırırsa counter2'deki count da 1 artar
çünkü bunlar aynı referans'a point eden değişkenlerdir.

#### Static Inner Class nedir?
Java'da top-level class'lar static olarak oluşturulamazlar.
```java
public static class WrongClassDefinition { 
    //... 
}
```
bu şekilde bir class tanımı yapılamaz. Static class'lar sadece ve sadece inner class olarak yer alabilirler.
```java
class Outer { 
    static class Inner { 
        // … 
    }
}
```
Static inner class'lar yukarıdaki şekilde oluşturulurlar. Outer class'ın instance'ına bağlı değildirler.
Mesela,
```java
// Normal Inner class (non-static inner class)
class Outer {
    class Inner {
        void sayHello() {
            System.out.println("Hello from Inner!");
        }
    }
}

Outer outer = new Outer();
Outer.Inner inner = outer.new Inner(); // dış sınıf nesnesi üzerinden
inner.sayHello();
```
Non-static inner class'lara erişebilmek için outer class'ın instance'ını oluşturmak gerekmektedir.
Fakat, static inner class'lar outer class'ların instance'larına bağlı olmadığı için outer class'ın instance'ını oluşturmadan
inner class'lara erişilebilir.

```java
class Outer {
    static class Inner {
        void sayHello() {
            System.out.println("Hello from Static Inner!");
        }
    }
}

Outer.Inner inner = new Outer.Inner(); // Outer nesnesine gerek yok
inner.sayHello();
````
JVM, static inner class'ları herkesten bağımsız bir class'mış gibi load eder ama ismi Outer$Inner.class şeklinde olur.