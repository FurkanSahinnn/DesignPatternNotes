package creational.factoryMethod.impls;

import creational.factoryMethod.factory.IDirectorFactory;
import creational.factoryMethod.models.Director;

public class DirectorFactoryImpl implements IDirectorFactory {
    @Override
    public Director create(String name, String department, String plan) {
        return new Director(name, department, plan);
    }
}
