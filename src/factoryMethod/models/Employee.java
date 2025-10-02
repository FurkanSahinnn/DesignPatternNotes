package factoryMethod.models;


public class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public void work(){
        System.out.println(name + ", Employee is working!");
    }
    // Getter, setter

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
