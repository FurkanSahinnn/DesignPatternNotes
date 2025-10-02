package factoryMethod.factory;

import factoryMethod.models.Manager;

public interface IManagerFactory {
    Manager create(String name, String department);
}
