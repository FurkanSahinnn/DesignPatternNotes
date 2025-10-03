package creational.abstractFactory.factory;

import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;

public interface IDBFactory {
    IDBConnection createConnection();
    IDBCommand createCommand();
}
