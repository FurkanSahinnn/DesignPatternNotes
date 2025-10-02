package factoryMethod.impls;

import factoryMethod.factory.IManagerFactory;
import factoryMethod.models.Manager;

public class ManagerFactoryImpl implements IManagerFactory {
    @Override
    public Manager create(String name, String department) {
        return new Manager(name, department);
    }
}
