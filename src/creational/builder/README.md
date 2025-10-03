## Builder Pattern:
Complex Object'lerin oluşturulma sürecinin constructor ile bir anda yapılması yerine bir process içerisinde adım adım 
object'i inşa ederek gerçekleştirilmesidir.

Object'in nasıl oluşturulacağı ile ilgilenir. Object'in oluşturulacağı yer ile ilgilenmez.

Object'in step step oluşturulmasını sağlar. Böylece Telescoping Constructor problemine çözüm sağlar. 
Ek olarak, setter method'larından ötürü object'in durumu sürekli değişiklik gösterir. Bu da tutarsız bir object oluşmasına
sebep olur yani oluşturulan object'in state'inde problemlere yol açabilir. Mesela, Car class'ının marka ve model 
parametrelerinin zorunlu olmasına rağmen setter'lar call edilmeden object'in kullanılmaya çalışılması gibi bir durum 
oluşabilir. Aynı zamanda multi-threading ortamlarda bu setter'lar sorun çıkartabilir.

### Amaç:
Complex object'leri adım adım oluştururken consistent state ve mutability özelliklerinin olmasına dikkat etmeliyiz.

### Nasıl Oluşturulur?
Yapıyı oluşturmadan önce static keyword'ünün Java'daki kullanımını bilmek gerekmektedir.
Static method'lar ile Static inner class'ların JVM tarafından nasıl load edildiğini öğrenmeden creational.builder pattern'ı anlamak 
pek mümkün değildir. StaticClassInfo.md dosyasının içerisinde static method, field ve class'lar hakkında bilgi yer almaktadır.

Konuya geri dönecek olursak, diğer bir bilmemiz gereken konu method chaining'tir. Method chaining, bir object üzerinde 
ardışık method calling'ler yaparak object'in state'ini tek seferde güncellemeyi sağlar. Method chaining'de her bir method,
üzerinde işlem yaptığı object'in referans'ını return etmek zorundadır. Java'da bu this keyword'ü ile yapılır.

Method Chaining entegre edilmemiş bir object üzerinde güncelleme işlemi:
```java
StringBuilder sb = new StringBuilder();
sb.append("test1");
sb.append(" ");
sb.append("test2");
System.out.println(sb.toString()); // test1 test2
```
Burada her bir append ayrı ayrı satırlarda call edildi. 

Method Chaining entegre edilmiş bir object üzerine güncelleme işlemi:
```java
String result = new StringBuilder()
                    .append("test1")
                    .append(" ")
                    .append("test2")
                    .toString();

System.out.println(result); // test1 test2
```
her bir append method'unun return'ünü this yaparsak yukarıdaki gibi bir yapı elde ederiz. Her bir method, üzerinde işlem
yaptığı object'i return eder. Böylece tek bir çizgi üzerinde state güncellemeleri yapılır.

Method chaining, her method sonunda this return eden bir tekniktir. Method'ları zincirleme olarak call etmeyi sağlar.
Bir object üzerinde birden fazla method'u tek seferde call etmeyi sağlar.

Builder pattern ise method chaining tekniğini static inner class'larda kullanan bir pattern'dır. kısacası, method chaining
tekniğini kullanan bir pattern'dır.

#### Builder Pattern Örneği:
```java
public class Pizza {
    // size, cheese, sausage

    private final String size;
    private final boolean cheese;
    private final boolean sausage;

    public Pizza(PizzaBuilder creational.builder) {
        this.size = creational.builder.size;
        this.cheese = creational.builder.cheese;
        this.sausage = creational.builder.sausage;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", cheese=" + cheese +
                ", sausage=" + sausage +
                '}';
    }

    public static class PizzaBuilder {
        // Immutable Fields
        private String size = "medium"; // mini, small, medium, large
        private boolean cheese = false;
        private boolean sausage = false;

        public PizzaBuilder size(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder cheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder sausage(boolean sausage) {
            this.sausage = sausage;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }

    }
}
```
StaticClassInfo.md dosyasının içerisinde de denildiği gibi static inner class'lar, outer class'lardan bağımsız oldukları için
Outer class'ın constructor'ına pass edilebilirler. Outer class'ın field'larının final olmasının sebebi, dışarıdan bir vatandaşın
setter method'larını bu class'ın object'i üzerinde uygulamasını önlemektir. Bu şekilde oluşturulan bir creational.builder class, 
Object'in state'inin sadece ve sadece object oluşturulurken değiştirilmesi gerektiğini savunur. 

Eğer ki projenin bazı yerlerinde bu field'ların güncellenmesi gerekiyorsa final keyword'ünün  kaldırılması gerekmektedir 
ama buradaki senaryoda Pizza'nın field'ları projenin farklı yerlerinde değişikliğe uğramayacağı için final olarak yazmak 
daha mantıklıdır. Olur da field'lar update edilmesi gerekir, ilgili field'ın final'ı kaldırılmalıdır.

Builder class da implement edildiğine göre geriye sadece object'i oluşturmak kaldı:
```java
public class Run {
    public static void main(String[] args) {
        Pizza defaultPizza1 = new Pizza.PizzaBuilder().build();
        Pizza defaultPizza2 = new Pizza.PizzaBuilder().build();

        Pizza pizzaWithCheese = new Pizza.PizzaBuilder()
                .cheese(true)
                .build();

        Pizza largePizzaWithCheeseAndSausage = new Pizza.PizzaBuilder() // 1️⃣ PizzaBuilder objesi oluştur
                .size("large") // 2️⃣ size'ı set et, PizzaBuilder return et
                .cheese(true) // 3️⃣ Önceki return'den gelen objeye cheese set et, yine return et
                .sausage(true) // 4️⃣ Önceki return'den gelen objeye sausage set et, yine return et
                .build(); // 5️⃣ Son objenin build metodunu çağır, Pizza'yı return et.
        /*
         * new PizzaBuilder() ──(size)──> PizzaBuilder ──(cheese)──> PizzaBuilder ──(sausage)──> PizzaBuilder ──(build)──> Pizza
         *         ↑                            ↑                         ↑                          ↑
         *       başla                      return this              return this               return this
         */

        System.out.println(defaultPizza1);
        System.out.println(defaultPizza2);
        System.out.println(pizzaWithCheese);
        System.out.println(largePizzaWithCheeseAndSausage);
    }
}
```