package creational.abstractFactory.impls;

import creational.abstractFactory.commands.MySQLCommand;
import creational.abstractFactory.connections.MySQLConnection;
import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;
import creational.abstractFactory.factory.IDBFactory;

public class MySQLFactoryImpl implements IDBFactory {
    @Override
    public IDBConnection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public IDBCommand createCommand() {
        return new MySQLCommand();
    }
}
