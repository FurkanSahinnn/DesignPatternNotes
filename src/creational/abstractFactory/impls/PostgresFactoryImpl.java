package creational.abstractFactory.impls;

import creational.abstractFactory.commands.PostgreCommand;
import creational.abstractFactory.connections.PostgreConnection;
import creational.abstractFactory.factory.IDBFactory;
import creational.abstractFactory.products.IDBCommand;
import creational.abstractFactory.products.IDBConnection;

public class PostgresFactoryImpl implements IDBFactory {
    @Override
    public IDBConnection createConnection() {
        return new PostgreConnection();
    }

    @Override
    public IDBCommand createCommand() {
        return new PostgreCommand();
    }
}
