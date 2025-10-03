package creational.factoryMethod.factory;

import creational.factoryMethod.models.Employee;

public interface IEmployeeFactory {
    Employee create(String name);
}
