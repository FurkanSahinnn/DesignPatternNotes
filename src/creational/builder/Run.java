package creational.builder;

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
