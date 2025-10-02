package factoryMethod.impls;

import factoryMethod.factory.IEmployeeFactory;
import factoryMethod.models.Employee;

public class EmployeeFactoryImpl implements IEmployeeFactory {
    @Override
    public Employee create(String name) {
        return new Employee(name);
    }
}
