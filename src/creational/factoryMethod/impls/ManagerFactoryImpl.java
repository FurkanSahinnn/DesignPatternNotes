package creational.factoryMethod.impls;

import creational.factoryMethod.factory.IManagerFactory;
import creational.factoryMethod.models.Manager;

public class ManagerFactoryImpl implements IManagerFactory {
    @Override
    public Manager create(String name, String department) {
        return new Manager(name, department);
    }
}
