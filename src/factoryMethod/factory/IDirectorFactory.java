package factoryMethod.factory;

import factoryMethod.models.Director;

public interface IDirectorFactory {
    Director create(String name, String department, String plan);
}
