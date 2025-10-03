package creational.factoryMethod.factory;

import creational.factoryMethod.models.Manager;

public interface IManagerFactory {
    Manager create(String name, String department);
}
