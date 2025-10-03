package creational.factoryMethod.impls;

import creational.factoryMethod.factory.IEmployeeFactory;
import creational.factoryMethod.models.Employee;

public class EmployeeFactoryImpl implements IEmployeeFactory {
    @Override
    public Employee create(String name) {
        return new Employee(name);
    }
}
