package builder;

public class Pizza {
    // size, cheese, sausage

    private final String size;
    private final boolean cheese;
    private final boolean sausage;

    public Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.sausage = builder.sausage;
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
