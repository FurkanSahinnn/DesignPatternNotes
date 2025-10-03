package creational.factoryMethod.models;

public class Manager extends Employee {
    protected  String managedDepartment;

    public Manager(String name, String managedDepartment) {
        super(name);
        this.managedDepartment = managedDepartment;
    }

    public void manage(){
        System.out.println(name + ", Manager is working at" + managedDepartment);
    }

    @Override
    public void work() {
        manage();
    }

    // Getter, setter


    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", managedDepartment='" + managedDepartment + '\'' +
                '}';
    }
}
