package creational.factoryMethod.factory;

import creational.factoryMethod.models.Director;

public interface IDirectorFactory {
    Director create(String name, String department, String plan);
}
