package abstractFactory.impls;

import abstractFactory.commands.PostgreCommand;
import abstractFactory.connections.PostgreConnection;
import abstractFactory.factory.IDBFactory;
import abstractFactory.products.IDBCommand;
import abstractFactory.products.IDBConnection;

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
