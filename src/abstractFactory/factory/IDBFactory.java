package abstractFactory.factory;

import abstractFactory.products.IDBCommand;
import abstractFactory.products.IDBConnection;

public interface IDBFactory {
    IDBConnection createConnection();
    IDBCommand createCommand();
}
