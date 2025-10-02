package factoryMethod.factory;

import factoryMethod.models.Employee;

public interface IEmployeeFactory {
    Employee create(String name);
}
